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
      <el-form-item label="用户昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户昵称"
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
      <el-form-item label="商品类型" prop="goodsType">
        <el-select v-model="queryParams.goodsType" placeholder="请选择商品类型" clearable>
          <el-option
            v-for="dict in dict.type.wtb_goods_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
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

      <el-form-item label="订单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable>
          <el-option
            v-for="dict in dict.type.wtb_order_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:orders:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="orderNumber" />
      <el-table-column label="用户手机号" align="center" prop="userPhone" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品图片" align="center" prop="goodsImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.goodsImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品类型" align="center" prop="goodsType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_goods_type" :value="scope.row.goodsType"/>
        </template>
      </el-table-column>
      <el-table-column label="单价" align="center" prop="price" />
      <el-table-column label="商品数量" align="center" prop="quantity" />
      <el-table-column label="运费" align="center" prop="costs" />
      <el-table-column label="消耗积分" align="center" prop="integral" />
      <el-table-column label="支付金额" align="center" prop="paymentAmount" />
      <el-table-column label="支付时间" align="center" prop="paymentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paymentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付方式" align="center" prop="paymentMethod">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_payment_method" :value="scope.row.paymentMethod"/>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_order_state" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-hasPermi="['qwk:orders:edit']"
            v-if="scope.row.status == 1"
            @click="handleSend(scope.row)"
          >发货</el-button>
          <el-button
            size="mini"
            type="text"
            v-else
            @click="logisticsDetails(scope.row)"
          >物流详情</el-button>
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

    <!--  发货对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" v-if="isShow" label-width="90px">
        <el-form-item label="用户手机号" prop="consigneePhone">
          <el-input v-model="form.userPhone" disabled placeholder="请输入收货人手机号" />
        </el-form-item>
        <el-form-item label="商品名称" prop="consigneePhone">
          <el-input v-model="form.goodsName" disabled placeholder="请输入收货人手机号" />
        </el-form-item>
        <el-form-item label="快递单号" prop="trackingNumber">
          <el-input v-model="form.trackingNumber" placeholder="请输入快递单号" />
        </el-form-item>
        <el-form-item label="快递公司" prop="expressCode">
          <el-select v-model="form.expressCode" placeholder="请选择快递公司(支持模糊查)"
                     clearable filterable style="width: 100%;">
            <el-option
              v-for="dict in dict.type.qwk_kd"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
      </el-form>

      <el-timeline :reverse="reverse" v-if="!isShow">
        <p>快递单号:<span style="font-weight: bold;">{{activities.nu}}</span></p>
        <p>
          快递公司:<span  style="font-weight: bold;">{{selectDictLabel(this.dict.type.qwk_kd, activities.com)}}</span>
        </p>
        <el-timeline-item
          v-for="(data, index) in activities.data"
          :key="index"
          :timestamp="data.time">
          {{data.context}}
        </el-timeline-item>
      </el-timeline>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-if="isShow">确 定</el-button>
        <el-button @click="cancel" v-if="isShow">取 消</el-button>
        <el-button type="primary" @click="cancel" v-if="!isShow">关 闭</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listOrders, getOrders, delOrders, addOrders, updateOrders } from "@/api/qwk/orders";
import { sendOutGoods,expressLogistics } from "@/api/qwk/logistics";

export default {
  name: "Orders",
  dicts: ['wtb_payment_method', 'wtb_order_state', 'wtb_goods_type','qwk_kd'],
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
      // 商品订单 表格数据
      ordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 更新时间时间范围
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
        nickName: null,
        goodsName: null,
        goodsImage: null,
        goodsType: null,
        price: null,
        quantity: null,
        costs: null,
        integral: null,
        paymentAmount: null,
        paymentTime: null,
        paymentMethod: null,
        status: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
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
        expressCode: [
          { required: true, message: "快递公司不能为空", trigger: "blur" }
        ],
        trackingNumber: [
          { required: true, message: "快递单号不能为空", trigger: "blur" }
        ],
      },
      reverse: false,
      activities: {},
      isShow : true,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品订单 列表 */
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
      listOrders(this.queryParams).then(response => {
        this.ordersList = response.rows;
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
        nickName: null,
        goodsName: null,
        goodsImage: null,
        goodsType: null,
        price: null,
        quantity: null,
        costs: null,
        integral: null,
        paymentAmount: null,
        paymentTime: null,
        paymentMethod: null,
        status: null,
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
      this.daterangePaymentTime = [];
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
      this.title = "添加商品订单 ";
    },
    /** 发货按钮操作 */
    handleSend(row) {
      this.reset();
      const id = row.id || this.ids
      getOrders(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.isShow = true;
        this.title = "发货 ";
      });
    },
    /** 物流详情按钮操作 */
    logisticsDetails(row) {
      this.reset();
      const id = row.id || this.ids
      console.log(row)
      let data = {
        com : row.logistics.expressCode,
        num : row.logistics.trackingNumber,
        phone : row.logistics.consigneePhone
      }
      expressLogistics(data).then(res=>{
        this.open = true;
        this.isShow = false;
        this.title = "物流详情";
        this.activities = res.data;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
          let logistics = {
            orderNumber: null,
            goodsName: null,
            goodsImage: null,
            consigneeName: null,
            consigneePhone: null,
            trackingNumber: null,
            expressCompany: null,
            consigneeAddress: null,
            createdBy: null,
            createdTime: null,
          };
          logistics.orderNumber=this.form.orderNumber;
          logistics.goodsImage=this.form.goodsImage;
          logistics.goodsName=this.form.goodsName;
          logistics.consigneeName=this.form.takeDeliveryAddress.consignee;
          logistics.consigneePhone=this.form.takeDeliveryAddress.phone;
          logistics.consigneeAddress=this.form.takeDeliveryAddress.address;
          logistics.expressCompany=this.kdFormat(this.form.expressCode);
          logistics.expressCode=this.form.expressCode;
          logistics.trackingNumber=this.form.trackingNumber;
          logistics.createdBy=this.$store.state.user.name;
          logistics.createdBy=new Date();
          logistics.logo="https://hbqwk-1318352516.cos.ap-shanghai.myqcloud.com/kdlogo/"+this.form.expressCode+".jpg";
            let data = {
              com : logistics.expressCode,
              num : logistics.trackingNumber,
              phone : logistics.consigneePhone
            }
          //校验物流单号
            expressLogistics(data).then(res=>{
              //发货
              sendOutGoods(logistics).then(response => {
                this.$modal.msgSuccess("发货成功");
                this.open = false;
                this.getList();
              });
            });
          //发货
          // sendOutGoods(logistics).then(response => {
          //   this.$modal.msgSuccess("发货成功");
          //   this.open = false;
          //   this.getList();
          // });

          } else {
            // addOrders(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除商品订单 编号为"' + ids + '"的数据项？').then(function() {
        return delOrders(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/orders/export', {
        ...this.queryParams
      }, `orders_${new Date().getTime()}.xlsx`)
    },
    kdFormat(name) {
      return this.selectDictLabel(this.dict.type.qwk_kd, name);
    },
  }
};
</script>
