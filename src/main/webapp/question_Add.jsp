<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery_3.4.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#title").bind("blur",function () {
                $.post(
                    "<%=request.getContextPath()%>/question/check.do",
                    {title:$(this).val()},
                    function (data) {
                        if (data.code!="0"){
                            $("#mySpan").html('<span style="color: red">'+data.errorMessage+'</span>')
                            return false
                        }
                        $("#mySpan").html('<span style="color: green">'+data.errorMessage+'</span>')
                    },
                    "json"
                )
            })
            if ("${errorMessage}"!="") {
                alert(1)
                $("#mySpan").html('<span style="color: red" >${errorMessage}</span>')
            }
        })
    </script>
</head>
<body>
          <center>
              <form action="<%=request.getContextPath()%>/question/private/add.do">

                  <table border="2">
                      <tr>
                          <td>题目</td>
                          <td><input type="text" name='title' id="title" value="${questionData.title}">

                              <span style="color: red" id="mySpan">*</span>
                          </td>
                      </tr>
                      <tr>
                          <td>A</td>
                          <td><input type="text" name='optionA'></td>
                      </tr>
                      <tr>
                          <td>B</td>
                          <td><input type="text" name='optionB'></td>
                      </tr>
                      <tr>
                          <td>C</td>
                          <td><input type="text" name='optionC'></td>
                      </tr>
                      <tr>
                          <td>D</td>
                          <td><input type="text" name='optionD'></td>
                      </tr>

                      <tr>
                          <td>answer</td>
                          <td>
                              <input type="radio" name='answer' value="A">A
                              <input type="radio" name='answer' value="B">B
                              <input type="radio" name='answer' value="C">C
                              <input type="radio" name='answer' value="D">D
                          </td>
                      </tr>
                      <tr>
                          <td colspan="2">
                              <center>
                                  <input type="submit" value="新增试题">&nbsp;&nbsp;&nbsp;
                                  <input type="reset" >
                              </center>
                          </td>
                      </tr>
                  </table>

              </form>

          </center>
</body>
</html>