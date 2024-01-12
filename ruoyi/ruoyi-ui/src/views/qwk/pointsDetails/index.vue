<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="85px">

      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户账号" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="兑换订单号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入兑换订单id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.qwk_payment_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="商品名称" prop="goodsName">-->
<!--        <el-input-->
<!--          v-model="queryParams.goodsName"-->
<!--          placeholder="请输入商品名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="兑换积分" prop="redeemPoints">
        <el-input
          v-model="queryParams.redeemPoints"
          placeholder="请输入兑换积分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="兑换时间">
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['qwk:pointsDetails:add']"-->
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
<!--          v-hasPermi="['qwk:pointsDetails:edit']"-->
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
<!--          v-hasPermi="['qwk:pointsDetails:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qwk:pointsDetails:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pointsDetailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="用户手机号" align="center" prop="userPhone" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="用户账号" align="center" prop="userName" />
<!--      <el-table-column label="仓库详情id" align="center" prop="wDId" />-->
      <el-table-column label="兑换订单号" align="center" prop="orderId" />
<!--      <el-table-column label="商品id" align="center" prop="goodsId" />-->
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品类型" align="center" prop="goodsType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wtb_goods_type" :value="scope.row.goodsType"/>
        </template>
      </el-table-column>
      <el-table-column label="商品价值" align="center" prop="commodityValue" />
      <el-table-column label="兑换积分" align="center" prop="redeemPoints" />
      <el-table-column label="兑换时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.qwk_payment_state" :value="scope.row.state"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="转赠账号" align="center" prop="transfer" />-->
<!--      <el-table-column label="物流详情" align="center" prop="logistics" />-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['qwk:pointsDetails:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['qwk:pointsDetails:remove']"-->
<!--          >删除</el-button>-->
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

    <!-- 添加或修改仓库商品积分详情 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户手机号" prop="userPhone">
          <el-input v-model="form.userPhone" placeholder="请输入用户手机号" />
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="用户账号" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户账号" />
        </el-form-item>
        <el-form-item label="仓库详情id" prop="wDId">
          <el-input v-model="form.wDId" placeholder="请输入仓库详情id" />
        </el-form-item>
        <el-form-item label="兑换订单id" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入兑换订单id" />
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.qwk_payment_state"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品id" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品id" />
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品类型" prop="goodsType">
          <el-select v-model="form.goodsType" placeholder="请选择商品类型">
            <el-option
              v-for="dict in dict.type.wtb_goods_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品价值" prop="commodityValue">
          <el-input v-model="form.commodityValue" placeholder="请输入商品价值" />
        </el-form-item>
        <el-form-item label="兑换积分" prop="redeemPoints">
          <el-input v-model="form.redeemPoints" placeholder="请输入兑换积分" />
        </el-form-item>
        <el-form-item label="转赠账号" prop="transfer">
          <el-input v-model="form.transfer" placeholder="请输入转赠账号" />
        </el-form-item>
        <el-form-item label="物流详情" prop="logistics">
          <el-input v-model="form.logistics" type="textarea" placeholder="请输入内容" />
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
import { listPointsDetails, getPointsDetails, delPointsDetails, addPointsDetails, updatePointsDetails } from "@/api/qwk/pointsDetails";

export default {
  name: "PointsDetails",
  dicts: ['qwk_payment_state', 'wtb_goods_type'],
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
      // 仓库商品积分详情 表格数据
      pointsDetailsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 物流详情时间范围
      daterangeCreateTime: [],
      // 物流详情时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userPhone: null,
        userId: null,
        userName: null,
        wDId: null,
        orderId: null,
        state: null,
        goodsId: null,
        goodsName: null,
        goodsType: null,
        commodityValue: null,
        redeemPoints: null,
        transfer: null,
        logistics: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        createBy: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "用户账号不能为空", trigger: "blur" }
        ],
        wDId: [
          { required: true, message: "仓库详情id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询仓库商品积分详情 列表 */
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
      listPointsDetails(this.queryParams).then(response => {
        this.pointsDetailsList = response.rows;
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
        userPhone: null,
        userId: null,
        userName: null,
        wDId: null,
        orderId: null,
        state: null,
        goodsId: null,
        goodsName: null,
        goodsType: null,
        commodityValue: null,
        redeemPoints: null,
        transfer: null,
        logistics: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        createBy: null
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
      this.title = "添加仓库商品积分详情 ";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPointsDetails(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改仓库商品积分详情 ";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePointsDetails(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPointsDetails(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除仓库商品积分详情 编号为"' + ids + '"的数据项？').then(function() {
        return delPointsDetails(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qwk/pointsDetails/export', {
        ...this.queryParams
      }, `pointsDetails_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
