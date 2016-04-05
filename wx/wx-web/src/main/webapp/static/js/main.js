/**
 * Created by Admin on 2016/4/5.
 */
$(function(){
    $('.menu-body li').click(function(){
        $('.menu-body li').removeClass('active');
        $(this).addClass('active')
    });

    $('.top-menu li').click(function(){
        $('.top-menu li').removeClass('top-menu-sel');
        $(this).addClass('top-menu-sel')
    });
});