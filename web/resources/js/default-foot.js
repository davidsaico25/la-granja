$(document).ready(function () {
    $('.btnLogout').click(function() {
        localStorage.removeItem("identity");
        localStorage.removeItem("token");
        $(location).attr('href', '/la-granja/login.jsp');
    });
    
    showIdentityData();
    function showIdentityData() {
        //var URLrest = "http://192.168.1.43:3000/api/";
        //$('.image-identity').attr("src", URLrest + "usuario/get-image/" + identity.image);
        $("#nombre-identity").text(identity.persona.nombre + " " + identity.persona.apellidop);
        //$(".email-identity").text(identity.email);
    }
});