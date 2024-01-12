<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="商品类型" prop="type">
        <el-radio-group v-model="formData.type" size="medium">
          <el-radio
            v-for="dict in dict.type.wtb_goods_type"
            :key="dict.value"
            :label="parseInt(dict.value)"
          >{{dict.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-row type="flex" justify="start" align="top" :gutter="10">
        <el-col :span="12">
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="formData.name" placeholder="请输入商品名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-form-item label="上传" prop="field108" required>
          <image-upload v-model="formData.image" :limit="5"/>
        </el-form-item>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="商品规格" prop="pSId">
            <el-input v-model="formData.pSId" placeholder="请输入商品规格" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="商品货号" prop="number">
            <el-input v-model="formData.number" placeholder="请输入商品货号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="供应商" prop="supplier">
            <el-input v-model="formData.supplier" placeholder="请输入供应商" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="成本价" prop="costPrice">
            <el-input v-model="formData.costPrice" placeholder="请输入成本价" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="平台价" prop="platformPrice">
            <el-input v-model="formData.platformPrice" placeholder="请输入平台价" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="现金价" prop="cashPrice">
            <el-input v-model="formData.cashPrice" placeholder="请输入现金价" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="库存" prop="inventory">
            <el-input v-model="formData.inventory" placeholder="请输入库存" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="邮费" prop="postage">
            <el-input v-model="formData.postage" placeholder="请输入邮费" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="图文描述" prop="describe">
<!--            <el-input v-model="formData.describe" type="textarea" placeholder="请输入多行文本"-->
<!--              :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>-->
            <!-- 富文本编辑器 -->
<!--            <my-quill-editor v-model="formData.describe" ref="quillEditor" />-->
            <editor v-model ="formData.describe" :minHeight="300"></editor>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { addGoods, updateGoods } from "@/api/qwk/goods";
// import myQuillEditor from "@/components/Editor";
// import ImgUploadCropper from "@/components/ImgUploadCropper";

export default {
  components: {
    // myQuillEditor,
    // ImgUploadCropper,
  },
  dicts: ['wtb_goods_state', 'wtb_goods_type'],
  props: [],
  data() {
    return {
      formData: {
        type: 1,
        name: undefined,
        image: null,
        pSId: undefined,
        number: undefined,
        supplier: undefined,
        costPrice: undefined,
        platformPrice: undefined,
        inventory: undefined,
        postage: undefined,
        describe: undefined,
        cashPrice:undefined,
      },
      rules: {
        type: [{
          required: true,
          message: '商品类型不能为空',
          trigger: 'change'
        }],
        name: [{
          required: true,
          message: '请输入商品名称',
          trigger: 'blur'
        }],
        pSId: [],
        number: [{
          required: true,
          message: '请输入商品货号',
          trigger: 'blur'
        }],
        supplier: [{
          required: true,
          message: '请输入供应商',
          trigger: 'blur'
        }],
        costPrice: [{
          required: true,
          message: '请输入成本价',
          trigger: 'blur'
        }],
        platformPrice: [{
          required: true,
          message: '请输入平台价',
          trigger: 'blur'
        }],
        cashPrice: [{
          required: true,
          message: '请输入现金价',
          trigger: 'blur'
        }],
        inventory: [{
          required: true,
          message: '请输入库存',
          trigger: 'blur'
        }],
        postage: [{
          required: true,
          message: '请输入邮费',
          trigger: 'blur'
        }],
        describe: [],
      },
      typeOptions: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],

    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
          if (this.formData.id != null) {
            updateGoods(this.formData).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.formData).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    // 失去焦点事件
    onEditorBlur() {
      this.$emit("on-blur", this.content);
    },
    onEditorFocus() {
      //获得焦点事件
    },
    // 内容改变事件
    onEditorChange() {
      this.$emit("on-blur", this.content);
    },


  }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>
