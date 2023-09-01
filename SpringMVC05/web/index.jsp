<%--
  Created by IntelliJ IDEA.
  User: zhucm
  Date: 2023-09-01
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style type="text/css">
    a {
        text-decoration: none;
        color: black;
        font-size: 18px;
    }

    h3 {
        width: 180px;
        height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background: deepskyblue;
        border-radius: 4px;
    }
</style>
<html>
<head>
    <title>Spring s coming</title>
</head>
<body>
<h3>
    <a href="${pageContext.request.contextPath}/book/allBook">点击进入书籍列表</a>
</h3>
</body>
</html>
