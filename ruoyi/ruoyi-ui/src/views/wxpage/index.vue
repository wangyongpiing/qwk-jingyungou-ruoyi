<template>
  <div class="goods_center w" id="goods">
    <div v-show="isGoods">
    <table>
      <thead>
      <tr>
        <th><input id="all" type="checkbox" @click="allClick()">全选</th>
        <th>商品图片</th>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品数量</th>
        <th>删除</th>
<!--        <th>备注</th>-->
      </tr>
      </thead>
      <tbody>
      <tr v-for="(item,index) in listGood" :key="index">
        <td><input type="checkbox" :checked="item.check" @click="select(index)"></td>
        <td><el-image :src="item.img" :initial-index="item-1"  loading="lazy" style="width:200px; height:200px" fit="contain"></el-image></td>
        <td>{{item.goodsName}}</td>
        <td>￥{{item.goodsPrice}}</td>
        <td class="yunsuan">
          <button @click="subs(index)" :disabled="item.goodCount-1<0">-</button>
          <span><input type="text" style="width: 70px" v-model="item.goodCount"></span>
          <button @click = "plus(index)">+</button>
        </td>
        <td @click="deletes(index)"class="del" style="padding-left: 38px">×</td>
        <td></td>
      </tr>
      <tr>
        <td></td>
        <td></td>
        <td colspan="2">合计：</td>
        <td style="font-size: 25px;color: #f30213;">￥&nbsp;&nbsp;&nbsp;{{getAllPrice}}</td>
        <td><button class="btns_tab" @click="allNull()">清空所有商品</button></td>
        <td><button class="btns_tab" @click="payPrice()">付款</button></td>
      </tr>
      </tbody>
    </table>
    <div class="shanChuXian">
      <hr>
      <h2>商品列表</h2>
    </div>

    <div class="photoBox">
      <div style="width: 200px; height: 200px; margin: 10px;" class="photoItem" v-for="(item,index) in msg" :key="item.id">
        <el-image :src="item.img" :initial-index="item-1"  loading="lazy" style="width:200px; height:200px" fit="contain"></el-image>
        <div class="info">{{item.goodsName}}</div>
        <div class="info" style="color: red;"><span>{{item.goodsPrice}}元</span></div>
        <div class="info"><button @click="addGoods(index)">加入购物车</button></div>
      </div>
    </div>
      <!---------------------------------------------序列号列表--------------------------------------->
      <div style="padding-top:90px">
        <hr>
        <h2>生肖灵宠卡列表</h2>
        <table>
          <thead>
          <tr>
            <th>卡号</th>
            <td></td>
            <th>面值</th>
            <th>是否已兑换</th>
            <th>生成时间</th>
            <th></th>
            <td colspan="2">合计：</td>
            <td style="font-size: 25px;color: #0000ff;">未兑换&nbsp;&nbsp;&nbsp;{{notRedeemed}}</td>
            <td></td>
            <td style="font-size: 25px;color: #f30213;">已兑换&nbsp;&nbsp;&nbsp;{{redeemed}}</td>
            <th></th>
            <th><button @click="refresh()">刷新</button></th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(item,index) in listCard" :key="index">
            <td>{{item.cardCode}}</td>
            <td></td>
            <td>{{item.cardType}}</td>
            <td>未兑换</td>
            <td>{{item.createTime}}</td>
          </tr>
          </tbody>
        </table>
      </div>

    </div>


    <!---------------------------------------------登录弹窗--------------------------------------->
    <div class="all" v-show="isLogin">
      <div class="box" :style="{border: '1px solid #000000'}">
        <div class="phonenum">账号</div>
        <div class="shouji">
<!--          <input  type="text" placeholder="请输入账号"  @change="getphone"  @blur="phoneblur"> <span v-show="isphone">请输入正确的手机号</span>-->
          <input  type="text" placeholder="请输入账号" v-model="username" style="border:0px;outline:none;">
        </div>
        <div class="yz">密码</div>
        <div class="mima">
<!--          <input  type="password" placeholder="6位有大小写字母数字字符" @change="getpwd"  @blur="pwdblur">-->
          <input type="password" v-model="password" style="border:0px;outline:none;">
        </div>
<!--        <div class="yz">验证码</div>-->
<!--        <div class="yanzhengma">-->
<!--          <input  type="text" placeholder="验证码" @blur="codeblur" @change="getmsg"> <div class="btn" v-show="isshow"  @click="getcode">获取验证码</div> <div v-show="!isshow" class="btn">{{ this.num }}s</div>-->
<!--        </div>-->

        <button class="login" @click="login"><span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span></button>
      </div>
    </div>
  </div>



</template>

<script src="./vue.js"></script>

