<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="服务器掉落数量" prop="serverNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.serverNum"-->
<!--          placeholder="请输入服务器掉落数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="个人掉落数量" prop="personNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.personNum"-->
<!--          placeholder="请输入个人掉落数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="掉落概率" prop="dropProbability">
        <el-input
          v-model="queryParams.dropProbability"
          placeholder="请输入掉落概率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="掉落数量" prop="dropNum">
        <el-input
          v-model="queryParams.dropNum"
          placeholder="请输入掉落数量"
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
          style="width: 240px"
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
          v-hasPermi="['qwk:animalsDrop:add']"
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
          v-hasPermi="['qwk:animalsDrop:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['qwk:animalsDrop:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:animalsDrop:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="animalsDropList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="掉落编号" align="center" prop="id" />
      <el-table-column label="服务器掉落数量" align="center" prop="serverNum" />
      <el-table-column label="个人掉落数量" align="center" prop="personNum" />
      <el-table-column label="随机类型" align="center" prop="randomType" />
      <el-table-column label="掉落类型" align="center" prop="dropType" />
      <el-table-column label="掉落内容" align="center" prop="dropContent" />
      <el-table-column label="掉落概率" align="center" prop="dropProbability" />
      <el-table-column label="掉落数量" align="center" prop="dropNum" />
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
            v-hasPermi="['qwk:animalsDrop:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['qwk:animalsDrop:remove']"
          >删除</el-button>
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

    <!-- 添加或修改掉落配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="服务器掉落数量" prop="serverNum">
          <el-input v-model="form.serverNum" placeholder="请输入服务器掉落数量" />
        </el-form-item>
        <el-form-item label="个人掉落数量" prop="personNum">
          <el-input v-model="form.personNum" placeholder="请输入个人掉落数量" />
        </el-form-item>
        <el-form-item label="随机类型" prop="randomType">
          <el-input v-model="form.randomType" placeholder="请输入随机类型" />
        </el-form-item>
        <el-form-item label="掉落类型" prop="dropType">
          <el-input v-model="form.dropType" placeholder="请输入掉落类型" />
        </el-form-item>
        <el-form-item label="掉落内容" prop="dropContent">
          <el-input v-model="form.dropContent" placeholder="请输入掉落内容" />
        </el-form-item>
        <el-form-item label="掉落概率" prop="dropProbability">
          <el-input v-model="form.dropProbability" placeholder="请输入掉落概率" />
        </el-form-item>
        <el-form-item label="掉落数量" prop="dropNum">
          <el-input v-model="form.dropNum" placeholder="请输入掉落数量" />
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
import { listAnimalsDrop, getAnimalsDrop, delAnimalsDrop, addAnimalsDrop, updateAnimalsDrop } from "@/api/qwk/catchFish/animalsDrop";

export default {
  name: "AnimalsDrop",
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
      // 掉落配置表格数据
      animalsDropList: [],
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
        serverNum: null,
        personNum: null,
        randomType: null,
        dropType: null,
        dropContent: null,
        dropProbability: null,
        dropNum: null,
        createBy: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询掉落配置列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listAnimalsDrop(this.queryParams).then(response => {
        this.animalsDropList = response.rows;
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
        serverNum: null,
        personNum: null,
        randomType: null,
        dropType: null,
        dropContent: null,
        dropProbability: null,
        dropNum: null,
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
      this.title = "添加掉落配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAnimalsDrop(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改掉落配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAnimalsDrop(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnimalsDrop(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除掉落配置编号为"' + ids + '"的数据项？').then(function() {
        return delAnimalsDrop(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/animalsDrop/export', {
        ...this.queryParams
      }, `animalsDrop_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
