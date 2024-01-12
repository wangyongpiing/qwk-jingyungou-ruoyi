<script src="../../../api/tool/common.js"></script>
<template>
  <div class="app-container">

    <div class="user-info-class">
      <el-row :gutter="0" style="display: flex">
        <el-col :span="3" style="align-items: stretch">
          <image-preview :src="user.avatar" :width="160" :height="160"/>
        </el-col>
        <el-col :span="18">
          <el-descriptions class="margin-top" title="基本信息" :column="4" size="medium" >
            <el-descriptions-item label="用户昵称">{{user.nickName}}</el-descriptions-item>
            <el-descriptions-item label="性别"><dict-tag :options="dict.type.sys_user_sex" :value="user.sex"/></el-descriptions-item>
            <el-descriptions-item label="用户id" >{{user.userId}}</el-descriptions-item>
            <el-descriptions-item label="用户活跃度" >30%</el-descriptions-item>
            <el-descriptions-item label="真实姓名" >{{user.isAuthentication == '1' ? '未实名':user.realName}}</el-descriptions-item>
            <el-descriptions-item label="手机号码" >{{user.phonenumber}}</el-descriptions-item>
            <el-descriptions-item label="积分余额" >30</el-descriptions-item>
            <el-descriptions-item label="钻石余额" >30</el-descriptions-item>
            <el-descriptions-item label="身份证号码" >{{user.isAuthentication == '1' ? '未实名':user.idCard}}</el-descriptions-item>
            <el-descriptions-item label="个性签名" >{{user.remark}}</el-descriptions-item>
            <el-descriptions-item label="注册时间" >{{user.createTime}}</el-descriptions-item>
            <el-descriptions-item label="邀请人数" >30%</el-descriptions-item>
            <el-descriptions-item label="登陆IP" >{{user.loginIp}}</el-descriptions-item>
            <el-descriptions-item label="IP归属地" >{{ipAddress}}</el-descriptions-item>
            <el-descriptions-item label="最后登陆时间" >{{user.loginDate}}</el-descriptions-item>
            <el-descriptions-item label="使用时长" >30%</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
    </div>

    <div class="user-info-class">
      <el-button type="primary" @click="this.goBill">充值记录</el-button>
      <el-button type="primary" @click="this.goExchange">钻石兑换记录</el-button>
      <el-button type="primary" @click="this.goPoints">积分兑换记录</el-button>
      <el-button type="primary" @click="this.goPointsOrders">积分订单记录</el-button>
      <el-button type="primary" @click="this.goWarehouse">仓库物品</el-button>
      <el-button type="warning">好友列表</el-button>
      <el-button type="primary" @click="this.goTDAddress">用户地址</el-button>
      <el-form :model="queryParams" ref="queryForm" size="small" style="margin:10px 10px 0px 10px;"
               :inline="true" v-show="showSearch" label-width="90px" label-position="left">
        <el-form-item label="邀请时间">
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
    </div>

    <el-row :gutter="10" class="mb8">
<!--   -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:bill:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="billList" @selection-change="handleSelectionChange">
      <el-table-column
        type="index"
        label="序号"
        width="50">
      </el-table-column>
      <el-table-column label="用户头像" align="center" key="avatar" prop="user.avatar" >
        <template slot-scope="scope">
          <image-preview :src="scope.row.user.avatar" :width="80" :height="80"/>
        </template>
      </el-table-column>
      <el-table-column label="用户昵称" align="center" key="user.nickName" prop="user.nickName"  :show-overflow-tooltip="true" >
      </el-table-column>
      <el-table-column label="姓名" align="center" key="realName" prop="user.realName"  :show-overflow-tooltip="true" />
      <el-table-column label="性别" align="center" key="sex" prop="user.sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type. sys_user_sex" :value="scope.row.user.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" align="center" key="phonenumber" prop="user.phonenumber" width="120" />
      <el-table-column label="邀请时间" align="center" prop="createTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
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
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="订单编号" prop="orderNumber">-->
<!--          <el-input v-model="form.orderNumber" placeholder="请输入订单编号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="用户id" prop="userId">-->
<!--          <el-input v-model="form.userId" placeholder="请输入用户id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="用户手机号" prop="userPhone">-->
<!--          <el-input v-model="form.userPhone" placeholder="请输入用户手机号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="充值类型" prop="type">-->
<!--          <el-select v-model="form.type" placeholder="请选择充值类型">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.wtb_asset_type"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--              :value="parseInt(dict.value)"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="充值账号" prop="account">-->
<!--          <el-input v-model="form.account" placeholder="请输入充值账号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="充值档位" prop="rechargeGrade">-->
<!--          <el-input v-model="form.rechargeGrade" placeholder="请输入充值档位" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="支付方式" prop="paymentMethod">-->
<!--          <el-input v-model="form.paymentMethod" placeholder="请输入支付方式" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="支付时间">-->
<!--          <el-date-picker-->
<!--            v-model="daterangePaymentTime"-->
<!--            style="width: 240px"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            type="daterange"-->
<!--            range-separator="-"-->
<!--            start-placeholder="开始日期"-->
<!--            end-placeholder="结束日期"-->
<!--          ></el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建人" prop="createdBy">-->
<!--          <el-input v-model="form.createdBy" placeholder="请输入创建人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建时间" prop="createdTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.createdTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="更新人" prop="updatedBy">-->
<!--          <el-input v-model="form.updatedBy" placeholder="请输入更新人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="更新时间" prop="updatedTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.updatedTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择更新时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import {getUser,listFriends}from "@/api/system/user";

