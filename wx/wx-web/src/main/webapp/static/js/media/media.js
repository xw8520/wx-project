$(function () {
    pager.initPager('/media/getMediaList');
    pager.loadData();
    $('#btnSearch').click(function () {
        pager.loadData();
    });

    $('#txtFile').change(function () {
        $('#txtFileName').val($(this).val());
    });

    $("#btnUpload").on("click", function () {

        $("#fromUpload").ajaxSubmit({
            url: "/file/upload",
            type: "POST",
            timeout: 60000,
            beforeSubmit:function(){
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
                    $.showToast(resp.errorMsg)
                }
                $.hideLoading(0);
            }
        });

    })
});

function getParam() {
    var param = {};
    if ($('#txtName').val() != null && $('#txtName').val() != '') {
        param.name = $('#txtName').val();
    }
    return param;
}

function add() {
    $.showModel();
}

function del() {

}

function mediaDetail() {

}