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
                        <img id="loading" src="/la-granja/resources/images/ajax-loader.gif" style="display: none;"/>
                    </form>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script type="text/javascript" src="/la-granja/resources/js/login.js"></script>
    </jsp:attribute>
</t:template>