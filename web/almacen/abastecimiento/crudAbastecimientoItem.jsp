<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template-user>
    <jsp:attribute name="head">
        <link href="/la-granja/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="content-wrapper">
            <div class="container-fluid">
                <c:if test="${message != null}">
                    ${message}
                </c:if>

                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Lista Item:
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTableI" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Marca</th>
                                        <th>Nombre</th>
                                        <th>Descripcion</th>
                                        <th>Accion</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listItem}" var="i">
                                        <tr>
                                            <td>${i.id} ${i.marca_item.nombre}</td>
                                            <td>${i.nombre}</td>
                                            <td>${i.unidad_medida.simbolo}</td>
                                            <td>
                                                <!--
                                                <a class="showModalAddItem" href="#" style="text-decoration: none;" i-id="${i.id}">
                                                    <i class="fa fa-fw fa-plus"></i>
                                                </a>
                                                -->
                                                <button type="button" class="btn btn-link btnShowModalAddItem" i-id="${i.id}" style="padding: 0px">
                                                    <i class="fa fa-fw fa-plus"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="addItemModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Agregar Item a Solicitud</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <img id="i_img" class="img-fluid" src="${initParam.la_granja_api_path}/presentacion_item/get-image/item.png"/>
                                        </div>
                                        <div class="col-md-6">
                                            <input id="i_id">
                                            <input id="i_json">
                                            <div class="form-group">
                                                <label for="ahi_cantidad">Cantidad</label>
                                                <input class="form-control" id="ahi_cantidad" name="ahi_cantidad" type="number"><span id="unidad_medida_simbolo"></span>
                                            </div>
                                            <button id="btnAddAHI" class="btn btn-primary">Agregar</button>
                                            <button id="btnEditAHI" class="btn btn-primary">Guardar</button>
                                            <button id="btnDeleteAHI" class="btn btn-danger">Eliminar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Solicitud Abastecimiento:
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTableAHI" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Marca</th>
                                        <th>Nombre</th>
                                        <th>Cantidad</th>
                                        <th>Unidad Medida</th>
                                        <th>Accion</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div>
                    <input id="action" name="action" value="create" hidden="hidden">
                    <div class="form-group">
                        <label for="observacion">Observacion</label>
                        <input class="form-control" id="observacion" name="observacion" type="text">
                    </div>
                    <button id="btnCRUDAbastecimiento" class="btn btn-primary" type="submit">Solicitar</button>
                    <img id="loading" src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" style="display: none;"/>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/almacen/abastecimiento/crud-abastecimiento-item.js"></script>
    </jsp:attribute>
</t:template-user>