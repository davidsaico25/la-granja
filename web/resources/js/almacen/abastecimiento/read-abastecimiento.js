$(document).ready(function () {
    /*
     * INICIO CARGAR_VISTA
     */

    var dataTableAHI = $('#dataTableAHI').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        columnDefs: [
            {width: "20%", targets: -2},
            {width: "10%", targets: -1, orderable: false}
        ],
        columns: [
            {data: 'item.marca_item.nombre'},
            {data: 'item.nombre'},
            {data: 'cantidad'},
            {data: 'item.unidad_medida.simbolo'}
        ],
        "info": false,
        "scrollX": true,
        "lengthChange": false,
        "searching": true,
        scrollY: '50vh',
        scrollCollapse: true,
        paging: false
    });

    readAbastecimiento();

    /*
     * FIN CARGAR_VISTA
     */

    function readAbastecimiento() {
        $.ajax({
            method: "GET",
            url: la_granja_api_url + '/abastecimiento/get/' + $.getURLParam("id"),
            headers: {
                Authorization: localStorage.getItem('token'),
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            //console.log(json);
            let abastecimiento = json.abastecimiento;
            let listAbastecimientoHasItem = abastecimiento.listAbastecimientoHasItem;
            $('#id').val(abastecimiento.id);
            $('#fecha_creacion').val(abastecimiento.fecha_creacion);
            $('#fecha_recepcion').val(abastecimiento.fecha_recepcion);
            $('#local_origen').val(abastecimiento.local_origen.nombre);
            $('#local_destino').val(abastecimiento.local_destino.nombre);
            $('#estado_abastecimiento').val(abastecimiento.estado_abastecimiento.nombre + ' (' + abastecimiento.estado_abastecimiento.simbolo + ')');
            $('#observacion').val(abastecimiento.observacion);
            dataTableAHI.rows.add(listAbastecimientoHasItem).draw();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }
});