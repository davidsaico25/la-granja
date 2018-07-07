<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template-user>
    <jsp:attribute name="head">
        <link href="${pageContext.request.contextPath}/resources/css/upload-file.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/resources/vendor/dropzone/css/dropzone.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/resources/css/upload-file2.css" rel="stylesheet" type="text/css">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="content-wrapper">
            <div class="container-fluid">

                <a class="btn btn-primary" href="/la-granja/almacen/presentacion-item/list.do?item_id=${item_id}">Atras</a>

                <div class="row">
                    <div class="col-md-6">
                        <c:if test="${message != null}">
                            <div class="alert alert-${status}" role="alert">
                                <h5>${message}</h5>
                                <c:if test="${errors != null}">
                                    <c:forEach items="${errors}" var="error">
                                        ${error}<br/>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </c:if>

                        <form id="formCRUD" action="crud.do" method="POST" accept-charset=utf-8>
                            <input name="action" value="${action}" hidden="hidden">
                            <input name="item_id" value="${item_id}" hidden="hidden">
                            <input name="presentacion_item_id" value="${presentacion_item_id}" hidden="hidden">
                            <input name="imagen" value="${imagen}" hidden="hidden">
                            <div class="form-group">
                                <label for="codigo_barra">Codigo Barra</label>
                                <input class="form-control" id="codigo_barra" name="codigo_barra" value="${map.codigo_barra != null ? map.codigo_barra : presentacionItem.codigo_barra}" type="text" placeholder="Codigo Barra" ${presentacion_item_id != null ? 'readonly' : ''}>
                            </div>
                            <div class="form-group">
                                <label for="nombre">Nombre</label>
                                <input class="form-control" id="nombre" name="nombre" value="${map.nombre != null ? map.nombre : presentacionItem.nombre}" type="text" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <label for="rendimiento">Rendimiento</label>
                                <input class="form-control" id="rendimiento" name="rendimiento" value="${map.rendimiento != null ? map.rendimiento : presentacionItem.rendimiento}" type="number" placeholder="Rendimiento">
                            </div>
                            <div class="form-group">
                                <label for="precio_costo">Precio Costo</label>
                                <input class="form-control" id="precio_costo" name="precio_costo" value="${map.precio_costo != null ? map.precio_costo : presentacionItem.precio_costo}" type="number" placeholder="Precio Costo">
                            </div>
                            <button id="btnCreatePresentacionItem" class="btn btn-primary" type="submit">Guardar</button>
                            <img id="loading" src="/la-granja/resources/images/ajax-loader.gif" style="display: none;"/>
                        </form>
                    </div>


                    <div class="col-md-6" ${presentacion_item_id == null ? "hidden" : ''}>
                        <div id="presentacionInsumoImagen">
                            <img src="${initParam.la_granja_api_url}/presentacion_item/get-image/${imagen}" class="img-fluid"/>
                        </div>

                        <div id="actions" class="row">
                            <div class="col-lg-7">
                                <!-- The fileinput-button span is used to style the file input field as button -->
                                <span class="btn btn-success fileinput-button">
                                    <i class="fa fa-fw fa-plus"></i>
                                    <span>Add files...</span>
                                </span>
                                <button type="submit" class="btn btn-primary start">
                                    <i class="fa fa-fw fa-upload"></i>
                                    <span>Start upload</span>
                                </button>
                                <button type="reset" class="btn btn-warning cancel">
                                    <i class="fa fa-fw fa-ban"></i>
                                    <span>Cancel upload</span>
                                </button>
                            </div>
                            <div class="col-lg-5">
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
                                    <button class="btn btn-primary start">
                                        <i class="fa fa-fw fa-upload"></i>
                                        <span>Start</span>
                                    </button>
                                    <button data-dz-remove class="btn btn-warning cancel">
                                        <i class="fa fa-fw fa-ban"></i>
                                        <span>Cancel</span>
                                    </button>
                                    <button data-dz-remove class="btn btn-danger delete">
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
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/almacen/presentacion-item/crud-presentacion-item.js"></script>

        <script src="${pageContext.request.contextPath}/resources/vendor/dropzone/js/dropzone.js"></script>
        
        <script type="text/javascript">
            var imageUploadURL = la_granja_api_url + "/presentacion_item/upload-image/${presentacion_item_id}";
            var paramNameFile = "image";
            var acceptedFile = "image/*";
            var url = "${pageContext.request.contextPath}/almacen/presentacion-item/crud.do?id=${presentacion_item_id}";
        </script>
        <script src="${pageContext.request.contextPath}/resources/js/upload-file.js"></script>
    </jsp:attribute>
</t:template-user>