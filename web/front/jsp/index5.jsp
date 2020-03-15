<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/9
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cssPath = application.getContextPath() + "/front/css/";
    String jsPath = application.getContextPath() + "/front/js/";
    String layuipath = application.getContextPath() + "/layui/";
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
<div style="text-align: center;">
    <h1>我要预约</h1>
    <div class="layui-form">
        咨询领域：
        <div class="layui-input-inline">
            <select name="domain" lay-verify="" id="domain" lay-filter="domain">
                <option value="">请选择领域</option>
                <option value="家庭关系">家庭关系</option>
                <option value="教育心理学">教育心理学</option>
                <option value="运动心理学">运动心理学</option>
            </select>
        </div>
        心理咨询师：
        <div class="layui-input-inline">
            <select name="" id="staffname" lay-verify="" lay-filter="staffname">
                <option value="">请选择咨询师</option>
            </select>
        </div>
    </div>
        <table border="1" style="margin: auto; width: 700px" id="mytable" hidden>
            <tr>
                <td width="100px">咨询师名字</td>
                <td id="myname">11111</td>
                <td>毕业院校</td>
                <td id="school">11111</td>
                <td>职称</td>
                <td id="aword">11111</td>
            </tr>
            <tr>
                <td>擅长领域</td>
                <td colspan="3" id="mydomain">亲子关系</td>
                <td>服务价格</td>
                <td id="mycost">200元/次</td>
            </tr>
            <tr>
                <td>专业背景</td>
                <td colspan="5" id="backgroud">xxxxxxxxxxxxxxxxxxxxxxxxxxx
                    xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
                </td>

            </tr>
            <tr>
                <td>可预约时间
                </td>
                <td colspan="6" style="text-align: center">
                    <label>日期</label>
                    <input type="text" id="start" name='data1' readonly
                           placeholder="请选择预约日期" class="form-control laydate-icon"/>

                    <div>
                        <fieldset>
                            <legend>可选时间</legend>
                            <div class="layui-form" id="mycheckbox9">
                                <input type="checkbox" name="myhour" value="9" title="09:00-10:00" lay-filter="myhour"
                                       class="myhourclass">
                            </div>
                            <div class="layui-form" id="mycheckbox10">
                                <input type="checkbox" name="myhour" value="10" title="10:00-11:00" lay-filter="myhour"
                                       class="myhourclass">
                            </div>
                            <div class="layui-form" id="mycheckbox11">
                                <input type="checkbox" name="myhour" value="11" title="11:00-12:00" lay-filter="myhour"
                                       class="myhourclass">
                            </div>
                            <div class="layui-form" id="mycheckbox14">
                                <input type="checkbox" name="myhour" value="14" title="14:00-15:00" lay-filter="myhour"
                                       class="myhourclass">
                            </div>
                            <div class="layui-form" id="mycheckbox15">
                                <input type="checkbox" name="myhour" value="15" title="15:00-16:00" lay-filter="myhour"
                                       class="myhourclass">
                            </div>
                            <div class="layui-form" id="mycheckbox16">
                                <input type="checkbox" name="myhour" value="16" title="16:00-17:00" lay-filter="myhour"
                                       class="myhourclass">
                            </div>
                        </fieldset>
                    </div>
                </td>
            </tr>
        </table>
        <div style="width: 700px;margin: auto" id="mydiv" hidden>
            <br>
            <label class="layui-form-label">问题描述</label>
            <div class="layui-input-block">
                <textarea id="mytext" name="mytext" placeholder="请输入内容" class="layui-textarea"
                          rows="7" style="resize: none"></textarea>
            </div>
        </div>
        <input type="submit" value="确认提交" id="mysure" onclick="return checkForm()" hidden>
</div>
</body>
</html>
