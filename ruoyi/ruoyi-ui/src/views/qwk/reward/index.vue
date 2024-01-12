<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="游戏id" prop="gameId">
        <el-select v-model="queryParams.gameId" placeholder="请选择游戏id" clearable>
          <el-option
            v-for="dict in dict.type.qwk_game"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="商品id" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品数量" prop="goodsNumber">
        <el-input
          v-model="queryParams.goodsNumber"
          placeholder="请输入商品数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品图片" prop="goodsImg">
        <el-input
          v-model="queryParams.goodsImg"
          placeholder="请输入商品图片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排名" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入排名"
          clearable
          @keyup.enter.native="handleQuery"

        />
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
          v-hasPermi="['qwk:reward:add']"
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
          v-hasPermi="['qwk:reward:edit']"
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
          v-hasPermi="['qwk:reward:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:reward:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rewardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="游戏id" align="center" prop="gameId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.qwk_game" :value="scope.row.gameId"/>
        </template>
      </el-table-column>
      <el-table-column label="商品id" align="center" prop="goodsId" />
      <el-table-column label="商品数量" align="center" prop="goodsNumber" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品图片" align="center" prop="goodsImg" >
        <template slot-scope="scope">
          <image-preview :src="scope.row.goodsImg" :width="150" :height="80"/>
        </template>
      </el-table-column>
      <el-table-column label="排名" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qwk:reward:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['qwk:reward:remove']"
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

    <!-- 添加或修改游戏奖励对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="游戏id" prop="gameId">
          <el-select v-model="form.gameId" placeholder="请选择游戏id">
            <el-option
              v-for="dict in dict.type.qwk_game"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品id" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品id" />
        </el-form-item>
        <el-form-item label="商品数量" prop="goodsNumber">
          <el-input v-model="form.goodsNumber" placeholder="请输入商品数量" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品图片" prop="goodsImg">
          <image-upload v-model="form.goodsImg"/>
        </el-form-item>
        <el-form-item label="排名" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排名" />
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
  import { listReward, getReward, delReward, addReward, updateReward } from "@/api/qwk/reward";

  export default {
    name: "Reward",
    dicts: ['qwk_game'],
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
        // 游戏奖励表格数据
        rewardList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 排名时间范围
        daterangeCreateTime: [],
        // 排名时间范围
        daterangeUpdateTime: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          gameId: null,
          goodsId: null,
          goodsNumber: null,
          goodsName: null,
          goodsImg: null,
          sort: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          gameId: [
            { required: true, message: "游戏id不能为空", trigger: "change" }
          ],
          goodsId: [
            { required: true, message: "商品id不能为空", trigger: "blur" }
          ],
          goodsNumber: [
            { required: true, message: "商品数量不能为空", trigger: "blur" }
          ],
          goodsName: [
            { required: true, message: "商品名称不能为空", trigger: "blur" }
          ],
          goodsImg: [
            { required: true, message: "商品图片不能为空", trigger: "blur" }
          ],
          sort: [
            { required: true, message: "排名不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询游戏奖励列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
          this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
          this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
        }
        if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
          this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
          this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
        }
        listReward(this.queryParams).then(response => {
          this.rewardList = response.rows;
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
          gameId: null,
          goodsId: null,
          goodsNumber: null,
          goodsName: null,
          goodsImg: null,
          sort: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null
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
        this.daterangeUpdateTime = [];
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
        this.title = "添加游戏奖励";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getReward(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改游戏奖励";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateReward(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addReward(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除游戏奖励编号为"' + ids + '"的数据项？').then(function() {
          return delReward(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('qwk/reward/export', {
          ...this.queryParams
        }, `reward_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
