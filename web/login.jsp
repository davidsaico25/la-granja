<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="t"%>
<t:template>
    <jsp:attribute name="body">
        <div class="container">
            <div class="card card-login mx-auto mt-5">
                <div class="card-header">Login</div>
                <div class="card-body">
                    <form id="formLogIn">
                        <div class="form-group">
                            <label for="username">Usuario</label>
                            <input class="form-control" id="username" type="text" aria-describedby="emailHelp" placeholder="usuario">
                        </div>
                        <div class="form-group">
                            <label for="password">Contrasena</label>
                            <input class="form-control" id="password" type="password" placeholder="contrasena">
                        </div>
                        <button id="btnLogin" type="submit" class="btn btn-primary btn-block">Entrar</button>
                        <img id="loading" src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" style="display: none;"/>
                    </form>
                    <label>webserviceip ${initParam.la_granja_api_path}</label><br/>
                    <label>contextPath: ${pageContext.request.contextPath}</label><br/>
                    <label>localAddr: ${pageContext.request.localAddr}</label><br/>
                    <label>localName: ${pageContext.request.localName}</label><br/>
                    <label>localPort: ${pageContext.request.localPort}</label><br/>
                    <label>locale: ${pageContext.request.locale}</label><br/>
                    <label>protocol: ${pageContext.request.protocol}</label><br/>
                    <label>remoteAddr: ${pageContext.request.remoteAddr}</label><br/>
                    <label>remoteHost: ${pageContext.request.remoteHost}</label><br/>
                    <label>remotePort: ${pageContext.request.remotePort}</label><br/>
                    <label>scheme: ${pageContext.request.scheme}</label><br/>
                    <label>secure: ${pageContext.request.secure}</label><br/>
                    <label>serverName: ${pageContext.request.serverName}</label><br/>
                    <label>serverPort: ${pageContext.request.serverPort}</label><br/>
                    <label>contextPath: ${pageContext.request.servletContext.contextPath}</label><br/>
                    <label>serverInfo: ${pageContext.request.servletContext.serverInfo}</label><br/>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
    </jsp:attribute>
</t:template>