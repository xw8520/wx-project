/**
 * Created by admin on 2016/11/27.
 */

function add() {
    var data = {
        id: $('#id').val(),
        mediaId: $('#mediaId').val(),
        author: $('#author').val(),
        title: $('#title').val(),
        contentUrl: $('#contentUrl').val(),
        content: $('#content').val(),
        digest: $('#digest').val(),
        showCover: $('#showCover').is(":checked"),
        articleId: $('#articleId').val()
    };
    if (data.title == '') {
        $.showToast("标题不能为空");
        return
    }
    if (data.mediaId == '') {
        $.showToast("素材id不能为空");
        return
    }
    if (data.content == '') {
        $.showToast("内容不能为空");
        return
    }
    $.ajax({
        url: '/media/addArticleItemInfo',
        type: 'POST',
        data: data,
        dataType: 'json',
        success: function (resp) {
            if (resp.success) {
                $.showToast('保存成功');
                window.location.href = "articleItem.html?aid=" + data.articleId;
            } else {
                $.showToast(resp.info)
            }
        },
        error: function (resp) {
            $.showToast('系统出错，请稍后再试');
        }
    })
}

function cancel() {
    window.location.href = "articleItem.html?aid=" + $('#articleId').val();
}