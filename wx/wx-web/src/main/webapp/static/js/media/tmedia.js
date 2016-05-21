$(function () {
    pager.initPager('/media/getTMeidaList');
    pager.loadData();
    $('#btnSearch').click(function () {
        pager.loadData();
    });
});

function getParam() {
    var param = {};
    if ($('#txtName').val() != null && $('#txtName').val() != '') {
        param.name = $('#txtName').val();
    }
    return param;
}