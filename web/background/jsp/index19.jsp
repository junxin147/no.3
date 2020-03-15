<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/15
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String cssPath = application.getContextPath() + "/background/css/";
    String jsPath = application.getContextPath() + "/background/js/";
    String layuipath = application.getContextPath() + "/layui/";
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src=<%=jsPath + "jquery-3.4.1.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "json2.js"%>></script>
    <script type="text/javascript" src=<%=layuipath + "layui.js"%>></script>
    <link rel="stylesheet" href=<%=layuipath+"css/layui.css" %>>
    <link rel="stylesheet" href=<%=cssPath+"index19.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "iconfont.js"%>></script>
    <script type="text/javascript" src=<%=jsPath + "index19.js"%>></script>
<body>

    <form action="${pageContext.request.contextPath}/ShuttleServlet?methodName=everyworkMenu" method="post"
    id="myform">
        <div class="layui-form"  style="width: 150px">
            <select name="staffwork" id="staffwork" lay-verify=""  lay-filter="staffwork">
                <c:choose>
                    <c:when test="${requestScope.workname eq'系统管理员'}">
                        <option value="系统管理员" selected="selected">系统管理员</option>
                    </c:when>
                    <c:otherwise>
                        <option value="系统管理员">系统管理员</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.workname eq'咨询师'}">
                        <option value="咨询师" selected="selected">咨询师</option>
                    </c:when>
                    <c:otherwise>
                        <option value="咨询师">咨询师</option>
                    </c:otherwise>
                </c:choose>

            </select>
            <input type="button" id="myclick"
                   value="查询菜单权限" hidden>
        </div>


    </form>

<input type="button" id="apply" onclick="applyAction()" class="layui-btn layui-btn-normal layui-btn-radius"
       value="提交菜单">
<div class="selection-container">
    <div class="select-box left">
        <div class="select-box-title">
            <input type="checkbox" class="checkbox-all" id="checkbox-all1"/>
            <label for="checkbox-all1"></label>
            <span>待分配的菜单</span>
        </div>
        <div class="select-content" id="left">
            <c:if test="${requestScope.list2!=null}">
                <ul class="unselect-ul">
                    <c:forEach items="${requestScope.list2}" begin="0" var="i">
                        <c:forEach items="${i.value}" begin="0" var="j">
                            <li>
                                <input type="checkbox" class="checkboxs"/>
                                <label></label>
                                <span>${j.secondmenu}</span>
                            </li>
                        </c:forEach>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
    <div class="arrows-box">
        <div class="arrow-btns">
            <button class="arrow-btn right">
                <svg class="icon icon-seach" aria-hidden="true">
                    <use xlink:href="#icon-com-dajiantouyou"></use>
                </svg>
            </button>
            <button class="arrow-btn left">
                <svg class="icon icon-seach" aria-hidden="true">
                    <use xlink:href="#icon-com-dajiantouzuo"></use>
                </svg>
            </button>
        </div>
    </div>
    <div class="select-box right">
        <div class="select-box-title">
            <input type="checkbox" class="checkbox-all" id="checkbox-all2"/>
            <label for="checkbox-all2"></label>
            <span>已分配的菜单</span>
        </div>
        <div class="select-content" id="right">
            <c:if test="${requestScope.list1!=null}">
                <ul class="selected-ul">

                    <c:forEach items="${requestScope.list1}" begin="0" var="i">
                        <c:forEach items="${i.value}" begin="0" var="j">
                            <li>
                                <input type="checkbox" class="checkboxs"/>
                                <label></label>
                                <span>${j.secondmenu}</span>
                            </li>
                        </c:forEach>
                    </c:forEach>
                </ul>

            </c:if>

        </div>
    </div>
</div>
</body>

</html>
