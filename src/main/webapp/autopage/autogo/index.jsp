<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/autopage/autogo/";
%>
<html>
<head>
    <title>自动化设备管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <base href="<%=basePath%>"></base>

    <!-- jQuery AND jQueryUI -->
    <script type="text/javascript" src="js/libs/jquery/1.6/jquery.min.js"></script>
    <script type="text/javascript" src="js/libs/jqueryui/1.8.13/jquery-ui.min.js"></script>
    <!-- Compressed Version
    <link type="text/css" rel="stylesheet" href="min/b=CoreAdmin&f=css/reset.css,css/style.css,css/jqueryui/jqueryui.css,js/jwysiwyg/jquery.wysiwyg.old-school.css,js/zoombox/zoombox.css" />
    <script type="text/javascript" src="min/b=CoreAdmin/js&f=cookie/jquery.cookie.js,jwysiwyg/jquery.wysiwyg.js,tooltipsy.min.js,iphone-style-checkboxes.js,excanvas.js,zoombox/zoombox.js,visualize.jQuery.js,jquery.uniform.min.js,main.js"></script>
    -->
    <link rel="stylesheet" href="css/min.css"/>
    <link rel="stylesheet" href="css/viewer.min.css">
    <%--<style>--%>
    <%--* {--%>
    <%--margin: 0;--%>
    <%--padding: 0;--%>
    <%--}--%>

    <%--#jq22 {--%>
    <%--width: 700px;--%>
    <%--margin: 0 auto;--%>
    <%--font-size: 0;--%>
    <%--}--%>

    <%--#jq22 li {--%>
    <%--display: inline-block;--%>
    <%--width: 32%;--%>
    <%--margin-left: 1%;--%>
    <%--padding-top: 1%;--%>
    <%--}--%>

    <%--#jq22 li img {--%>
    <%--width: 100%;--%>
    <%--}--%>
    <%--</style>--%>
    <%--<ul id="jq22">--%>
    <%--<li><img data-original="img/tibet-1.jpg" src="img/thumbnails/tibet-1.jpg" alt="图片1"></li>--%>
    <%--</ul>--%>
</head>
<body>


<script type="text/javascript" src="content/settings/main.js"></script>

<link rel="stylesheet" href="content/settings/style.css"/>
<link rel="stylesheet" href="css/common.css"/>

<script type="text/javascript" src="js/tinybox.js"></script>
<!-- HEAD -->
<div id="head">
    <div class="left">
        <a class="button profile"><img src="img/icons/top/huser.png" alt=""/></a>
        Hi,
        <a><%=session.getAttribute("name") %>
        </a>
        |
        <a onclick=loginOut()>Logout</a>
    </div>
</div>
<!-- SIDEBAR 菜单  -->
<div id="sidebar">

</div>

<!--
      CONTENT
                -->
<div id="content" class="white">

</div>


<div id="wrapper">
    <div class="box">
        <div id="dialogBg"></div>
        <div id="dialog" class="animated">
            <img class="dialogIco" width="50" height="50" src="images/ico.png" alt=""/>
            <div class="dialogTop">
                <a href="javascript:;" class="claseDialogBtn">关闭</a>
            </div>
            <%--<form action="" method="post" id="editForm">--%>
            <ul class="editInfos">
                <input type="hidden" id="user_update_position_uid"/>
                <li>
                    <div id="updateUP" style="display: block">
                    </div>
                </li>
                <li><input type="submit" value="确认提交" onclick=updateUsPs() class="submitBtn"/></li>
            </ul>
            <%--</form>--%>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/viewer.min.js"></script>
<script type="text/javascript" src="js/min.js"></script>
<script type="text/javascript" src="js/autojs/index.js"></script>
<script type="text/javascript" src="js/autojs/permission.js"></script>
<script type="text/javascript" src="js/autojs/position.js"></script>
<script type="text/javascript" src="js/autojs/user.js"></script>
<script type="text/javascript" src="js/autojs/basdats.js"></script>
<script type="text/javascript" src="js/autojs/autoproduct.js"></script>
<script type="text/javascript" src="js/autojs/datetime.js"></script>
<script type="text/javascript" src="js/autojs/laydate/laydate.js"></script>
<script type="text/javascript" src="js/autojs/menutree.js"></script>
<script type="text/javascript">
    function loginOut() {
        $.ajax({
            url: "http://" + url + ":8888/automation/automation/login/loginOut",
            type: "post"
        });
    }
    var w, h, className;
    function getSrceenWH() {
        w = $(window).width();
        h = $(window).height();
        $('#dialogBg').width(w).height(h);
    }
    window.onresize = function () {
        getSrceenWH();
    }
    $(window).resize();
    $(function () {

        getSrceenWH();
        //显示弹框
        $('.box a').click(function () {
            className = $(this).attr('class');
            $('#dialogBg').fadeIn(300);
            $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
        });
        //关闭弹窗
        $('.claseDialogBtn').click(function () {
            $('#dialogBg').fadeOut(300, function () {
                $('#dialog').addClass('bounceOutUp').fadeOut();
            });
        });
    });
</script>
</body>
</html>