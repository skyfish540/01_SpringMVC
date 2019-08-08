<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8"/>
</head>
<body bgcolor="#7fffd4">
<ul>
    <li>用户管理
        <ol>
            <li ><a href="<%=request.getContextPath()%>/user_Add.jsp" target="right">用户添加</a></li>
            <li><a href="<%=request.getContextPath()%>/user/private/find.do" target="right">用户查询</a></li>
        </ol>
    </li>

    <li>试题管理
        <ol>
            <li><a href="<%=request.getContextPath()%>/question_Add.jsp" target="right">试题添加</a></li>
            <li><a href="<%=request.getContextPath()%>/question/private/find.do" target="right">试题查询</a></li>
        </ol>
    </li>

           <li><a href="<%=request.getContextPath()%>/question/private/randExam.do" target="right">参加考试</a></li>
</ul>

</body>
</html>