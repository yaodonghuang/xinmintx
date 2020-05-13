$(function () {
    wechatshare();
});

function wechatshare() {
    var url = window.location.href;
    var ptUserId = $("#ptUserId").val();
    $.ajax({
        url: "/wx/link/shareLink?url="+url,
        type: "POST",
        processData: false,
        contentType: false,
        dataType: 'json',
        success: function (data) {
            wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: 'wxfae6eed7965225be', // 必填，公众号的唯一标识
                timestamp:data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.noncestr, // 必填，生成签名的随机串
                signature: data.signature,// 必填，签名，见附录1
                jsApiList: [
                    // 所有要调用的 API 都要加到这个列表中
                    "onMenuShareAppMessage", //分享给好友
                    "onMenuShareTimeline", //分享到朋友圈
                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
        }
    });
    window.share_config = {
        "share": {
            "imgUrl": "http://agent2.xinmintx.cn/images/xinmintx.jpg",//分享图，默认当相对路径处理，所以使用绝对路径的的话，“http://”协议前缀必须在。
            "desc" : "大回馈，花少钱享优质生活，更多优惠商品，就等你喊朋友一起来选！",//摘要,如果分享到朋友圈的话，不显示摘要。
            "title" : '更多优惠，尽在惠商',//分享卡片标题
            "link": 'http://www.xinmintx.cn/#/home?ptUserId='+ptUserId,//分享出去后的链接，这里可以将链接设置为另一个页面。
            "success":function(){//分享成功后的回调函数
            }
        }
    };
    wx.ready(function () {
        wx.onMenuShareAppMessage(share_config.share);
        wx.onMenuShareTimeline(share_config.share);
    });
}