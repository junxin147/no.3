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
    <script type="text/javascript" src=<%=jsPath + "index18.js"%>></script>
</head>
<body>
<div style="text-align:center;margin:auto">
    <h1>渠道量统计</h1>
    <label for="">开始日期</label>
    <input type="text" id="start" name='data1'  value="${requestScope.data1}"
           placeholder="请选择开始时间" class="form-control laydate-icon"/>
    <label for="">结束日期</label>
    <input type="text" id="end" name='data2'   value="${requestScope.data2}"
           placeholder="请选择结束时间" class="form-control laydate-icon"/>
    <span style="margin-left: 140px"></span>
    <input type="submit" value="查询" onclick="dateCount()"> <br>


    <h3 id="mytext"></h3>
</div>
<div id="echarts_div" style="height: 600px"></div>
<div id="echarts_div2"></div>
</body>
</html>
