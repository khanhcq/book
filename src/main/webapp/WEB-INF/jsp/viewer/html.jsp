<%@ include file="/WEB-INF/common/taglibs.jsp" %>
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
        <li>
            <a href="<c:url value="/book?id=${book.id}"/>">${book.title}</a>
        </li>
        <li class="active">${chapter.chapterTitle}</li>
    </ul>
</div>

<div class="page-content chapter-content">
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
    <c:url var="prevUrl" value="/chapter?id=${chapter.bookId}&no=${chapter.chapterNumber - 1}"/>
    <c:url var="nextUrl" value="/chapter?id=${chapter.bookId}&no=${chapter.chapterNumber + 1}"/>
    <c:if test="${chapter.chapterNumber == 1}">
        <c:url var="prevUrl" value="#"/>
    </c:if>
    <c:if test="${chapter.chapterNumber == lastChapter}">
        <c:url var="nextUrl" value="#"/>
    </c:if>
    <div class="page-header">
        <div class="row">
            <div class="col-sm-3">
                <a href="${prevUrl}" class="btn btn-success ${chapter.chapterNumber == 1 ? "disabled" : ""}">
                    <i class="ace-icon fa fa-arrow-left"></i>
                    <fmt:message key="label.prev.chapter"/>
                </a>
            </div>
            <div class="col-sm-6">
            </div>
            <div class="col-sm-3">
                <a  href="${nextUrl}"class="btn btn-success ${chapter.chapterNumber == lastChapter ? "disabled" : ""}">
                    <fmt:message key="label.next.chapter"/> &nbsp; <i class="ace-icon fa fa-arrow-right"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            ${chapter.content}
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <a href="${prevUrl}" class="btn btn-success ${chapter.chapterNumber == 1 ? "disabled" : ""}">
                <i class="ace-icon fa fa-arrow-left"></i>
                <fmt:message key="label.prev.chapter"/>
            </a>
        </div>
        <div class="col-sm-6">
        </div>
        <div class="col-sm-3">
            <a  href="${nextUrl}"class="btn btn-success ${chapter.chapterNumber == lastChapter ? "disabled" : ""}">
                <fmt:message key="label.next.chapter"/> &nbsp; <i class="ace-icon fa fa-arrow-right"></i>
            </a>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('.btnSave').click(function(){
            $('#itemForm').submit();
        });
    });
</script>
</body>



