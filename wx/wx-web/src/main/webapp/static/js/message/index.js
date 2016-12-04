/**
 * Created by admin on 2016/12/2.
 */
$(function () {
    pager.initPager('/message/getMessageList');
    pager.loadData();
    $('#btnSearch').click(function () {
        pager.loadData();
    });
    initSelect();

    $('#btnAdd').click(function () {
        var data = {
            id: $('#id').val() == '' ? 0 : $('#id').val(),
            title: $('#title').val(),
            mediaId: $('#mediaId').val(),
            accountId: $('#accountId').val(),
            tagId: $('#tagId').val(),
            domain: $('#domain').val(),
            toall: $('#toall').is(':checked'),
            type: $('#selType2').val(),
            content: $('#content').val(),
            domain: $.cookie('d')
        };
        if (data.mediaId == '') {
            $.showToast('素材id不能为空')
            return
        }
        if (data.type == '') {
            $.showToast('素材类型不能为空')
            return
        }
        if (data.content == '' && data.type == 2) {
            $.showToast('消息内容不能为空')
            return
        }
        if (data.accountId == -1) {
            $.showToast('公众号不能为空')
            return
        }
        if (data.accountId == -1) {
            $.showToast('公众号不能为空')
            return
        }
        $.ajax({
            url: '/message/addMessage',
            type: 'POST',
            data: data,
            dataType: 'json',
            success: function (resp) {
                if (resp.success) {
                    $.showToast('保存成功');
                    $.hideModel();
                    pager.loadData();
                    $.clearForm('#popModel');
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
        dataType: 'json',
        success: function (resp) {
            $('#tempSelc').tmpl(resp).appendTo('#txtAccount,#accountId');
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
    $.ajax({
        url: '/message/getMessageType',
        type: 'POST',
        dataType: 'json',
        success: function (resp) {
            $('#tempSelc').tmpl(resp).appendTo('#selType,#selType2');
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
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
    $('#accountId').change(function () {
        $('#wxTagId').empty();
        loadTag();
    });
    $("#tagId").chosen({
        no_results_text: "没有找到",
        width: '300px',
        allow_single_de: true
    })
}

function loadTag(callback) {
    var data = {
        accountId: $('#accountId').val(), domain: $.cookie('d')
    };
    $.ajax({
        url: '/wxtag/getTagSelect',
        type: 'POST',
        data: data,
        dataType: 'json',
        success: function (resp) {
            $('#tempSelc').tmpl(resp).appendTo('#tagId');
            $("#tagId").trigger("chosen:updated")
            if (callback) {
                callback();
            }
            $("#tagId").trigger("chosen:updated")
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
    if ($('#selAccount').val() != null && $('#selAccount').val() != '') {
        param.accountId = $('#selAccount').val();
    }
    if ($('#selType').val() != null && $('#selType').val() != '-1') {
        param.type = $('#selType').val();
    }
    if ($('#selState').val() != '' && $('#selState').val() != '') {
        param.state = $('#selState').val();
    }
    return param;
}

function add() {
    $.showModel();
}

function edit(id) {
    $.showModel();
    $.ajax({
        url: '/message/getMessage',
        type: 'POST',
        data: {id: id},
        dataType: 'json',
        success: function (resp) {
            $('#id').val(resp.id);
            $('#title').val(resp.title);
            $('#mediaId').val(resp.mediaId)
            $('#accountId').val(resp.accountId)
            $('#domain').val(resp.domain)
            if (resp.toall) {
                $('#toall').attr("checked", "checked")
            } else {
                $('#toall').removeAttr('checked')
            }
            $('#selType2').val(resp.type)
            if (resp.type != 2) {
                $('#fContent').css('display', 'none');
            }
            $('#content').val(resp.content)
            loadTag(function () {
                $('#tagId').val(resp.tagId)
            });
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
}

function del(id) {
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/media/deleteMedia',
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

function delWx(id) {
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/media/deleteMedia',
            type: 'POST',
            data: {id: id, delWx: true},
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

function syncState(id){
    $.ajax({
        url: '/message/syncSendState',
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