<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/13
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String cssPath = application.getContextPath() + "/front/css/";
    String jsPath = application.getContextPath() + "/front/js/";
    String layuipath = application.getContextPath() + "/layui/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <script type="text/javascript" src=<%=layuipath + "layui.js"%>></script>
    <link rel="stylesheet" href=<%=layuipath+"css/layui.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index10.js"%>></script>

    <title>Title</title>
</head>
<body>
<div style="text-align: center;">
    <br>      <br>      <br>
    <form action="">
        <select name="domain" lay-verify="" id="domain" lay-filter="mydomain">
            <option value="家庭关系">家庭关系</option>
            <option value="教育心理学">教育心理学</option>
            <option value="运动心理学">运动心理学</option>

        </select>

        <br>      <br>      <br>
        <input type="button" value="开始评估" onclick=" myassess(this)"
               class="layui-btn layui-btn-normal layui-btn-radius">

    </form>

</div>



</body>
</html>
