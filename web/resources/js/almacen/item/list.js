$(document).ready(function () {
    /*
     * INICIO CARGAR_VISTA
     */

    var dataTableI = $('#datatable_i').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        columns: [
            {data: 'nombre'},
            {data: 'marca_item.nombre', orderable: false},
            {data: 'unidad_medida.simbolo', width: "10%", orderable: false}
        ],
        info: false,
        lengthChange: false
    });

    var dataTablePI = $('#datatable_pi').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
        },
        columns: [
            {data: 'codigo_barra', width: "25%"},
            {data: 'nombre'},
            {
                data: null,
                width: "15%",
                orderable: false,
                createdCell: function (cell, cellData, rowData, rowIndex, colIndex) {
                    $(cell).html(cellData.rendimiento + ' ' + cellData.item.unidad_medida.simbolo);
                }
            },
            {
                data: null,
                width: "20%",
                orderable: false,
                createdCell: function (cell, cellData, rowData, rowIndex, colIndex) {
                    $(cell).children('button').attr('pi-id', cellData.id);
                },
                defaultContent: "<button class='btn btn-link eye' style='padding: 0px'><i class='fa fa-fw fa-eye'></i></button>"
                        + "<button class='btn btn-link edit' style='padding: 0px'><i class='fa fa-fw fa-edit'></i></button>"
                        + "<button class='btn btn-link camera' style='padding: 0px'><i class='fa fa-fw fa-camera'></i></button>"
                        + "<button class='btn btn-link trash' style='padding: 0px'><i class='fa fa-fw fa-trash-o'></i></button>"
            }
        ],
        info: false,
        lengthChange: false
    });

    cargarDataTableI($.getURLParam("grupo_item_id"));

    //Imagen PI

    var previewNode = document.querySelector("#template");
    previewNode.id = "";
    var previewTemplate = previewNode.parentNode.innerHTML;
    previewNode.parentNode.removeChild(previewNode);

    var myDropzone = new Dropzone('div#div_image_pi_upload', {// Make the whole body a dropzone
        url: la_granja_api_url + '/presentacion_item/upload_image/', // Set the url,
        headers: {
            Authorization: localStorage.getItem('token')
        },
        paramName: 'image',
        thumbnailWidth: 80,
        thumbnailHeight: 80,
        parallelUploads: 20,
        previewTemplate: previewTemplate,
        autoQueue: false, // Make sure the files aren't queued until manually added
        previewsContainer: "#previews", // Define the container to display the previews
        clickable: ".fileinput-button", // Define the element that should be used as click trigger to select files.
        maxFiles: 1,
        acceptedFiles: 'image/*',
        dictDefaultMessage: "Arrastre sus archivos aqui",
        dictInvalidFileType: "No se puede cargar archivos de esta extension",
        dictCancelUpload: "dictCancelUpload",
        dictCancelUploadConfirmation: "dictCancelUploadConfirmation:",
        dictRemoveFile: "Quitar archivo",
        dictMaxFilesExceeded: "Usted no puede subir mas archivos (max. 1)"
    });

    myDropzone.on("addedfile", function (file) {
        // Hookup the start button
        file.previewElement.querySelector(".start").onclick = function () {
            myDropzone.enqueueFile(file);
        };
    });

    // Update the total progress bar
    myDropzone.on("totaluploadprogress", function (progress) {
        document.querySelector("#total-progress .progress-bar").style.width = progress + "%";
    });

    myDropzone.on("sending", function (file) {
        // Show the total progress bar when upload starts
        document.querySelector("#total-progress").style.opacity = "1";
        // And disable the start button
        file.previewElement.querySelector(".start").setAttribute("disabled", "disabled");
    });

    // Hide the total progress bar when nothing's uploading anymore
    myDropzone.on("queuecomplete", function (progress) {
        document.querySelector("#total-progress").style.opacity = "0";
    });

    myDropzone.on("complete", function (file) {
        myDropzone.removeFile(file);
    });

    myDropzone.on("success", function (file, json) {
        let pi = dataTablePI.row('.selected').data();
        pi.imagen = json.file_name;
        $('#pi_image_upload').attr('src', la_granja_api_url + '/presentacion_item/get_image/' + pi.imagen);
    });

    myDropzone.on("error", function (file, errorMessage) {
        alert(JSON.stringify(errorMessage));
    });

    // Setup the buttons for all transfers
    // The "add files" button doesn't need to be setup because the config
    // `clickable` has already been specified.
    document.querySelector("#actions .start").onclick = function () {
        myDropzone.enqueueFiles(myDropzone.getFilesWithStatus(Dropzone.ADDED));
    };
    document.querySelector("#actions .cancel").onclick = function () {
        myDropzone.removeAllFiles(true);
    };

    /*
     * FIN CARGAR_VISTA
     */

    function cargarDataTableI(grupo_item_id) {
        $.ajax({
            method: "GET",
            url: la_granja_api_url + '/item/get_list/' + grupo_item_id,
            headers: {
                Authorization: localStorage.getItem('token')
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            //console.log(json);
            let listItem = json.listItem;
            dataTableI.rows.add(listItem).draw();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    $('#datatable_i tbody').on('click', 'tr', function () {
        dataTableI.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');

        let item = dataTableI.row($(this)).data();
        cargarDataTablePI(item);

        $('#btn_modal_crud_pi').removeAttr('hidden');
    });

    function cargarDataTablePI(item) {
        $.ajax({
            method: "GET",
            url: la_granja_api_url + '/presentacion_item/get_list_by_item/' + item.id,
            headers: {
                Authorization: localStorage.getItem('token')
            },
            dataType: 'json',
            beforeSend: function () {
            }
        }).done(function (json) {
            //console.log(json);
            let listPresentacionItem = json.listPresentacionItem;
            dataTablePI.clear().draw();
            dataTablePI.rows.add(listPresentacionItem).draw();
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    //CRUD PI

    //mostrar modal crud
    $('#btn_modal_crud_pi').click(function () {
        $('#div_image_pi').hide();

        $('#div_form_pi').removeAttr('class');
        $('#div_form_pi').addClass('col-md-12');

        $('#div_form_pi input').removeAttr('disabled');
        $("#form_crud_pi")[0].reset();

        $('#pi_action').val('crear');

        $('#modal_crud_pi').modal('show');
    });

    $('#datatable_pi tbody').on('click', 'button.eye', function () {
        dataTablePI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        $('#div_image_pi').show();

        $('#div_form_pi').removeAttr('class');
        $('#div_form_pi').addClass('col-md-6');

        $('#div_form_pi input').attr('disabled', 'disabled');

        let pi = dataTablePI.row($(this).parents('tr')).data();
        colocarValoresFormCrudPI('ver', pi);

        $('#btn_guardar_pi').attr('hidden', 'hidden');

        $('#modal_crud_pi').modal('show');
    });

    $('#datatable_pi tbody').on('click', 'button.edit', function () {
        dataTablePI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        $('#div_image_pi').hide();

        $('#div_form_pi').removeAttr('class');
        $('#div_form_pi').addClass('col-md-12');

        $('#div_form_pi input').removeAttr('disabled');
        $('#pi_codigo_barra').attr('disabled', 'disabled');

        let pi = dataTablePI.row($(this).parents('tr')).data();
        colocarValoresFormCrudPI('editar', pi);

        $('#btn_guardar_pi').removeAttr('hidden');

        $('#modal_crud_pi').modal('show');
    });

    $('#datatable_pi tbody').on('click', 'button.camera', function () {
        dataTablePI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        let pi = dataTablePI.row($(this).parents('tr')).data();
        $('#pi_image_upload').attr('src', la_granja_api_url + '/presentacion_item/get_image/' + pi.imagen);

        myDropzone.options.url = la_granja_api_url + '/presentacion_item/upload_image/' + pi.id;

        $('#modal_image_pi').modal('show');
    });
    
    $('#datatable_pi tbody').on('click', 'button.trash', function () {
        dataTablePI.$('tr.selected').removeClass('selected');
        $(this).parents('tr').addClass('selected');

        let pi = dataTablePI.row($(this).parents('tr')).data();
        alert(JSON.stringify(pi));
    });

    $("#form_crud_pi").submit(function (event) {
        let action = $('#pi_action').val();

        if (action == 'crear') {
            let item = dataTableI.row('tr.selected').data();
            crearPresentacionItem(item);
        } else if (action == 'editar') {
            let pi = dataTablePI.row('tr.selected').data();
            actualizarPresentacionItem(pi);
        }

        return false;
    });

    function crearPresentacionItem(item) {
        $.ajax({
            method: "POST",
            url: la_granja_api_url + '/presentacion_item/create',
            headers: {
                Authorization: localStorage.getItem('token')
            },
            data: {
                codigo_barra: $('#pi_codigo_barra').val(),
                nombre: $('#pi_nombre').val(),
                rendimiento: $('#pi_rendimiento').val(),
                precio_costo: $('#pi_precio_costo').val(),
                item_id: item.id
            },
            dataType: 'json',
            beforeSend: function () {
                $('#btn_guardar_pi').attr('disabled', 'disabled');
                $('#loading').show();
            }
        }).done(function (json) {
            //console.log(json);
            let presentacion_item = json.presentacion_item;
            presentacion_item.item = item;
            
            dataTablePI.row.add(presentacion_item).draw();
            
            $("#form_crud_pi")[0].reset();
            $('#btn_guardar_pi').removeAttr('disabled');
            $('#loading').hide();
            $('#modal_crud_pi').modal('hide');
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    function actualizarPresentacionItem(pi) {
        $.ajax({
            method: "PUT",
            url: la_granja_api_url + '/presentacion_item/update/' + pi.id,
            headers: {
                Authorization: localStorage.getItem('token')
            },
            data: {
                codigo_barra: $('#pi_codigo_barra').val(),
                nombre: $('#pi_nombre').val(),
                rendimiento: $('#pi_rendimiento').val(),
                precio_costo: $('#pi_precio_costo').val()
            },
            dataType: 'json',
            beforeSend: function () {
                $('#btn_guardar_pi').attr('disabled', 'disabled');
                $('#loading').show();
            }
        }).done(function (json) {
            //console.log(json);
            let presentacion_item = json.presentacion_item;
            presentacion_item.id = pi.id;
            presentacion_item.item = pi.item;
            presentacion_item.imagen = pi.imagen;
            
            dataTablePI.row('.selected').remove().draw();
            dataTablePI.row.add(presentacion_item).draw();
            
            $('#btn_guardar_pi').removeAttr('disabled');
            $('#loading').hide();
            $('#modal_crud_pi').modal('hide');
        }).fail(function (jqXHR) {
            console.log(jqXHR);
        });
    }

    function colocarValoresFormCrudPI(action, pi) {
        $('#pi_image').attr('src', la_granja_api_url + '/presentacion_item/get_image/' + pi.imagen);
        $('#pi_action').val(action);
        $('#pi_codigo_barra').val(pi.codigo_barra);
        $('#pi_nombre').val(pi.nombre);
        $('#pi_rendimiento').val(pi.rendimiento);
        $('#pi_precio_costo').val(pi.precio_costo);
    }
});