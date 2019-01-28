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
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li class="active">
            <i class="ace-icon fa fa-home home-icon"></i>
            <fmt:message key="label.home"/>
        </li>
    </ul>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-8 books">
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
            <div class="row">
                <div class="col-xs-12">
                    <c:forEach items="${items.listResult}" var="book">
                        <c:url var="bookUrl" value="/book?id=${book.id}"/>
                        <div class="with-border media book-item">
                            <div class="media-left">
                                <a href="${bookUrl}" class="book-cover-trans">
                                    <img src="https://pic.truyen.co/img/<str:lowerCase>${my:normalizeTitle(book.title)}</str:lowerCase>.jpg" class="book-thumb">
                                </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading book-title"><a href="${bookUrl}">${book.title}</a></h4>
                                <div class="small">
                                    <a href="<c:url value="/books?author=${book.author.code}"/>" class="text-muted">
                                        <i class="iconfont icon-write icon-18"></i> ${book.author.name}
                                    </a>
                                </div>
                                <div>
                                    <str:truncateNicely upper="100">${book.description}</str:truncateNicely>
                                </div>
                                <ul class="tag-list tag-small list-unstyled mt-2">
                                    <c:forEach items="${book.categories}" var="category">
                                        <li class="tag green"><a
                                                href="<c:url value="/books?category=${category.code}"/>">${category.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>


                            </div>
                        </div>


                        <%--<div class="">--%>
                        <%--<div class="book-thumb-img">--%>
                        <%--<a href="${bookUrl}" title="${book.title}" class="thumb">--%>
                        <%--<img alt="${book.title}"--%>
                        <%--src="<c:url value="/themes/book/img/book-default-img.png"/>">--%>
                        <%--</a>--%>
                        <%--</div>--%>
                        <%--<div>--%>
                        <%--<a class="title" href="${bookUrl}" title="${book.title}">${book.title}</a>--%>
                        <%--</div>--%>

                        <%--<div>--%>
                        <%--<a class="author" href="<c:url value="/books?author=${book.author.code}"/>">--%>
                        <%--<span class="category-span"><fmt:message key="label.author"/>:</span>--%>
                        <%--<span>${book.author}</span>--%>
                        <%--</a>--%>
                        <%--</div>--%>
                        <%--<div>--%>
                        <%--<a class="category"--%>
                        <%--href="<c:url value="/books?category=${book.categoryCode}"/>">--%>
                        <%--<span class="category-span"><fmt:message key="label.type"/>:</span>--%>
                        <%--<span>${book.category}</span>--%>
                        <%--</a>--%>
                        <%--</div>--%>

                        <%--<div>--%>
                        <%--<a href="${bookUrl}">--%>
                        <%--<span class="label label-primary"><fmt:message key="label.chapter"/> </span>--%>
                        <%--</a>--%>
                        <%--</div>--%>

                        <%--</div>--%>
                    </c:forEach>

                </div>
            </div>
            <div class="text-center">
                <c:url var="pageUrl" value="/books?"/>
                <c:if test="${!empty category}"><c:url var="pageUrl" value="/books?category=${category}&"/></c:if>
                <c:if test="${!empty author}"><c:url var="pageUrl" value="/books?author=${author}&"/></c:if>
                ${my:pagination(items, pageUrl, false)}
            </div>
        </div>
        <%--<div class="col-sm-4">--%>
            <%--<div class="">--%>
                <%--<div class="">--%>
                    <%--<h3 class=""><a href="<c:url value="/books?status=done"/>"><fmt:message key="label.book.done"/></a>--%>
                    <%--</h3>--%>
                <%--</div>--%>
                <%--<ul class="list-unstyled">--%>
                    <%--<c:forEach items="${items.listResult}" var="book">--%>
                        <%--<li class="">--%>
                            <%--<a href="<c:url value="/book?id=${book.id}"/>">${book.title}</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</div>

<script type="text/javascript">

</script>
</body>
</html>