<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/14
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cssPath = application.getContextPath() + "/background/css/";
    String jsPath = application.getContextPath() + "/background/js/";
    String layuipath=application.getContextPath()+"/layui/";
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "echarts.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <script type="text/javascript" src=<%=layuipath + "layui.js"%>></script>
    <link rel="stylesheet" href=<%=layuipath+"css/layui.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index17.js"%>></script>
</head>
<body>
<div style="text-align:center;margin:auto">
    <h1>用户统计</h1>
    <button type="button"  id="myweek"
                 class="layui-btn layui-btn-normal layui-btn-radius">
    本周
</button>
    <button type="button"  id="mymonth"
            class="layui-btn layui-btn-normal layui-btn-radius">
        本月
    </button>
    <button type="button" id="myyear"
            class="layui-btn layui-btn-normal layui-btn-radius">
        近半年
    </button>
    <h3 id="mytext"></h3>
</div>
<div id="echarts_div" style="height: 600px"></div>
<div id="echarts_div2"></div>
</body>
</html>
