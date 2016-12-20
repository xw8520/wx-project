/**
 * Created by admin on 2016/12/18.
 */
$(function () {
    pager.initPager('/message/getMessageRecordList');
    pager.loadData();
    $('#btnSearch').click(function () {
        pager.loadData();
    });
    initSelect();
    $('#btnAdd').click(function(){
        var mid=$('#hidMid').val();
        var openId=$('#txtOpenId').val();
        if(openId==''){
            $.showToast('请输入OpenId')
            return
        }
        $.ajax({
            url: '/message/previewMessage',
            type: 'POST',
            data: {mid: mid,openId:openId},
            dataType: 'json',
            success: function (resp) {
                if (resp.success) {
                    $.showToast('发送成功');
                    $.hideModel();
                    pager.loadData();
                    return
                }
                $.showToast(resp.info);
            },
            error: function (resp) {
                $.showToast('系统出错')
            }
        })
    });
});

function initSelect() {
    $.ajax({
        url: '/media/getAccountSelect',
        type: 'POST',
        data: {domain: $.cookie('d')},
        dataType: 'json',
        success: function (resp) {
            $('#tempSelc').tmpl(resp).appendTo('#selAccount');
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    });
    $.ajax({
        url: '/message/getMessageState',
        type: 'POST',
        dataType: 'json',
        success: function (resp) {
            $('#tempSelc').tmpl(resp).appendTo('#selState');
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    });
    $('#selTag').chosen()
    $('#selAccount').change(loadTag);
}

function loadTag() {
    $('#selTag').empty()
    var accountId = $('#selAccount').val();
    if (accountId == -1) {
        $('#selTag').trigger("chosen:updated")
        return
    }
    $.ajax({
        url: '/wxtag/getTagSelect',
        type: 'POST',
        data: {domain: $.cookie('d'), accountId: accountId},
        dataType: 'json',
        success: function (resp) {
            $('#tempSelc').tmpl(resp).appendTo('#selTag');
            $('#selTag').trigger("chosen:updated")
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
}

function getParam() {
    var param = {};
    if ($('#txtTitle').val() != '') {
        param.title = $('#txtTitle').val();
    }
    if ($('#selAccount').val() != null
        && $('#selAccount').val() != '') {
        param.accountId = $('#selAccount').val();
    }else{
        param.accountId=-1;
    }
    if ($('#selState').val() != null
        && $('#selState').val() != '') {
        param.stateId = $('#selState').val();
    } else {
        param.stateId = -1;
    }
    if ($('#selTag').val() != null && $('#selTag').val() != '-1') {
        param.tagId = $('#selTag').val();
    }else{
        param.tagId=0;
    }
    return param;
}

function edit(id,mid, sendTypeId) {
    if (sendTypeId == 1) {
        window.location.href = "sendByTagId.html?mid=" + mid+"&id="+id;
    } else {
        window.location.href = "sendByOpenId.html?mid=" + mid+"&id="+id;
    }
}

function del(id) {
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/message/deleteMessageRecord',
            type: 'POST',
            data: {id: id},
            dataType: 'json',
            success: function (resp) {
                if (resp.success) {
                    $.showToast('删除成功');
                    pager.loadData();
                } else {
                    $.showToast(resp.info)
                }
            },
            error: function (resp) {
                $.showToast('系统出错，请稍后再试');
            }
        })
    })

}

function send(id) {
    $.ajax({
        url: '/message/sendMessage',
        type: 'POST',
        data: {id: id},
        dataType: 'json',
        success: function (resp) {
            if (resp.success) {
                $.showToast('发送成功');
                $.hideModel();
                pager.loadData();
                return
            }
            $.showToast(resp.info);
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
}

function preview(mid){
    $.showModel();
    $('#hidMid').val(mid)
}

function sync(id, accountId,msgId) {
    if(msgId==''||msgId==undefined){
        $.showToast('消息未发送')
        return
    }
    $.ajax({
        url: '/message/syncSendState',
        type: 'POST',
        data: {id: id, accountId: accountId, msgId: msgId},
        dataType: 'json',
        success: function (resp) {
            if (resp.success) {
                $.showToast('发送成功');
                $.hideModel();
                pager.loadData();
                return
            }
            $.showToast(resp.info);
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
}