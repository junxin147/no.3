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
    String cssPath = application.getContextPath() + "/front/css/";
    String jsPath = application.getContextPath() + "/front/js/";
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
              <td width="100px">咨询师名字</td>
              <td id="myname">${sessionScope.myapAppointment.staff_name}</td>
              <td>毕业院校</td>
              <td id="school">${sessionScope.myapAppointment.staff_School}</td>
              <td>职称</td>
              <td id="aword">${sessionScope.myapAppointment.staff_Awarded}</td>
          </tr>
          <tr>
              <td>擅长领域</td>
              <td colspan="3" id="mydomain">${sessionScope.myapAppointment.staff_Domain}</td>

          </tr>
          <tr>
              <td>专业背景</td>
              <td colspan="5" id="backgroud">${sessionScope.myapAppointment.staff_Background}
              </td>

          </tr>
          <tr>
              <td>简介：</td>
              <td colspan="5" >${sessionScope.myapAppointment.staff_Resume}</td>
          </tr>
      </table>
        <span>用户评价</span>
      <div>
          <c:if test="${requestScope.diagnostic!=null}">
              <c:forEach items="${requestScope.diagnostic}"  begin="0" var="i">
                  <span>${i.patient_Name}</span>:
                  <span>${i.acttime}</span>
                  <br>
                  <span>${i.appraise}</span>
                  <br>
              </c:forEach>
          </c:if>


            </div>
  </div>
</body>
</html>