<script>

  import {login, payment, refresh} from "@/api/qwk/zodiaccard";
  import Cookies from "js-cookie";

  export default {
    name: "zodiacCardMall",
    data() {
      return {
        isLogin:true,
        loading:false,
        phone:'',
        code:'',
        pwd:'',
        rpwd:'',
        num:10,
        isGoods:false,
        isshow:true,
        isphone:false,
        form: {
          username:"",
          password:""
        },
        //---------------------------------登录弹出结束-----------------------------------
        listCard:[],
        notRedeemed:0,
        redeemed:0,
        //---------------------------------生肖灵宠卡列表结束-------------------------------------
        listGood:[],
        msg: [
          {
            id: "01",
            img: "http://admin.hbqwk.com/images/sxk1.png",
            goodsName: "生肖卡灵宠1",
            goodsPrice: 35,
            goodsType:"1",
            // goodsPL: "评论",
            goodCount: 0,
            check: true
          },
          {
            id: "02",
            img: "http://admin.hbqwk.com/images/sxk1.png",
            goodsName: "生肖卡灵宠10",
            goodsPrice: 350,
            goodsType:"10",
            // goodsPL: "评论",
            goodCount: 0,
            check: false
          },
          {
            id: "03",
            img: "http://admin.hbqwk.com/images/sxk1.png",
            goodsName: "生肖卡灵宠100",
            goodsPrice: 3500,
            goodsType:"100",
            // goodsPL: "评论",
            goodCount: 0,
            check: false
          }

        ]
      }
    },
    methods: {
      // login(){
      //   let  r1= /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?]).*$/; //密码
      //   let r2 = /^\d{4}$/;   //验证码
      //   let r3 = /^(?:(?:\+|00)86)?1\d{10}$/ //手机号
      //
      //   if(r1.test(this.pwd)&&r2.test(this.code)&&r3.test(this.phone)&&this.pwd==this.rpwd){
      //     this.$msg.success('登录成功');
      //   }else {
      //     this.$msg.error('请检查是否有错误！');
      //
      //   }
      //
      // },
      login(){
          if(this.password=='' || this.username==''){
            this.$modal.msgError("账号，密码必填!")
            return;
          }
          this.form.username = this.username;
          this.form.password = this.password;
          this.loading = true;
        login(this.form).then(ret => {
          if(ret.code==200){
            this.$modal.msgSuccess("登录成功");
            this.isLogin = false;
            this.isGoods = true;
            this.refresh();
            Cookies.set("Admin-Token", ret.token, {expires: 30});
          }else{
            Cookies.remove("Admin-Token");
          }
        }).catch(error=>{
          console.log(error);
        })
      },
      pwdblur(){
        // 密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符 。
        let  r= /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
        // console.log(r.test(this.pwd));
        if(!r.test(this.pwd)){
          this.$msg.error('请输入正确的密码,至少1个大写字母，1个小写字母，1个数字，1个特殊字符');

        }

      },
      // codeblur(){
      //   let r = /^\d{4}$/;
      //   //    console.log(r.test(this.code));
      //   if( !r.test(this.code)) {
      //     this.$msg.error('请输入正确格式的验证码');
      //   }
      // },
      // getmsg(v){
      //   this.code=v.target.value
      // },
      getpwd(v){
        this.pwd=v.target.value
      },
      getphone(v){
        this.phone=v.target.value
      },
      phoneblur(){
        let r = /^(?:(?:\+|00)86)?1\d{10}$/
        // console.log(r.test(this.phone));
        if(r.test(this.phone)){
          this.isphone=false
        }else {
          this.isphone=true
        }
      },
      getcode(){
        let r = /^(?:(?:\+|00)86)?1\d{10}$/
        console.log(r.test(this.phone));
        if(r.test(this.phone)){
          this.isshow=false
          this.isphone=false
          let t = setInterval(()=>{
            this.num--
            if(this.num<1) {
              clearInterval(t)
              this.isshow=true
              this.num=10
            }
          },100)
        } else {
          this.$msg.error('请输入正确格式的手机号');

          this.isphone=true

        }

      },

      //-------------------------登录弹出结束
      closePopup() {
        this.showPopup = false;
      },
      // 添加商品
      addGoods(index){
        let obj = {};
        obj = this.msg[index];
        // this.listGood.push(obj);
        const isAdded = this.listGood.find(item => item.id === obj.id);
        if(isAdded){
          isAdded.goodCount++;
        }else{
          this.listGood.push(obj);
        }

      },
      // 删除
      deletes(index){
        if(confirm('你确认删除这件商品吗?')){
          this.listGood[index].goodCount=0;
          this.listGood.splice(index,1);
        }
      },

      // 减法
      subs(index){
        this.listGood[index].goodCount--;

      },
      plus(index){
        this.listGood[index].goodCount++;

      },
       allClick(){
        // 首先，获取到全选按钮，赋给变量isAll
        let isAll = document.getElementById('all');
        // 如果全选按钮被选中，单选按钮就全部选中  == >  list数组的check属性变为true
        if(isAll.checked == true){
          this.listGood.forEach(function(item,index){
            item.check = true;
            // this.allPrice += this.listGood[i].goodsPrice * this.listGood[i].goodCount;
          })
        }else{
          this.listGood.forEach(function(item,index){
            item.check = false;
          })
        }
      },
      // 单选按钮
      select(index){
        this.listGood[index].check = !this.listGood[index].check;
        let isAll = document.getElementById('all');
        let n = 0;
        for (var i = 0; i<this.listGood.length;i++){
          if(this.listGood[i].check == true){
            n++
          }
        }
        if( n == this.listGood.length){
          isAll.checked = true;
        }else{
          isAll.checked = false;
        }
      },
      //    清空所有商品
      allNull(){
        if(this.listGood.length>0){
          // console.log(this.listGood.length);
          var num=0;
          for(var i = 0 ;i<this.listGood.length;i++){
            num++;
            this.listGood[i].goodCount = 0;
            // console.log("i"+i);
            // console.log(this.listGood[i]);
          }
          this.listGood.splice(0,num);
        }else{
          this.$modal.msgError("您的购物车内暂时没有商品");
        }
      },
      // 付款
      payPrice(){
        if(this.listGood.length>0 && this.getAllPrice >0){
          if(confirm("您共购买了"+this.getAllCount+"件商品，共计：￥"+this.getAllPrice+"\n您确定购买吗？")){
            payment(this.listGood).then(response => {
              if(response.code==200){
                this.$modal.msgSuccess("购买成功");
                this.refresh();
                this.allNull();
              }
            });
          }
        }else{
          this.$modal.msgError("请您选购商品！");
        }

      },

      //刷新
      refresh(){
        refresh().then(response => {
          if(response.code==200){
            this.listCard = response.data.listCard;
            this.notRedeemed = response.data.notRedeemed;
            this.redeemed = response.data.redeemed;
          }else{
            this.$modal.msgError(response.msg);
          }
        });
      }
    },
    created() {

    },
    computed:{
      getAllPrice(){
        // 获取listGood中Check为true的数据
        //  filter()创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
        // filter() 方法用于把Array中的某些元素过滤掉，然后返回剩下的未被过滤掉的元素。
        //   注意事项：
        //     1、filter() 不会对空数组进行检测；
        //     2、filter() 不会改变原始数组。

        let listsCheck=this.listGood.filter(function (val){
          return val.check===true;
        })

        //总价
        let totalPrice=0;
        for (let i = 0; i < listsCheck.length; i++) {  //循环获取listGood中check为true的数据
          totalPrice+=listsCheck[i].goodCount*listsCheck[i].goodsPrice//将对应check为true的数量与价格相乘得出总价
        }

        return totalPrice;
      },
      getAllCount(){
        let totalCount = 0;
        for(var i = 0 ;i<this.listGood.length;i++){
          totalCount += this.listGood[i].goodCount;
        }
        return totalCount;
      }
    }
  }



