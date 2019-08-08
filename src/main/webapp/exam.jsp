<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/28 0028
  Time: 21:45
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
             <form action="<%=request.getContextPath()%>/question/private/score.do" method="post">
                 <table border="2">
                    <c:forEach items="${exams}" var="obj">
                        <tr>
                            <td rowspan="5">题目${obj.questionId}:${obj.title}</td>

                            <td>A:${obj.optionA}</td>
                        </tr>
                        <tr>
                            <td>B:${obj.optionB}</td>
                        </tr>
                        <tr>
                            <td>C:${obj.optionC}</td>
                        </tr>
                        <tr>
                            <td>D:${obj.optionD}</td>
                        </tr>
                        <tr>
                            <td>正确答案:
                                <input type="radio" name="${obj.questionId}" value="A">A
                                <input type="radio" name="${obj.questionId}" value="B">B
                                <input type="radio" name="${obj.questionId}" value="C">C
                                <input type="radio" name="${obj.questionId}" value="D">D
                                <input type="hidden" name="answer${obj.questionId}" value="${obj.answer}">
                            </td>
                        </tr>
                    </c:forEach>
                     <tr>
                         <td colspan="2"><input type="submit" value="交卷"></td>
                     </tr>
                 </table>

             </form>
         </center>
</body>
</html>
