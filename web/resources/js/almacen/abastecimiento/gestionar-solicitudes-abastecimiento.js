$(document).ready(function () {
    /*
     * INICIO CARGAR_VISTA
     */

    var dataTableA = $('#dataTableA').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        columnDefs: [
        ],
        columns: [
            {data: 'fecha_creacion'},
            {data: 'local_destino.nombre'},
            {data: 'estado_abastecimiento.nombre'}
        ],
        info: false
    });

    var dataTableAHI = $('#dataTableAHI').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        columnDefs: [
            {targets: -2, width: "20%"},
            {targets: -1, width: "20%", orderable: false}
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
            method: 'GET',
            url: la_granja_api_url + '/abastecimiento/get_list_by_estado',
            headers: {
                Authorization: localStorage.getItem("token")
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            console.log(json);
            dataTableA.clear().draw();
            dataTableA.rows.add(json.listAbastecimiento).draw();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
            //console.log('status: ' + jqXHR.status + '; statusText: ' + jqXHR.statusText + '; message: ' + jqXHR.responseJSON.message);
        }).always(function () {
        });
    }

    $('#dataTableA tbody').on('click', 'tr', function () {
        let abastecimiento = dataTableA.row($(this)).data();

        if (abastecimiento !== undefined) {
            dataTableA.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');

            dataTableAHI.clear().draw();
            dataTableAHI.rows.add(abastecimiento.listAbastecimientoHasItem).draw();

            $('#selectEstadoAbastecimiento').val(abastecimiento.estado_abastecimiento_id);

            $('html,body').animate({
                scrollTop: $("#detalle").offset().top
            }, 1000);
        }
    });

    $('#btnUpdateA').click(function () {
        let abastecimiento = dataTableA.row('tr.selected').data();

        let selectedEstadoAbastecimiento = $('#selectEstadoAbastecimiento').val();

        if (selectedEstadoAbastecimiento == 3) { //Confirmar abastecimiento
            console.log(JSON.stringify(abastecimiento.listAbastecimientoHasItem));
            //confirmarAbastecimiento(selectedEstadoAbastecimiento);
        } else {
            updateAbastecimiento(abastecimiento, selectedEstadoAbastecimiento);
        }
    });

    function confirmarAbastecimiento(estadoAbastecimiento) {
        $.ajax({
            method: 'PUT',
            url: la_granja_api_url + '/abastecimiento/confirmar/' + abastecimiento.id,
            headers: {
                Authorization: localStorage.getItem("token")
            },
            data: {
                observacion: abastecimiento.observacion,
                estado_abastecimiento_id: estadoAbastecimiento,
                local_id_origen: abastecimiento.local_id_origen,
                local_id_destino: abastecimiento.local_id_destino
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            console.log(json);
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        }).always(function () {
        });

        dataTableAHI.clear().draw();
        $('#selectEstadoAbastecimiento').val(99);
        cargarDataTableA();
    }

    function updateAbastecimiento(abastecimiento, estadoAbastecimiento) {
        $.ajax({
            method: 'PUT',
            url: la_granja_api_url + '/abastecimiento/update/' + abastecimiento.id,
            headers: {
                Authorization: localStorage.getItem("token")
            },
            data: {
                observacion: abastecimiento.observacion,
                estado_abastecimiento_id: estadoAbastecimiento,
                local_id_origen: abastecimiento.local_id_origen,
                local_id_destino: abastecimiento.local_id_destino
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            console.log(json);
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        }).always(function () {
        });

        dataTableAHI.clear().draw();
        $('#selectEstadoAbastecimiento').val(99);
        cargarDataTableA();
    }
});