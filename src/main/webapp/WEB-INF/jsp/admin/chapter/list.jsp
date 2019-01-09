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

    <div class="page-header">
        <div class="row">
            <h1 class="col-sm-12"><fmt:message key="list.product.title"/> </h1>
        </div>
    </div><!-- /.page-header -->

    <form:form action="${formUrl}" modelAttribute="items" role="form" id="listForm" cssClass="form-horizontal">
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box table-filter">
                    <div class="widget-header">
                        <h4 class="widget-title"><fmt:message key="label.searchfilter"/></h4>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><fmt:message key="label.name"/></label>
                                    <div class="col-sm-9">
                                        <%--<form:input path="pojo.name" cssClass="form-control input-sm"/>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget-bottom form-actions center">
                            <button id="btnSearch" type="button" class="btn btn-sm btn-success">
                                <fmt:message key="label.apply.search"/>
                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="table-btn-controls">
                    <div class="pull-right tableTools-container">
                        <a href="${addUrl}" class="btn btn-success">
                            <i class="ace-icon fa fa-plus"></i>
                            <fmt:message key="label.add"/>
                        </a>
                        <button id="confirmDelete" class="btn btn-danger">
                            <i class="fa fa-trash-o"></i>
                            <fmt:message key="label.delete"/>
                        </button>
                    </div>
                </div>

                <c:if test="${items.totalItems > 0}">
                    <div class="dataTables_wrapper form-inline no-footer">
                        <c:forEach items="${items.listResult}" var="tableList">
                            <div>
                                ${tableList.chapterTitle}
                            </div>
                        </c:forEach>

                    </div>
                </c:if>

                <c:url var="pageUrl" value="/chapters?"/>
                    ${my:pagination(items, pageUrl)}

                <input type="hidden" name="crudaction" id="crudaction"/>
            </div>
        </div>
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