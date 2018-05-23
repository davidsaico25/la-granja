$(document).ready(function () {
    var token = localStorage.getItem("token");
    
    $("#formCRUD").submit(function (event) {
        $('#btnCRUDAbastecimiento').attr('disabled', '');
        $('#loading').show();
        return true;
    });
    
    $('.showModalAddPI').click(function() {
        var id = $(this).attr("pi-id");
        showAddModal(id);
    });
    
    function showAddModal(id) {
        $('#addPIModal').modal('show');
        $.ajax({
            method: "GET",
            url: URLRest + "presentacion_item/get/" + id,
            headers: {
                'Authorization': token
            },
            dataType: 'json'
        }).done(function (json) {
            console.log(json);
            var pi = json.presentacion_item;
            if (pi.imagen != null) {
                $('#pi-img').attr('src', URLRest + "presentacion_item/get-image/" + pi.imagen);
            }
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }
});