<template>
  <div class="content">
<!--    <div class="main">
      <ul>
        <li
          v-for="(v,i) in list"
          :key="i"
          :class="active===i ? 'active' : null"
          @click.stop="active=i"
        >
          <span>{{v.name}}</span>
          <span>{{v.version}}</span>
          <div @click.stop="handerClickDown(v)"></div>
        </li>
      </ul>
    </div> -->

    <div class="footer">
      <div class="btn-box">
<!--        <div class="text">生肖宝</div> -->
        <van-button
          round
          block
          :color="btnColor"
          :icon="require('@/assets/android.png')"
          @click.stop="androidClick"
        >下 载</van-button>
      </div>
      <div class="btn-box">
<!--        <div class="text">版本: v{{this.version}}</div> -->
        <van-button
          round
          block
          :color="btnColor2"
          :icon="require('@/assets/apple.png')"
          @click.stop="iosClick"
        >下 载</van-button>
      </div>
    </div>
	<div class="middle">
	</div>
	<div class="bottom">
	</div>

    <!-- <van-action-sheet v-model="show" :actions="actions" :title="title" @select="onSelect" /> -->
  </div>

</template>


<script>
import { Button, ActionSheet } from 'vant'
import 'vant/lib/button/style'
import 'vant/lib/action-sheet/style'
import axios from "axios"

export default {
  name: 'HelloWorld',
  components: {
    [Button.name]: Button,
    [ActionSheet.name]: ActionSheet
  },
  data() {
    return {
      show: false,
      actions: [],
      title: null,
      btnColor: '#7384f0',
	  btnColor2: '#808080',
      active: 0,
      list: window.list,
		fileUrl:"http://ccc.hbqwk.com/uploadPath/sxb_1.3.7.apk",
		version:"",
		url:"http://admin.hbqwk.com",
		// url:"https://www.hbqwk.com/prod-api",
		userId:null,
    }
  },
  computed: {
    activeName() {
      return "";
    },
    activeVersion() {
      return "1.0";
    }
  },
  methods: {
	// 安卓下载
    async androidClick() {
		if(this.userId != null){
			// this.$axios({
			await axios({
				method: 'get',
				url: this.url+'/qwk/friends/addInviteFriends/'+this.userId,
			}).then(res=>{
				// console.log(res);
			},err=>{
				console.log(err);
			});
		}
		console.log("下载地址:"+this.fileUrl)
		window.open(this.fileUrl);
		
    },
    iosClick() {
		this.$message({
			showClose: true,
			message: '开发中,敬请期待!',
			type: 'warning',
			customClass: "msgbox",
			
        });
    },
	getTest(){
		this.$axios({
			method: 'get',
			url: this.url+'/qwk/versioning/getLatest',
		}).then(res=>{
			console.log(res);
			if(res.data.code == 200){
				this.fileUrl=res.data.data.versioning.fileUrl;
				this.version=res.data.data.versioning.version;
				console.log(this.fileUrl)
			}
		},err=>{
			console.log(err);
		});
	},
	getUrl(){
		var url = window.location.href ;//获取当前url
		var cs = url.split('?')[1];//获取?之后的参数字符串
		if(cs != '' && cs != null && cs != undefined){
			// cs.substring(cs.indexOf("="))
			// console.log("---------"+cs.substring(cs.indexOf("=")+1))
			this.userId = cs.substring(cs.indexOf("=")+1);
		}
		
	},
  },
  beforeCreate() {
    document.title = '生肖宝APP下载'
  },
  created() {
	this.getTest();
	this.getUrl();
  },
	// mounted(){
	// 	this.getTest();
	// 	this.getUrl();
	// },


}
</script>

<style>

</style>

<style lang="scss" scoped>
$btn-bg: #7384f0;
$font-color: #5064eb;
$cell-bg: #edeffc;

.middle {
  background-image: url(~@/assets/2.jpg);
  background-size: 100% auto;
  background-repeat: no-repeat;
  width: 100%;  
  height: 5.56rem;
  // position: relative;
  // overflow-y: scroll;
  -webkit-overflow-scrolling: touch;
}
.bottom {
  background-image: url(~@/assets/3.jpg);
  background-size: 100% auto;
  background-repeat: no-repeat;
  width: 100%;  
  height: 6.5rem;
  // position: relative;
  // overflow-y: scroll;
  -webkit-overflow-scrolling: touch;
}

.content {
  box-sizing: border-box;
  padding-top: 221px;
  color: $font-color;
  font-size: 18px;
}

.main {
  height: calc(100vh - 221px - 100px);
  box-sizing: border-box;
  padding: 0 15px;
  ul {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-around;
    li {
      background-color: $cell-bg;
      display: flex;
      align-items: center;
      justify-content: space-between;
      width: 100%;
      height: 54px;
      box-sizing: border-box;
      padding: 0 20px;
      border-radius: 25px;
      transition: all 0.21s linear;
      span {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
      span:first-of-type {
        flex: 2;
      }
      span:last-of-type {
        flex: 1;
      }
      div {
        width: 28px;
        height: 28px;
        background-image: url(~@/assets/down.png);
        background-size: 100% 100%;
      }
      div:active {
        background-image: url(~@/assets/down-white.png);
      }
    }
    li.active {
      background-color: $font-color;
      color: white;
      div {
        background-image: url(~@/assets/down-white.png);
      }
      div:active {
        background-image: url(~@/assets/down.png);
      }
    }
  }
}

.footer {
  width: 100%;
  height: 30px;
  box-sizing: border-box;
  padding: 0 15px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  .btn-box {
    width: 0;
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .text {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      text-align: center;
      width: 100%;
      margin-bottom: 7px;
      color: grey;
    }
  }
  .btn-box:first-of-type {
    margin-right: 7.5px;
  }
  .btn-box:last-of-type {
    margin-left: 7.5px;
  }
}
</style>