function onBridgeReady(data){
  WeixinJSBridge.invoke(
    'getBrandWCPayRequest', {
      // 公众号名称，由商户传入
      appId: data.appId,
      // 时间戳，自1970年以来的秒数
      timeStamp: data.timeStamp,
      // 随机串
      nonceStr: data.nonceStr,
      package: data.package,
      // 微信签名方式：
      signType: data.signType,
      // 微信签名
      paySign: data.paySign,
    },
    function(res){
      // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回 ok，但并不保证它绝对可靠。
      if(res.err_msg == "get_brand_wcpay_request:ok" ) {}
    }
  );
}
if (typeof WeixinJSBridge == "undefined"){
  if(document.addEventListener ){
    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
  }else if (document.attachEvent){
    document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
  }
}else{
  onBridgeReady();
}
