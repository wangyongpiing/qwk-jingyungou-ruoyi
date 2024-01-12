import service from "../utils/request.js";
//请求根路径
const BaseUrl="http://111.229.167.238:8080";

export function getDate(){
	return service({
		//请求路径
		url:`${BaseUrl}/getDate`,
		//请求方法
		methods:'get',
		//数据
	})
}