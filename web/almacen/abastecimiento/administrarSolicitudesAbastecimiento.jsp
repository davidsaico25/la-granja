<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template-user>
    <jsp:attribute name="head">
        <link href="/la-granja/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="body">

    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/almacen/abastecimiento/administrar-solicitudes-abastecimiento.js"></script>
    </jsp:attribute>
</t:template-user>