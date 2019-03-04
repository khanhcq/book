<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<html>
<head>
    <title>${book.title}</title>
    <meta name="keywords"
          content="${book.title}, <str:lowerCase>${my:normalizeKeyword(book.title)}</str:lowerCase>,
          doc truyen <str:lowerCase>${my:normalizeKeyword(book.title)}</str:lowerCase>, <str:lowerCase>${my:normalizeKeyword(book.title)}</str:lowerCase> full,
          <str:lowerCase>${my:normalizeKeyword(book.title)}</str:lowerCase> prc, <str:lowerCase>${my:normalizeKeyword(book.title)}</str:lowerCase> epub,
            <str:lowerCase>${my:normalizeKeyword(book.title)}</str:lowerCase> online">

    <link rel="stylesheet"  media="all" type="text/css" href="<c:url value="/themes/star-rating-v4.0.5-3/css/star-rating.min.css"/>">
    <script src="<c:url value="/themes/star-rating-v4.0.5-3/js/star-rating.min.js"/>"></script>
    <script src="<c:url value="/themes/star-rating-v4.0.5-3/js/locales/vi.js"/>"></script>

</head>
<body>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon green"></i>
            <a href="<c:url value="/"/>"><fmt:message key="label.home"/></a>
        </li>
        <li>
            <a href="<c:url value="/books?category=${book.categories[0].code}"/>">${book.categories[0].name}</a>
        </li>
        <li class="active">${book.title}</li>
    </ul>
    <jsp:include page="/themes/ace_1.4/include/public/search.jsp"/>
</div>

<div class="container">
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
    <div id="messages"></div>
    <div class="book-info page-header">
        <%--<h1>${book.title}</h1>--%>
        <div class="row">
            <div class="col-xs-12">
                <div class="with-border media">
                    <div class="media-left">
                        <div class="book-cover-trans">
                            <img src="https://pic.truyen.co/img/<str:lowerCase>${my:normalizeTitle(book.title)}</str:lowerCase>.jpg"
                                 class="book-thumb">
                        </div>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading book-title"><span class="green">${book.title}</span></h4>
                        <div class="rate" itemscope="" itemtype="https://schema.org/Book">
                            <c:set var="ratePoint" value="${!empty book.rate ? book.rate.point : 60}"/>
                            <div class="rate-holder" style="cursor: pointer;">
                                <input id="rating-input" name="input-name" type="number" class="kv-fa rating"  data-language="vi" value="${ratePoint}" min=0 max=100 step=10 data-size="sm">
                            </div>

                            <div class="small" itemprop="aggregateRating" itemscope=""
                                 itemtype="https://schema.org/AggregateRating">
                                <em>
                                    <fmt:message key="label.rate"/>
                                    <strong><span itemprop="ratingValue">${ratePoint}</span></strong>/<span class="text-muted" itemprop="bestRating">100</span>
                                    <fmt:message key="label.rate.by"/>
                                    <strong>
                                        <span itemprop="ratingCount">${!empty book.rate ? book.rate.no : "1"}</span>
                                        <fmt:message key="label.rate.count"/>
                                    </strong>
                                </em>
                            </div>
                        </div>
                        <div class="small">
                            <a href="<c:url value="/books?author=${book.author.code}"/>" class="text-muted author">
                                ${book.author.name}
                            </a>
                        </div>

                        <ul class="list-unstyled tag-list">
                            <c:forEach items="${book.categories}" var="category">
                                <li class="label label-success arrowed-in-right arrowed">
                                    <a href="<c:url value="/books?category=${category.code}"/>">${category.name}</a>
                                </li>
                            </c:forEach>
                        </ul>


                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                ${book.description}
            </div>
        </div>
    </div>

    <br>
    <c:if test="${items.totalItems > 0}">
        <div class="chapters">
            <div class="row">
                <div class="col-xs-12">
                    <c:forEach items="${items.listResult}" var="tableList">
                        <div class="chap">
                            <a href="<c:url value="/chapter?id=${book.id}&no=${tableList.number}"/>">
                                <str:truncateNicely upper="52">${tableList.title}</str:truncateNicely>
                            </a>
                        </div>
                    </c:forEach>

                </div>
                <c:url var="pageUrl" value="/book?id=${book.id}&"/>
                    ${my:pagination(items, pageUrl, true)}
            </div>
        </div>

    </c:if>
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
                $('#crudAction').val('delete');
//                $("#itemForm").submit();
            });
        });

        $('#rating-input').on('rating:change', function(event, value, caption) {
            $.ajax({
                type: "GET",
                url: "<c:url value="/ajax/rating/save"/>",
                data: {
                    id: ${book.id},
                    point: value
                },
                success: function (data) {
                    var messageAlert = 'alert-' + data.type;
                    var messageText = data.message;

                    var alertBox = '<div class="alert ' + messageAlert + ' alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' + messageText + '</div>';
                    if (messageAlert && messageText) {
                        $('#messages').html(alertBox);
                        $('#itemForm')[0].reset();
                    }
                }
            });

        });
    });
</script>

</body>
</html>