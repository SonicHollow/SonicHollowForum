$(function () {
    // 给验证码的图片，绑定单击事件
    $("#code_img").click(function () {
        this.src = "loginimage?d=" + Math.random();
    });

    $("#signin").click(function () {
        var usernameText = $("#username").val();
        var usernamePatt = /^\w{5,12}$/;
        if (!usernamePatt.test(usernameText)) {
            $("span.errorMsg").text("Illegal Username!");
            return false;
        }
        var passwordText = $("#password").val();
        var passwordPatt = /^\w{5,12}$/;
        if (!passwordPatt.test(passwordText)) {
            $("span.errorMsg").text("Illegal Password!");
            return false;
        }

        var codeText = $("#code").val();

        //去掉验证码前后空格
        // alert("去空格前：["+codeText+"]")
        codeText = $.trim(codeText);
        // alert("去空格后：["+codeText+"]")

        if (codeText == null || codeText === "") {
            //4 提示用户
            $("span.errorMsg").text("Fill in the validation code!");
            return false;
        }
        // 去掉错误信息
        $("span.errorMsg").text("");
    });
});