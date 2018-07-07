$(document).ready(function () {
    /*
     * INICIO CARGAR_VISTA
     */

    var dataTableI = $('#dataTableI').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        }
    });

    var dataTableAHI = $('#dataTableAHI').DataTable({
        language: {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        data: JSON.parse(localStorage.getItem("listAHI")),
        columns: [
            {data: 'item.marca_item.nombre'},
            {data: 'item.nombre'},
            {data: 'cantidad'},
            {data: 'item.unidad_medida.simbolo', width: "15%"},
            {
                targets: -1,
                data: null,
                width: "10%",
                defaultContent: "<button class='btn btn-link edit' style='padding: 0px'><i class='fa fa-fw fa-edit'></i></button><button class='btn btn-link trash-o' style='padding: 0px'><i class='fa fa-fw fa-trash-o'></i></button>"
            }
        ]
    });

    //lista solicitud al hacer click en una fila para modificar o quitar el item
    $('#dataTableAHI tbody').on('click', 'button.edit', function () {
        dataTableAHI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        showAddItemModal(dataTableAHI.row($(this).parents('tr')).data(), true);
    });
    $('#dataTableAHI tbody').on('click', 'button.trash-o', function () {
        dataTableAHI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        showAddItemModal(dataTableAHI.row($(this).parents('tr')).data(), true);
    });

    deshabilitarBotonesAddItems();

    function deshabilitarBotonesAddItems() {
        $('table button').removeAttr('disabled');
        //disabled buttons de items ya agregados a lista solicitud
        listAHI = JSON.parse(localStorage.getItem("listAHI"));
        $.each(listAHI, function (index, value) {
            $("[i-id='" + value.item.id + "']").attr('disabled', 'disabled');
        });
    }

    /*
     * FIN CARGAR_VISTA
     */

    //Cuando se da click en el boton para que muestre modal e ingresar la cantidad del item a solicitar
    $('.btnShowModalAddItem').click(function () {
        let id = $(this).attr("i-id");
        showAddItemModal(id, false);
    });

    //mostrar modal para ingresar la cantidad del item a solicitar
    function showAddItemModal(data, modify) {
        if (!modify) {
            $.ajax({
                method: 'GET',
                url: 'create.do',
                data: {
                    'action': 'read-i',
                    'id': data
                },
                dataType: 'json'
            }).done(function (json) {
                //console.log(json);
                if (json.status == 200) {
                    let item = JSON.parse(json.json_string).item;
                    $('#i_id').val(item.id);
                    $('#unidad_medida_simbolo').html(item.unidad_medida.simbolo);
                    $('#i_json').val(JSON.stringify(item));
                    $('#ahi_cantidad').val('');
                    $('#btnAddAHI').removeAttr('hidden');
                    $('#btnEditAHI').attr('hidden', 'hidden');
                    $('#btnDeleteAHI').attr('hidden', 'hidden');
                    $('#addItemModal').modal('show');
                } else {
                    console.log('else');
                }
            }).fail(function (jqXHR) {
                console.log(jqXHR);
            });
        } else {
            $('#i_id').val(data.item.id);
            $('#unidad_medida_simbolo').html(data.item.unidad_medida.simbolo);
            $('#i_json').val(JSON.stringify(data.item));
            $('#ahi_cantidad').val(data.cantidad);
            $('#btnAddAHI').attr('hidden', 'hidden');
            $('#btnEditAHI').removeAttr('hidden');
            $('#btnDeleteAHI').removeAttr('hidden');
            $('#addItemModal').modal('show');
        }
    }

    //Agregar item a la solicitud de abastecimiento
    $("#btnAddAHI").click(function () {
        let listAHI;
        if (localStorage.getItem("listAHI") === null) {
            listAHI = [];
        } else {
            listAHI = JSON.parse(localStorage.getItem("listAHI"));
        }

        let ahi = {};
        ahi.item = JSON.parse($('#i_json').val());
        ahi.cantidad = $('#ahi_cantidad').val();
        listAHI.push(ahi);
        localStorage.setItem("listAHI", JSON.stringify(listAHI));

        $("[i-id='" + ahi.item.id + "']").attr('disabled', 'disabled');

        dataTableAHI.row.add(ahi).draw(false);

        $('#addItemModal').modal('hide');
    });

    $("#btnEditAHI").click(function () {
        id = $('#i_id').val();
        cantidad = $('#ahi_cantidad').val();

        let listAHI = JSON.parse(localStorage.getItem("listAHI"));
        $.each(listAHI, function (index, value) {
            if (value.item.id == id) {
                value.cantidad = cantidad;
                return false;
            }
        });
        localStorage.setItem("listAHI", JSON.stringify(listAHI));

        let ahi = dataTableAHI.row('.selected').data();
        ahi.cantidad = cantidad;
        dataTableAHI.row('.selected').data(ahi).draw();

        $('#addItemModal').modal('hide');
    });

    $("#btnDeleteAHI").click(function () {
        id = $('#i_id').val();

        let listAHI = JSON.parse(localStorage.getItem("listAHI"));
        $.each(listAHI, function (index, value) {
            if (value.item.id == id) {
                listAHI.splice(index, 1);
                return false;
            }
        });
        localStorage.setItem("listAHI", JSON.stringify(listAHI));

        dataTableAHI.row('.selected').remove().draw(false);

        $("[i-id='" + id + "']").removeAttr('disabled');

        $('#addItemModal').modal('hide');
    });

    //Registrar la solicitud de abastecimiento
    $("#btnCreateAbastecimiento").click(function () {
        if (dataTableAHI.data().length == 0) {
            alert('tiene que seleccionar items para registrar la solicitud');
            return;
        }
        let local_id_destino = JSON.parse(localStorage.getItem("identity")).local.id;
        $.ajax({
            method: 'POST',
            url: 'create.do',
            data: {
                'action': $('#action').val(),
                'local_id_destino': local_id_destino,
                'observacion': $('#observacion').val(),
                'listAHI': localStorage.getItem("listAHI")
            },
            dataType: 'json',
            beforeSend: function () {
                $('table button').attr('disabled', 'disabled');
                $('#btnCreateAbastecimiento').attr('disabled', 'disabled');
                $('#loading').show();
            }
        }).done(function (json) {
            //console.log(json);
            if (json.status == 200) {
                let abastecimiento = JSON.parse(json.json_string).abastecimiento;

                $('#observacion').val('');
                dataTableAHI.clear().draw();
                localStorage.removeItem("listAHI");

                $(location).attr('href', '/la-granja/almacen/abastecimiento/read.do?id=' + abastecimiento.id);
            } else {
                console.log('else');
            }
        }).fail(function (jqXHR) {
            console.log('fail');
            console.log(jqXHR);
        }).always(function () {
            deshabilitarBotonesAddItems();
            $('#btnCreateAbastecimiento').removeAttr('disabled');
            $('#loading').hide();
        });
    });
});