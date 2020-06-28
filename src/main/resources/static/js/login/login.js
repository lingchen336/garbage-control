
$(function () {
    $("#ajaxForm").submit(function () {
        $(this).ajaxSubmit(function (data) {
            if(data){
                window.location="/user/index";
            }else{
                sweetAlert(
                    '错误...',
                    '账号或密码错误!',
                    'error'
                )
            }
        });
        return false;
    })
});

