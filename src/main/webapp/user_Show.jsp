<%--
  Created by IntelliJ IDEA.
  User: D
  Date: 2019/7/24
  Time: 17:51
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
    <form action="" method="post">
        <table border="2">
            <tr>
                <td>用户编号</td>
                <td>用户名</td>
                <td>登录名</td>
                <td>密&nbsp;码</td>
                <td>邮&nbsp;箱</td>
            </tr>
            <c:forEach items="${userList}" var="user" varStatus="s">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.uname}</td>
                    <td>${user.loginName}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</center>

</body>
</html>
