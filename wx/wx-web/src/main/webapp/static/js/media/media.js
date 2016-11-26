$(function () {
    pager.initPager('/media/getMediaList');
    pager.loadData();
    $('#btnSearch').click(function () {
        pager.loadData();
    });
    initSelect();
    $('#txtFile').change(function () {
        $('#txtFileName').val($(this).val());
    });

    $("#btnUpload").on("click", function () {

        $("#fromUpload").ajaxSubmit({
            url: "/file/upload",
            type: "POST",
            timeout: 60000,
            beforeSubmit: function () {
                var fileName = $('#txtFileName').val();
                if (fileName == '') return false;
                fileName = fileName.substr(fileName.lastIndexOf('.') + 1);
                var reg = /^(bmp|png|jpeg|jpg|gif|mp3|wma|wav|amr|AMR|MP3)$/;
                if (!reg.test(fileName)) {
                    $.showToast('文件格式有误');
                    return false;
                }
                $.showLoading("正在上传...");
                return true;
            },
            beforeSend: function (data) {

            },
            success: function (resp) {
                if (resp.success) {
                    $('#hidFile').val(resp.data);
                    $.showToast('上传成功');
                } else {
                    $.showToast(resp.info)
                }
                $.hideLoading(0);
            }
        });

    })

    $('#btnAdd').click(function () {
        var data = {
            title: $('#txtTitle').val(),
            remark: $('#txtRemark').val(),
            permanent: $('#txtPermanent').val(),
            accountid: $('#txtAccount').val(),
            filename: $('#hidFile').val()
        };
        if (data.title == '') {
            $.showToast('标题不能为空')
            return
        }
        if (data.filename == '' || data.filename == null) {
            $.showToast('请上传文件')
            return
        }
        $.ajax({
            url: '/media/addMedia',
            type: 'POST',
            data: data,
            dataType: 'json',
            success: function (resp) {
                if (resp.success) {
                    $.showToast('保存成功');
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
    if ($('#txtName').val() != null && $('#txtName').val() != '') {
        param.name = $('#txtName').val();
    }
    if ($('#selAccount').val() != null && $('#selAccount').val() != '') {
        param.account = $('#selAccount').val();
    }
    if ($('#selPermanent').val() != null && $('#selPermanent').val() != '') {
        param.permanent = $('#selPermanent').val();
    }
    if ($('#selType').val() != null && $('#selType').val() != '') {
        param.type = $('#selType').val();
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
    $.showConfirm('','',function(){
        $.ajax({
            url: '/media/deleteMedia',
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

function mediaDetail() {

}