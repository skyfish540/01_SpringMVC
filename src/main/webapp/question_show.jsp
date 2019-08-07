<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.model.Question" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/23 0023
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


             <center>
                 <form action="<%=request.getContextPath()%>/question/delete.do">
                    <table border="2">
                        <tr>
                            <td>试题编号</td>
                            <td>题目内容</td>
                            <td>A选项</td>
                            <td>B选项</td>
                            <td>C选项</td>
                            <td>D选项</td>
                            <td>正确答案</td>
                            <td>更新</td>
                            <td><input type="submit" value="删除"></td>
                        </tr>

                        <c:forEach items="${questionList}" var="question">
                            <tr>
                                <td>${question.questionId}</td>
                                <td>${question.title}</td>
                                <td>${question.optionA}</td>
                                <td>${question.optionB}</td>
                                <td>${question.optionC}</td>
                                <td>${question.optionD}</td>
                                <td>${question.answer}</td>
                                <td><a href="<%=request.getContextPath()%>/question/private/toUpdate.do?questionId=${question.questionId}">试题更新</a></td>
                                <td><input type="radio" name="questionId" value="${question.questionId}"></td>
                            </tr>
                        </c:forEach>

                    </table>
                 </form>
             </center>
</body>
</html>
