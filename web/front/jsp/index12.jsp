<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/13
  Time: 15:01
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
    <script type="text/javascript" src=<%=jsPath + "index12.js"%>></script>

</head>

<body>
<div style="text-align: center;">
    <h1>评测报告</h1>
    <br>
    <br>
</div>
<table style="margin: auto;width: 700px">
    <tr>
        <td>
            <c:if test="${requestScope.myarray!=null}">
                <c:forEach items="${requestScope.myarray}" begin="0" var="i" varStatus="status">
              <span>
                   <button type="button" class="layui-btn layui-btn-lg" value="${i.report_Id}"
                      onclick="myreport(this)"  style="margin-left: 20px;margin-bottom:20px;
                height: 100px"><span>${i.report_Time}</span> <br>评测报告
                </button>
              </span>

                </c:forEach>
            </c:if>
        </td>

    </tr>

</table>
<div style="text-align: center;">
    <form action="${pageContext.request.contextPath}/UserPageServlet?methodName=myReport" method="post" id="form3">
        <c:if test="${requestScope.page.nowpage>1}" var="flag">
            <input type="submit" name="up" value="上一页" onclick="pageup(this)">
        </c:if>
        <span>${requestScope.page.nowpage}/${requestScope.page.countpage}</span>
        <c:if test="${requestScope.page.nowpage<requestScope.page.countpage}">
            <input type="submit" name="down" value="下一页" onclick="pagedown(this)">
        </c:if>
        <input type="hidden" id="pageTurn" name="mypage" value=${requestScope.page.nowpage}>
    </form>
</div>

</body>
</html>
