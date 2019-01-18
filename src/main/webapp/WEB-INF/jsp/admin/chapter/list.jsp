<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<html>
<head>
    <title>${book.title}</title>
</head>
<body>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="<c:url value="/"/>"><fmt:message key="label.home"/></a>
        </li>
        <li>
            <a href="<c:url value="/books?category=${book.categoryCode}"/>">${book.category}</a>
        </li>
        <li class="active">${book.title}</li>
    </ul>
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

    <form:form action="${formUrl}" modelAttribute="items" role="form" id="listForm" cssClass="form-horizontal">
        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-4">
                    <div class="book-thumb-img">
                        <img alt="${book.title}"
                             src="<c:url value="/themes/book/img/book-default-img.jpg"/>">
                    </div>
                    <div>
                        <span class="title">${book.title}</span>
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
                </div>
                <div class="col-xs-8">
                        ${book.description}
                </div>
            </div>
        </div>
        <br>
        <c:if test="${items.totalItems > 0}">
            <div class="row">
                <div class="dataTables_wrapper form-inline no-footer">
                    <c:forEach items="${items.listResult}" var="tableList">
                        <div>
                            <a href="<c:url value="/chapter?id=${book.id}&no=${tableList.chapterNumber}"/>">
                                    ${tableList.chapterTitle}
                            </a>

                        </div>
                    </c:forEach>

                </div>
                <c:url var="pageUrl" value="/book?id=${book.id}&"/>
                    ${my:pagination(items, pageUrl)}
            </div>
        </c:if>
    </form:form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#btnSearch").click(function () {
            $("#itemForm").submit();
        });


        $("#confirmDelete").click(function () {
            deleteBySelectedIds({
                title: "<fmt:message key="delete.confirm.message.title"/>",
                text: "<fmt:message key="delete.confirm.message.content"/>",
                cancelButtonText: "<fmt:message key="label.cancel"/>",
                confirmButtonText: "<fmt:message key="label.delete"/>"
            }, function () {
                $('#crudaction').val('delete');
//                $("#itemForm").submit();
            });
        });
    });
</script>

</body>
</html>