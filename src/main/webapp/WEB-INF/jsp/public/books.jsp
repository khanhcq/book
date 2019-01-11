<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<c:url var="formUrl" value="/admin/product/list.html"/>
<c:url var="addUrl" value="/admin/product/edit.html"/>
<html>
<head>
    <title><fmt:message key="list.book"/></title>
    <meta name="menu" content="users-manage"/>
    <meta name="gmenu" content="product"/>
</head>
<body>

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
                    <div class="page-header">
                        <h1 class="">
                            <c:choose>
                                <c:when test="${!empty category}">
                                    ${category}
                                </c:when>
                                <c:when test="${!empty author}">
                                    ${author}
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="label.book.newest"/>
                                </c:otherwise>
                            </c:choose>
                        </h1>
                    </div>
                    <div class="">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <c:forEach items="${items.listResult}" var="book">
                                    <c:url var="bookUrl" value="/book?id=${book.id}"/>
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
                                            <a class="author" href="<c:url value="/books?author=${book.authorCode}"/>">
                                                <span class="category-span"><fmt:message key="label.author"/>:</span>
                                                <span>${book.author}</span>
                                            </a>
                                        </div>
                                        <div>
                                            <a class="category"
                                               href="<c:url value="/books?category=${book.categoryCode}"/>">
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
                            <c:url var="pageUrl" value="/books?"/>
                                ${my:pagination(items, pageUrl)}
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="">
                    <div class="">
                        <h3 class=""><a href="<c:url value="/books?status=done"/>"><fmt:message key="label.book.done"/></a></h3>
                    </div>
                    <ul class="">
                        <c:forEach items="${items.listResult}" var="book">
                            <li class="">
                                <a href="<c:url value="/book?id=${book.id}"/>">${book.title}</a>
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