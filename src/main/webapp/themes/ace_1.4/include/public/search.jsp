<%@page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<div class="nav-search" id="nav-search">
    <c:url var="searchUrl" value="/books"/>
    <form class="form-search" action="${searchUrl}">
								<span class="input-icon">
									<input name="search" value="${search}" type="text"
                                           placeholder="<fmt:message key="label.search"/>" class="nav-search-input"
                                           id="nav-search-input" autocomplete="off"/>
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
    </form>
</div>
<!-- /.nav-search -->