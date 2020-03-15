<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/1
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String cssPath = application.getContextPath() + "/background/css/";
    String jsPath = application.getContextPath() + "/background/js/";
    String layuipath=application.getContextPath()+"/layui/";
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <script type="text/javascript" src=<%=layuipath + "layui.js"%>></script>
    <link rel="stylesheet" href=<%=layuipath+"css/layui.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index4.js"%>></script>

</head>
<body>
<div style="text-align: center;">
    <form   id="myform1"
            action="${pageContext.request.contextPath}/AppointmentServlet?methodName=updatediagnosis" onsubmit="return myucheck()" method="post">
        <table border="1" style="margin: auto; width: 700px" id="mytable" >
            <tr>
                <td width="100px">咨询人</td>
                <td id="myname">${requestScope.patient.patient_Name}</td>
                <td>领域</td>
                <td id="domain">${sessionScope.mygetappointment.staff_Domain}</td>
                <td>预约时间</td>
                <td id="mytime">${requestScope.mygetappointment.dataday} <span style="margin-right: 10px">
                  ${requestScope.myapAppointment.hour}:00
              </span> </td>
            </tr>
            <tr>
                <td>问题描述</td>
                <td colspan="5" id="question">${requestScope.mygetappointment.problem}
                </td>
            </tr>
            <tr>
                <td>我的答复</td>
                <td colspan="5" id="reply"><div class="layui-input-block">
                <textarea id="mytext" name="mytext" placeholder="请输入内容" class="layui-textarea"
                          rows="7" style="resize: none"></textarea>
                </div>
                </td>
            </tr>

        </table>
        <input type="submit" id="mydiagnosis" value="提交" hidden>
    </form>
</div>
</body>
</html>
