<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/1
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <script type="text/javascript" src=<%=jsPath + "index3.js"%>></script>


</head>
<body>
<div style="text-align: center">
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=appointmentManagement"
          id="form1" method="post">
        <label for="">开始日期</label>
        <input type="text" id="start" name='data1'  value="${requestScope.data1}"
               placeholder="请选择开始时间" class="form-control laydate-icon"/>
        <label for="">结束日期</label>
        <input type="text" id="end" name='data2'   value="${requestScope.data2}"
               placeholder="请选择结束时间" class="form-control laydate-icon"/>

        <span style="margin-left: 140px"></span>
        <select name="stage" id="">
        <option value="" selected="selected">请选择状态</option>

        <c:choose>
            <c:when test="${requestScope.stage=='未预约'}">
                <option value="未预约" selected="selected">未预约</option>
            </c:when>
            <c:otherwise>
                <option value="未预约">未预约</option>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.myselect=='已预约'}">
                <option value="已预约" selected="selected">已预约</option>
            </c:when>
            <c:otherwise>
                <option value="已预约">已预约</option>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.myselect=='已确认'}">
                <option value="已确认" selected="selected">已确认</option>
            </c:when>
            <c:otherwise>
                <option value="已确认">已确认</option>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.myselect=='已终止'}">
                <option value="已终止" selected="selected">已终止</option>
            </c:when>
            <c:otherwise>
                <option value="已终止">已终止</option>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.myselect=='已诊断'}">
                <option value="已诊断" selected="selected">已诊断</option>
            </c:when>
            <c:otherwise>
                <option value="已诊断">已诊断</option>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${requestScope.myselect=='已评价'}">
                <option value="已评价" selected="selected">已评价</option>
            </c:when>
            <c:otherwise>
                <option value="已评价">已评价</option>
            </c:otherwise>
        </c:choose>
        </select>
        <input type="submit" value="查询"> <br>
    </form>
    <form action="#" id="form2">
        <table border="1" style="margin: auto; width: 700px;">
            <tr>
                <th colspan="6">预约列表</th>
            </tr>
            <tr id="tr2" style="background-color: #00a5a5">
                <th>预约时间</th>
                <th>用户</th>
                <th>领域</th>
                <th>咨询问题</th>
                <th>完成状态</th>
                <th>操作</th>

            </tr>
            <c:if test="${requestScope.myarray!=null}">
                <c:forEach items="${requestScope.myarray}" begin="0" var="i">
                    <tr>
                        <td><span class="mydate">${i.dataday}</span>
                            <br><span class="myhour" >${i.hour}</span>:00—${i.hour+1}:00</td>
                        <td class="name">${i.patient_Account}</td>
                        <td>
                                ${i.staff_Domain}
                        </td>
                        <td> ${i.problem}</td>
                        <td class="mystage">
                            <c:if test="${i.appointment_Stage=='未预约'}">
                                未预约
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已确认'}">
                                已确认
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已预约'}">
                                已预约
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已诊断'}">
                                已诊断
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已终止'}">
                                已终止
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已评价'}">
                                已评价
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${i.appointment_Stage=='未预约'}">
                                <input type="button"  value="查看详情" onclick="getIfo(this)"
                                       class="layui-btn layui-btn-disabled layui-btn-radius">
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已确认'}">
                                <input type="button"  value="诊断"  onclick="mydiagnosis(this)"
                                       class="layui-btn layui-btn-normal layui-btn-radius">
                                <input type="button"  value="查看详情" onclick="getIfo(this)"
                                       class="layui-btn layui-btn-normal layui-btn-radius">
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已预约'}">
                                <input type="button"  value="确认" onclick="mysure(this)"
                                       class="layui-btn layui-btn-normal layui-btn-radius">
                                <input type="button"  value="查看详情" onclick="getIfo(this)"
                                       class="layui-btn layui-btn-normal layui-btn-radius">
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已诊断'}">
                                <input type="button"  value="查看详情" onclick="getIfo(this)"
                                       class="layui-btn layui-btn-normal layui-btn-radius">
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已终止'}">
                                <input type="button"  value="查看详情"
                                       class="layui-btn layui-btn-disabled layui-btn-radius">
                            </c:if>
                            <c:if test="${i.appointment_Stage=='已评价'}">
                                <input type="button"  value="查看详情" onclick="getIfo(this)"
                                       class="layui-btn layui-btn-normal layui-btn-radius">
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </form>
    <form action="${pageContext.request.contextPath}/PageServlet?methodName=appointmentManagement" method="post" id="form3">
        <c:if test="${requestScope.page.nowpage>1}" var="flag">
            <input type="submit" name="up" value="上一页" onclick="pageup(this)">
        </c:if>
        <span>${requestScope.page.nowpage}/${requestScope.page.countpage}</span>
        <c:if test="${requestScope.page.nowpage<requestScope.page.countpage}">
            <input type="submit" name="down" value="下一页" onclick="pagedown(this)">
        </c:if>
        <input type="hidden" id="pageTurn" name="mypage" value=${requestScope.page.nowpage}>
        <input type="hidden" name="data1" value=${requestScope.data1}>
        <input type="hidden" name="data2" value=${requestScope.data2}>
        <input type="hidden" name="stage" value=${requestScope.stage}>
    </form>

</div>

</body>
</html>
