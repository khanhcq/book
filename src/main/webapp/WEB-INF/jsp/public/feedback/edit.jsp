<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<c:url var="url" value="/public/feedback/edit"/>
<c:url var="backUrl" value="/"/>
<head>
    <title><fmt:message key="edit.feedback.title"/></title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon green"></i>
            <a href="<c:url value="/"/>"><fmt:message key="label.home"/></a>
        </li>
        <li class="active"><fmt:message key="edit.feedback.title"/></li>
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
            <%--<h1 class="col-sm-8"><fmt:message key="edit.feedback.title"/> </h1>--%>
            <h1 class="col-sm-8 page-header header green clearfix"><fmt:message key="edit.feedback.title"/></h1>
        </div>
    <%--</div>--%>
    <div class="row">
        <div class="col-xs-12">
            <form:form modelAttribute="item" action="${url}" method="post" id="itemForm" class="form-horizontal" novalidate="novalidate">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="feedback.name"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.name" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.name" cssClass="red-text"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="feedback.email"/></label>

                    <div class="col-sm-9">
                        <form:input path="pojo.email" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.email" cssClass="red-text"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"><fmt:message key="feedback.content"/></label>

                    <div class="col-sm-9">
                        <form:textarea path="pojo.content" cssClass="col-xs-10 col-sm-5"/>
                        <form:errors path="pojo.content" cssClass="red-text"/>
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
        // CKEDITOR.replace('feedback-content-editor');
        $('.btnSave').click(function(){
//            $('#itemForm').submit();

            var url = "<c:url value="/ajax/feedback/save"/>";

            $.ajax({
                type: "POST",
                url: url,
                data: $(this).serialize(),
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
        <%--<c:if test="${!empty item.pojo.thumbnail}">--%>
        <%--$('.ace_file_input').ace_file_input('show_file_list', ['<c:url value="/repository${item.pojo.thumbnail}"/>']);--%>
        <%--</c:if>--%>


        window.verifyRecaptchaCallback = function (response) {
            $('input[data-recaptcha]').val(response).trigger('change')
        };

        window.expiredRecaptchaCallback = function () {
            $('input[data-recaptcha]').val("").trigger('change')
        };

        // $('#itemForm').validator();

        $('#itemForm').on('submit', function (e) {
            if (!e.isDefaultPrevented()) {
                var url = "<c:url value="/ajax/feedback/save"/>";

                $.ajax({
                    type: "POST",
                    url: url,
                    data: $(this).serialize(),
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
                return false;
            }
        })
    });
</script>
</body>



