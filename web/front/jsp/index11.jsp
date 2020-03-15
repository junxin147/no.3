<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/12
  Time: 16:07
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
    <title>题目</title>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <script type="text/javascript" src=<%=layuipath + "layui.js"%>></script>
    <link rel="stylesheet" href=<%=layuipath+"css/layui.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index11.js"%>></script>

</head>
<body>
<div style="text-align: center;">
    <h1>评估答题</h1>

</div>
<form action="${pageContext.request.contextPath}/UserSubjectServlet?methodName=mySubmit" method="post" onsubmit="return myradio()">
    <table border="1" style="margin: auto;">
        <c:if test="${requestScope.myarray!=null}">
            <c:forEach items="${requestScope.myarray}" begin="0" var="i" varStatus="status">
                <tr>

                    <td width="500px">
                        <span>${status.index+1}.</span>
                        <span class="subject_Text">${i.subject_Text}</span> <br>
                        <span class="subject_Id" hidden>${i.subject_Id}</span>
                        <label> <input type="radio" name="${status.index+1}" value="4"><span
                                class="option_a">${i.option_A}</span></label>
                        <label> <input type="radio" name="${status.index+1}" value="3"><span
                                class="option_b">${i.option_B}</span></label>
                        <label> <input type="radio" name="${status.index+1}" value="2"><span
                                class="option_c">${i.option_C}</span></label>
                        <label> <input type="radio" name="${status.index+1}" value="1"><span
                                class="option_d">${i.option_D}</span></label>
                    </td>

                <tr>
                    <td></td>
                </tr>

            </c:forEach>
        </c:if>
    </table>
    <div style="text-align: center;">
        <input type="submit" value="提交" id="sure" >

    </div>
</form>

</body>
</html>
