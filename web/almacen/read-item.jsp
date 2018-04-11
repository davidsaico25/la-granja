<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template-user>
    <jsp:attribute name="head">
        <link href="/la-granja/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="content-wrapper">
            <div class="container-fluid">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="#">Almacen</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="/la-granja/almacen/listGrupoItem.do">Admin. Items</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="/la-granja/almacen/listItem.do?grupo_item_id=${item.grupo_item.id}">${item.grupo_item.nombre}</a>
                    </li>
                    <li class="breadcrumb-item active">${item.nombre}</li>
                </ol>

                <div>
                    <a class="btn btn-primary" href="/la-granja/almacen/crudPresentacionItem.do?item_id=${item.id}">Nueva Presentacion Insumo</a>
                </div>

                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Lista Presentaciones: <strong>${fn:toUpperCase(item.nombre)}</strong>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Codigo Barra</th>
                                        <th>Nombre</th>
                                        <th>Descripcion</th>
                                        <th>Accion</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listPresentacionItem}" var="pi">
                                        <tr>
                                            <td>${pi.codigo_barra}</td>
                                            <td>${pi.nombre}</td>
                                            <td>${pi.rendimiento} ${item.unidad_medida.simbolo}</td>
                                            <td>
                                                <!--<a href="/la-granja/almacen/updateImagePresentacionItem.do?id=${pi.id}" class="btn btn-link"><i class="fa fa-fw fa-photo"></i></a>-->
                                                <a onclick="showInfoModal(${pi.id})" href="#" style="text-decoration: none;">
                                                    <i class="fa fa-fw fa-eye"></i>
                                                </a>
                                                <!--<button onclick="showInfoPresentacionItem(${pi.id})" class="btn btn-link"><i class="fa fa-fw fa-eye"></i></button>-->
                                                <a href="/la-granja/almacen/crudPresentacionItem.do?id=${pi.id}" style="text-decoration: none;">
                                                    <i class="fa fa-fw fa-edit"></i>
                                                </a>
                                                <a onclick="showconfirmarEliminarModal(${pi.id}, '${pi.nombre}')" href="#" style="text-decoration: none;">
                                                    <i class="fa fa-fw fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <img id="pi-img" class="img-fluid"/>
                                        </div>
                                        <div class="col-md-6">.col-md-6</div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Confirmar Eliminar Modal-->
                <div class="modal fade" id="confirmarEliminarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Seguro que desea eliminar?</h5>
                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">×</span>
                                </button>
                            </div>
                            <div class="modal-body">Select "Eliminar" para eliminar item codigo <span id="pi-id"></span> <span id="pi-nombre"></span></div>
                            <div class="modal-footer">
                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                <a class="btn btn-primary btnLogout" href="#">Logout</a>
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

        <script src="/la-granja/resources/js/global.js"></script>
        <script src="/la-granja/resources/js/almacen/read-item.js"></script>
    </jsp:attribute>
</t:template-user>