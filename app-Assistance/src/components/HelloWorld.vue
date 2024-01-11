<template>
  <div class="content" >
	  <!-- <h1 class="title" style="color: aliceblue;text-align: center;font-size: 5.5vw;">鲸云购大家一起捕盲盒</h1> -->
	  <!-- <div style="text-align: center;border: 1px;">
		  <img style="width: 50%;height: 50%;"  src="~@/assets/2-2.png"/>
	  </div> -->
	  <div style="margin-top:85%;margin-left:73%;font-size: 14px;">{{userBalance}}</div>
	  <div style="text-align: center;">
	  		  <img style="width:91%"  src="~@/assets/4-4.jpg"/>
	  </div>
	  <div style="height: 6.25rem;width: 100%;padding:0.9375rem;">
		<div :style="`background-image: url(${imgUrl};background-size: 100% auto;
				background-repeat: no-repeat;text-align: center;margin:85px;margin-left:10px;height:83px;`"
				@click.stop="assistanceClick">
<!-- 				<h1 style="color: aliceblue;font-size: 5vw;"	>帮他助力</h1> -->
		</div>
	  </div>
	  <!-- <h2 style="color: orange;text-align: center;font-size: 4vw;">只需要您助力一次他马上就可以得到:</h2>
	  <div style="text-align: center;">
		  <el-image
		        style="width: 120px; height: 120px"
		        :src="goodsImg"
		        fit="contain">
		  </el-image>
		  <h3 style="color:burlywood;font-size: 3vw;">{{goodsName}}</h3>
	  </div> -->
	  
	  
<!-- 	<el-dialog
		:visible.sync="centerDialogVisible"
		width="300px"
		center
		modal="false"
		append-to-body = "true"
		>
		<div style="height:300px;" @click="open()"></div>
	</el-dialog> -->

	  
	</div>



</template>



<script>
// import 'vant/lib/button/style'
// import 'vant/lib/action-sheet/style'
import axios from "axios"

export default {
  name: 'HelloWorld',
  components: {
  },
  data() {
    return {
		show: false,
		actions: [],
		title: null,
		btnColor: '#7384f0',
		active: 0,
		list: window.list,
		fileUrl:"http://ccc.hbqwk.com/uploadPath/sxb_1.3.7.apk",
		version:"",
		url:"http://admin.hbqwk.com",
		// url:"https://www.hbqwk.com/prod-api",
		userId:null,
		imgUrl:require('@/assets/3-3.png'),
		goodsImg:"",
		goodsName:"",
		userBalance:"",
		money:100,
		centerDialogVisible: false
    }
  },
  computed: {
  },
  methods: {
	open(){
		window.open("https://ccc.hbqwk.com/download/?userId="+this.userId, "_blank");
	},
	getUrl(){
		let url = new URL(location.href);
		this.userId = url.searchParams.get('userId');
		this.goodsImg = url.searchParams.get('imgUrl');
		this.goodsName = url.searchParams.get('goodsName');
		console.log("userId="+this.userId);
		console.log("imgUrl="+this.imgUrl);
		console.log("goodsName="+this.goodsName);
		
	},
	getUserBalance(){
		if(this.userId != null){
			this.$axios({
				method: 'get',
				url: this.url+'/qwk/balance/queryUserAccount?userId='+this.userId,
			}).then(res=>{
				console.log(res.data.code);
				console.log(res.data.data.price);
				if(res.data.code == 200 && res.data.data.price!=null){
				   this.userBalance = "仅差"+res.data.data.price+"元";
					console.log(this.userBalance)
				}
			},err=>{
				console.log(err);
			});
		}	
	},
	assistanceClick(){
		window.open("https://www.wlxxl.top/?userId="+this.userId);
	}
  },
  beforeCreate() {
    document.title = '生肖宝助力'
  },
  created() {
	this.getUrl();
	this.getUserBalance();
  },
}
</script>

<style lang="scss" scoped>
// .content{
// 	background-image: url(~@/assets/1-1.jpg);
// 	background-size: 100% auto;
// 	background-repeat: no-repeat;
// 	width:100%;
// 	height:100%;
// 	position:relative;
// 	background-size:100% 100%;
// 	overflow-y: scroll;
// 	-webkit-overflow-scrolling: touch;
// }
</style>

<style>
	.el-dialog,
	.el-pager li {
		background-color: rgba(255, 0, 0, 0.0);
		color: #FFFFFF;
		background-image: url(../assets/tanchuang.png);
		background-size: 100%  100%;
		background-repeat: no-repeat;
	}
	//将el-dialog__header、el-dialog__body、el-dialog__footer背景设置为透明
	// ```css
	.el-dialog__header {
		padding-top: 10px !important;
		background-color:rgb(255,255,255,0);
		border-radius: 14px 14px  0  0 ;
	}
	.el-dialog__body {
		border-top: 0 !important;
		background-color: rgba(19, 31, 59, 0);
		color: #FFFFFF;
	}
	.el-dialog__footer{
		text-align: center;
		background-color: rgba(255,255,255,0);
	}
</style>
