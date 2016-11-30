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
    var input = $('.chkId:checked');
    var data = new Array();
    input.each(function (index, el) {
        data.push(parseInt($(el).attr('val')));
    });
    if (data.length == 0) {
        $.showToast("请选择要删除的数据")
        return;
    }
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/wxtag/deleteTag',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json",
            traditional: true,
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
    $.showModel();
}