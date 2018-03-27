<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template-user>
    <jsp:attribute name="body">
        <div class="content-wrapper">
            <div class="container-fluid">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="#">Administrar</a>
                    </li>
                    <li class="breadcrumb-item active">Locales</li>
                </ol>
                <c:if test="${message != null}">
                    ${message}
                </c:if>
                <h1>GRUPO ITEMS</h1>
                <div class="row">
                    <c:forEach items="${listLocal}" var="local">
                        <div class="col-xl-3 col-sm-6 mb-3">
                            <div class="card text-white bg-primary o-hidden h-100">
                                <div class="card-body">
                                    <div class="card-body-icon">
                                        <i class="fa fa-fw fa-comments"></i>
                                    </div>
                                    <div class="mr-5">${local.nombre}</div>
                                </div>
                                <a class="card-footer text-white clearfix small z-1" href="/la-granja/almacen/listLocalHasItem.do?local_id=${local.id}">
                                    <span class="float-left">Ver</span>
                                    <span class="float-right">
                                        <i class="fa fa-angle-right"></i>
                                    </span>
                                </a>
                                    <!--
                                <a class="card-footer text-white clearfix small z-1" href="/la-granja/almacen/listItem.do?grupo_item_id=${local.id}">
                                    <span class="float-left">Ver</span>
                                    <span class="float-right">
                                        <i class="fa fa-angle-right"></i>
                                    </span>
                                </a>
                                    -->
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:template-user>