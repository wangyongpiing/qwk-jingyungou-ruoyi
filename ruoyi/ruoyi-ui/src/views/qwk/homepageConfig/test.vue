<!-- 添加或修改物流 对话框 -->
<el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
  <el-form ref="form" :model="form" :rules="rules" label-width="80px">

    <el-form-item label="用户手机号" prop="consigneePhone">
      <el-input v-model="form.consigneePhone" placeholder="请输入收货人手机号" />
    </el-form-item>
    <el-form-item label="商品名称" prop="consigneePhone">
      <el-input v-model="form.consigneePhone" placeholder="请输入收货人手机号" />
    </el-form-item>
    <el-form-item label="快递单号" prop="trackingNumber">
      <el-input v-model="form.trackingNumber" placeholder="请输入快递单号" />
    </el-form-item>
    <el-form-item label="快递公司" prop="expressCompany">
      <el-input v-model="form.expressCompany" placeholder="请输入快递公司" />
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button type="primary" @click="submitForm">确 定</el-button>
    <el-button @click="cancel">取 消</el-button>
  </div>
</el-dialog>
