$(function () {
    $("#loginbtn").click(function () {
        var loginName = $("#loginName");
        var password = $("#password");
        var msg = "";
        if (loginName.val() == "") {
            msg = "登录名不能为空!";
            loginName.focus();
        } else if (password.val() == "") {
            msg = "密码不能为空!";
            password.focus();
        }
        if (msg != "") {
            alert(msg);
        } else {
            $("#loginform").submit();
        }
    });
    $('#registerbtn').click(function(){
        alert('no impl!');
    });

    $("#addNewPrj").click(function(){
        var prj = $("[name='prjName']");
        var path = $("[name='remotepath']");
        var msg = "";
        if(prj.val() == ""){
            msg = "工程名不能为空";
        }else if(path.val() == ""){
            msg = "工程路径不能为空";
        }
        if(msg != ""){
            alert(msg);
        }else{
           // $("#prjForm").submit();
        }

    })
})