</script>

<style rel="stylesheet/scss" lang="scss">
  .photoBox{
    width: calc(100% - 20px);
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    padding: 10px;

  }
  .photoItem{
    margin: 10px;
    cursor: pointer;
  }
  .info{
    text-align: center;
    color: #444;
  }
  .helpLayout{
    width: auto;
  }

//-----------------------登录弹窗开始
  .login {
    width: 360px;
    height: 40px;
    background-color: darkred;
    color: #fff;
    text-align: center;
    line-height: 40px;
    margin-top: 30px;
  }
  .yz {
    margin-top: 10px;
  }

  .box {
    padding: 20px;
    position: absolute;
    width: 400px;
    height: 270px;
    /* background-color: red; */
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    margin: auto;
    .shouji  {
      margin-top: 10px;
      width: 360px;
      height: 30px;
      border-bottom: 1px solid gray;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    /*.yanzhengma {*/
    /*  margin-top: 10px;*/
    /*  width: 360px;*/
    /*  height: 30px;*/
    /*  border-bottom: 1px solid gray;*/
    /*  display: flex;*/
    /*  justify-content: space-between;*/
    /*  align-items: center;*/
    /*  .btn {*/
    /*    width: 100px;*/
    /*    height: 20px;*/
    /*    background-color: darkred;*/
    /*    color: #fff;*/
    /*    margin-right: 10px;*/
    /*    text-align: center;*/
    /*  }*/
    /*}*/
    .mima {
      margin-top: 10px;
      width: 360px;
      height: 30px;
      border-bottom: 1px solid gray;
    }



  }
</style>
