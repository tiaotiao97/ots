<%--
  Created by IntelliJ IDEA.
  User: Guanyufen
  Date: 2019/3/5
  Time: 2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    补充用户信息：<br>
    <form action="updateTeacherUserInfo.action" method="post" enctype="multipart/form-data">
        真实姓名：<input name="userInfo.realName"><br>
        身份证号：<input name="userInfo.idCardNum"><br>
        银行卡号：<input name="userInfo.bankCardNo"><br>
        上传头像：<input name="avatar" type="file"><br>
        <input name="submit" type="submit">

    </form>
</body>
</html>
