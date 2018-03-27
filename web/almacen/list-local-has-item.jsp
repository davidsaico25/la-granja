<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <a href="/la-granja/administrar/listLocal.do">Locales</a>
                    </li>
                    <li class="breadcrumb-item active">qwerty</li>
                </ol>
                <c:if test="${message != null}">
                    ${message}
                </c:if>
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-table"></i> Lista Items
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Marca</th>
                                        <th>Cantidad</th>
                                        <th>Accion</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Marca</th>
                                        <th>Cantidad</th>
                                        <th>Accion</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach items="${listLocalHasItem}" var="lhi">
                                        <tr>
                                            <td>${lhi.item.nombre}</td>
                                            <td>${lhi.item.marca_item.nombre}</td>
                                            <td>${lhi.cantidad} ${lhi.item.unidad_medida.simbolo}</td>
                                            <td><a href="#"><i class="fa fa-fw fa-arrow-right"></i></a></td>
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
        <script src="/la-granja/resources/vendor/datatables/jquery.dataTables.js"></script>
        <script src="/la-granja/resources/vendor/datatables/dataTables.bootstrap4.js"></script>
        <script src="/la-granja/resources/js/sb-admin-datatables.min.js"></script>
    </jsp:attribute>
</t:template-user>