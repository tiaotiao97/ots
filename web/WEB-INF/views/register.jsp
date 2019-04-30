<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 	<head>

  </head>
  
  <body>
  	注册功能<br>
  	<form id="teacher_register_submit" action="doTeacherRegister.action" method="post">
    username:<input name="username"><br>
    password:<input name="password"><br>
    telphone:<input name="phone"><br>
    <input id="submit" type="submit">
    </form>
  </body>
</html>
