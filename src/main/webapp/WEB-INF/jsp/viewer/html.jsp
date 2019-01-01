<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<c:url var="url" value="/admin/product/edit.html"/>
<c:url var="backUrl" value="/admin/product/list.html"/>
<head>
    <title><fmt:message key="edit.product.title"/></title>
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
        <li class="active"><fmt:message key="edit.product.title"/></li>
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

    <div class="page-header">
        <div class="row">
            <h1 class="col-sm-8"><fmt:message key="edit.product.title"/> </h1>
            <div class="col-sm-3">
                <a href="${backUrl}" class="btn btn-grey">
                    <i class="ace-icon fa fa-arrow-left"></i>
                    <fmt:message key="label.back"/>
                </a>
                <button class="btn btn-success btnSave">
                    <i class="ace-icon fa fa-save"></i>
                    <fmt:message key="label.save"/>
                </button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            ${content}
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



