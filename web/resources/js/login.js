$(document).ready(function () {
    $('#formLogIn').submit(function (event) {
        login();
        event.preventDefault();
    });

    function login() {
        $.ajax({
            method: "POST",
            url: "usuario.do",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: {
                'action': 'login',
                'username': $('#username').val(),
                'password': $('#password').val(),
                'getToken': true
            },
            dataType: 'json',
            beforeSend: function () {
                $('#btnLogin').attr('disabled', '');
                $('#loading').show();
            }
        }).done(function (json) {
            console.log(json);
            if (json.token) {
                localStorage.setItem("token", JSON.stringify(json.token));
                identity();
            } else {
                alert(JSON.stringify(json));
                $('#btnLogin').removeAttr('disabled');
                $('#loading').hide();
            }
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    function identity() {
        $.ajax({
            method: "POST",
            url: "usuario.do",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: {
                'action': 'identity',
                'username': $('#username').val(),
                'password': $('#password').val(),
                'getToken': null
            },
            dataType: 'json'
        }).done(function (json) {
            console.log(json);
            if (json.usuario) {
                localStorage.setItem("identity", JSON.stringify(json.usuario));
                $(location).attr('href', '/la-granja/dashboard.jsp');
            } else {
                alert(json);
            }
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        }).always(function () {
            if (localStorage.getItem("identity") == null) {
                $('#btnLogin').removeAttr('disabled');
                $('#loading').hide();
            }
        });
    }
});