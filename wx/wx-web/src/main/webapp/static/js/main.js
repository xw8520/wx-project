/**
 * Created by Admin on 2016/4/5.
 */
$(function () {
    $('.navbar-left ul li a ').click(function () {
        $('.navbar-left ul li a').removeClass("actived");
        $(this).addClass('actived');
        if($('.actived i').hasClass('transform-90')){
            $('.actived i').removeClass('transform-90')
        }else{
            $('.actived i').addClass('transform-90')
        }
    })
});