<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_3.4.1.js"></script>
    <script type="text/javascript">
        alert(1111)
        $(function () {
            //为id为uname的元素绑定一个失去焦点的事件
            $("#lgName").bind("blur",function () {
                $.post(
                    "<%=request.getContextPath()%>/checkLoginName.do",
                     {loginName:$(this).val()},
                    function (data) {
                        //此处的设计是为了让submit按钮的默认提交行为失效,意思说当loginName输入框失去焦点后
                        //检测到了登录名已经存在,但是当点击提交依然能添加成功,此时我们就需要让提交默认行为失效
                        //几个函数的执行顺序 onclick,onsubmit,只要 onclick 未 return false 那么就继续执行 onsubmit
                        //只要 onsubmit 未return false 那么表单就被提交出去了
                        if (data.code!="0"){
                            $("#mySpan").html('<span style="color: red">'+data.errorMessage+'</span>')
                            return false;
                        }
                        $("#mySpan").html('<span style="color: green" >'+data.data+'</span>')
                    },
                    "json"
                )
            })
            //判断作用域中的数据是否为空字符串,如果不是空字符串表示有错误信息
            if ("${errorMessage}"!=""){
                $("#mySpan").html('<span style="color: red">${errorMessage}</span>')
            }
        })
    </script>

</head>
<body>
            <center>
                <form action="<%=request.getContextPath()%>/user/private/add.do">

                    <table border="2">
                        <tr>
                            <td>真实姓名</td>
                            <td><input type="text" name="uname" ></td>
                        </tr>

                        <tr>
                            <td>登录名</td>
                            <td><input type="text" name="loginName" id="lgName">
                                <span  id="mySpan" style="color: red">
                                    <span style="color:red">*</span>
                                    ${errorMessage}
                                </span>
                            </td>

                        </tr>

                        <tr>
                            <td>登录密码</td>
                            <td><input type="text" name="password"></td>
                        </tr>

                        <tr>
                            <td>用户邮箱</td>
                            <td><input type="text" name="email"></td>
                        </tr>

                        <tr>
                                <td colspan="2">
                                    <center>
                                    <input type="submit" value="用户注册">&nbsp;&nbsp;&nbsp;
                                    <input type="reset" >
                                    </center>
                                </td>
                        </tr>

                    </table>

                </form>

            </center>
</body>
</html>