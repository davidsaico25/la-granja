<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template-user>
    <jsp:attribute name="head">
        <link href="/la-granja/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
        <link href="/la-granja/resources/vendor/datatables/jquery.dataTables.min.css" rel="stylesheet">
        <style>
            .centrar-image {
                display: flex;
                justify-content: center;
                align-items: center;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="content-wrapper">
            <div class="container-fluid">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="#">Almacen</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="/la-granja/almacen/grupo-item/list.do">Admin. Item</a>
                    </li>
                    <li class="breadcrumb-item active">grupoItem Nombre</li>
                </ol>

                <div class="row">
                    <div class="col-sm-12 col-md-6">
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fa fa-table"></i> Lista Items
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="datatable_i" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Marca</th>
                                                <th>Unidad Medida</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-6">
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fa fa-table"></i> Presentaciones Item:
                                <button id="btn_modal_crud_pi" type="button" class="btn btn-primary" hidden="hidden">
                                    Nuevo
                                </button>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="datatable_pi" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>Codigo Barra</th>
                                                <th>Nombre</th>
                                                <th>Rendimiento</th>
                                                <th>Accion</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal Crud pi -->
                <div class="modal fade" id="modal_crud_pi" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">CRUD PI</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div id="div_image_pi" class="col-md-6 centrar-image">
                                            <img id="pi_image" class="img-fluid"/>
                                        </div>
                                        <div id="div_form_pi" class="col-md-6">
                                            <form id="form_crud_pi">
                                                <input id="pi_action" hidden="hidden">
                                                <div class="form-group">
                                                    <label for="codigo_barra">Codigo Barra</label>
                                                    <input class="form-control" id="pi_codigo_barra" name="codigo_barra" type="text" placeholder="Codigo Barra">
                                                </div>
                                                <div class="form-group">
                                                    <label for="nombre">Nombre</label>
                                                    <input class="form-control" id="pi_nombre" name="nombre" type="text" placeholder="Nombre">
                                                </div>
                                                <div class="form-group">
                                                    <label for="rendimiento">Rendimiento</label>
                                                    <input class="form-control" id="pi_rendimiento" name="rendimiento" type="number" value="0">
                                                </div>
                                                <div class="form-group">
                                                    <label for="precio_costo">Precio Costo (S/)</label>
                                                    <input class="form-control" id="pi_precio_costo" name="precio_costo" type="number" value="0.0">
                                                </div>
                                                <button id="btn_guardar_pi" class="btn btn-primary" type="submit">Guardar</button>
                                                <img id="loading" src="/la-granja/resources/images/ajax-loader.gif" style="display: none;"/>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal Image pi -->
                <div class="modal fade" id="modal_image_pi" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Modal title</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div id="div_image_pi_upload" class="row">
                                        <img id="pi_image_upload" class="img-fluid"/>
                                    </div>

                                    <div id="actions" class="row">
                                        <div class="col-lg-6">
                                            <!-- The fileinput-button span is used to style the file input field as button -->
                                            <span class="btn btn-success btn-sm fileinput-button">
                                                <i class="fa fa-fw fa-plus"></i>
                                                <span>Add files...</span>
                                            </span>
                                            <button type="submit" class="btn btn-primary btn-sm start">
                                                <i class="fa fa-fw fa-upload"></i>
                                                <span>Start upload</span>
                                            </button>
                                            <button type="reset" class="btn btn-warning btn-sm cancel">
                                                <i class="fa fa-fw fa-ban"></i>
                                                <span>Cancel upload</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-6">
                                            <!-- The global file processing state -->
                                            <span class="fileupload-process">
                                                <div id="total-progress" class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
                                                    <div class="progress-bar progress-bar-success" style="width:0%;" data-dz-uploadprogress></div>
                                                </div>
                                            </span>
                                        </div>
                                    </div>

                                    <div class="table table-striped files" id="previews">
                                        <div id="template" class="file-row">
                                            <!-- This is used as the file preview template -->
                                            <div>
                                                <span class="preview"><img data-dz-thumbnail /></span>
                                            </div>
                                            <div>
                                                <p class="name" data-dz-name></p>
                                                <strong class="error text-danger" data-dz-errormessage></strong>
                                            </div>
                                            <div>
                                                <p class="size" data-dz-size></p>
                                                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
                                                    <div class="progress-bar progress-bar-success" style="width:0%;" data-dz-uploadprogress></div>
                                                </div>
                                            </div>
                                            <div>
                                                <button class="btn btn-primary btn-sm start">
                                                    <i class="fa fa-fw fa-upload"></i>
                                                    <span>Start</span>
                                                </button>
                                                <button data-dz-remove class="btn btn-warning btn-sm cancel">
                                                    <i class="fa fa-fw fa-ban"></i>
                                                    <span>Cancel</span>
                                                </button>
                                                <button data-dz-remove class="btn btn-danger btn-sm delete">
                                                    <i class="fa fa-fw fa-trash-o"></i>
                                                    <span>Delete</span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="/la-granja/resources/vendor/datatables/jquery.dataTables.js"></script>
        <script src="/la-granja/resources/vendor/datatables/dataTables.bootstrap4.js"></script>
        <script src="/la-granja/resources/js/sb-admin-datatables.min.js"></script>

        <script src="${pageContext.request.contextPath}/resources/vendor/dropzone/js/dropzone.js"></script>

        <script src="${pageContext.request.contextPath}/resources/js/almacen/item/list.js"></script>
    </jsp:attribute>
</t:template-user>