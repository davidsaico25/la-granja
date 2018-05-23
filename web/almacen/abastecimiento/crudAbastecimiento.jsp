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
                        <i class="fa fa-table"></i> Lista Presentaciones:
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
                                            <td>${pi.rendimiento} ${pi.item.unidad_medida.simbolo}</td>
                                            <td>
                                                <a class="showModalAddPI" href="#" style="text-decoration: none;" pi-id="${pi.id}">
                                                    <i class="fa fa-fw fa-plus"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="addPIModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
                                        <div class="col-md-6">
                                            <form method="POST">
                                                <div class="form-group">
                                                    <label for="cantidadPI">Codigo Barra</label>
                                                    <input class="form-control" id="cantidadPI" name="cantidadPI" type="number">
                                                </div>
                                                <button id="btnAddPI" class="btn btn-primary" type="submit">Agregar</button>
                                                <img id="loading" src="/la-granja/resources/images/ajax-loader.gif" style="display: none;"/>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                            </div>
                        </div>
                    </div>
                </div>


                <form id="formCRUD" action="crud.do" method="POST">
                    <input name="action" value="create" hidden="hidden">
                    <input name="listAHPI" hidden="hidden">
                    <div class="form-group">
                        <label for="local_id">Local</label>
                        <select class="form-control" id="local_id" name="local_id">
                            <c:forEach items="${listLocal}" var="local">
                                <option value="${local.id}">${local.id} ${local.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button id="btnCRUDAbastecimiento" class="btn btn-primary" type="submit">Guardar</button>
                    <img id="loading" src="/la-granja/resources/images/ajax-loader.gif" style="display: none;"/>
                </form>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.request.contextPath}/resources/js/global.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/almacen/abastecimiento/crud-abastecimiento.js"></script>
    </jsp:attribute>
</t:template-user>