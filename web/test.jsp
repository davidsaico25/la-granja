<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template>
    <jsp:attribute name="body">
        <div class="content-wrapper">
            <div class="container-fluid">
                <h1>Hello World! from jspTest template.tag</h1>

                <c:forEach items="${listUsuario}" var="usuario">
                    </span>${usuario.username}<br/>
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</t:template>