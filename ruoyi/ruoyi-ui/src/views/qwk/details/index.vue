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
      <el-button type="warning">仓库物品</el-button>
      <el-button type="primary" @click="this.goFriends">好友列表</el-button>
      <el-button type="primary" @click="this.goTDAddress">用户地址</el-button>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" style="margin:10px 10px 0px 10px;"
             v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品来源" prop="source">
        <el-select v-model="queryParams.source" placeholder="请选择商品来源" clearable>
          <el-option
            v-for="dict in dict.type.wtb_source"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="获得时间">
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
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdateTime"
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
    </div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:details:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="detailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
<!--      <el-table-column label="仓库id" align="center" prop="wid" />-->
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品来源" align="center" prop="source">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_source" :value="scope.row.source"/>
        </template>
      </el-table-column>
      <el-table-column label="商品数量" align="center" prop="quantity" />
      <el-table-column label="商品价格" align="center" prop="price" />
      <el-table-column label="获得时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态 " align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_integral_status" :value="scope.row.state"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="更新人" align="center" prop="updatedBy" />-->
      <el-table-column label="操作时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            v-hasPermi="['qwk:details:edit']"-->
<!--            v-if="scope.row.state == 0"-->
<!--          >兑换积分</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            v-hasPermi="['qwk:details:edit']"-->
<!--            v-if="scope.row.state == 0"-->
<!--          >转赠好友</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            v-hasPermi="['qwk:details:remove']"-->
<!--            v-if="scope.row.state == 1"-->
<!--          >兑积分详情</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            v-hasPermi="['qwk:details:remove']"-->
<!--            v-if="scope.row.state == 2"-->
<!--          >转赠详情</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            v-hasPermi="['qwk:details:remove']"-->
<!--            v-if="scope.row.state == 3"-->
<!--          >物流详情</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改仓库详情 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="仓库id" prop="wid">
          <el-input v-model="form.wid" placeholder="请输入仓库id" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品来源" prop="source">
          <el-select v-model="form.source" placeholder="请选择商品来源">
            <el-option
              v-for="dict in dict.type.wtb_source"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入商品数量" />
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入商品价格" />
        </el-form-item>
        <el-form-item label="获得时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择获得时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态 " prop="state">
          <el-select v-model="form.state" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.wtb_integral_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listDetails, getDetails, delDetails, addDetails, updateDetails } from "@/api/qwk/details";
import {getUser}from "@/api/system/user";

export default {
  name: "Details",
  dicts: ['wtb_integral_status', 'wtb_source','sys_user_sex'],
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
      // 仓库详情 表格数据
      detailsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 更新时间时间范围
      daterangeCreateTime: [],
      // 更新时间时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wid: null,
        goodsName: null,
        source: null,
        quantity: null,
        price: null,
        createdTime: null,
        state: null,
        updatedBy: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wid: [
          { required: true, message: "仓库id不能为空", trigger: "blur" }
        ],
        goodsName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
      },
      userId : null,
      user : {},
      ipAddress : '',
    };
  },
  created() {
    this.userId = this.$route.query.id;
    this.getUser();
    this.getList();

  },
  methods: {
    /** 查询仓库详情 列表 */
    getList() {
      this.loading = true;
      // this.queryParams.userId = this.$route.query.id;
      this.queryParams.userId = this.userId;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listDetails(this.queryParams).then(response => {
        this.detailsList = response.rows;
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
        userId: null,
        wid: null,
        ordersId: null,
        goodsId: null,
        goodsImage: null,
        goodsName: null,
        source: null,
        quantity: null,
        price: null,
        state: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        createBy: null,
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
      this.title = "添加仓库详情 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDetails(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改仓库详情 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDetails(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDetails(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除仓库详情 编号为"' + ids + '"的数据项？').then(function() {
        return delDetails(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/details/export', {
        ...this.queryParams
      }, `details_${new Date().getTime()}.xlsx`)
    },
    /** 获取用户信息操作 */
    getUser(){
      getUser(this.userId).then(response => {
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
    /** 前往好友列表*/
    goFriends(){
      this.$router.push({
        path: '/gameUser/userFriends',
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
  },
  watch: {
    '$route.query.id'(newId, oldId) {
      if (newId !== oldId) {
        // 路由参数发生变化时，执行相应操作
        this.userId = this.$route.query.id;
        this.getUser();
        this.getList();
      }
    }
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
