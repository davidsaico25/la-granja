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

                <h1>Gestionar Solicitudes</h1>

                <div class="row">
                    <div class="col-sm-12 col-md-6">
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fa fa-table"></i> Lista AHI
                                <button id="btnVisibleTable" type="button" class="btn btn-primary d-block d-sm-none">
                                    Ocultar Tabla
                                </button>
                            </div>
                            <div class="card-body">
                                <div id="divDataTableA" class="table-responsive">
                                    <table class="table table-bordered" id="dataTableA" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Fecha Creacion</th>
                                                <th>Local Origen</th>
                                                <th>Estado</th>
                                                <th>Accion</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="detalle" class="col-sm-12 col-md-6">
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fa fa-table"></i> Solicitud Abastecimiento:
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTableAHI" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>Marca</th>
                                                <th>Nombre</th>
                                                <th>Cantidad</th>
                                                <th>Unidad Medida</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/almacen/abastecimiento/gestionar-solicitudes-abastecimiento.js"></script>
    </jsp:attribute>
</t:template-user>