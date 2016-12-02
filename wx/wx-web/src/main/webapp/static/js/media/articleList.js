$(function () {
    pager.initPager('/media/getArticleList');
    pager.loadData();

    $('#btnSearch').click(function () {
        pager.loadData();
    });

    initSelect();

    $('#btnAdd').click(function () {
        var data = {
            title: $('#txtTitle').val(),
            remark: $('#txtRemark').val(),
            accountid: $('#txtAccount').val(),
            domain: $.cookie("d"),
            id: $('#hidId').val() == '' ? 0 : $('#hidId').val()
        };
        if (data.title == '') {
            $.showToast('标题不能为空')
            return
        }
        if (data.accountid == -1) {
            $.showToast('公众号不能为空')
            return
        }
        $.ajax({
            url: '/media/addArticle',
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
    if ($('#txtId').val() != null && $('#txtId').val() != '') {
        param.id = $('#txtId').val();
    }
    if ($('#txtName').val() != null && $('#txtName').val() != '') {
        param.title = $('#txtName').val();
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
    var data = {};
    var idList = new Array();
    input.each(function (index, el) {
        idList.push(parseInt($(el).attr('val')));
    });
    if (idList.length == 0) {
        $.showToast("请选择要删除的数据")
        return;
    }
    data.data = idList;
    data.wx = false;
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/media/deleteArticle',
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

function delWx() {
    var input = $('.chkId:checked');
    var data = {};
    var idList = new Array();
    input.each(function (index, el) {
        idList.push(parseInt($(el).attr('val')));
    });
    if (idList.length == 0) {
        $.showToast("请选择要删除的数据")
        return;
    }
    data.data = idList;
    data.wx = true;
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/media/deleteArticle',
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

function articleDetail() {
    var id = getCurrent();
    if (!id) {
        $.showToast("请选择要查看的数据")
        return;
    }
    $.showModel();
    $.ajax({
        url: '/media/getArticle',
        type: 'POST',
        data: {id: id},
        dataType: 'json',
        success: function (resp) {
            $('#txtTitle').val(resp.title);
            $('#txtRemark').val(resp.remark);
            $('#txtAccount').val(resp.accountId);
            $('#txtMediaId').val(resp.mediaid);
            $('#hidId').val(resp.id);
            $('#txtAccount').attr('readonly', 'readonly');
        },
        error: function (resp) {
            $.showToast('系统出错')
        }
    })
}

function editArtile() {
    var id = getCurrent();
    if (!id) {
        $.showToast("请选择要编辑的数据")
        return;
    }
    window.location.href = "articleItem.html?aid=" + id;
}

function sendToWx() {
    var id = getCurrent();
    if (id) {
        $.ajax({
            url: '/media/sendArticleToWx',
            type: 'POST',
            data: {id: id},
            dataType: 'json',
            success: function (resp) {
                if (resp.success) {
                    $.showToast('上传成功');
                    pager.loadData();
                } else {
                    $.showToast(resp.info)
                }
            },
            error: function (resp) {
                $.showToast('系统出错，请稍后再试');
            }
        })
        return;
    }
    $.showToast("请选择要上传的数据")
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