<template>
  <div class="app-container">

    <el-row :gutter="20">
      <el-col :span="2.5">
        <div>
          <el-button type="primary" @click="this.goRMBExchange">人民币换积分</el-button>
        </div>
      </el-col>
      <el-col :span="2.5">
        <div>
          <el-button type="primary" @click="this.goIntegral">积分换钻石</el-button>
        </div>
      </el-col>
      <el-col :span="2.5">
        <div>
          <el-button type="warning">商品换积分</el-button>
        </div>
      </el-col>
    </el-row>
    <el-form ref="form" :model="form" :rules="rules"  style="padding-top: 20px">

      <el-form-item label="积分兑换钻石比例" prop="integralProportion">
        <el-row>
          <el-col :span="3">
            <el-input v-model="form.goodsProportion" :disabled="true" placeholder="请输入商品占比"/>
          </el-col>
          <el-col :span="1" style="text-align:center;">
            <span >:</span>
          </el-col>
          <el-col :span="3">
            <el-input v-model="form.integralProportion" placeholder="请输入积分占比"/>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <el-button type="primary" @click="submitForm">保 存</el-button>
    <el-button @click="cancel">取 消</el-button>

  </div>
</template>

<script>
  import {  addRatio,  } from "@/api/qwk/ratio";

  export default {
    name: "MoneyRatio",
    // dicts: [''],
    data() {
      return {
        // 表单参数
        form: {
          goodsProportion: 1,
        },
        // 表单校验
        rules: {
          integralProportion: [
            { required: true, message: "积分占比不能为空", trigger: "blur" }
          ],
        },

      };
    },
    created() {
    },
    methods: {
      // 取消按钮
      cancel() {
        // this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          goodsProportion: 1,
          integralProportion: null,
          applicable: null,
          enableOrNot: null,
          createdBy: null,
          createdTime: null,
          updatedBy: null,
          updatedTime: null
        };
        this.resetForm("form");
      },
      /** 提交按钮 */
      submitForm() {
        this.form.applicable = 0;
        this.form.enableOrNot = 0;
        this.$refs["form"].validate(valid => {
          if (valid) {
            addRatio(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.reset();
            });
          }
        });
      },
      //前往人民币兑换积分
      goRMBExchange(){
        this.$router.push({
          path: '/financial/exchange/RMBExchange',
        })
      },
      //前往积分兑换钻石
      goIntegral(){
        this.$router.push({
          path: '/financial/exchange/integral',
        })
      },
    }
  };
</script>
