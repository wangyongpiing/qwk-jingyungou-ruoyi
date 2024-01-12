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
          <el-button type="warning">积分换钻石</el-button>
        </div>
      </el-col>
      <el-col :span="2.5">
        <div>
          <el-button type="primary" @click="this.goCommodity">商品换积分</el-button>
        </div>
      </el-col>
    </el-row>
    <el-form ref="form" :model="form" :rules="rules"  style="padding-top: 20px">

      <el-form-item label="积分兑换钻石比例" prop="unrealProportion">
        <el-row>
          <el-col :span="3">
            <el-input v-model="form.moneyProportion" :disabled="true" placeholder="请输入积分占比"/>
          </el-col>
          <el-col :span="1" style="text-align:center;">
            <span >:</span>
          </el-col>
          <el-col :span="3">
            <el-input v-model="form.unrealProportion" placeholder="请输入钻石占比"/>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="充值金额设置" prop="gearPosition">
        <el-row :gutter="20">
          <el-col :span="3" >
            <el-form-item prop="input1">
              <el-input  v-model="form.input1">
                <template slot="append">积分</template>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <el-form-item prop="input2">
              <el-input  v-model="form.input2">
                <template slot="append">积分</template>
              </el-input>
            </el-form-item>

          </el-col>
          <el-col :span="3" >
            <el-form-item prop="input3">
              <el-input  v-model="form.input3">
                <template slot="append">积分</template>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <el-form-item prop="input4">
              <el-input  v-model="form.input4">
                <template slot="append">积分</template>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="3">
            <el-form-item  prop="input5">
              <el-input  v-model="form.input5">
                <template slot="append">积分</template>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="3" >
            <el-form-item prop="input6">
              <el-input  v-model="form.input6">
                <template slot="append">积分</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form-item>
    </el-form>
    <el-button type="primary" @click="submitForm">保 存</el-button>
    <el-button @click="cancel">取 消</el-button>

  </div>
</template>

<script>
  import { addMoneyRatio, } from "@/api/qwk/moneyRatio";

  export default {
    name: "MoneyRatio",
    // dicts: [''],
    data() {
      return {
        // 表单参数
        form: {
          moneyProportion : 1,
          type : 2,
          input1:"",
          input2:"",
          input3:"",
          input4:"",
          input5:"",
          input6:"",
        },
        // 表单校验
        rules: {
          type: [
            {required: true, message: "充值类型不能为空", trigger: "change"}
          ],
          unrealProportion: [
            {required: true, message: "兑换比例不能为空", trigger: "blur"}
          ],
          // gearPosition: [
          //   {required: true, message: "充值金额不能为空", trigger: "blur"}
          // ],
          input1: [
            {required: true, message: "充值金额不能为空", trigger: "blur"}
          ],
          input2: [
            {required: true, message: "充值金额不能为空", trigger: "blur"}
          ],
          input3: [
            {required: true, message: "充值金额不能为空", trigger: "blur"}
          ],
          input4: [
            {required: true, message: "充值金额不能为空", trigger: "blur"}
          ],
          input5: [
            {required: true, message: "充值金额不能为空", trigger: "blur"}
          ],
          input6: [
            {required: true, message: "充值金额不能为空", trigger: "blur"}
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
          type: 2,
          gearPosition: null,
          moneyProportion: 1,
          unrealProportion: null,
          createdBy: null,
          createdTime: null,
          updatedBy: null,
          updatedTime: null
        };
        this.resetForm("form");
      },

      /** 提交按钮 */
      submitForm() {
        // this.form.type = 1;
        this.form.gearPosition = this.form.input1+','+this.form.input2+','+this.form.input3+','+this.form.input4+','+this.form.input5+','+this.form.input6;
        this.$refs["form"].validate(valid => {
          if (valid) {
            addMoneyRatio(this.form).then(response => {
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
      //前往商品兑换积分
      goCommodity(){
        this.$router.push({
          path: '/financial/exchange/commodity',
        })
      },
    }
  };
</script>
