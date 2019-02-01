<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Google Tag Manager -->
    <script>(function (w, d, s, l, i) {
        w[l] = w[l] || [];
        w[l].push({
            'gtm.start': new Date().getTime(), event: 'gtm.js'
        });
        var f = d.getElementsByTagName(s)[0],
            j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : '';
        j.async = true;
        j.src =
            'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
        f.parentNode.insertBefore(j, f);
    })(window, document, 'script', 'dataLayer', 'GTM-PQRH3RQ');</script>
    <!-- End Google Tag Manager -->
    <%@ include file="/WEB-INF/common/meta.jsp" %>
    <title><fmt:message key="webapp.name"/>&nbsp;-&nbsp;<sitemesh:write property="title"/></title>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/font-awesome-4.6.3/css/font-awesome.min.css"/>">

    <!-- page specific plugin styles -->
    <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/select2-4.0.3/dist/css/select2.min.css"/>">--%>
    <!-- text fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,300"/>

    <!-- page specific plugin styles -->
    <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/_mod/jquery-ui.custom/jquery-ui.custom.min.css"/>">--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/fullcalendar/dist/fullcalendar.min.css"/>">--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>">--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/bootstrap-select/dist/css/bootstrap-select.css"/>">--%>
    <%--<link rel="stylesheet" href="<c:url value="/themes/ace_1.4/components/chosen/chosen.min.css"/>">--%>

    <%--vendor theme--%>
    <%--<link href="<c:url value="/themes/plugins/kartik-bootstrap-fileinput/css/fileinput.min.css"/>" rel="stylesheet" >--%>

    <!-- ace styles -->
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-skins.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-rtl.min.css"/>">

    <%--popup detele--%>
    <%--<link href="<c:url value="/themes/plugins/sweetalert/lib/sweet-alert.css"/>" rel="stylesheet">--%>

    <link rel="stylesheet" href="<c:url value="/themes/book/css/global.css"/>">


    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="<c:url value="/themes/ace_1.4/dist/js/ace-extra.min.js"/>"></script>
    <!--[if !IE]> -->
    <script src="<c:url value="/themes/ace_1.4/jquery/2.1.4/jquery.min.js"/>"></script>
    <!-- <![endif]-->
    <!--[if IE]>
    <script src="<c:url value="/themes/ace_1.4/jquery/1.11.3/jquery.min.js"/>"></script>
    <![endif]-->
    <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <script>
        (adsbygoogle = window.adsbygoogle || []).push({
            google_ad_client: "ca-pub-7959388451620151",
            enable_page_level_ads: true
        });
    </script>
    <sitemesh:write property="head"/>
</head>

<body class="no-skin">
<!-- Google Tag Manager (noscript) -->
<noscript>
    <iframe src="https://www.googletagmanager.com/ns.html?id=GTM-PQRH3RQ"
            height="0" width="0" style="display:none;visibility:hidden"></iframe>
</noscript>
<!-- End Google Tag Manager (noscript) -->
<!-- Header -->
<jsp:include page="/themes/ace_1.4/include/public/header.jsp"/>
<div class="main-container ace-save-state" id="main-container">
    <%--<div class="form-group">--%>
        <%--<script>--%>
            <%--(function() {--%>
                <%--// var cx = 'partner-pub-7959388451620151:8809436690';--%>
                <%--var cx = '013386194828306596408:ogtqx0ing10';--%>
                <%--var gcse = document.createElement('script');--%>
                <%--gcse.type = 'text/javascript';--%>
                <%--gcse.async = true;--%>
                <%--gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;--%>
                <%--var s = document.getElementsByTagName('script')[0];--%>
                <%--s.parentNode.insertBefore(gcse, s);--%>
            <%--})();--%>
        <%--</script>--%>
        <%--<gcse:search></gcse:search>--%>
    <%--</div>--%>
    <jsp:include page="/themes/ace_1.4/include/public/sidebar.jsp"/>
    <div class="main-content">
        <div class="main-content-inner">
            <sitemesh:write property="body"/>
        </div>
    </div>

    <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
                            <span class="bigger-120">
                                <span class="blue bolder">&#169; 2019 <fmt:message key="copyright.text"/></span>
                            </span>
            </div>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- Footer-->
<jsp:include page="/themes/ace_1.4/include/public/footer.jsp"/>

</body>
</html>