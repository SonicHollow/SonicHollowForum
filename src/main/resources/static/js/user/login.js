$(function () {
    // Bind the click event to the picture of the verification code
    $("#code_img").click(function () {
        //There is a 'this' object in the 'function' function of the event response. This 'this' object is the dom object that is currently responding to the event
        //SRC attribute indicates the picture path of verification code img label.It is readable and writable
        //alert(this.src);
        this.src = "loginimage?d=" + Math.random();
    });

    //Click event to register binding
    $("#signin").click(function () {
        //Authentication username: it must be composed of letters, numbers and underscores, and the length is 5 to 12 digits
        //1 get the content in the username input box
        var usernameText = $("#username").val();
        //2 create regular expression object
        var usernamePatt = /^\w{5,12}$/;
        if (!usernamePatt.test(usernameText)) {
            //3 prompt user for results
            $("span.errorMsg").text("Illegal Username!");
            return false;
        }


        //Verification password: it must be composed of letters, numbers and underscores, and the length is 5 to 12 digits
        //1 get the content in the username input box
        var passwordText = $("#password").val();
        //2 create regular expression object
        var passwordPatt = /^\w{5,12}$/;
        if (!passwordPatt.test(passwordText)) {
            //3 prompt user for results
            $("span.errorMsg").text("Illegal Password!");
            return false;
        }

        var codeText = $("#code").val();

        //Remove the space before and after the verification code
        // alert("Before deleting the space：["+codeText+"]")
        codeText = $.trim(codeText);
        // alert("After deleting the space：["+codeText+"]")

        if (codeText == null || codeText === "") {
            //4 Prompt user
            $("span.errorMsg").text("Fill in the validation code!");
            return false;
        }
        // Remove error messages
        $("span.errorMsg").text("");
    });
});
