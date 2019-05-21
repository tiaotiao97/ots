var table;
var layer;
layui.use([ 'layer', 'table', 'element' ], function() {
    table = layui.table;
    layer = layui.layer;
    // 执行一个 table 实例
    table.render({
        elem : '#courses',
        height:350,
        url : '/ots/teacher/course/queryCourses',
        cols : [ [
            {fixed : 'left', width:100, type : 'checkbox'},
            {field: 'courseId', title: '课程Id',hide:true},
            {field : 'courseName', title : '课程名称', width : 200, fixed : 'left'},
            {field : 'grade', title : '年级', width : 140, fixed : 'left'},
            {field : 'coursePrice', title : '课程价格', width : 100, fixed : 'left'},
            {title : '操作', width : 200, align : 'center', fixed : 'left', toolbar : '#tools'}
            ] ]
    });
// 监听工具条
    table.on('tool(tools)', function(obj) { // 注：tool是工具条事件名，test是table原始容器的属性
        var data = obj.data // 获得当前行数据
            , layEvent = obj.event; // 获得 lay-event 对应的值
        if ('edit' == layEvent) {
            alert(data.courseId);
            updateCourse(data.courseId);
        } else if ('del' == layEvent) {
            del(data.courseId);
        }
    });
});

var index;
function addCourse(id) {
    index = parent.layer.open({
        type : 2,
        title : "添加课程",
        area: ['550px', '400px'],
        content : '/ots/teacher/course/courseEdit?id=' + id
    });
    layer.full(index);
}

var index;
function updateCourse(id) {
    index = parent.layer.open({
        type : 2,
        title : "更新课程",
        area: ['550px', '400px'],
        content : '/user/edit?id=' + id
    });
    layer.full(index);
}


function del(id) {
    parent.layer.open({
        type : 1,
        content : '<div style="padding: 20px 80px;">确定删除记录?</div>',
        btn : [ '确定', '取消' ],
        yes : function(index, layero) {
            $.ajax({
                url : "/ots/teacher/course/deleteOne",
                data : {
                    "courseId" : id
                },
                dataType : "text",
                success : function(data) {
                    if(data==0){
                        layer.msg("删除成功！");
                        layer.close(index);
                        table.reload("courses",{where:{},page:{curr:1}});
                    }else{
                        layer.msg("删除失败！");
                    }
                },
                error : function() {
                }
            });
        }
    });

}

/**
 * 获取选中数据
 */
function delList(){
    var checkStatus = table.checkStatus('courses');
    var data = checkStatus.data;
    var id = "";
    for(var i=0;i<data.length;i++){
        id += data[i].courseId;
        if(i<data.length-1){
            id += ",";
        }
    }
    if(data.length != 0){
        del(id);
    }
}
