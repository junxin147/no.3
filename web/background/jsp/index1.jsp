<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/11/30
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href=<%=cssPath+"index1.css" %>>
    <script type="text/javascript" src=<%=jsPath + "util.js"%>></script>

    <script type="text/javascript" src=<%=jsPath + "index1.js"%>></script>
</head>
<body>
<%
    if (session.getAttribute("erroring")!=null)
    {
        out.write("<script>alert('"+session.getAttribute("erroring")+"')</script>");
        session.invalidate();
    }
%>
<div>


    <h1>心理咨询与诊断后台管理系统</h1>
    <input class=input_1 id="username" size=15  name="username"  placeholder=用户名  value="${requestScope.account}"  ><br />
    <input class=input_1 id="password" type=text size=15 name="password" placeholder=密码"><br />
    <input type="text" value="" placeholder="请输入验证码" class="input_1" id="input-val" style="width:200px">
    <canvas id="canvas" width="100" height="43"></canvas>
    <br>
    <input type="radio" id="radio_1" name="gettype" value="1" checked>
               <label for="radio_1"><span class="radio_box">咨询师</span>
    </label>
               <input type="radio" id="radio_2" name="gettype" value="0">
               <label for="radio_2"> <span class="radio_box">管理员</span></label>
       		<br/>
    <input class=input_3 type="submit" id="#submit" value="登录"  onclick="login()"/>
    <input class=input_3 type="button" onclick=document.form1.reset(); value="重置" />
</div>
</body>
</html>
