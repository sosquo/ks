<%--
  Created by IntelliJ IDEA.
  User: zhucm
  Date: 2023-09-03
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
id：<input type="text" id="user_id" value="12580" /><br>
name：<input type="text" id="user_name" value="不告诉你"/><br>
jsonString：<textarea id="jStr" style="size: A4"></textarea><br>
<button id="toJSON" onclick="toJSON()">对象转JSON</button>
<br>
<button id="toObj" onclick="toObj()">JSON转对象</button>
<br>
</body>

<script>
    function toJSON() {
        let elementId = document.getElementById("user_id");
        let elementName = document.getElementById("user_name");
        let elementJStr = document.getElementById("jStr");
        console.log(elementId.value);
        console.log(elementName.value);
        console.log(elementName);
        let data = {user_id: elementId.value, user_name: elementName.value};
        console.log(data);
        let str = JSON.stringify(data);
        console.log(str);
        elementJStr.value = str;
    }

    function toObj() {
        let elementId = document.getElementById("user_id");
        let elementName = document.getElementById("user_name");
        let elementJStr = document.getElementById("jStr");
        let str = elementJStr.value;
        let data = JSON.parse(str);
        elementId.value = data.user_id;
        elementName.value = data.user_name;
    }
</script>
</html>
