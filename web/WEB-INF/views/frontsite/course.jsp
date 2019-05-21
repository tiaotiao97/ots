<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>unique</title>
    <link type="text/css" href="css/css.css" rel="stylesheet"/>
    <link rel="stylesheet" href="layui/css/layui.css" media="all"/>
    <script type="text/javascript" src="js/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/vue.js"></script>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/course.js"></script>
    <script type="text/html" id="tools">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</head>
<body>
<jsp:include page="top.jsp"/>
<div class="vipBox">
    <jsp:include page="left.jsp"/>
    <div class="vipRight">
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal newsAdd_btn" onclick="addCourse('')">添加课程</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-danger batchDel" onclick="delList();">批量删除</a>
            </div>
        <table class="layui-hide" id="courses" lay-filter="tools"></table>
    </div>
    <div class="clears"></div>
</div>
<jsp:include page="foot.jsp"/>
</body>
</html>
