/**
 * Created by admin on 2016/11/26.
 */
$(function () {
    pager.initPager('/media/getarticleItemList');
    pager.loadData();
});
function getParam() {
    var param = {
        aid: $('#hidAid').val()
    };
    return param;
}

function add() {
    window.location.href = "addArticleItem.html?aid=" + $('#hidAid').val();
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
    data.aid = $('#hidAid').val();
    $.showConfirm('', '', function () {
        $.ajax({
            url: '/media/deleteArticleItemInfo',
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

function articleItemDetail() {
    var id = getCurrent();
    if (id) {
        window.location.href = "addArticleItem.html?aid=" + $('#hidAid').val() + "&id=" + id;
        return;
    }
    $.showToast("请选择要编辑的数据")
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