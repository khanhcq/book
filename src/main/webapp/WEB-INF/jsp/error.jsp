<%@ page language="java" isErrorPage="true" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/common/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title><fmt:message key="errorPage.title"/></title>
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/font-awesome-4.6.3/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,300"/>
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-skins.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/ace_1.4/dist/css/ace-rtl.min.css"/>">
</head>

<body id="error">
<div class="main-container ace-save-state" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="container">
                <!-- /.ace-settings-container -->

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <div class="error-container">
                            <div class="well">
                                <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-random"></i>
												<%=response.getStatus() %>
											</span>
                                    <fmt:message key="something.went.wrong"/>
                                </h1>

                                <hr>
                                <h3 class="lighter smaller">
                                    <fmt:message key="but.we.are.working"/>
                                    <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
                                    <fmt:message key="on.it"/>
                                </h3>

                                <div class="space"></div>

                                <div>
                                    <h4 class="lighter smaller"><fmt:message key="try.another.page"/></h4>

                                    <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            <a href="<c:url value="/"/>"><fmt:message key="go.home.page"/></a>
                                        </li>

                                        <li>
                                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                                            <a href="<c:url value="/public/feedback/edit"/>"><fmt:message key="give.us.info"/></a>
                                        </li>
                                    </ul>
                                </div>

                                <hr>
                                <div class="space"></div>

                                <div class="center">
                                    <a href="javascript:history.back()" class="btn btn-grey">
                                        <i class="ace-icon fa fa-arrow-left"></i>
                                        <fmt:message key="previous.page"/>
                                    </a>

                                    <%--<a href="#" class="btn btn-primary">--%>
                                    <%--<i class="ace-icon fa fa-tachometer"></i>--%>
                                    <%--Dashboard--%>
                                    <%--</a>--%>
                                </div>
                            </div>
                        </div>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>

        </div>
    </div>
</div>



</body>
</html>
