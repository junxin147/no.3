<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/6
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>预约时间设置</title>

    <link rel="shortcut icon" href=<%=path+"/image/favicon.ico"%>>
    <link rel="stylesheet" type="text/css" href=<%=path+"/background/css/jquery-ui.min.css" %>>
    <link rel="stylesheet" type="text/css" href=<%=path+"/background/css/jquery-ui.structure.min.css" %>>
    <link rel="stylesheet" type="text/css" href=<%=path+"/background/css/jquery-ui.theme.min.css" %>>
    <link rel="stylesheet" type="text/css" href=<%=path+"/background/css/core/main.css" %>>
    <link rel="stylesheet" type="text/css" href=<%=path+"/background/css/daygrid/main.css" %>>
    <link rel="stylesheet" type="text/css" href=<%=path+"/background/css/demostyle.css" %>>
    <script type="text/javascript" src=<%=path + "/background/js/jquery.js" %>></script>
    <script type="text/javascript" src=<%=path + "/background/js/jquery-ui.min.js" %>></script>
    <script type="text/javascript" src=<%=path + "/background/js/core/main.js" %>></script>
    <script type="text/javascript" src=<%=path + "/background/js/daygrid/main.js" %>></script>
    <script type="text/javascript" src=<%=path + "/background/js/interaction/main.js" %>></script>
    <script type="text/javascript" src=<%=path + "/background/js/util.js" %>></script>
    <script type="text/javascript" src=<%=path + "/background/js/index8.js" %>></script>
</head>
<body>
<div id='calendar'></div>
<div id="caidan" title="请输入排班日程" hidden="hidden">
    <form class="form-inline">
        <p>
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：</label>
            <input type="text" class="sear datepicker" id="starttime" disabled="disabled">
        </p>
        <p>
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>
            <!--医生列表通过ajax获取-->
            <c:if test="${sessionScope.staff.role_Id==1}">
                <c:if test="${sessionScope.staff.staff_Stage==1}">
                    <select name="groups" id="groups">
                        <option value="${sessionScope.staff.staff_Name}"
                                selected="selected">${sessionScope.staff.staff_Name}</option>
                    </select>
                </c:if>
            </c:if>

        </p>
    </form>
</div>

<div id="sameday" title="日程明细" hidden="hidden">
    <form class="form-inline">
        <p>
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员：</label>

            <c:if test="${sessionScope.staff.role_Id==1}">
                <c:if test="${sessionScope.staff.staff_Stage==1}">

                    <select name="groups2" id="groups2">
                        <option value="${sessionScope.staff.staff_Name}"
                                selected="selected">${sessionScope.staff.staff_Name}</option>
                    </select>
                </c:if>
            </c:if>
        </p>
        <p>
            <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：</label>
        <fieldset>
            <legend>上午时间</legend>
            <label for="checkbox-1" id="label_1" class="ui-checkboxradio-label ui-corner-all ui-button ui-widget" value="9" onclick="return true">09:00-10:00</label>
            <input type="checkbox" name="checkbox-1" id="checkbox-1" value="9">
            <label for="checkbox-2" id="label_2" class="ui-checkboxradio-label ui-corner-all ui-button ui-widget"  value="10" onclick="return true">10:00-11:00</label>
            <input type="checkbox" name="checkbox-2" id="checkbox-2"  value="10">
            <label for="checkbox-3" id="label_3" class="ui-checkboxradio-label ui-corner-all ui-button ui-widget" value="11" onclick="return true">11:00-12:00</label>
            <input type="checkbox" name="checkbox-3" id="checkbox-3"  value="11">
        </fieldset>
        <fieldset>
            <legend>下午时间</legend>
            <label for="checkbox-4" id="label_4" class="ui-checkboxradio-label ui-corner-all ui-button ui-widget" value="14" onclick="return true">14:00-15:00</label>
            <input type="checkbox" name="checkbox-4" id="checkbox-4"  value="14">
            <label for="checkbox-5"  id="label_5" class="ui-checkboxradio-label ui-corner-all ui-button ui-widget" value="15" onclick="return true">15:00-16:00</label>
            <input type="checkbox" name="checkbox-5" id="checkbox-5" value="15" >
            <label for="checkbox-6"  id="label_6" class="ui-checkboxradio-label ui-corner-all ui-button ui-widget" value="16" onclick="return true">16:00-17:00</label>
            <input type="checkbox" name="checkbox-6" id="checkbox-6" value="16" >
        </fieldset>
        </p>
    </form>
</div>
</body>
</html>
