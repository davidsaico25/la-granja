$(document).ready(function () {
    /*
     * INICIO CARGAR_VISTA
     */

    var dataTableA = $('#dataTableA').DataTable({
        "language": {
            "loadingRecords": "Please wait - loading..."
        },
        columns: [
            {data: 'id'},
            {data: 'fecha_creacion'},
            {data: 'local_destino.nombre'},
            {data: 'estado_abastecimiento.nombre'},
            {
                targets: -1,
                data: null,
                width: "10%",
                orderable: false,
                defaultContent: "<button class='btn btn-link edit' style='padding: 0px'><i class='fa fa-fw fa-eye'></i></button>"
            }
        ]
    });

    var dataTableAHI = $('#dataTableAHI').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        columnDefs: [
            {width: "20%", targets: -2},
            {width: "20%", targets: -1, orderable: false}
        ],
        columns: [
            {data: 'item.marca_item.nombre'},
            {data: 'item.nombre'},
            {data: 'cantidad'},
            {data: 'item.unidad_medida.simbolo'}
        ],
        info: false,
        lengthChange: false,
        searching: false
    });

    cargarDataTableA();

    //setInterval(cargarDataTableA, 5000);

    /*
     * INICIO CARGAR_VISTA
     */

    function cargarDataTableA() {
        $.ajax({
            method: 'POST',
            url: 'gestionar.do',
            data: {
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            console.log(json);
            console.log(json.listAbastecimiento);
            dataTableA.clear().draw();
            dataTableA.rows.add(json.listAbastecimiento).draw();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        }).always(function () {
        });
    }

    $('#dataTableA tbody').on('click', 'button.edit', function () {
        dataTableA.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        let abastecimiento = dataTableA.row($(this).parents('tr')).data();
        console.log(abastecimiento);

        verDetalle(abastecimiento);
    });

    function verDetalle(abastecimiento) {
        $.ajax({
            method: 'GET',
            url: 'gestionar.do',
            data: {
                action: 'read',
                id: abastecimiento.id
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            console.log(json);
            dataTableAHI.clear().draw();
            dataTableAHI.rows.add(json.abastecimiento.listAbastecimientoHasItem).draw();
            $('html,body').animate({
                scrollTop: $("#detalle").offset().top
            }, 2000);
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        }).always(function () {
        });
    }
});