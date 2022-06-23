$("#loginButton").click(function () {
    let url = "loginKontrol";
    let email = $("#email").val();
    let password = $("#password").val();
    if (email && password) {
        $.ajax({
            type: "POST",
            url: url,
            data: {
                email: email,
                password: password
            },
            success: function (response) {
                alert("Login Successful!")
                window.location.href = "welcome";
            },
            dataType: "json"
        });
    } else {
        alert("Login Failed!")
    }
});