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
    <script type="text/javascript" src=<%=jsPath + "index3.js"%>></script>
</head>
<body>
<div style="text-align: center">
    <h1>预约列表</h1>
    <br>
    <input type="button" value="我要预约" onclick="myOpen()"
           class="layui-btn layui-btn-normal layui-btn-radius">


    <form action="#" id="form2">
        <table border="1" style="margin: auto; width: 700px;">
            <tr id="tr2" style="background-color: #00a5a5">
                <th>预约时间</th>
                <th>心理咨询师</th>
                <th>领域</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:if test="${requestScope.myarray!=null}">
                <c:forEach items="${requestScope.myarray}" begin="0" var="i">
             <tr>
                 <td>
                     <span class="mydate">${i.dataday}</span>
                         <br>
                     <span class="myhour">${i.hour}</span>:00- ${i.hour+1}:00
                 </td>
                 <td class="myname">${i.staff_name}</td>
                 <td>${i.staff_Domain}</td>
                 <td>${i.appointment_Stage}
                 </td>
                 <td>
                     <c:if test="${i.appointment_Stage eq '已诊断'}">
                         <input type="button" value="评价" onclick="return myappraise(this)"
                                class="layui-btn layui-btn-normal layui-btn-radius">
                     </c:if>
                     <input type="button" value="查看详情" onclick="return myinfo(this)"
                            class="layui-btn layui-btn-normal layui-btn-radius">
                     <input type="button" value="查看咨询师" onclick="return staffinfo(this)"
                            class="layui-btn layui-btn-normal layui-btn-radius">

                 </td>
             </tr>
                </c:forEach>
            </c:if>

        </table>
    </form>
    <form action="${pageContext.request.contextPath}/UserPageServlet?methodName=userAppointment" method="post" id="form3">
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
