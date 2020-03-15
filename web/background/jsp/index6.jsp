<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/4
  Time: 15:48
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
    <script type="text/javascript" src=<%=jsPath + "index6.js"%>></script>
</head>
<body style="margin-top: 10px;height: 768px">
<div style="text-align: center;">
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=backUserManagement" id="form1" method="post">
        姓名：<input type="text" placeholder="请输入姓名" name="username" value="${requestScope.username}">
        <span style="margin-left: 70px"></span>
        职称：<select name="jobtitle" >
        <option value="" selected="selected">请选择职称</option>
        <c:choose>
            <c:when test="${requestScope.jobtitle=='教授'}">
                <option value="教授" selected="selected">教授</option>
            </c:when>
            <c:otherwise>
                <option value="教授">教授</option>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.jobtitle=='副教授'}">
                <option value="副教授" selected="selected">副教授</option>
            </c:when>
            <c:otherwise>
                <option value="副教授">副教授</option>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.jobtitle=='助教'}">
                <option value="助教" selected="selected">助教</option>
            </c:when>
            <c:otherwise>
                <option value="助教">助教</option>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.jobtitle=='讲师'}">
                <option value="讲师" selected="selected">讲师</option>
            </c:when>
            <c:otherwise>
                <option value="讲师">讲师</option>
            </c:otherwise>
        </c:choose>
    </select>

        <span style="margin-left: 70px"></span>
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
        <span style="margin-left: 600px"></span>
    </form>

        <table border="1" style="margin: auto  ; width: 700px;">
            <tr>
                <th colspan="6">用户信息管理</th>
            </tr>
            <tr id="tr2" style="background-color: #00a5a5">
                <th >账号</th>
                <th >姓名</th>
                <th class="table_user">职称</th>
                <th class="table_pwd">毕业院校</th>
                <th>状态</th>
                <th class="table_submit">操作</th>
            </tr>
            <c:if test="${requestScope.myarray!=null}">
                <c:forEach items="${requestScope.myarray}" begin="0" var="i">
                    <tr>
                        <td class="insertAccount">${i.staff_Account}</td>
                        <td>${i.staff_Name}</td>
                        <td>
                                ${i.staff_Awarded}
                        </td>
                        <td> ${i.staff_School}</td>
                        <td >
                            <c:choose>
                                <c:when test="${i.staff_Stage==1}">
                                <span class="insertStage">启用</span>
                                </c:when>
                                <c:when test="${i.staff_Stage==2}">
                                    <span  class="insertStage">禁用</span>
                                </c:when>
                                <c:when test="${i.staff_Stage==3}">
                                    <span  class="insertStage">已删除</span>
                                </c:when>
                                <c:otherwise>
                                    <span  class="insertStage">我是超级管理员</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${sessionScope.staff.staff_Stage==null}">
                                <c:if test="${i.staff_Stage==null}">
                                    <input type="button" class="layui-btn layui-btn-disabled layui-btn-radius" value="启用">
                                    <input type="button" value="删除" class="layui-btn layui-btn-disabled layui-btn-radius">
                                    <input type="button" value="重置密码" class="layui-btn layui-btn-disabled layui-btn-radius">
                                </c:if>
                                <c:if test="${i.staff_Stage==3}">
                                    <input type="button" class="layui-btn layui-btn-disabled layui-btn-radius" value="启用">
                                    <input type="button" value="删除" class="layui-btn layui-btn-disabled layui-btn-radius">
                                    <input type="button" value="重置密码" class="layui-btn layui-btn-disabled layui-btn-radius">
                                </c:if>

                                <c:if test="${i.staff_Stage==2}">
                                    <input type="button"  onclick="mybackstatus(this)" value="启用" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="删除" onclick="mybackdelete(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="重置密码" onclick="mybackreset(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                </c:if>
                                <c:if test="${i.staff_Stage==1}">
                                    <input type="button"  onclick="mybackstatus(this)" value="禁用" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="删除" onclick="mybackdelete(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="重置密码" onclick="mybackreset(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                </c:if>
                            </c:if>

                            <c:if test="${sessionScope.staff.staff_Stage!=null}">
                                <c:if test="${i.staff_Stage==null}">
                                    <input type="button" class="layui-btn layui-btn-disabled layui-btn-radius" value="启用">
                                    <input type="button" value="删除" class="layui-btn layui-btn-disabled layui-btn-radius">
                                    <input type="button" value="重置密码" class="layui-btn layui-btn-disabled layui-btn-radius">
                                </c:if>
                                <c:if test="${i.staff_Stage==3}">
                                        <input type="button" class="layui-btn layui-btn-disabled layui-btn-radius" value="启用">
                                        <input type="button" value="删除" class="layui-btn layui-btn-disabled layui-btn-radius">
                                        <input type="button" value="重置密码" class="layui-btn layui-btn-disabled layui-btn-radius">
                                </c:if>
                                <c:if test="${i.role_Id==1}">
                                <c:if test="${i.staff_Stage==2}">
                                    <input type="button"  onclick="mybackstatus(this)" value="启用" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="删除" onclick="mybackdelete(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="重置密码" onclick="mybackreset(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                    </c:if>

                                <c:if test="${i.staff_Stage==1}">
                                    <input type="button"  onclick="mybackstatus(this)" value="禁用" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="删除" onclick="mybackdelete(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                    <input type="button" value="重置密码" onclick="mybackreset(this)" class="layui-btn layui-btn-normal layui-btn-radius">
                                </c:if>
                                </c:if>
                                <c:if test="${i.role_Id==0}">
                                    <c:if test="${i.staff_Stage==2}">
                                        <input type="button" class="layui-btn layui-btn-disabled layui-btn-radius" value="启用">
                                        <input type="button" value="删除" class="layui-btn layui-btn-disabled layui-btn-radius">
                                        <input type="button" value="重置密码" class="layui-btn layui-btn-disabled layui-btn-radius">
                                    </c:if>

                                    <c:if test="${i.staff_Stage==1}">
                                        <input type="button" class="layui-btn layui-btn-disabled layui-btn-radius" value="禁用">
                                        <input type="button" value="删除" class="layui-btn layui-btn-disabled layui-btn-radius">
                                        <input type="button" value="重置密码" class="layui-btn layui-btn-disabled layui-btn-radius">
                                    </c:if>
                                </c:if>

                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    <span style="margin-right: 610px"></span>
    <a  class="layui-btn layui-btn-normal layui-btn-radius"
       onclick="myOpen()">新增人员</a>
        <form action="${pageContext.request.contextPath}/PageServlet?methodName=backUserManagement" method="post" id="form3">
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
            <input type="hidden" name="jobtitle" value=${requestScope.jobtitle}>
        </form>
</div>

</body>

</html>
