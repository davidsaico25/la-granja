<%@tag description="" pageEncoding="UTF-8"%>

<%@attribute name="head" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="body" fragment="true" required="true"%>
<%@attribute name="footer" fragment="true"%>
<%@attribute name="scripts" fragment="true"%>

<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/default-head.js"></script>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>La Granja</title>
        <!-- Bootstrap core CSS-->
        <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <jsp:invoke fragment="head"/>
        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/resources/css/sb-admin.css" rel="stylesheet">
    </head>
    <body class="bg-dark">
        <jsp:invoke fragment="header"/>
        <jsp:invoke fragment="body"/>
        <jsp:invoke fragment="footer"/>
        <!-- Bootstrap core JavaScript-->
        <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
        <jsp:invoke fragment="scripts"/>
    </body>
</html>