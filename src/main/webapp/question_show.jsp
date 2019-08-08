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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_3.4.1.js"></script>
    <script type="text/javascript">
        $(function () {
            //全选与全不选，标题行状态影响数据行状态
            $("#titleCk").bind("click",function () {
                var flag=$(":checkbox[name=ck]")[0].checked
                var dataCkArray=$(":checkbox[name=questionId]")
                for (i=0;i<dataCkArray.length;i++){
                    var dataCk=$(":checkbox[name=questionId]")[i]
                    dataCk.checked=flag;
                }
            });
            //全选与全不选，数据行状态影响标题行
            funDataCK();

            //如果删除时没有选中试题，则提示用户
            $("#deleteBtn").bind("click",function () {
                var ckNum=$(":checkbox[name=questionId]").length;
                var checkedNum=0;
                for (i=0;i<ckNum;i++){
                    var domObj=$(":checkbox[name=questionId]")[i];
                    if (domObj.checked==true){
                        checkedNum++;
                    }
                }
                if (checkedNum==0){
                    alert("请选择要删除的试题")
                }
            })
        })
        //全选与全不选，数据行状态影响标题行
        function funDataCK() {
            var dataCkArray=$(":checkbox[name=questionId]")
            for (i=0;i<dataCkArray.length;i++){
                var dataCk=$(":checkbox[name=questionId]")[i]
                //为数据行上的每个checkbox绑定单击事件
                dataCk.onclick=function () {
                    var ckNum=$(":checkbox[name=questionId]").length;
                    var checkedNum=0;
                    for (i=0;i<ckNum;i++){
                        var domObj=$(":checkbox[name=questionId]")[i];
                        if (domObj.checked==true){
                            checkedNum++;
                        }
                    }
                    if (checkedNum==dataCkArray.length){
                        $(":checkbox[name=ck]")[0].checked=true;
                    }else {
                        $(":checkbox[name=ck]")[0].checked=false;
                    }
                };
            }
        }


    </script>
</head>
<body>
             <center>
                 <form action="<%=request.getContextPath()%>/question/private/delete.do">
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
                            <td>
                                <input type="checkbox" name="ck" id="titleCk"/>
                                <input type="submit" id="deleteBtn" value="删除"/>
                            </td>
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
                                <td><input type="checkbox" name="questionId" value="${question.questionId}"></td>
                            </tr>
                        </c:forEach>

                    </table>
                 </form>
             </center>
</body>
</html>
