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

                <h1>ABASTECIMIENTO</h1>

                <form>
                    <div class="form-group" hidden="hidden">
                        <label for="id">ID</label>
                        <input class="form-control" id="id" value="${abastecimiento.id}" disabled="disabled">
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-4">
                                <label for="fecha_creacion">Fecha Creacion</label>
                                <input class="form-control" id="fecha_creacion" value="${abastecimiento.fecha_creacion}" disabled="disabled">
                            </div>
                            <c:if test="${abastecimiento.fecha_recepcion != null}">
                                <div class="col-md-4">
                                    <label for="fecha_recepcion">Fecha Recepcion</label>
                                    <input class="form-control" id="fecha_recepcion" value="${abastecimiento.fecha_recepcion}" disabled="disabled">
                                </div>
                            </c:if>
                            <div class="col-md-4">
                                <label for="estado_abastecimiento">Estado</label>
                                <input class="form-control" id="estado_abastecimiento" value="${abastecimiento.estado_abastecimiento.nombre} (${abastecimiento.estado_abastecimiento.simbolo})" disabled="disabled">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="local_origen">Local Origen</label>
                                <input class="form-control" id="local_origen" value="${abastecimiento.local_origen.nombre}" disabled="disabled">
                            </div>
                            <div class="col-md-6">
                                <label for="local_destino">Local Destino</label>
                                <input class="form-control" id="local_destino" value="${abastecimiento.local_destino.nombre}" disabled="disabled">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="observacion">Observacion</label>
                        <textarea class="form-control" id="observacion" name="observacion" rows="1" disabled="disabled">${abastecimiento.observacion}</textarea>
                    </div>
                </form>

                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Lista AHI
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTableAHI" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Marca</th>
                                        <th>Nombre</th>
                                        <th>Cantidad</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${abastecimiento.listAbastecimientoHasItem}" var="ahi">
                                        <tr>
                                            <td>${ahi.item.marca_item.nombre}</td>
                                            <td>${ahi.item.nombre}</td>
                                            <td>${ahi.cantidad} ${ahi.item.unidad_medida.simbolo}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/almacen/abastecimiento/read-abastecimiento.js"></script>
    </jsp:attribute>
</t:template-user>