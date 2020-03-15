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
      <table border="1" style="margin: auto; width:500px" id="mytable" >
          <tr>
              <td width="100px">咨询结果</td>
              <td id="myname">${requestScope.reportTable.result}</td>
          </tr>
          <tr>
                  <td>报告内容</td>
                  <td id="question">${requestScope.reportTable.result_Text}
                  </td>
          </tr>
      </table>

  </div>
</body>
</html>
