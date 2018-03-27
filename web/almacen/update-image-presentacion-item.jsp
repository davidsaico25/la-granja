<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template-user>
    <jsp:attribute name="head">
        <link href="/la-granja/resources/css/upload-file.css" rel="stylesheet" type="text/css">
        <link href="/la-granja/resources/vendor/dropzone/css/dropzone.css" rel="stylesheet" type="text/css">
        <link href="/la-granja/resources/css/upload-file2.css" rel="stylesheet" type="text/css">
    </jsp:attribute>
    <jsp:attribute name="body">
        <div class="content-wrapper">
            <div class="container-fluid">
                <a class="btn btn-primary" href="/la-granja/almacen/readItem.do?item_id=${presentacionItem.item_id}">Atras</a>

                <h2>${presentacionItem.codigo_barra}</h2>
                <h2>${presentacionItem.nombre}</h2>
                <h2>${presentacionItem.rendimiento}</h2>

                <div id="presentacionInsumoImagen">
                    <c:if test="${presentacionItem.imagen != null}">
                        <img src="http://localhost:3000/api/presentacion_item/get-image/${presentacionItem.imagen}" style="height:500px; width:500px"/>
                    </c:if>
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
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="/la-granja/resources/vendor/dropzone/js/dropzone.js"></script>
        <script src="/la-granja/resources/js/global.js"></script>
        <script type="text/javascript">
            var imageUploadURL = URLRest + "presentacion_item/upload-image/${presentacionItem.id}";
            var paramNameFile = "image";
            var acceptedFile = "image/*";
        </script>
        <script src="/la-granja/resources/js/upload-file.js"></script>
    </jsp:attribute>
</t:template-user>