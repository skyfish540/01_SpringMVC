<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
              <center>
                  <span style="color: red;">${errorMessage}</span>
               <form action="<%=request.getContextPath()%>/login.do" method="post">
                   <table border="2">
                       <tr>
                           <td>登录名</td>
                           <td><input type="text" name="loginName"></td>
                       </tr>

                       <tr>
                           <td>密&nbsp;码</td>
                           <td><input type="text" name="password"></td>
                       </tr>

                       <tr>
                           <td colspan="2">
                               <input type="checkbox" name="isAutoLogin" value="yes">十天之内免登录
                           </td>
                       </tr>

                       <tr>
                           <td><input type="submit" value="登录"></td>
                           <td><input type="reset" ></td>
                       </tr>

                   </table>

               </form>
              </center>
</body>
</html>