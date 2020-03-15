<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/11/19
  Time: 21:36
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
    <link rel="stylesheet" href=<%=cssPath+"index4.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index4.js"%>></script>
</head>
<body>
<div id="div1">
    <h3>心理咨询与诊断平台系统</h3>
    <div id="div_a">
        <c:if test="${requestScope.patient!=null}">
			<span>欢迎${requestScope.patient.patient_Name}
			<c:if test="${requestScope.patient.role_Id==2}" >
                用户
            </c:if>
			</span>
        </c:if>
        <span>/</span>
        <a href="${pageContext.request.contextPath}/MyLinkServlet?methodName=logOut">注销</a>
    </div>
</div>
<div id="div2">
    <ul class="layui-nav" lay-filter="">
        <c:if test="${requestScope.getMenu!=null}"	>
            <c:forEach items="${requestScope.getMenu}" begin="0" var="i" >
                <li class="layui-nav-item layui-this">
                    <a href="${i.menu_Path}" target="myframe">${i.menu_Name}</a></li>
                <span style="margin-right: 20px"></span>
            </c:forEach>
        </c:if>
    </ul>
</div>
<div id="div3">
    <iframe frameborder="0" id="myi" name="myframe" src="${pageContext.request.contextPath}/front/jsp/bj.jsp"></iframe>
</div>
<div id="div4">
</div>
</body>
</html>
