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
    <script type="text/javascript" src=<%=jsPath + "index12.js"%>></script>
</head>
<body>
<div style="text-align: center;">
    <h1>题库管理</h1>
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=mySubject" method="post" id="myform">
        <div class="layui-form"  style="width: 100px;margin: auto">
            <select name="domain" lay-verify="" id="domain" lay-filter="mydomain">
                <option value="">请选择领域</option>
                <c:choose>
                    <c:when test="${requestScope.domain eq'家庭关系'}">
                        <option value="家庭关系" selected="selected">家庭关系</option>
                    </c:when>
                    <c:otherwise>
                        <option value="家庭关系">家庭关系</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.domain eq'教育心理学'}">
                        <option value="教育心理学" selected="selected">教育心理学</option>
                    </c:when>
                    <c:otherwise>
                        <option value="教育心理学">教育心理学</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.domain eq'运动心理学'}">
                        <option value="运动心理学" selected="selected">运动心理学</option>
                    </c:when>
                    <c:otherwise>
                        <option value="运动心理学">运动心理学</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>


        <span>一共有 <span>${requestScope.count}</span>  条记录 </span>
        <input type="submit" id="mysubmit" value="查询" hidden>
    </form>

    <br>
    <input type="button" value="新增题目" onclick="myinsert(this)">
    <br>
    <span style="margin-right: 500px">题目预览：</span>
</div>

<table border="1" style="margin: auto;">
    <c:if test="${requestScope.myarray!=null}">
        <c:forEach items="${requestScope.myarray}" begin="0" var="i" varStatus="status">
            <tr>

                <td width="500px">
                    <span>${status.index+1}.</span>
                    <span class="subject_Text">${i.subject_Text}</span> <br>
                    <span class="subject_Id" hidden>${i.subject_Id}</span>
                    <label> <input type="radio" name="${status.index+1}"><span
                            class="option_a">${i.option_A}</span></label>
                    <label> <input type="radio" name="${status.index+1}"><span
                            class="option_b">${i.option_B}</span></label>
                    <label> <input type="radio" name="${status.index+1}"><span
                            class="option_c">${i.option_C}</span></label>
                    <label> <input type="radio" name="${status.index+1}"><span
                            class="option_d">${i.option_D}</span></label>
                </td>
                <td>
                    <input type="button" value="删除" onclick="mydelete(this)">
                    <input type="button" value="修改" onclick="myupdata(this)">
                </td>
            <tr>
                <td></td>
            </tr>

        </c:forEach>
    </c:if>

</table>
<div style="text-align: center;">
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=mySubject" method="post" id="form3">
        <c:if test="${requestScope.page.nowpage>1}" var="flag">
            <input type="submit" name="up" value="上一页" onclick="pageup(this)">
        </c:if>
        <span>${requestScope.page.nowpage}/${requestScope.page.countpage}</span>
        <c:if test="${requestScope.page.nowpage<requestScope.page.countpage}">
            <input type="submit" name="down" value="下一页" onclick="pagedown(this)">
        </c:if>
        <input type="hidden" id="pageTurn" name="mypage" value=${requestScope.page.nowpage}>
        <input type="hidden" name="domain" value=${requestScope.domain}>
    </form>


</div>

</body>
</html>
