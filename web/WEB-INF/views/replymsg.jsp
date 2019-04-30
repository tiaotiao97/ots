<%--
  Created by IntelliJ IDEA.
  User: Guanyufen
  Date: 2019/4/7
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="replyMsg.action" method="post">
        要回复的消息Id：<input name="msgId">
        <br>
        要回复的用户Id：<input name="targetUserId">
        <br>
        回复内容：<textarea name="content"></textarea>
        <br>
        <input type="submit">

    </form>
</body>
</html>
