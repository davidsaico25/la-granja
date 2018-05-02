//var URLrest = "http://localhost:3000/api/";
var token = localStorage.getItem("token");

function showInfoModal(id) {
    $('#exampleModalCenter').modal('show');
    $.ajax({
        method: "GET",
        url: URLRest + "presentacion_item/get/" + id,
        headers: {
            'Authorization': token
        },
        dataType: 'json'
    }).done(function (json) {
        //console.log(json);
        var pi = json.presentacion_item;
        if (pi.imagen != null) {
            $('#pi-img').attr('src', URLRest + "presentacion_item/get-image/" + pi.imagen);
        }
    }).fail(function (jqXHR) {
        console.log(jqXHR);
    });
}

function showconfirmarEliminarModal(id, nombre) {
    $('#confirmarEliminarModal').modal('show');
    $('#pi-id').html(id);
    $('#pi-nombre').html(nombre);

    $.ajax({
        method: "POST",
        url: 'http://localhost:8085/la-granja/almacen/crudPresentacionItem.do',
        headers: {
            'Authorization': token
        },
        data: {
            'action': 'deactivate',
            'presentacion_item_id': id,
        },
        dataType: 'json'
    }).done(function (json) {
        console.log(json);
    }).fail(function (jqXHR) {
        console.log(jqXHR);
    });
}