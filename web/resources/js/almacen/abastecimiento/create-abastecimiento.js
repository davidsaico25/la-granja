$(document).ready(function () {
    /*
     * INICIO CARGAR_VISTA
     */

    var dataTableI = $('#dataTableI').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        columns: [
            {data: 'id', width: "10%"},
            {data: 'marca_item.nombre'},
            {data: 'nombre'},
            {data: 'unidad_medida.simbolo', width: "15%"},
            {
                targets: -1,
                data: null,
                width: "10%",
                orderable: false,
                createdCell: function (cell, cellData, rowData, rowIndex, colIndex) {
                    $(cell).children('button').attr('i-id', cellData.id);
                },
                defaultContent: "<button class='btn btn-link add' style='padding: 0px'><i class='fa fa-fw fa-plus'></i></button>"
            }
        ],
        info: false,
        lengthChange: false
    });

    var dataTableAHI = $('#dataTableAHI').DataTable({
        language: {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        data: JSON.parse(localStorage.getItem("listAHI")),
        columns: [
            {data: 'item.marca_item.nombre'},
            {data: 'item.nombre'},
            {
                data: null,
                createdCell: function (cell, cellData, rowData, rowIndex, colIndex) {
                    $(cell).html(cellData.cantidad + ' ' + cellData.item.unidad_medida.simbolo);
                }
            },
            {
                data: null,
                width: "10%",
                orderable: false,
                defaultContent: "<button class='btn btn-link edit' style='padding: 0px'><i class='fa fa-fw fa-edit'></i></button><button class='btn btn-link trash-o' style='padding: 0px'><i class='fa fa-fw fa-trash-o'></i></button>"
            }
        ],
        info: false,
        lengthChange: false,
        searching: false
    });

    cargardataTableI();

    /*
     * FIN CARGAR_VISTA
     */

    function cargardataTableI() {
        $.ajax({
            method: "GET",
            url: la_granja_api_url + '/item/get_list',
            headers: {
                Authorization: localStorage.getItem('token'),
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            //console.log(json);
            let listItem = json.listItem;
            dataTableI.rows.add(listItem).draw();
            deshabilitarBotonesAddItems();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    function deshabilitarBotonesAddItems() {
        $('table button').removeAttr('disabled');
        //disabled buttons de items ya agregados a lista solicitud
        listAHI = JSON.parse(localStorage.getItem("listAHI"));
        $.each(listAHI, function (index, value) {
            $("[i-id='" + value.item.id + "']").attr('disabled', 'disabled');
        });
    }

    $('#dataTableI tbody').on('click', 'button.add', function () {
        dataTableI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        let ahi = {
            item: dataTableI.row($(this).parents('tr')).data(),
            cantidad: 0
        };

        showModalAddItem(ahi, true);
    });

    //lista solicitud al hacer click en una fila para modificar o quitar el item
    $('#dataTableAHI tbody').on('click', 'button.edit', function () {
        dataTableAHI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        showModalAddItem(dataTableAHI.row($(this).parents('tr')).data(), false);
    });

    $('#dataTableAHI tbody').on('click', 'button.trash-o', function () {
        dataTableAHI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        let ahi = dataTableAHI.row($(this).parents('tr')).data();

        let listAHI = JSON.parse(localStorage.getItem("listAHI"));
        $.each(listAHI, function (index, value) {
            if (value.item.id == ahi.item.id) {
                listAHI.splice(index, 1);
                return false;
            }
        });
        localStorage.setItem("listAHI", JSON.stringify(listAHI));

        dataTableAHI.row('.selected').remove().draw();

        $("[i-id='" + ahi.item.id + "']").removeAttr('disabled');
    });

    //mostrar modal para ingresar la cantidad del item a solicitar
    function showModalAddItem(data, add = true) {
        $('#i_id').val(data.item.id);
        $('#unidad_medida_simbolo').html(data.item.unidad_medida.simbolo);
        $('#i_json').val(JSON.stringify(data.item));
        $('#ahi_cantidad').val(data.cantidad);
        //$('#btnAddAHI').attr('hidden', 'hidden');
        //$('#btnEditAHI').removeAttr('hidden');
        if (add) {
            $('#btnAddAHI').removeAttr('hidden');
            $('#btnEditAHI').attr('hidden', 'hidden');
        } else {
            $('#btnAddAHI').attr('hidden', 'hidden');
            $('#btnEditAHI').removeAttr('hidden');
        }
        $('#addItemModal').modal('show');
    }

    //Agregar item a la solicitud de abastecimiento
    $("#btnAddAHI").click(function () {
        let listAHI = [];

        if (localStorage.getItem("listAHI") !== null)
            listAHI = JSON.parse(localStorage.getItem("listAHI"));

        let ahi = {
            item: JSON.parse($('#i_json').val()),
            cantidad: $('#ahi_cantidad').val()
        };
        listAHI.push(ahi);
        localStorage.setItem("listAHI", JSON.stringify(listAHI));

        $("[i-id='" + ahi.item.id + "']").attr('disabled', 'disabled');

        dataTableAHI.row.add(ahi).draw();

        dataTableI.$('tr.selected').removeClass('selected');

        $('#addItemModal').modal('hide');

        $('html,body').animate({
            scrollTop: $("#solicitudAbastecimiento").offset().top
        }, 2000);
    });

    $("#btnEditAHI").click(function () {
        id = $('#i_id').val();
        cantidad = $('#ahi_cantidad').val();

        let listAHI = JSON.parse(localStorage.getItem("listAHI"));
        $.each(listAHI, function (index, value) {
            if (value.item.id == id) {
                value.cantidad = cantidad;
                dataTableAHI.row('.selected').data(value).draw();
                return false;
            }
        });
        localStorage.setItem("listAHI", JSON.stringify(listAHI));

        dataTableAHI.$('tr.selected').removeClass('selected');

        $('#addItemModal').modal('hide');
    });

    $('#formCreateAbastecimiento').submit(function (event) {
        crearAbastecimiento();
        return false;
    });

    function crearAbastecimiento() {
        if (dataTableAHI.data().length == 0) {
            alert('tiene que seleccionar items para registrar la solicitud');
            return;
        }
        let local_id_destino = JSON.parse(localStorage.getItem("identity")).local.id;

        $.ajax({
            method: 'POST',
            url: la_granja_api_url + '/abastecimiento/create',
            headers: {
                Authorization: localStorage.getItem('token'),
            },
            data: {
                observacion: $('#observacion').val(),
                estado_abastecimiento_id: 1,
                local_id_origen: 1,
                local_id_destino: local_id_destino,
                json_abastecimiento_has_item: localStorage.getItem("listAHI")
            },
            dataType: 'json',
            beforeSend: function () {
                $('table button').attr('disabled', 'disabled');
                $('#btnCreateAbastecimiento').attr('disabled', 'disabled');
                $('#loading').show();
            }
        }).done(function (json) {
            //console.log(json);
            let abastecimiento = json.abastecimiento;

            $('#observacion').val('');
            dataTableAHI.clear().draw();
            localStorage.removeItem("listAHI");

            $(location).attr('href', '/la-granja/almacen/abastecimiento/leer.jsp?id=' + abastecimiento.id);
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        }).always(function () {
            deshabilitarBotonesAddItems();
            $('#btnCreateAbastecimiento').removeAttr('disabled');
            $('#loading').hide();
        });
    }

    //Registrar la solicitud de abastecimiento
    $("#btnCreateAbastecimiento").click(function () {

    });
});