export default {
  name: "Bill",
  dicts: ['wtb_asset_type','sys_common_status','sys_user_sex'],
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
      // 是否删除时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: undefined,
        friendsId: undefined,
        createTime: undefined,
        deleted: undefined,
      },
      userId : null,
      user : {},
      ipAddress : '',
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.userId = this.$route.query.id;
    this.getUser();
  },
  methods: {
    /** 查询充值流水 列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      this.queryParams.userId = this.$route.query.id;
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.friendsParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.friendsParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listFriends(this.queryParams).then(response => {
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
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {

          } else {

          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {

    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/bill/export', {
        ...this.queryParams
      }, `bill_${new Date().getTime()}.xlsx`)
    },
    /** 获取用户信息操作 */
    getUser(){
      getUser(this.$route.query.id).then(response => {
        this.user = response.data;
        this.getIP(response.data.loginIp);
        console.log(response.data)
      });
    },
    getIP(ip) {
      if (ip == '127.0.0.1'){
        this.ipAddress='本机地址'
      }else {
        var data = {
          ip : ip,
          key: 'KSPBZ-V5L33-5EM3D-35N5X-DIY66-CRF7J', //密钥
        }
        var url = 'https://apis.map.qq.com/ws/location/v1/ip' //腾讯地理位置信息接口
        data.output = 'jsonp' // 解决跨域问题
        this.$jsonp(url, data).then((res) => {
          this.ipAddress=res.result.ad_info.nation+' '+res.result.ad_info.city+' '+res.result.ad_info.district
          console.log('IP所在市',res.result.ad_info.city);
          console.log('ip所属区',res.result.ad_info.district);
          return res;
        }).catch((error) => {});
      }
    },
    /** 前往用户地址*/
    goTDAddress(){
      this.$router.push({
        path: '/gameUser/TakeDeliveryAddress',
        query: {
          id: this.userId,
        }
      })
    },
    /** 充值记录*/
    goBill(){
      this.$router.push({
        path: '/gameUser/userBill',
        query: {
          id: this.userId,
        }
      })
    },
    /** 前往仓库列表*/
    goWarehouse(){
      this.$router.push({
        path: '/gameUser/details',
        query: {
          id: this.userId,
        }
      })
    },

    /** 前往钻石兑换列表*/
    goExchange(){
      this.$router.push({
        path: '/gameUser/diamondExchange',
        query: {
          id: this.userId,
        }
      })
    },
    /** 前往积分兑换列表*/
    goPoints(){
      this.$router.push({
        path: '/gameUser/redemptionPoints',
        query: {
          id: this.userId,
        }
      })
    },
    /** 前往积分订单列表*/
    goPointsOrders(){
      this.$router.push({
        path: '/gameUser/pointOrders',
        query: {
          id: this.userId,
        }
      })
    },
  }
};

</script>

<style>
  .user-info-class {
    /*color: red*/
    padding:10px;
    border: 1px solid #b8b7b7;
    border-radius: 10px;
    margin-bottom:10px;
  }
</style>
