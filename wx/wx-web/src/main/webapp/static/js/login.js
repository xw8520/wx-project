/**
 * Created by Admin on 2016/4/4.
 */
$(function(){
    $('#btnLogin').click(function(){
        var account=$('#txtAccount').val();
        var password=$('#txtPassword').val();
        if(account==''||password==''){
            Tools.showToast("请输入账号和密码");
            return;
        }
        $.ajax({
            url:'/home/login.action',
            type:'POST',
            data:{account:account,password:password},
            dataType:'json',
            success:function(resp){
                if(resp.success){
                    Tools.showToast("登录成功",function(){
                        window.location.href="main/index.html";
                    });
                }else{
                    Tools.showToast(resp.msg);
                }
            },
            error:function(resp){
                Tools.showToast("登录失败,请稍后再试");
            }
        })
    });
});