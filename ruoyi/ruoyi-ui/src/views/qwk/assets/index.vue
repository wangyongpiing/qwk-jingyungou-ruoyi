<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">

      <el-form-item label="用户手机号" prop="userPhone">
        <el-input
          v-model="queryParams.userPhone"
          placeholder="请输入用户手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前总额" prop="total">
        <el-input
          v-model="queryParams.total"
          placeholder="请输入当前总额"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['qwk:assets:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['qwk:assets:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['qwk:assets:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:assets:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assetsList" @selection-change="handleSelectionChange"
              :span-method="objectSpanMethod" border>
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="用户id" align="center" prop="userId" />-->
      <el-table-column label="用户手机号" align="center" prop="userPhone" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="资产类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_asset_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="当前总额" align="center" prop="total" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qwk:assets:edit']"
          >查看详情</el-button>
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

    <!-- 添加或修改用户资产 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form :model="assetDetailsParams" ref="assetDetaiForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="类型" prop="type">
          <el-select v-model="assetDetailsParams.addOrSubtract" placeholder="请选择类型" clearable>
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
        :page.sync="assetDetailsParams.pageNum"
        :limit.sync="assetDetailsParams.pageSize"
        @pagination="getList"
      />

<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
    </el-dialog>
  </div>
</template>

<script>
import { listAssets, getAssets, delAssets, addAssets, updateAssets } from "@/api/qwk/assets";
import { listAssetDetails } from "@/api/qwk/assetDetails";

export default {
  name: "Assets",
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
      total: 0,
      assetDetailsTotal:0,
      // 用户资产 表格数据
      assetsList: [],
      // 用户资产详细信息 表格数据
      assetDetailsList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 更新时间时间范围
      daterangeCreatedTime: [],
      // 更新时间时间范围
      daterangeUpdatedTime: [],
      assetDetailsDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userPhone: null,
        nickName: null,
        type: null,
        total: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
      },
      assetDetailsParams:{
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
      },
      mergeObj: {},
      mergeArr: ['userId', 'userPhone','nickName'],
      //消耗类型
      options : [{
        value: '1',
        label: '已获得'
      }, {
        value: '0',
        label: '已消耗'
      }],
      type : null,
      userId : null,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getSpanArr(data) {
      this.mergeArr.forEach((key, index1) => {
        let count = 0; // 用来记录需要合并行的起始位置
        this.mergeObj[key] = []; // 记录每一列的合并信息
        data.forEach((item, index) => {
          // index == 0表示数据为第一行，直接 push 一个 1
          if(index === 0) {
            this.mergeObj[key].push(1);
          } else {
            // 判断当前行是否与上一行其值相等 如果相等 在 count 记录的位置其值 +1 表示当前行需要合并 并push 一个 0 作为占位
            if(item[key] === data[index - 1][key]) {
              this.mergeObj[key][count] += 1;
              this.mergeObj[key].push(0);
            } else {
              // 如果当前行和上一行其值不相等
              count = index; // 记录当前位置
              this.mergeObj[key].push(1); // 重新push 一个 1
            }
          }
        })
        this.mergeObj['userPhone']=this.mergeObj['nickName']
      })
    },
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      // 判断列的属性
      if(this.mergeArr.indexOf(column.property) !== -1) {
        // 判断其值是不是为0
        if(this.mergeObj[column.property][rowIndex]) {
          return [this.mergeObj[column.property][rowIndex], 1]
        } else {
          // 如果为0则为需要合并的行
          return [0, 0];
        }
      }
    },
    /** 查询用户资产 列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreatedTime && '' != this.daterangeCreatedTime) {
        this.queryParams.params["beginCreatedTime"] = this.daterangeCreatedTime[0];
        this.queryParams.params["endCreatedTime"] = this.daterangeCreatedTime[1];
      }
      if (null != this.daterangeUpdatedTime && '' != this.daterangeUpdatedTime) {
        this.queryParams.params["beginUpdatedTime"] = this.daterangeUpdatedTime[0];
        this.queryParams.params["endUpdatedTime"] = this.daterangeUpdatedTime[1];
      }
      listAssets(this.queryParams).then(response => {
        this.assetsList = response.rows;
        this.total = response.total;
            this.mergeObj={};
            this.getSpanArr( response.rows);
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
        nickName: null,
        type: null,
        total: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    handleAssetDetailsQuery() {
      this.assetDetailsParams.pageNum = 1;
      this.getAssetDetailsList();
    },
    resetAssetDetailsQuery() {
      this.assetDetailsDate = [];
      this.resetForm("assetDetailsForm");
      this.handleAssetDetailsQuery();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreatedTime = [];
      this.daterangeUpdatedTime = [];
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
      this.title = "添加用户资产 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      let params = {}
      // getAssets(id).then(response => {
      //   this.form = response.data;
      //   params = response.data;
      //
      // });
      this.open = true;
      let str = this.typeFormat(row.type);
      // switch (row.type) {
      //   case 1: str = '积分'; break;
      //   case 2: str = '钻石'; break;
      //   case 3: str = '金币'; break;
      // }
      this.title = str+"详情";
      this.type = row.type;
      this.userId = row.userId;
      this.getAssetDetailsList();
    },
    typeFormat(type) {
      return this.selectDictLabel(this.dict.type.wtb_asset_type, type);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            // updateAssets(this.form).then(response => {
            //   this.$modal.msgSuccess("修改成功");
            //   this.open = false;
            //   this.getList();
            // });
          } else {
            // addAssets(this.form).then(response => {
            //   this.$modal.msgSuccess("新增成功");
            //   this.open = false;
            //   this.getList();
            // });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用户资产 编号为"' + ids + '"的数据项？').then(function() {
        return delAssets(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/assets/export', {
        ...this.queryParams
      }, `assets_${new Date().getTime()}.xlsx`)
    },
    /** 查询资产详情 列表 */
    getAssetDetailsList() {
      this.loading = true;
      this.assetDetailsParams.params = {};
      this.assetDetailsParams.type=this.type;
      this.assetDetailsParams.userId=this.userId;
      if (null != this.assetDetailsDate && '' != this.assetDetailsDate) {
        this.assetDetailsParams.params["beginCreatedTime"] = this.assetDetailsDate[0];
        this.assetDetailsParams.params["endCreatedTime"] = this.assetDetailsDate[1];
      }
      console.log(this.assetDetailsParams)
      listAssetDetails(this.assetDetailsParams).then(response => {
        this.assetDetailsList = response.rows;
        this.assetDetailsTotal = response.total;
        this.loading = false;
      });
    },
  }
};
</script>
