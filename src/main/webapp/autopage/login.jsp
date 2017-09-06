<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/7/18
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>自动化设备管理系统</title>
    <base href="<%=basePath%>"></base>
    <link rel="stylesheet" href="autopage/css/style.css">
    <!-- For-Mobile-Apps-and-Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Simple Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //For-Mobile-Apps-and-Meta-Tags -->

</head>

<body>
<h1>自动化设备管理系统</h1>
<div class="container w3">
    <h2>登录</h2>
    <form action="login/loginIn" method="post">
        <div class="username">
            <span class="username" style="height:19px">用户:</span>
            <input type="text" name="name" class="name" placeholder="" required="">
            <div class="clear"></div>
        </div>
        <div class="password-agileits">
            <span class="username" style="height:19px">密码:</span>
            <input type="password" name="password" class="password" placeholder="" required="">
            <div class="clear"></div>
        </div>
        <div class="rem-for-agile">
            <%--<input type="checkbox" name="remember" class="remember">　　--%>
            <%--<br>  记得我--%>
            <a href="#">忘记了密码</a><br>
</div>
        <div class="login-w3">
            <input type="submit" class="login" value="登录">
        </div>
        <div class="clear"></div>
    </form>
</div>
<div class="footer-w3l">
    <p> 自动化设备管理系统</p>
</div>

</body>
</html>