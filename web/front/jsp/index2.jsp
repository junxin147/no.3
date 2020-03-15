<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/11/300
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cssPath = application.getContextPath() + "/front/css/";
    String jsPath = application.getContextPath() + "/front/js/";
%>
<html>
<head>
    <title>Title</title>
    <script !src="">
        if (window != top) {
        top.location.href = location.href;
    }</script>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <link rel="stylesheet" href=<%=cssPath+"index2.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index2.js"%>></script>
</head>
<body>
<%


    if (session.getAttribute("erroring") != null) {
        out.write("<script>alert('" + session.getAttribute("erroring") + "')</script>");
        session.removeAttribute("erroring");

    }
    if (session.getAttribute("regsuccess") != null) {
        out.write("<script>alert('" + session.getAttribute("regsuccess") + "')</script>");
        session.removeAttribute("regsuccess");
    }
%>
<%--<form name="form1" action="${pageContext.request.contextPath}/FrontUserServlet?methodName=login" method="post"--%>
<%--onsubmit="return login()">--%>
<div><h1>心理咨询与诊断平台系统</h1>
    <input class=input_1 id='username' size=15 name="username" placeholder=用户名 value="${requestScope.account}"><br/>
    <input class=input_1 id='password' type=text size=15 name="password" placeholder=密码"><br/>
    <input type="text" value="" placeholder="请输入验证码" class="input_1" id="input-val" style="width:200px">
    <canvas id="canvas" width="100" height="43"></canvas>
    <br><br/>
    <input class=input_3 type="submit" id="#submit" value="登录" onclick="login()"/>
    <input class=input_3 type="button" onclick=document.form1.reset(); value="重置"/>
    <br>
    <a href="${pageContext.request.contextPath}/MyLinkServlet?methodName=regLink"
       style="color: #0000FF">没有账号吗？点击此处可以注册</a>
</div>
<%--</form>--%>
</body>
</html>
