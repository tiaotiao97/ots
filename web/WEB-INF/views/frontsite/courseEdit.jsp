<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="wcourseIdth=device-wcourseIdth, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="layui/css/layui.css" media="all" />
</head>

<body class="childrenBody">
<form class="layui-form changePwd">
    <input type="hcourseIdden" name="courseId"  id="courseId" th:value="${courseId}">
    <div class="layui-form-item">
        <label class="layui-form-label">课程名称</label>
        <div class="layui-input-block">
            <input type="text" name="courseName" value="" placeholder="课程名称" courseId="name"  lay-verify="required|name" class="layui-input pwd">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年级</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="0" title="女" checked="">
            <input type="radio" name="sex" value="1" title="男" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" name="coursePrice" value="" placeholder="课程价格"  courseId="age" class="layui-input pwd">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="*">保存</button>
            <button type="button" class="layui-btn layui-btn-primary" onclick="layerclose();">关闭</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/js/jquery-3.4.1.min.js"></script>
<script>
    $(function(){
        var courseId = $("#courseId").val();
        if(courseId != ""){
            $.ajax({
                url: "/user/queryBycourseId",
                data:{"courseId":courseId},
                dataType:"json",
                success: function(data){
                    $("#name").val(data.name);
                    $("#age").val(data.age);
                    $("input[name='sex']").eq(data.sex).attr("checked",'checked');

                },error:function(){
                }
            });
        }
    })

    function layerclose() {
        layui.use(['layer' ], function() {
            var layer = layui.layer;
            var index=parent.layer.getFrameIndex(window.name);//获取当前弹出层的层级
            parent.layer.close(index);//关闭弹出层
            location.reload();//刷新父页面
        })
    }


    layui.use(['form','layer' ], function() {
        var form = layui.form;
        var layer = layui.layer;
        // 添加验证规则
        form.verify({
            name : function(value, item) {
                value = value.trim();
                if (value.length < 0) {
                    return "请输入教师名称";
                }
            }
        });

        form.on('submit(*)', function(data) {
            var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var d = data.field;
            var url = "/user/add";
            if(d.courseId != ""){
                url = "/user/edit";
            }
            $.ajax({
                url: url,
                data:d,
                dataType:"text",
                success: function(data){
                    layer.close(index);
                    if(data == 0){
                        layer.msg("保存成功！");
                        parent.queryTea();
                        parent.close();
                    }else{
                        layer.msg("保存失败！");
                    }
                },error:function(){
                    layer.close(index);
                    layer.msg("保存失败！");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
