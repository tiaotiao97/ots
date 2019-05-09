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
    登陆：<br>
    <form id="login_form" method="post" action="doLogin.action">
    username:<input name="username"><br>
    password:<input name="password"><br>
    identity:<input name="identity">（老师1，学生2，平台3）<br>
    <input type="submit" name="submit">
    </form>
  </body>
</html>
