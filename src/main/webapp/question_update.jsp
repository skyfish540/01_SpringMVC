<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/24 0024
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
          <center>
              <form action="<%=request.getContextPath()%>/question/private/update.do">
                  <table border="2">

                      <tr>
                          <td>题目编号</td>
                          <td><input type="text" name='questionId' value="${question.questionId}"></td>
                      </tr>
                      <tr>
                          <td>题目</td>
                          <td><input type="text" name='title' value="${question.title}"></td>
                      </tr>
                      <tr>
                          <td>A</td>
                          <td><input type="text" name='optionA' value="${question.optionA}"></td>
                      </tr>
                      <tr>
                          <td>B</td>
                          <td><input type="text" name='optionB' value="${question.optionB}"></td>
                      </tr>
                      <tr>
                          <td>C</td>
                          <td><input type="text" name='optionC' value="${question.optionC}"></td>
                      </tr>
                      <tr>
                          <td>D</td>
                          <td><input type="text" name='optionD' value="${question.optionD}"></td>
                      </tr>

                      <tr>
                          <td>answer</td>
                          <td>
                             <input type="text" name="answer" value="${question.answer}">
                          </td>
                      </tr>
                      <tr>
                          <td><input type="submit" value="更新试题"></td>
                          <td><input type="reset"></td>
                      </tr>
                  </table>

              </form>
          </center>
</body>
</html>
