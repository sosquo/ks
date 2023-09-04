<%--
  Created by IntelliJ IDEA.
  User: zhucm
  Date: 2023-09-04
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>你好</title>
  </head>
<%--  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>--%>
  <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
  <body>
  <div>
    用户名：<input type="text" id="txtName" onblur="a1()"/>
  </div>
<h1> --------------------------------- </h1>
  <div>
    <input type="button" id="btn" value="获取列表数据">
    <table width="80%" align="center">
      <thead>
      <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
      </tr>
      </thead>
      <tbody  id="content">
      </tbody>
    </table>
  </div>
  <h1> --------------------------------- </h1>
  <p>
    用户名:<input type="text" id="name" onblur="a1()"/>
    <span id="userInfo"></span>
  </p>
  <p>
    密码:<input type="text" id="pwd" onblur="a2()"/>
    <span id="pwdInfo"></span>
  </p>
  </body>
<script>
  function a1() {
    $.post({
      url: "${pageContext.request.contextPath}/a1",
      data: {'name': $("#txtName").val()},
      success: function (data, status) {
        alert(data + " -- " + status);
      },
    })
  }


  $(function () {
    $("#btn").click(function () {
      $.post("${pageContext.request.contextPath}/a2",function (data) {
        console.log(data)
        var html="";
        for (var i = 0; i <data.length ; i++) {
          html+= "<tr>" +
                  "<td>" + data[i].name + "</td>" +
                  "<td>" + data[i].age + "</td>" +
                  "<td>" + data[i].sex + "</td>" +
                  "</tr>"
        }
        $("#content").html(html);
      });
    })
  })


  function a1(){
    $.post({
      url:"${pageContext.request.contextPath}/a3",
      data:{'name':$("#name").val()},
      success:function (data) {
        if (data.toString()=='OK'){
          $("#userInfo").css("color","green");
        }else {
          $("#userInfo").css("color","red");
        }
        $("#userInfo").html(data);
      }
    });
  }
  function a2(){
    $.post({
      url:"${pageContext.request.contextPath}/a3",
      data:{'pwd':$("#pwd").val()},
      success:function (data) {
        if (data.toString()=='OK'){
          $("#pwdInfo").css("color","green");
        }else {
          $("#pwdInfo").css("color","red");
        }
        $("#pwdInfo").html(data);
      }
    });
  }
</script>
</html>
