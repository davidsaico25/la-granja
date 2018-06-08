$(document).ready(function () {
    var dataTablePI = $('#dataTablePI').DataTable({
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        }
    });

    var dataTableAHPI = $('#dataTableAHPI').DataTable({
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json",
        },
        data: JSON.parse(localStorage.getItem("listAHPI")),
        columns: [
            {data: 'nombre'}
        ]
    });

    //colocar el json de localStorage en el form para registrar solicitud de abastecimiento
    $('#jsonListAHPI').val(localStorage.getItem("listAHPI"));

    //Registrar la solicitud de abastecimiento
    $("#formCRUD").submit(function (event) {
        $('#btnCRUDAbastecimiento').attr('disabled', '');
        $('#loading').show();
        return true;
    });

    //Agregar presentacion item a la solicitud de abastecimiento
    $("#formAddPI").submit(function (event) {
        if (localStorage.getItem("listAHPI") === null) {
            var listAHPI = [];
        } else {
            var listAHPI = JSON.parse(localStorage.getItem("listAHPI"));
        }
        var pi = JSON.parse($('#json-pi').val());
        listAHPI.push(pi);
        
        localStorage.setItem("listAHPI", JSON.stringify(listAHPI));
        $('#jsonListAHPI').val(localStorage.getItem("listAHPI"));
        
        $('#addPIModal').modal('hide');
        
        $("[pi-id='2']").attr('isListed', 'true');
        addPIdataTableAHPI(pi);
        
        event.preventDefault();
    });

    //Cuando se da click en el boton para que muestre modal e ingresar la cantidad del item a solicitar
    $('.showModalAddPI').click(function () {
        var id = $(this).attr("pi-id");
        if ($(this).attr("isListed") === 'true') {
            alert('el item ya fue agregado');
        } else {
            showAddModal(id);
        }
    });

    //agregar dinamicamente una fila a la tabla de detalle de abastecimiento
    function addPIdataTableAHPI(pi) {
        dataTableAHPI.row.add(pi).draw();
    }

    //mostrar modal para ingresar la cantidad del item a solicitar
    function showAddModal(id) {
        $('#addPIModal').modal('show');
        $.ajax({
            method: 'GET',
            url: 'crud.do',
            data: {
                'action': 'read-pi',
                'id': id
            },
            dataType: 'json'
        }).done(function (json) {
            console.log(json);
            var pi = json.presentacion_item;
            $('#pi-img').attr('src', URLRest + "presentacion_item/get-image/" + pi.imagen);
            $('#id-pi').val(pi.id);
            $('#json-pi').val(JSON.stringify(pi));
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }
});