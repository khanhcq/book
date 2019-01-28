<%@ include file="/WEB-INF/common/taglibs.jsp" %>

<html>
<head>
    <title><fmt:message key="list.store.title"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">

    <link rel="stylesheet" href="<c:url value="/ebook/lib/css/normalize.css"/>">
    <link rel="stylesheet" href="<c:url value="/ebook/lib/css/main.css"/>">
    <link rel="stylesheet" href="<c:url value="/ebook/lib/css/popup.css"/>">
    <link rel="stylesheet" href="<c:url value="/themes/font-awesome-4.6.3/css/font-awesome.min.css"/>">

    <script src="<c:url value="/ebook/lib/js/libs/jquery.min.js"/>"></script>
    <script src="<c:url value="/ebook/lib/js/libs/zip.min.js"/>"></script>
    <script src="<c:url value="/ebook/lib/js/libs/screenfull.min.js"/>"></script>
    <script src="<c:url value="/ebook/lib/js/epub.js"/>"></script>
    <script src="<c:url value="/ebook/lib/js/reader.js"/>"></script>
    <!-- Plugins -->
    <!-- <script src="js/plugins/search.js"></script> -->
    <!-- Highlights -->
    <!-- <script src="js/libs/jquery.highlight.js"></script> -->
    <!-- <script src="js/hooks/extensions/highlight.js"></script> -->
    <script>
        document.onreadystatechange = function () {
            if (document.readyState == "complete") {
                window.reader = ePubReader("<c:url value="/ebook/epub/progit.epub"/>", {
                    restore: true
                });
            }
        };
    </script>
</head>
<body>
<div id="sidebar">
    <div id="panels">
        <!-- <input id="searchBox" placeholder="search" type="search"> -->

        <!-- <a id="show-Search" class="show_view icon-search" data-view="Search">Search</a> -->
        <a id="show-Toc" class="show_view icon-list-1 active" data-view="Toc">TOC</a>
        <a id="show-Bookmarks" class="show_view icon-bookmark" data-view="Bookmarks">Bookmarks</a>
        <!-- <a id="show-Notes" class="show_view icon-edit" data-view="Notes">Notes</a> -->

    </div>
    <div id="tocView" class="view">
    </div>
    <div id="searchView" class="view">
        <ul id="searchResults"></ul>
    </div>
    <div id="bookmarksView" class="view">
        <ul id="bookmarks"></ul>
    </div>
    <div id="notesView" class="view">
        <div id="new-note">
            <textarea id="note-text"></textarea>
            <button id="note-anchor">Anchor</button>
        </div>
        <ol id="notes"></ol>
    </div>
</div>
<div id="main">

    <div id="titlebar">
        <div id="opener">
            <a id="slider" class="icon-menu">Menu</a>
        </div>
        <div id="metainfo">
            <span id="book-title"></span>
            <span id="title-seperator">&nbsp;&nbsp;–&nbsp;&nbsp;</span>
            <span id="chapter-title"></span>
        </div>
        <div id="title-controls">
            <a id="bookmark" class="icon-bookmark-empty">Bookmark</a>
            <a id="setting" class="icon-cog">Settings</a>
            <a id="fullscreen" class="icon-resize-full">Fullscreen</a>
        </div>
    </div>

    <div id="divider"></div>
    <div id="prev" class="arrow"><i class="ace-icon fa fa-angle-left"></i></div>
    <div id="viewer"></div>
    <div id="next" class="arrow"><i class="ace-icon fa fa-angle-right"></i></div>

    <div id="loader"><img src="<c:url value="/ebook/lib/img/loader.gif"/>"></div>
</div>
<div class="modal md-effect-1" id="settings-modal">
    <div class="md-content">
        <h3>Settings</h3>
        <div>
            <p>
                <input type="checkbox" id="sidebarReflow" name="sidebarReflow">Reflow text when sidebars are open.
            </p>
        </div>
        <div class="closer icon-cancel-circled"></div>
    </div>
</div>
<div class="overlay"></div>
</body>
</html>