var URLorigin = window.location.origin;

if (localStorage.getItem("token") === null || comprobarCookie("token") === false) {
    //no existe token en al menos uno: localstorage o cookie
    localStorage.removeItem("identity");
    localStorage.removeItem("token");
    delete_cookie("identity");
    delete_cookie("token");
    if (location.href !== URLorigin + "/la-granja/login.jsp") {
        window.location.replace(URLorigin + "/la-granja/login.jsp");
    }
} else {
    //existe token en ambos: localstorage y cookie
    if (location.href === URLorigin + "/la-granja/login.jsp" || location.href === URLorigin + "/la-granja/") {
        window.location.replace(URLorigin + "/la-granja/dashboard.jsp");
    }
    var identity = JSON.parse(localStorage.getItem("identity"));
}

/*
 if (localStorage.getItem("token") === null && location.href !== URLorigin + "/la-granja/login.jsp") {
 window.location.replace(URLorigin + "/la-granja/login.jsp");
 } else if (localStorage.getItem("token") !== null) {
 if (location.href === URLorigin + "/la-granja/login.jsp" || location.href === URLorigin + "/la-granja/") {
 window.location.replace(URLorigin + "/la-granja/dashboard.jsp");
 }
 var identity = JSON.parse(localStorage.getItem("identity"));
 }
 */

function comprobarCookie(key) {
    var clave = obtenerCookie(key);
    if (clave != "") {
        return true;
    } else {
        return false;
    }
}

function obtenerCookie(clave) {
    var name = clave + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1);
        if (c.indexOf(name) == 0)
            return c.substring(name.length, c.length);
    }
    return "";
}

function delete_cookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}