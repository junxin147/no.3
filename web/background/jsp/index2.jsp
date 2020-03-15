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
	<link rel="stylesheet" href=<%=cssPath+"index2.css" %>>
	<script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
	<script type="text/javascript" src=<%=jsPath + "index2.js"%>></script>
</head>
<body>
<div id="div1">
	<h3>心理咨询与诊断后台管理系统</h3>
	<div id="div_a">
		<c:if test="${requestScope.staff!=null}">
			<span>欢迎${requestScope.staff.staff_Name}
				<c:if test="${requestScope.patient.role_Id==1}" >
					咨询师
				</c:if>
	<c:if test="${requestScope.patient.role_Id==0}" >
		系统管理员
	</c:if>
			</span>
		</c:if>
		<span>/</span>
		<a href="${pageContext.request.contextPath}/BackLinkServlet?methodName=logOut">注销</a>
	</div>
</div>
<div id="div2">
	<c:if test="${requestScope.getMenu!=null}"	>
		<c:forEach items="${requestScope.getMenu}" begin="0" var="i" >
			<h2 onclick="check(this)">${i.key}</h2>
			<div class="close">
				<ul>
					<c:forEach items="${i.value}" begin="0" var="j">
						<li><a href="${j.menupath}" target="myframe">${j.secondmenu}</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</c:if>
</div>
<div id="div3">
	<iframe frameborder="0" id="myi" name="myframe" src="${pageContext.request.contextPath}/background/jsp/bj.jsp"></iframe>
</div>
<div id="div4">
</div>
</body>
</html>
