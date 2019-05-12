<%--
  Created by IntelliJ IDEA.
  User: Guanyufen
  Date: 2019/5/11
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    index test jsp
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script>
        function selectCourseName(item) {
            document.getElementById("courseNameInput").value = item;
        }
        function addCourseNameOptions(item) {
            return "<option value='"+item+"'>"+item+"</option>";
        }

    </script>
    <script>
        $.ajax({
            url:"/ots/course/getCourseName",
            method:"GET",
            success:function (result) {
                alert(JSON.stringify(result));
                var options = "";
                $.each(result.data,function (index,item) {
                    options += addCourseNameOptions(item.courseName)
                });
                $("#courseNameSelectId").html(options);
            }
        })


    </script>
</head>
<body>
<div>

    <div class="btn-group">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="selectCourseNameButton">
            请选择课程

        </button>
        <ul class="dropdown-menu" role="menu" id="courseNameSelectUl">

        </ul>
    </div>

    <form>
        <input type="text" value="" name="courseName" id="courseNameInput">

    </form>

</div>
</body>
</html>
