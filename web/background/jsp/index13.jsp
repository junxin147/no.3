<%--
  Created by IntelliJ IDEA.
  User: Alan
  Date: 2019/12/12
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/MySubjectServlet?methodName=myInsert" method="post" >
    <table border="1" style="margin: auto">
        <tr>
            <td colspan="2">
                <select name="domain" lay-verify="" id="domain" lay-filter="mydomain">
                    <option value="家庭关系">家庭关系</option>
                    <option value="教育心理学">教育心理学</option>
                    <option value="运动心理学">运动心理学</option>
                </select>
            </td>

        </tr>

        <tr>
            <td> 题目：
            </td>
            <td>
              <textarea id="mytext" name="mytext" cols="15" rows="5" PLACEHOLDER="请输入你的题目"
                        style="resize:none;">${requestScope.subject.subject_Text}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                A选项
            </td>
            <td>
                 <textarea id="a" name="a" cols="15" rows="5" PLACEHOLDER="请输入你要设置的选项A"
                           style="resize:none;">${requestScope.subject.option_A}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                B选项
            </td>
            <td>
 <textarea id="b" name="b" cols="15" rows="5" PLACEHOLDER="请输入你要设置的选项B"
           style="resize:none;">${requestScope.subject.option_B}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                C选项
            </td>
            <td>
 <textarea id="c" name="c" cols="15" rows="5" PLACEHOLDER="请输入你要设置的选项C"
           style="resize:none;">${requestScope.subject.option_C}</textarea>
            </td>
        </tr>
        <tr>
            <td>
                D选项
            </td>
            <td>
 <textarea   id="d" name="d" cols="15" rows="5" PLACEHOLDER="请输入你要设置的选项D"
             style="resize:none;">${requestScope.subject.option_D}</textarea></td>
        </tr>
    </table>
    <input type="submit" id="sureupdata" value="确认修改" hidden>
</form>
</body>
</html>
