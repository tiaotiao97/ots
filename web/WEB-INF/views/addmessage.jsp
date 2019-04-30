<%--
  Created by IntelliJ IDEA.
  User: Guanyufen
  Date: 2019/4/6
  Time: 2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="sendMessage.action" method="post">
    留言接收者：<input name="targetUserId">
    <br>
    留言内容：<textarea name="content"></textarea>
    <br>
    <input type="submit">
</form>

</body>
</html>
