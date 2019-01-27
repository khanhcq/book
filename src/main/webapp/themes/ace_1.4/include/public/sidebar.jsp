<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<div id="sidebar" class="sidebar  h-sidebar  navbar-collapse collapse ace-save-state">
    <script type="text/javascript">
        try {
            ace.settings.loadState('sidebar')
        } catch (e) {
        }
    </script>
    <%--<div class="sidebar-shortcuts" id="sidebar-shortcuts">--%>
    <%--<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">--%>
    <%--<button class="btn btn-success">--%>
    <%--<i class="ace-icon fa fa-signal"></i>--%>
    <%--</button>--%>

    <%--<button class="btn btn-info">--%>
    <%--<i class="ace-icon fa fa-pencil"></i>--%>
    <%--</button>--%>

    <%--<button class="btn btn-warning">--%>
    <%--<i class="ace-icon fa fa-users"></i>--%>
    <%--</button>--%>

    <%--<button class="btn btn-danger">--%>
    <%--<i class="ace-icon fa fa-cogs"></i>--%>
    <%--</button>--%>
    <%--</div>--%>

    <%--<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">--%>
    <%--<span class="btn btn-success"></span>--%>

    <%--<span class="btn btn-info"></span>--%>

    <%--<span class="btn btn-warning"></span>--%>

    <%--<span class="btn btn-danger"></span>--%>
    <%--</div>--%>
    <%--</div><!-- /.sidebar-shortcuts -->--%>


    <ul class="nav nav-list">
        <li class="hover">
            <a href="index.html">
                <i class="menu-icon fa fa-tachometer"></i>
                <span class="menu-text"> Dashboard </span>
            </a>

            <b class="arrow"></b>
        </li>

        <li class="active open hover">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-desktop"></i>
                <span class="menu-text"><fmt:message key="book.types"/> </span>
                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li class="hover">
                    <a href="<c:url value="/books?category=Huyen-Huyen"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.huyen.huyen"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Khoa-Huyen"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.khoa.huyen"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Trong-Sinh"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.trong.sinh"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Tien-Hiep"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.tien.hiep"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Ngon-Tinh"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.ngon.tinh"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Dam-My"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.dam.my"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Hai-Huoc"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.hai.huoc"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Co-Dai"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.co.dai"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Do-Thi"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.do.thi"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Mat-The"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.mat.the"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Xuyen-Khong"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.xuyen.khong"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Nguoc"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.nguoc"/>
                    </a>
                    <b class="arrow"></b>
                </li>

                <li class="hover">
                    <a href="<c:url value="/books?category=Sac"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.sac"/>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="hover">
                    <a href="<c:url value="/books?category=Doan-Van"/>">
                        <i class="menu-icon fa fa-caret-right"></i>
                        <fmt:message key="book.type.doan.van"/>
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </li>

        <li class="hover">
            <a href="#" class="dropdown-toggle">
                <i class="menu-icon fa fa-file-o"></i>

                <span class="menu-text">
								Other Pages

								<span class="badge badge-primary">5</span>
							</span>

                <b class="arrow fa fa-angle-down"></b>
            </a>

            <b class="arrow"></b>

            <ul class="submenu">
                <li class="hover">
                    <a href="faq.html">
                        <i class="menu-icon fa fa-caret-right"></i>
                        FAQ
                    </a>

                    <b class="arrow"></b>
                </li>
            </ul>
        </li>
    </ul><!-- /.nav-list -->

</div>
