<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>
<h1>首页</h1>
<hr>
<div>
<%--登录--%>
<a href="${pageContext.request.contextPath}/user/jumplogin">登录</a>
<a href="${pageContext.request.contextPath}/user/jumpSuccess">查看用户信息(成功页面)</a>
</div>
<h1> ------------- ------------- ------------- -------------</h1>
<div>
  <form action="${pageContext.request.contextPath}/file/upload" enctype="multipart/form-data" method="post">
    <input type="file" name="file"/>
    <input type="submit" value="上传"/>
  </form>
</div>
<h1> ------------- ------------- ------------- -------------</h1>
<div>
  <form action="${pageContext.request.contextPath}/file/fileTransfer" enctype="multipart/form-data" method="post">
    <input type="file" name="file"/>
    <input type="submit" value="上传transfer"/>
  </form>
</div>
<h1> ------------- ------------- ------------- -------------</h1>
<a href="${pageContext.request.contextPath}/file/download">下载超1</a>
</body>
</html>