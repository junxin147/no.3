<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/12
  Time: 0:56
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
    <title>预约列表</title>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <script type="text/javascript" src=<%=layuipath + "layui.js"%>></script>
    <link rel="stylesheet" href=<%=layuipath+"css/layui.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index9.js"%>></script>
</head>
<body>
<div style="text-align: center;">
    <h1>资金账户</h1>
    <span style="margin-right:420px">余额：${sessionScope.patient.purse}</span>
    <input type="button" value="充值" onclick=" myclick(this)"
           class="layui-btn layui-btn-normal layui-btn-radius">
    <br>
    <br>
    <span style="margin-right: 500px">业务收入情况：</span>

    <table border="1" style="margin: auto;width: 600px">
        <tr id="tr2" style="background-color: #00a5a5">
            <th>发生时间</th>
            <th>发生事项</th>
            <th>关系人</th>
            <th>收入/支出</th>
            <th>金额（元）</th>
        </tr>
        <c:if test="${requestScope.myarray!=null}">
            <c:forEach items="${requestScope.myarray}" begin="0" var="i"
>
                <tr>
                    <td>${i.moneytime}</td>
                    <td>${i.matter}</td>
                    <td>${i.key_Person}</td>
                    <td>${i.type}</td>
                    <td>${i.money}</td>
                </tr>
            </c:forEach>

        </c:if>
    </table>
    <form action="${pageContext.request.contextPath}/UserPageServlet?methodName=mypursepage" method="post" id="form3">
        <c:if test="${requestScope.page.nowpage>1}" var="flag">
            <input type="submit" name="up" value="上一页" onclick="pageup(this)">
        </c:if>
        <span>${requestScope.page.nowpage}/${requestScope.page.countpage}</span>
        <c:if test="${requestScope.page.nowpage<requestScope.page.countpage}">
            <input type="submit" name="down" value="下一页" onclick="pagedown(this)">
        </c:if>
        <input type="hidden" id="pageTurn" name="mypage" value=${requestScope.page.nowpage}>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/front/jsp/bj.jsp"
       class="layui-btn layui-btn—normal layui-btn-radius">返回</a>
</div>

</body>
</html>
