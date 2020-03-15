<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/10
  Time: 23:39
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
</head>
<body>
  <div style="text-align: center;">
      <table border="1" style="margin: auto; width: 700px" id="mytable" >
          <tr>
              <td width="100px">咨询人</td>
              <td id="myname">${requestScope.patient.patient_Name}</td>
              <td>领域</td>
              <td id="domain">${sessionScope.mygetappointment.staff_Domain}</td>
              <td>预约时间</td>
              <td id="mytime">${requestScope.mygetappointment.dataday} <span style="margin-right: 10px">
                  ${requestScope.mygetappointment.hour}:00
              </span> </td>
          </tr>
          <tr>
              <td>预约状态</td>
              <td  id="mystage">${requestScope.mygetappointment.appointment_Stage}</td>
              <td>费用</td>
              <td id="mycost">${sessionScope.mygetappointment.cost}元/次</td>
              <td>咨询师</td>
              <td id="staff">${sessionScope.mygetappointment.staff_name}</td>
          </tr>
          <tr>
                  <td>问题描述</td>
                  <td colspan="5" id="question">${requestScope.mygetappointment.problem}
                  </td>


          </tr>
          <tr>
              <c:if test="${requestScope.mygetappointment.appointment_Stage=='已诊断'}">
                  <td>诊断答复</td>
                  <td colspan="5" id="reply">${requestScope.mygetappointment.reply}
                  </td>
              </c:if>
              <c:if test="${requestScope.mygetappointment.appointment_Stage=='已评价'}">
                  <td>诊断答复</td>
                  <td colspan="5" id="reply">${requestScope.mygetappointment.reply}
                  </td>
              </c:if>
          </tr>
          <tr>
              <c:if test="${requestScope.mygetappointment.appointment_Stage=='已诊断'}">
                  <td>答复时间</td>
                  <td colspan="5">${requestScope.mygetappointment.reply_Time} </td>
              </c:if>
              <c:if test="${requestScope.mygetappointment.appointment_Stage=='已评价'}">
                  <td>答复时间</td>
                  <td colspan="5">${requestScope.mygetappointment.reply_Time} </td>
              </c:if>
          </tr>
          <tr>
              <c:if test="${requestScope.mygetappointment.appointment_Stage=='已评价'}">
                  <td>评价内容</td>
                  <td colspan="5">${requestScope.mygetappointment.appraise} </td>
              </c:if>

          </tr>
      </table>

  </div>
</body>
</html>
