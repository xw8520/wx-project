$(function () {
    pager.initPager('/wxtag/getTagList');
    pager.loadData();
    $('#btnSearch').click(function () {
        pager.loadData();
    });
    initSelect();

    $('#btnAdd').click(function () {
        var data = {
            id: $('#hidId').val() == '' ? 0 : $('#hidId').val(),
            name: $('#txtTitle').val(),
            tagId: $('#txtWxId').val() == '' ? 0 : $('#txtWxId').val(),
            accountId: $('#txtAccount').val(),
            remark: $('#txtRemark').val(),
            domain: $.cookie("d")
        };
        if (data.title == '') {
            $.showToast('标签名不能为空')
            return
        }
        if (data.accountId == -1) {
            $.showToast('公众号不能为空')
            return
        }
        $.ajax({
            url: '/wxtag/addTag',
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
            $('#tempSelc').tmpl(resp).appendTo('#txtAccount,#selAccount');
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
}

function getParam() {
    var param = {};
    if ($('#txtName').val() != '') {
        param.name = $('#txtName').val();
    }
    if ($('#selAccount').val() != null && $('#selAccount').val() != '') {
        param.accountId = $('#selAccount').val();
    }
    return param;
}

function add() {
    $.showModel();
}

function del() {
    var id = getCurrent();
    if (!id) {
        $.showToast("请选择要删除的数据")
        return;
    }
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/wxtag/deleteTag?tagId='+id,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json",
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

function edit() {
    var id = getCurrent();
    if (!id) {
        $.showToast("请选择要编辑的数据")
        return
    }
    $.showModel();

    $.ajax({
        url: '/wxtag/getWxTag?tagId=' + id,
        type: 'POST',
        dataType: 'json',
        success: function (resp) {
            $('#txtTitle').val(resp.name);
            $('#txtWxId').val(resp.wxTagId);
            $('#txtAccount').val(resp.accountId);
            $('#txtRemark').val(resp.remark);
            $('#hidId').val(resp.id);
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })

    function getCurrent() {
        var input = $('.chkId:checked');
        var data = new Array();
        input.each(function (index, el) {
            data.push(parseInt($(el).attr('val')));
        });
        if (data.length > 0) {
            return data[0];
        }
        return null;
    }
}

function sync() {
    var accountId = $('#selAccount').val();
    if (accountId == -1 || accountId == '') {
        $.showToast("请选择公众号")
        return
    }
    $.ajax({
        url: '/wxtag/syncTag',
        type: 'POST',
        data: {accountId: accountId, domain: $.cookie("d")},
        dataType: 'json',
        success: function (resp) {
            if (resp.success) {
                $.showToast('同步成功');
                pager.loadData();
            } else {
                $.showToast(resp.info)
            }
        },
        error: function (resp) {
            $.showToast('系统出错，请稍后再试');
        }
    })
}

function getCurrent() {
    var input = $('.chkId:checked');
    var data = new Array();
    input.each(function (index, el) {
        data.push(parseInt($(el).attr('val')));
    });
    if (data.length > 0) {
        return data[0];
    }
    return null;
}