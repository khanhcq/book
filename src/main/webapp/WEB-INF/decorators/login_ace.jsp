<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/common/meta.jsp" %>
    <title><fmt:message key="webapp.name"/>&nbsp;-&nbsp;<sitemesh:write property="title"/></title>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/font-awesome-4.6.3/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,300"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-skins.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-rtl.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/book/css/global_v1.0.css"/>">
    <script src="<c:url value="/themes/ace_1.4/dist/js/ace-extra.min.js"/>"></script>
    <!--[if !IE]> -->
    <script src="<c:url value="/themes/ace_1.4/jquery/2.1.4/jquery.min.js"/>"></script>
    <!-- <![endif]-->
    <!--[if IE]>
    <script src="<c:url value="/themes/ace_1.4/jquery/1.11.3/jquery.min.js"/>"></script>
    <![endif]-->
    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <sitemesh:write property="head"/>
</head>

<body class="login-layout light-login">
<!-- Header -->
<div class="main-container">
    <div class="main-content">
        <sitemesh:write property="body"/>
    </div>
</div>
</body>
</html>