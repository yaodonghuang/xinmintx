var pay_flag = false;

function onBridgeReady(data) {
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId": data.appId,     //公众号名称，由商户传入
            "timeStamp": data.timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr": data.nonceStr, //随机串
            "package": data.package,
            "signType": data.signType,         //微信签名方式：
            "paySign": data.paySign //微信签名
        },
        function (res) {
            WeixinJSBridge.log(res.err_msg);
            if (res.err_msg == "get_brand_wcpay_request:ok") {
                //使用以上方式判断前端返回,微信团队郑重提示：
                //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                location.href = "/agent/user/index";
            }
            if (res.err_msg == "get_brand_wcpay_request:cancel" || res.err_msg == "get_brand_wcpay_request:fail"){
                pay_flag = false;
            }
        });
}

function shoppingReady(data) {
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId": data.appId,     //公众号名称，由商户传入
            "timeStamp": data.timeStamp,         //时间戳，自1970年以来的秒数
            "nonceStr": data.nonceStr, //随机串
            "package": data.package,
            "signType": "MD5",         //微信签名方式：
            "paySign": data.paySign //微信签名
        },
        function (res) {
            WeixinJSBridge.log(res.err_msg);
            if (res.err_msg == "get_brand_wcpay_request:ok") {
                //使用以上方式判断前端返回,微信团队郑重提示：
                //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                queryOder(data.out_trade_no);

            }
        });
}
function queryOder(out_trade_no) {
    $.ajax({
        url: "/agent/SelfBuying/queryPayStatus?out_trade_no="+out_trade_no,
        type: "POST",
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function(data) {
            if(data.msg === "已收到付款"){
                alert("付款成功");
                location.href = "/agent/procurementMarket/jumpProcurementMarket";
            }else {
                alert("网络有误");
            }
        }
    });
}