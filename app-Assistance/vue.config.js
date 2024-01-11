const autoprefixer = require('autoprefixer');
const pxtorem = require('postcss-pxtorem');

module.exports = {
  productionSourceMap: false,
  publicPath: './',
  css: {
    loaderOptions: {
      postcss: {
        plugins: [
          autoprefixer(),
          pxtorem({
            rootValue: 37.5,
            propList: ['*']
          })
        ]
      }
    }
  },
  devServer: {
    host: '0.0.0.0',
    port: 8084, // 端口
	// proxy：{
	// 	// '/api': {
	// 	// 	target: 'http://192.168.5.8:8080',//后端接口地址
	// 	// 	changeOrigin: true,  //是否允许跨域
	// 	// 	pathRewrite: {
	// 	// 		'^/api': '' //注册全局路径
	// 	// 	}
	// 	// },
	// 	["/"]: {
	// 	    target: `http://localhost:8080`,
	// 	    changeOrigin: true,
	// 	    pathRewrite: {
	// 	      ['^' + "/"]: ''
	// 		}
	// 	}
	// },
  }
  // devServer: {
  //   host: '0.0.0.0',  
  //   port: 8080, // 生产环境端口  
  //   https: false, // 使用HTTPS协议  
  //   proxy: {  
  //     '/api': {  
  //       target: 'http://admin.hbqwk.com', // 后端接口地址  
  //       changeOrigin: true, // 是否允许跨域  
  //       pathRewrite: {  
  //         '^/api': '' // 注册全局路径  
  //       }  
  //     }  
  //   }  
  // }
};