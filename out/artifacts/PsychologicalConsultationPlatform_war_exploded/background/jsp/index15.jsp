<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/1
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String cssPath = application.getContextPath() + "/background/css/";
    String jsPath = application.getContextPath() + "/background/js/";
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
    <script type="text/javascript" src=<%=jsPath + "index15.js"%>></script>


</head>
<body>
<div style="text-align: center">
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=myReportliset"
          id="form1" method="post">
       得分：<input type="number" placeholder="请输入开始分数" name="score1" min="0" max="20" id="score1" value="${requestScope.score1}">
        至：<input type="number" placeholder="请输入结束分数" name="score2" min="0" max="20" id="score2"  value="${requestScope.score2}">
        <br>
        <label for="">开始日期</label>
        <input type="text" id="start" name='data1'  value="${requestScope.date1}"
               placeholder="请选择开始时间" class="form-control laydate-icon"/>
        <label for="">结束日期</label>
        <input type="text" id="end" name='data2'   value="${requestScope.date2}"
               placeholder="请选择结束时间" class="form-control laydate-icon"/>
        <span style="margin-left: 140px"></span>
        <input type="submit" value="查询"> <br>
    </form>
    <form action="#" id="form2">
        <table border="1" style="margin: auto; width: 700px;">
            <tr>
                <th colspan="4">用户评估列表</th>
            </tr>
            <tr id="tr2" style="background-color: #00a5a5">
                <th>评估日期</th>
                <th>用户</th>
                <th>领域</th>
                <th>得分</th>
                <th>操作</th>
            </tr>
            <c:if test="${requestScope.myarray!=null}">
                <c:forEach items="${requestScope.myarray}" begin="0" var="i">
                    <tr>
                        <td>${i.report_Time}</td>
                        <td>${i.patient_Account}</td>
                        <td>${i.subject_Domain}</td>
                        <td>${i.score}</td>
                        <td>
                            <button type="button" value="${i.report_Id}" onclick="myreportlist(this)"
                                    class="layui-btn layui-btn-normal layui-btn-radius">查看报告</button>

                        </td>

                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </form>
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=myReportliset" method="post" id="form3">
        <c:if test="${requestScope.page.nowpage>1}" var="flag">
            <input type="submit" name="up" value="上一页" onclick="pageup(this)">
        </c:if>
        <span>${requestScope.page.nowpage}/${requestScope.page.countpage}</span>
        <c:if test="${requestScope.page.nowpage<requestScope.page.countpage}">
            <input type="submit" name="down" value="下一页" onclick="pagedown(this)">
        </c:if>
        <input type="hidden" id="pageTurn" name="mypage" value=${requestScope.page.nowpage}>
        <input type="hidden" name="data1" value=${requestScope.date1}>
        <input type="hidden" name="data2" value=${requestScope.date2}>
        <input type="hidden" name="score1" value=${requestScope.score1}>
        <input type="hidden" name="score2" value=${requestScope.score2}>
    </form>
</div>

</body>
</html>
