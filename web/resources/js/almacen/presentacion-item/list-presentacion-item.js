$(document).ready(function () {
    $('#dataTablePI').DataTable();
    
    var token = localStorage.getItem("token");
    
    $('.showInfoPI').click(function() {
        var id = $(this).attr("pi-id");
        showInfoModal(id);
    });
    
    $('.showModalDeactivatePI').click(function() {
        var id = $(this).attr("pi-id");
        showconfirmarEliminarModal(id);
    });
    
    $('#deactivatePI').click(function() {
        var id = $(this).attr("pi-id");
        deactivatePI(id);
    });
    
    function showInfoModal(id) {
        $('#exampleModalCenter').modal('show');
        $.ajax({
            method: 'GET',
            url: 'crud.do',
            data: {
                'action': 'read',
                'id': id
            },
            dataType: 'json'
        }).done(function (json) {
            console.log(json);
            var pi = json.presentacion_item;
            if (pi.imagen != null) {
                $('#pi-img').attr('src', la_granja_api_path + "/presentacion_item/get-image/" + pi.imagen);
                $('#valor-temp').html(JSON.stringify(pi));
            }
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    function showconfirmarEliminarModal(id, nombre) {
        $('#confirmarEliminarModal').modal('show');
        $('#pi-id').html(id);
        $('#pi-nombre').html(nombre);
        $('#deactivatePI').attr('pi-id', id);
    }

    function deactivatePI(id) {
        $.ajax({
            method: "POST",
            url: 'crud.do',
            data: {
                'action': 'deactivate',
                'presentacion_item_id': id,
            },
            dataType: 'json'
        }).done(function (json) {
            console.log(json);
            location.reload();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }
});