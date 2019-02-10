<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<c:url var="url" value="/public/user/edit"/>
<c:url var="backUrl" value="/"/>
<head>
    <title><fmt:message key="edit.user.title"/></title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon green"></i>
            <a href="<c:url value="/"/>"><fmt:message key="label.home"/></a>
        </li>
        <li class="active"><fmt:message key="edit.user.title"/></li>
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

    <div id="messages"></div>


<%--<div class="page-header">--%>
        <div class="row">
            <%--<h1 class="col-sm-8"><fmt:message key="edit.user.title"/> </h1>--%>
            <h1 class="col-sm-8 page-header header green clearfix"><fmt:message key="edit.user.title"/></h1>
        </div>
    <%--</div>--%>
    <div class="row">
        <div class="col-xs-12">
            <form:form modelAttribute="item" action="${url}" method="post" id="itemForm" class="form-horizontal" novalidate="novalidate">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="user.email"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.email" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.email" cssClass="red-text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="user.manager.password"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.password" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.password" cssClass="red-text"/>
                    </div>
                </div>

                <div class="hr hr-double dotted"></div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"></label>
                    <div class="col-sm-9">
                        <div class="g-recaptcha" data-sitekey="6Lc7no0UAAAAAOwNjHyKmoCiBiCPQrI21rRAI21Y" data-callback="verifyRecaptchaCallback" data-expired-callback="expiredRecaptchaCallback"></div>
                        <input class="form-control hidden" data-recaptcha="true" required data-error="Please complete the Captcha">
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"></label>
                    <div class="col-sm-9">
                        <button class="btn btn-success btnSave">
                            <i class="ace-icon fa fa-paper-plane"></i>
                            <fmt:message key="label.send"/>
                        </button>
                    </div>
                </div>
                <form:hidden path="crudAction" value="insert-update"/>
            </form:form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('.btnSave').click(function(){
            var url = "<c:url value="/ajax/user/save"/>";
            $.ajax({
                type: "POST",
                url: url,
                data: $('#itemForm').serialize(),
                success: function (data) {
                    var messageAlert = 'alert-' + data.type;
                    var messageText = data.message;

                    var alertBox = '<div class="alert ' + messageAlert + ' alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' + messageText + '</div>';
                    if (messageAlert && messageText) {
                        $('#messages').html(alertBox);
                        $('#itemForm')[0].reset();
                        grecaptcha.reset();
                    }
                }
            });
        });

        window.verifyRecaptchaCallback = function (response) {
            $('input[data-recaptcha]').val(response).trigger('change')
        };

        window.expiredRecaptchaCallback = function () {
            $('input[data-recaptcha]').val("").trigger('change')
        };
    });
</script>
</body>



