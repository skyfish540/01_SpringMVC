<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8"/>
</head>
<frameset rows="15%,75%,*" border="2">
    <frame src="<%=request.getContextPath()%>/top.jsp">
    <frameset cols="15%,*">
        <frame src="<%=request.getContextPath()%>/left.jsp">
        <frame name="right" style="background-color: darkseagreen">
    </frameset>
    <frame src="<%=request.getContextPath()%>/footer.jsp">

</frameset>
<body>
</body>
</html>