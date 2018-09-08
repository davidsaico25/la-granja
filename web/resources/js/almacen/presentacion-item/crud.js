$(document).ready(function () {
    if ($.getURLParam("item_id") !== null) {
        $('#imageSection').attr('hidden', 'hidden');
    } else if ($.getURLParam("id") !== null) {
        $('#imageSection').removeAttr('hidden');
    }

    $("#formCRUD").submit(function (event) {
        $('#btnCreatePresentacionItem').attr('disabled', 'disabled');
        $('#loading').show();

        if ($.getURLParam("item_id") !== null)
            crearPresentacionItem($.getURLParam("item_id"));
        if ($.getURLParam("id") !== null)
            actualizarPresentacionItem($.getURLParam("id"));
        return false;
    });

    if ($.getURLParam("id") !== null)
        getPresentacionItem($.getURLParam("id"));

    function crearPresentacionItem(item_id) {
        $.ajax({
            method: "POST",
            url: la_granja_api_url + '/presentacion_item/create',
            headers: {
                Authorization: localStorage.getItem('token')
            },
            data: {
                codigo_barra: $('#codigo_barra').val(),
                nombre: $('#nombre').val(),
                rendimiento: $('#rendimiento').val(),
                precio_costo: $('#precio_costo').val(),
                item_id: item_id
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            console.log(json);
            let pi = json.presentacion_item;
            $(location).attr('href', '/la-granja/almacen/presentacion-item/crud.jsp?id=' + pi.id);
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    function getPresentacionItem(id) {
        $.ajax({
            method: "GET",
            url: la_granja_api_url + '/presentacion_item/get/' + id,
            headers: {
                Authorization: localStorage.getItem('token')
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            console.log(json);
            let pi = json.presentacion_item;
            $('#codigo_barra').attr('disabled', 'disabled');
            $('#codigo_barra').val(pi.codigo_barra);
            $('#nombre').val(pi.nombre);
            $('#rendimiento').val(pi.rendimiento);
            $('#precio_costo').val(pi.precio_costo);
            $('#img_presentacion_item').attr('src', la_granja_api_url + '/presentacion_item/get_image/' + pi.imagen);
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    function actualizarPresentacionItem(id) {
        console.log(id);
    }
});