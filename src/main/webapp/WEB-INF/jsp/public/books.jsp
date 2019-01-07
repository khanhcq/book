<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<c:url var="formUrl" value="/admin/product/list.html"/>
<c:url var="addUrl" value="/admin/product/edit.html"/>
<html>
<head>
    <title><fmt:message key="list.product.title"/></title>
    <meta name="menu" content="users-manage"/>
    <meta name="gmenu" content="product"/>
</head>
<body>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="#"><fmt:message key="label.home"/></a>
        </li>
        <li>
            <a href="#"><fmt:message key="label.product.title"/></a>
        </li>
        <li class="active"><fmt:message key="list.product.title"/></li>
    </ul><!-- /.breadcrumb -->
</div>

<div class="page-content">
    <c:if test="${not empty messageResponse}">
        <div class="row">
            <div class="col-xs-12">

                <div class="alert alert-${alert} alert-dismissible fade in" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        ${messageResponse}
                </div>
            </div>
        </div>
    </c:if>

    <%--<div class="page-header">--%>
    <%--<div class="row">--%>
    <%--<h1 class="col-sm-12"><fmt:message key="list.product.title"/></h1>--%>
    <%--</div>--%>
    <%--</div>--%>
    <!-- /.page-header -->

    <form:form action="${formUrl}" modelAttribute="items" role="form" id="listForm" cssClass="form-horizontal">
        <div class="row">
            <div class="col-sm-8">
                <div class="">
                    <div class="page-header"><h1 class=""><fmt:message key="label.book.newest"/></h1></div>
                    <div class="">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <c:forEach items="${items.listResult}" var="book">
                                    <c:url var="bookUrl" value="/book.html?code=${book.code}"/>
                                    <div class="">
                                        <div class="book-thumb-img">
                                            <a href="${bookUrl}" title="${book.title}" class="thumb">
                                                <img alt="${book.title}"
                                                     src="<c:url value="/themes/book/img/book-default-img.jpg"/>">
                                            </a>
                                        </div>
                                        <div>
                                            <a class="title" href="${bookUrl}" title="${book.title}">${book.title}</a>
                                        </div>

                                        <div>
                                            <a class="author" href="${bookUrl}&author=${book.author}">
                                                <span class="category-span"><fmt:message key="label.author"/>:</span>
                                                <span>${book.author}</span>
                                            </a>
                                        </div>

                                        <div>
                                            <a class="category"
                                               href="<c:url value="/book.html?category=${book.category}"/>">
                                                <span class="category-span"><fmt:message key="label.type"/>:</span>
                                                <span>${book.category}</span>
                                            </a>
                                        </div>

                                        <div>
                                            <a href="${bookUrl}">
                                                <span class="label label-primary"><fmt:message key="label.chapter"/> </span>
                                            </a>
                                        </div>

                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                        <div class="text-center">
                            <ul class="pagination">
                                <li><a href="/?page=1"><i class="ace-icon fa fa-angle-left"></i></a></li>
                                <li><a href="/?page=196">196</a></li>
                                <li><a href="/?page=197">197</a></li>
                                <li><a href="/?page=198">198</a></li>
                                <li><a href="/?page=199">199</a></li>
                                <li class="active"><span>200</span></li>
                                <li><a href="/?page=201">201</a></li>
                                <li><a href="/?page=202">202</a></li>
                                <li><a href="/?page=203">203</a></li>
                                <li><a href="/?page=204">204</a></li>
                                <li><a href="/?page=205">205</a></li>
                                <li><a href="/?page=797"><i class="ace-icon fa fa-angle-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="">
                    <div class="">
                        <h3 class=""><a href="<c:url value="/book.html?status=done"/>"><fmt:message key="label.book.done"/></a></h3>
                    </div>
                    <ul class="">
                        <c:forEach items="${items.listResult}" var="book">
                            <li class="">
                                <a href="<c:url value="/book.html?code=${book.code}"/>">${book.title}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </form:form>
</div>

<script type="text/javascript">

</script>
</body>
</html>