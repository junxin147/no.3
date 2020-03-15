<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/1
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cssPath = application.getContextPath() + "/front/css/";
    String jsPath = application.getContextPath() + "/front/js/";
    String layuipath=application.getContextPath()+"/layui/";
    String path=application.getContextPath()+"/";
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <script type="text/javascript" src=<%=layuipath + "layui.js"%>></script>
    <link rel="stylesheet" href=<%=layuipath+"css/layui.css" %>>
    <script type="text/javascript" src=<%=jsPath + "index1.js"%>></script>
    <link rel="stylesheet" href=<%=cssPath+"index1.css" %>>
</head>
<body>
<%
if (session.getAttribute("regfalse")!=null)
{
out.write("<script> alert('"+session.getAttribute("regfalse")+"')</script>");
session.removeAttribute("regfalse");
}
%>

<form action="${pageContext.request.contextPath}/FrontUserServlet?methodName=registered" onsubmit="return f()" method="post">

<div class="text_box" style="text-align: center;" >
    <a href="${pageContext.request.contextPath}/MyLinkServlet?methodName=loginLink" style="color: #0000FF">已有账号，返回登录？</a>
        <div>
            <div><i><img src="/front/image/icon01.png"></i><input type="text" placeholder="请输入用户账号" id="1" name="account">
                <!-- 对号 -->
                <i class="layui-icon" id="rpri" style="color: green;font-weight: bolder;" hidden></i>
                <!-- 错号 -->
                <i class="layui-icon" id="rpwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                <span>*</span>
            </div>
                <div><i><img src="/front/image/icon01.png"></i><input type="text" placeholder="请输入名字" id="0" name="myname">
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri0" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr0" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                    <span>*</span>
                </div>
            <div><i><img src="/front/image/icon02.png"></i><input type="password" placeholder="请输入您的密码" id="2" name="pwd">
                <!-- 对号 -->
                    <i class="layui-icon" id="rpri1" style="color: green;font-weight: bolder;" hidden></i>
                <!-- 错号 -->
                    <i class="layui-icon" id="rpwr1" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                <span>*</span>
            </div>
            <div><i><img src="/front/image/icon02.png"></i><input type="password" placeholder="请再次输入您的密码" id="3">
                <!-- 对号 -->
                <i class="layui-icon" id="rpri2" style="color: green;font-weight: bolder;" hidden></i>
                <!-- 错号 -->
                <i class="layui-icon" id="rpwr2" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                <span>*</span>
            </div>
            <div>
                <i><img src="/front/image/icon03.png"></i>
                <input type="number" max="150"  min="0" placeholder="请输入年龄" id="4" name="age">
                <!-- 对号 -->
                <i class="layui-icon" id="rpri3" style="color: green;font-weight: bolder;" hidden></i>
                <!-- 错号 -->
                <i class="layui-icon" id="rpwr3" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                <span>*</span>
            </div>
            <div>
                <i><img src="/front/image/icon03.png"></i>
                <input type="text"  placeholder="请输入手机号" id="5" name="phone">
                <!-- 对号 -->
                <i class="layui-icon" id="rpri4" style="color: green;font-weight: bolder;" hidden></i>
                <!-- 错号 -->
                <i class="layui-icon" id="rpwr4" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                <span>*</span>
            </div>
            <label>
                <input type="radio" name="sexy" value="0">
                <span>男</span>
            </label>
            <label>
                <input type="radio" name="sexy" value="1">
                <span>女</span></label>
            <div><input type="submit" value="确认注册"></div>
        </div>



    </div>
</form>
</body>
</html>
