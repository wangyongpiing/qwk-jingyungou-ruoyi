<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
      <el-form-item label="订单编号" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户手机号" prop="userPhone">
        <el-input
          v-model="queryParams.userPhone"
          placeholder="请输入用户手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充值类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择充值类型" clearable>
          <el-option
            v-for="dict in dict.type.wtb_asset_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="充值账号" prop="account">
        <el-input
          v-model="queryParams.account"
          placeholder="请输入充值账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充值档位" prop="rechargeGrade">
        <el-input
          v-model="queryParams.rechargeGrade"
          placeholder="请输入充值档位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="金额" prop="numberRange">
        <input-number-range
          :disabled="false"
          :precision=0
          v-model="numberRange"
        ></input-number-range>
      </el-form-item>
      <el-form-item label="支付方式" prop="paymentMethod">
        <el-select v-model="queryParams.paymentMethod" placeholder="请选择">
          <el-option
            v-for="item in channels"
            :key="item.id"
            :label="item.channels"
            :value="item.channels">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付时间">
        <el-date-picker
          v-model="daterangePaymentTime"
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
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:bill:export']"
        >导出</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <span style="font-size: 18px;color: #606266;font-weight: bold;">当前充值用户总人数：{{sum.totalPeople}}</span>
      </el-col>
      <el-col :span="1.5">
        <span style="font-size: 18px;color: #606266;font-weight: bold;">累计充值总额：{{sum.totalRecharge}}</span>
      </el-col>
      <el-col :span="1.5">
        <span style="font-size: 18px;color: #606266;font-weight: bold;">今日充值总额：{{sum.todayTotalRecharge}}</span>
      </el-col>
      <el-col :span="1.5">
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['qwk:bill:export']"-->
<!--        >导出</el-button>-->
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="billList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="充值订单编号" align="center" prop="orderNumber" />
      <el-table-column label="支付订单编号" align="center" prop="paymentNumber" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="用户手机号" align="center" prop="userPhone" />
      <el-table-column label="充值类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_asset_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="充值账号" align="center" prop="account" />
      <el-table-column label="充值档位" align="center" prop="rechargeGrade" />
      <el-table-column label="支付方式" align="center" prop="paymentMethod" />
      <el-table-column label="支付金额" align="center" prop="paymentAmount" />
      <el-table-column label="支付时间" align="center" prop="paymentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paymentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.qwk_payment_state" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-refresh"
            v-if="scope.row.state == '0'"
          >刷新订单</el-button>
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['qwk:bill:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['qwk:bill:remove']"-->
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

    <!-- 添加或修改充值流水 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="用户手机号" prop="userPhone">
          <el-input v-model="form.userPhone" placeholder="请输入用户手机号" />
        </el-form-item>
        <el-form-item label="充值类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择充值类型">
            <el-option
              v-for="dict in dict.type.wtb_asset_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="充值账号" prop="account">
          <el-input v-model="form.account" placeholder="请输入充值账号" />
        </el-form-item>
        <el-form-item label="充值档位" prop="rechargeGrade">
          <el-input v-model="form.rechargeGrade" placeholder="请输入充值档位" />
        </el-form-item>
        <el-form-item label="支付方式" prop="paymentMethod">
          <el-input v-model="form.paymentMethod" placeholder="请输入支付方式" />
        </el-form-item>
        <el-form-item label="支付时间">
          <el-date-picker
            v-model="daterangePaymentTime"
            style="width: 240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
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
import { listBill, getBill, delBill, addBill, updateBill , getStatistics} from "@/api/qwk/bill";
import {getByStatusList} from "@/api/qwk/channels";


export default {
  name: "Bill",
  dicts: ['wtb_asset_type','sys_common_status','qwk_payment_state'],
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
      // 充值流水 表格数据
      billList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      daterangePaymentTime: [],
      // 更新时间时间范围
      daterangeCreatedTime: [],
      // 更新时间时间范围
      daterangeUpdatedTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNumber: null,
        userId: null,
        userPhone: null,
        type: null,
        account: null,
        rechargeGrade: null,
        paymentMethod: null,
        paymentTime: null,
        state: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderNumber: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
      },
      channels : [],//支付渠道
      numberRange : [],//金额区间
      sum : {},//金额统计
    };
  },
  created() {
    this.getChannels();
    this.getList();
    this.statistics();
  },
  methods: {
    /** 查询充值流水 列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePaymentTime && '' != this.daterangePaymentTime) {
        this.queryParams.params["beginPaymentTime"] = this.daterangePaymentTime[0];
        this.queryParams.params["endPaymentTime"] = this.daterangePaymentTime[1];
      }
      if (null != this.daterangeCreatedTime && '' != this.daterangeCreatedTime) {
        this.queryParams.params["beginCreatedTime"] = this.daterangeCreatedTime[0];
        this.queryParams.params["endCreatedTime"] = this.daterangeCreatedTime[1];
      }
      if (null != this.daterangeUpdatedTime && '' != this.daterangeUpdatedTime) {
        this.queryParams.params["beginUpdatedTime"] = this.daterangeUpdatedTime[0];
        this.queryParams.params["endUpdatedTime"] = this.daterangeUpdatedTime[1];
      }
      if (null != this.numberRange && '' != this.numberRange) {
        this.queryParams.params["beginPaymentAmount"] = this.numberRange[0];
        this.queryParams.params["endPaymentAmount"] = this.numberRange[1];
      }
      listBill(this.queryParams).then(response => {
        this.billList = response.rows;
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
        orderNumber: null,
        userId: null,
        userPhone: null,
        type: null,
        account: null,
        rechargeGrade: null,
        paymentMethod: null,
        paymentTime: null,
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
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreatedTime = [];
      this.daterangeUpdatedTime = [];
      this.numberRange = [];
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
      this.title = "添加充值流水 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBill(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改充值流水 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBill(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBill(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除充值流水 编号为"' + ids + '"的数据项？').then(function() {
        return delBill(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/bill/export', {
        ...this.queryParams
      }, `bill_${new Date().getTime()}.xlsx`)
    },
    //获取支付渠道
    getChannels(){
      getByStatusList().then(res=>{
        this.channels=res.data;
      });
    },
    statistics(){
      getStatistics().then(res=>{
        this.sum = res.data;
        console.log(res.data);
      })
    },
  }
};
</script>
