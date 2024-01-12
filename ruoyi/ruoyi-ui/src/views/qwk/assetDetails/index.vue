<template>
  <div class="app-container">
    <el-form :model="assetDetaiParams" ref="assetDetailsForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="type">
        <el-select v-model="assetDetaiParams.addOrSubtract" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in options"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="日期">
        <el-date-picker
          v-model="assetDetailsDate"
          style="width: 280px"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetimerange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleAssetDetailsQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetAssetDetailsQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assetDetailsList" @selection-change="handleSelectionChange">
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_asset_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="消耗类型" align="center" prop="addOrSubtract" >
        <template slot-scope="scope">
          {{scope.row.addOrSubtract === '1' ? "已获得" : "已消耗"}}
        </template>
      </el-table-column>
      <el-table-column label="总额变化记录" align="center" prop="records" >
        <template slot-scope="scope">
          {{scope.row.addOrSubtract === '1' ? "+" : "-"}}
          {{scope.row.records}}
        </template>
      </el-table-column>
      <el-table-column label="余额" align="center" prop="balance" />
      <el-table-column label="日期" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="notes" />
    </el-table>

    <pagination
      v-show="assetDetailsTotal>0"
      :total="assetDetailsTotal"
      :page.sync="assetDetaiParams.pageNum"
      :limit.sync="assetDetaiParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改资产详情 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="用户手机号" prop="userPhone">
          <el-input v-model="form.userPhone" placeholder="请输入用户手机号" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option
              v-for="dict in dict.type.wtb_asset_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="总额变化记录" prop="records">
          <el-input v-model="form.records" placeholder="请输入总额变化记录" />
        </el-form-item>
        <el-form-item label="余额" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入余额" />
        </el-form-item>
        <el-form-item label="备注" prop="notes">
          <el-input v-model="form.notes" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新人" prop="updatedBy">
          <el-input v-model="form.updatedBy" placeholder="请输入更新人" />
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable
            v-model="form.updatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
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
import { listAssetDetails, getAssetDetails, delAssetDetails, addAssetDetails, updateAssetDetails } from "@/api/qwk/assetDetails";

export default {
  name: "AssetDetails",
  dicts: ['wtb_asset_type'],
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
      assetDetailsTotal: 0,
      // 资产详情 表格数据
      assetDetailsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 更新时间时间范围
      assetDetailsDate: [],
      // 更新时间时间范围
      daterangeUpdatedTime: [],
      //消耗类型
      options : [{
        value: '1',
        label: '已获得'
      }, {
        value: '0',
        label: '已消耗'
      }],
      // 查询参数
      assetDetaiParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userPhone: null,
        type: null,
        addOrSubtract: null,
        records: null,
        balance: null,
        notes: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        userPhone: [
          { required: true, message: "用户手机号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询资产详情 列表 */
    getList() {
      this.loading = true;
      this.assetDetaiParams.params = {};
      if (null != this.assetDetailsDate && '' != this.assetDetailsDate) {
        this.assetDetaiParams.params["beginCreatedTime"] = this.assetDetailsDate[0];
        this.assetDetaiParams.params["endCreatedTime"] = this.assetDetailsDate[1];
      }
      if (null != this.daterangeUpdatedTime && '' != this.daterangeUpdatedTime) {
        this.assetDetaiParams.params["beginUpdatedTime"] = this.daterangeUpdatedTime[0];
        this.assetDetaiParams.params["endUpdatedTime"] = this.daterangeUpdatedTime[1];
      }
      listAssetDetails(this.assetDetaiParams).then(response => {
        this.assetDetailsList = response.rows;
        this.assetDetailsTotal = response.total;
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
        userId: null,
        userPhone: null,
        type: null,
        records: null,
        balance: null,
        notes: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleAssetDetailsQuery() {
      this.assetDetailsParams.pageNum = 1;
      // this.getList();
    },
    /** 重置按钮操作 */
    resetAssetDetailsQuery() {
      this.assetDetailsDate = [];
      // this.daterangeUpdatedTime = [];
      this.resetForm("assetDetailsForm");
      this.handleAssetDetailsQuery();
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
      this.title = "添加资产详情 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAssetDetails(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资产详情 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAssetDetails(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAssetDetails(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除资产详情 编号为"' + ids + '"的数据项？').then(function() {
        return delAssetDetails(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/assetDetails/export', {
        ...this.assetDetaiParams
      }, `assetDetails_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
