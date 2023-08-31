<%--
  Created by IntelliJ IDEA.
  User: zhucm
  Date: 2023-08-31
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>提交一个小表单</title>
  </head>
  <body>
  <form action="/hello5">
    id <input type="text " name="uid" title="id" placeholder="请输入id" />
    名称 <input type="text " name="name" placeholder="名称..."/>
    <button type="submit">提交</button>
  </form>
  </body>
</html>
