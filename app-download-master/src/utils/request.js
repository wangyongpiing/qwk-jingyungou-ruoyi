// //创建axios实例
// import axios from 'axios'


// //创建axios实例
// const service = axios.create({
// 	baseURL: '/',
// 	//超时时间，这里是10秒
// 	timeout: 10000,
// })

// // 添加请求拦截器
// service.interceptors.request.use(res=> {  
// 	//配置请求头
// 	config.headers = {
// 		/*配置请求数据格式Content-Type*/
// 		//传json数据格式给后端，如果后端是这个接收格式，配置如下（可不写，因为axios默认将 JavaScript 对象序列化为 json格式）
// 		'Content-Type':'application/json;charset=utf-8',
// 		/*
// 		//数据会以普通表单形式（键值对，但不支持文件）发送到后端,如果后端是这个接收格式，配置如下
// 		'Content-Type':'application/x-www-form-urlencoded'
// 		//一般上传文件用此类型，数据是表单上传文件，配置如下（ps一般这种数据类型将在具体的请求接口中配置，而不在此统一封装，后面将介绍）
// 		'Content-Type':'multipart/form-data'
// 		*/
// 		//其他自定义的请求头也可写在这里，比如常见的请求头中携带tooken
// 	}	
//     return res;
//   }, error =>{
//     // 对请求错误做些什么
//     return Promise.reject(error);
// });

// // 添加响应拦截器
// service.interceptors.response.use(response=> {
// 	//只返回data中的信息
//     const resp=response.data;
// 	//文件下载类型，直接返回所有的
// 	if (response.request.responseType == "blob") {
// 	    return response;
// 	}
//     return resp;
//   }, error => {
//     // 超出 2xx 范围的状态码都会触发该函数。
//     // 对响应错误做点什么
//     return Promise.reject(error);
// });
// //导入文件
// export default service

// import axios from 'axios'
// import { Notification, MessageBox, Message, Loading } from 'element-ui'

// axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// // 创建axios实例
// const service = axios.create({
//   // axios中请求配置有baseURL选项，表示请求URL公共部分
//   baseURL: process.env.VUE_APP_BASE_API,
//   // 超时
//   timeout: 10000
// })

// // request拦截器
// service.interceptors.request.use(config => {
//   // 是否需要设置 token
//   const isToken = (config.headers || {}).isToken === false
//   // 是否需要防止数据重复提交
//   const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
//   if (getToken() && !isToken) {
//     config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
//   }
//   // get请求映射params参数
//   if (config.method === 'get' && config.params) {
//     let url = config.url + '?' + tansParams(config.params);
//     url = url.slice(0, -1);
//     config.params = {};
//     config.url = url;
//   }
//   if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
//     const requestObj = {
//       url: config.url,
//       data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
//       time: new Date().getTime()
//     }
//     const sessionObj = cache.session.getJSON('sessionObj')
//     if (sessionObj === undefined || sessionObj === null || sessionObj === '') {
//       cache.session.setJSON('sessionObj', requestObj)
//     } else {
//       const s_url = sessionObj.url;                  // 请求地址
//       const s_data = sessionObj.data;                // 请求数据
//       const s_time = sessionObj.time;                // 请求时间
//       const interval = 1000;                         // 间隔时间(ms)，小于此时间视为重复提交
//       if (s_data === requestObj.data && requestObj.time - s_time < interval && s_url === requestObj.url) {
//         const message = '数据正在处理，请勿重复提交';
//         console.warn(`[${s_url}]: ` + message)
//         return Promise.reject(new Error(message))
//       } else {
//         cache.session.setJSON('sessionObj', requestObj)
//       }
//     }
//   }
//   return config
// }, error => {
//     console.log(error)
//     Promise.reject(error)
// })

// // 响应拦截器
// service.interceptors.response.use(res => {
//     // 未设置状态码则默认成功状态
//     const code = res.data.code || 200;
//     // 获取错误信息
//     const msg = errorCode[code] || res.data.msg || errorCode['default']
//     // 二进制数据则直接返回
//     if (res.request.responseType ===  'blob' || res.request.responseType ===  'arraybuffer') {
//       return res.data
//     }
//     if (code === 401) {
//       if (!isRelogin.show) {
//         isRelogin.show = true;
//         MessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', { confirmButtonText: '重新登录', cancelButtonText: '取消', type: 'warning' }).then(() => {
//           isRelogin.show = false;
//           store.dispatch('LogOut').then(() => {
//             location.href = '/index';
//           })
//       }).catch(() => {
//         isRelogin.show = false;
//       });
//     }
//       return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
//     } if (code === 10002) {
//       // 第三方登录错误提示
//       MessageBox.confirm(msg, '系统提示', {
//           confirmButtonText: '重新登录',
//           cancelButtonText: '取消',
//           type: 'warning'
//         }
//       ).then(() => {
//         store.dispatch('LogOut').then(() => {
//           location.href = '/index';
//         })
//       }).catch(() => {});
//       return Promise.reject(new Error(msg))
//     } else if (code === 500) {
//       Message({ message: msg, type: 'error' })
//       return Promise.reject(new Error(msg))
//     } else if (code === 601) {
//       Message({ message: msg, type: 'warning' })
//       return Promise.reject('error')
//     } else if (code !== 200) {
//       Notification.error({ title: msg })
//       return Promise.reject('error')
//     } else {
//       return res.data
//     }
//   },
//   error => {
//     console.log('err' + error)
//     let { message } = error;
//     if (message == "Network Error") {
//       message = "后端接口连接异常";
//     } else if (message.includes("timeout")) {
//       message = "系统接口请求超时";
//     } else if (message.includes("Request failed with status code")) {
//       message = "系统接口" + message.substr(message.length - 3) + "异常";
//     }
//     Message({ message: message, type: 'error', duration: 5 * 1000 })
//     return Promise.reject(error)
//   }
// )

// // 通用下载方法
// export function download(url, params, filename, config) {
//   downloadLoadingInstance = Loading.service({ text: "正在下载数据，请稍候", spinner: "el-icon-loading", background: "rgba(0, 0, 0, 0.7)", })
//   return service.post(url, params, {
//     transformRequest: [(params) => { return tansParams(params) }],
//     headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
//     responseType: 'blob',
//     ...config
//   }).then(async (data) => {
//     const isBlob = blobValidate(data);
//     if (isBlob) {
//       const blob = new Blob([data])
//       saveAs(blob, filename)
//     } else {
//       const resText = await data.text();
//       const rspObj = JSON.parse(resText);
//       const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
//       Message.error(errMsg);
//     }
//     downloadLoadingInstance.close();
//   }).catch((r) => {
//     console.error(r)
//     Message.error('下载文件出现错误，请联系管理员！')
//     downloadLoadingInstance.close();
//   })
// }

// export default service