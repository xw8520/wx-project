<%--
  Created by Admin on 2016/2/28.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微信分享</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
</head>
<body>
测试分享功能
url:${requestScope.sign.all}
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    wx.config({
        debug: true,
        appId: '${requestScope.sign.appid}',
        timestamp: ${requestScope.sign.timestamp},
        nonceStr: '${requestScope.sign.nonceStr}',
        signature: '${requestScope.sign.signature}',
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
            'onMenuShareQZone',
            'hideMenuItems',
            'showMenuItems',
            'hideAllNonBaseMenuItem',
            'showAllNonBaseMenuItem',
            'translateVoice',
            'startRecord',
            'stopRecord',
            'onVoiceRecordEnd',
            'playVoice',
            'onVoicePlayEnd',
            'pauseVoice',
            'stopVoice',
            'uploadVoice',
            'downloadVoice',
            'chooseImage',
            'previewImage',
            'uploadImage',
            'downloadImage',
            'getNetworkType',
            'openLocation',
            'getLocation',
            'hideOptionMenu',
            'showOptionMenu',
            'closeWindow',
            'scanQRCode',
            'chooseWXPay',
            'openProductSpecificView',
            'addCard',
            'chooseCard',
            'openCard'
        ]
    });

    wx.error(function (res) {
        alert(res.errMsg);
    });

    wx.ready(function () {
        var url = '${requestScope.sign.url}';
        var link = url + "/share.html";
        var imgUrl = url + '/static/image/share.jpg';

        //转发到朋友圈
        wx.onMenuShareTimeline({
            title: '测试转发朋友圈',
            link: link,
            imgUrl: imgUrl,
            success: function () {
                alert('转发成功！');
            },
            cancel: function () {
                alert('转发失败！');
            }
        });
        //转发给朋友
        wx.onMenuShareAppMessage({
            title: '测试转发给朋友',
            desc: '转发给朋友',
            link: link,
            imgUrl: imgUrl,
            type: 'link',
            dataUrl: '',
            success: function () {
                alert('转发成功！');
            },
            cancel: function () {
                alert('转发失败！');
            }
        });
    });
</script>
</html>
