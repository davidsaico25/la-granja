var URLorigin = window.location.origin;

if (localStorage.getItem("token") === null) {
    //no existe token en al menos uno: localstorage o cookie
    localStorage.removeItem("identity");
    localStorage.removeItem("token");
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