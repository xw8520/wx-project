/**
 * Created by Admin on 2016/4/5.
 */
$(function () {
    $('.navbar-left ul li a ').click(function () {
        $('.navbar-left ul li a').removeClass("actived");
        $(this).addClass('actived');
        var url = $(this).attr('url');
        if (url != '' && url != null) {
            $('#frameMain').attr('src', url);
        }
        //子菜单
        var child = $(this).next('ul');
        if ($('.actived i').hasClass('transform-90')) {
            $('.actived i').removeClass('transform-90');
            if (child.length > 0) {
                child.slideUp(300);
            }
        } else {
            $('.actived i').addClass('transform-90');
            if (child.length > 0) {
                child.slideDown(300);
            }
        }
    });
});