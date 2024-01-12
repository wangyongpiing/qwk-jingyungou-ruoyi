<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="盲盒编号" prop="fMId">
        <el-input
          v-model="queryParams.fMId"
          placeholder="请输入盲盒编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="场次编号" prop="fieldId">
        <el-input
          v-model="queryParams.fieldId"
          placeholder="请输入场次编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Fn次数" prop="fn">
        <el-input
          v-model="queryParams.fn"
          placeholder="请输入Fn次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Oi概率" prop="oi">
        <el-input
          v-model="queryParams.oi"
          placeholder="请输入Oi概率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Ui概率" prop="ui">
        <el-input
          v-model="queryParams.ui"
          placeholder="请输入Ui概率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 215px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['qwk:probabilityConfig:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['qwk:probabilityConfig:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['qwk:probabilityConfig:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:probabilityConfig:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="probabilityConfigList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="盲盒编号" align="center" prop="fMId" />
      <el-table-column label="场次编号" align="center" prop="fieldId" />
      <el-table-column label="Fn次数" align="center" prop="fn" />
      <el-table-column label="Oi概率" align="center" prop="oi" />
      <el-table-column label="Ui概率" align="center" prop="ui" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qwk:probabilityConfig:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['qwk:probabilityConfig:remove']"-->
<!--          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改每种物品的中奖概率配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="盲盒编号" prop="fMId">
          <el-input v-model="form.fMId" placeholder="请输入盲盒编号" />
        </el-form-item>
        <el-form-item label="场次编号" prop="fieldId">
          <el-input v-model="form.fieldId" placeholder="请输入场次编号" />
        </el-form-item>
        <el-form-item label="Fn次数" prop="fn">
          <el-input v-model="form.fn" placeholder="请输入Fn次数" />
        </el-form-item>
        <el-form-item label="Oi概率" prop="oi">
          <el-input v-model="form.oi" placeholder="请输入Oi概率" />
        </el-form-item>
        <el-form-item label="Ui概率" prop="ui">
          <el-input v-model="form.ui" placeholder="请输入Ui概率" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listProbabilityConfig, getProbabilityConfig, delProbabilityConfig, addProbabilityConfig, updateProbabilityConfig } from "@/api/qwk/catchFish/probabilityConfig";

  export default {
    name: "ProbabilityConfig",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 每种物品的中奖概率配置表格数据
        probabilityConfigList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 备注时间范围
        daterangeCreateTime: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          fMId: null,
          fieldId: null,
          fn: null,
          oi: null,
          ui: null,
          createBy: null,
          createTime: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          fMId: [
            { required: true, message: "盲盒编号不能为空", trigger: "blur" }
          ],
          fieldId: [
            { required: true, message: "场次编号不能为空", trigger: "blur" }
          ],
          fn: [
            { required: true, message: "Fn次数不能为空", trigger: "blur" }
          ],
          oi: [
            { required: true, message: "Oi概率不能为空", trigger: "blur" }
          ],
          ui: [
            { required: true, message: "Ui概率不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询每种物品的中奖概率配置列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
          this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
          this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
        }
        listProbabilityConfig(this.queryParams).then(response => {
          this.probabilityConfigList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          fMId: null,
          fieldId: null,
          fn: null,
          oi: null,
          ui: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeCreateTime = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加每种物品的中奖概率配置";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getProbabilityConfig(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改每种物品的中奖概率配置";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateProbabilityConfig(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addProbabilityConfig(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除每种物品的中奖概率配置编号为"' + ids + '"的数据项？').then(function() {
          return delProbabilityConfig(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('qwk/probabilityConfig/export', {
          ...this.queryParams
        }, `probabilityConfig_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
