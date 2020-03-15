<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/16
  Time: 13:55
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
    <script type="text/javascript" src=<%=jsPath + "index7.js"%>></script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/BackUserTbActServlet?methodName=registered" method="post" onsubmit="return myadd()">
        <div class="text_box" style="text-align: center;" >
            <div>
                <div>
                    <select name="role_id" id="0" onchange="f1()" >
                        <option value="">请选择角色</option>
                        <option value="1">咨询师</option>
                        <option value="0">普通管理员</option>
                    </select>
                    <!-- 对号 -->
                    <i class="layui-icon" id="ri4" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="wr4" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>

                <div><input type="text" placeholder="请输入用户账号" id="1" name="account" hidden>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div><input type="text" placeholder="请输入名字" id="2" name="myname" hidden>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri0" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr0" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div><input type="password" placeholder="请输入密码" id="3" name="pwd" hidden>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri1" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr1" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div><input type="text" placeholder="请输入毕业院校" id="4" name="school" hidden>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri2" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr2" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div>
                    <select name="staff_job" id="5" hidden>
                        <option value="">请选择职称</option>
                        <option value="教授">教授</option>
                        <option value="副教授">副教授</option>
                        <option value="助教">助教</option>
                        <option value="讲师">讲师</option>
                    </select>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri3" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr3" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div>
                    <select name="domain" id="6" hidden>
                        <option value="">请选择领域</option>
                        <option value="家庭关系">家庭关系</option>
                        <option value="教育心理学">教育心理学</option>
                        <option value="运动心理学">运动心理学</option>
                    </select>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri4" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr4" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div>
                <textarea   id="7" name="background" cols="15" rows="5"
                            placeholder="请输入专业背景"  style="resize:none;" hidden></textarea>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri5" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr5" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div><textarea   id="8" name="resume" cols="15" rows="5" PLACEHOLDER="请输入简介"
                                 style="resize:none;" hidden></textarea>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri6" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr6" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
                <div>
                    <select name="mycost" id="9" hidden>
                        <option value="">请选择该咨询师的价格</option>
                        <option value="100">100</option>
                        <option value="200">200</option>
                        <option value="300">300</option>
                    </select>
                    <!-- 对号 -->
                    <i class="layui-icon" id="rpri7" style="color: green;font-weight: bolder;" hidden></i>
                    <!-- 错号 -->
                    <i class="layui-icon" id="rpwr7" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div >
                <div id="10" hidden>
                    <label>
                        <input type="radio" name="sexy" value="0">
                        <span>男</span>
                    </label>
                    <label>
                        <input type="radio" name="sexy" value="1">
                        <span>女</span></label>
                </div>
                <div><input type="submit" value="确认新增" id="addStaff" hidden></div>
            </div>
        </div>
    </form>
</body>
</html>
