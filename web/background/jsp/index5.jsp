<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/3
  Time: 21:21
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
    <script type="text/javascript" src=<%=jsPath + "index5.js"%>></script>
</head>
<body>
<div style="text-align: center">
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=userManagement" id="form1" method="post">
        用户名：<input type="text" placeholder="请输入用户名" name="username" value="${requestScope.username}">
        <span style="margin-left: 140px"></span>
        状态： <select name="myselect" >
        <option value="" selected="selected">请选择状态</option>

        <c:choose>
            <c:when test="${requestScope.myselect==1}">
                <option value="1" selected="selected">启用</option>
            </c:when>
            <c:otherwise>
                <option value="1">启用</option>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.myselect==2}">
                <option value="2" selected="selected">禁用</option>
            </c:when>
            <c:otherwise>
                <option value="2">禁用</option>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.myselect==3}">
                <option value="3" selected="selected">已删除</option>
            </c:when>
            <c:otherwise>
                <option value="3">已删除</option>
            </c:otherwise>
        </c:choose>
    </select>
        <input type="submit" value="查询"> <br>
    </form>
    <form action="#" id="form2">
        <table border="1" style="margin: auto">
            <tr>
                <th colspan="7">用户信息管理</th>
            </tr>
            <tr id="tr2" style="background-color: #00a5a5">
                <th >账号</th>
                <th >用户名</th>
                <th class="table_user">性别</th>
                <th class="table_pwd">年龄</th>
                <th>手机号</th>
                <th >状态</th>
                <th class="table_submit">操作</th>
            </tr>
            <c:if test="${requestScope.myarray!=null}">
                <c:forEach items="${requestScope.myarray}" begin="0" var="i">
                    <tr>
                        <td class="insertAccount">${i.patient_Account}</td>
                        <td>${i.patient_Name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${i.patient_Sexy==0}">
                                    男
                                </c:when>
                                <c:otherwise>
                                    女
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td> ${i.patient_Age}</td>
                        <td> ${i.phone}</td>
                        <td >
                            <c:choose>
                                <c:when test="${i.patient_Stage==1}">
                            <span class="insertStage">
                                 启用
                            </span>

                                </c:when>
                                <c:when test="${i.patient_Stage==2}">
                                    <span  class="insertStage">禁用</span>
                                </c:when>
                                <c:otherwise>
                                    <span  class="insertStage">已删除</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${i.patient_Stage==3}">
                                <input type="button" class="layui-btn layui-btn-disabled layui-btn-radius" value="启用">
                                <input type="button" value="删除" class="layui-btn layui-btn-disabled layui-btn-radius">
                                <input type="button" value="重置密码" class="layui-btn layui-btn-disabled layui-btn-radius">
                            </c:if>
                            <c:if test="${i.patient_Stage==2}">
                                <input type="button"  onclick="mystatus(this)" value="禁用" class="layui-btn layui-btn-normal layui-btn-radius">
                                <input type="button" value="删除" onclick="mydelete(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                <input type="button" value="重置密码" onclick="myreset(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                            </c:if>
                            <c:if test="${i.patient_Stage==1}">
                                <input type="button"  onclick="mystatus(this)" value="禁用" class="layui-btn layui-btn-normal layui-btn-radius">
                                <input type="button" value="删除" onclick="mydelete(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                <input type="button" value="重置密码" onclick="myreset(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>

            </c:if>
        </table>
    </form>
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=userManagement" method="post" id="form3">
        <c:if test="${requestScope.page.nowpage>1}" var="flag">
            <input type="submit" name="up" value="上一页" onclick="pageup(this)">
        </c:if>
        <span>${requestScope.page.nowpage}/${requestScope.page.countpage}</span>
        <c:if test="${requestScope.page.nowpage<requestScope.page.countpage}">
            <input type="submit" name="down" value="下一页" onclick="pagedown(this)">
        </c:if>
        <input type="hidden" id="pageTurn" name="mypage" value=${requestScope.page.nowpage}>
        <input type="hidden" name="username" value=${requestScope.username}>
        <input type="hidden" name="myselect" value=${requestScope.myselect}>
    </form>

</div>

</body>
</html>
