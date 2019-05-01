<%--
  Created by IntelliJ IDEA.
  User: Guanyufen
  Date: 2019/5/1
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="queryTeacherCourses.action">
        填0个或1个或多个条件进行查询<br>
        老师id：<input name="userId"><br>
        老师姓名：<input name="realName"><br>
        课程id：<input name="courseId"><br>
        课程价格：<input name="coursePrice"><br>
        课程名：<input name="courseName"><br>
        年级：<input name="grade"><br>
        <input type="submit">


    </form>
</body>
</html>
