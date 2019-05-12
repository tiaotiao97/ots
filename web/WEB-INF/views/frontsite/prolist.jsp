<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>unique</title>
<link type="text/css" href="css/css.css" rel="stylesheet" />
 <link type="text/css" href="css/base.css" rel="stylesheet" />
 <link type="text/css" href="css/teacher_list.css" rel="stylesheet" />
 <link type="text/css" href="css/main.css" rel="stylesheet" />
 <script type="text/javascript" src="js/teacher_list.js"></script>
<script type="text/javascript" src="js/js/jquery-1.9.1.min.js"></script>
 <link type="text/css" href="css/public_index.css" rel="stylesheet" />
 <script type="text/javascript" src="js/js/feedback_util.js"></script>
 <script type="text/javascript" src="js/js/onlineService.js"></script>

<script type="text/javascript" src="js/js.js"></script>

 <script type="text/javascript" src="js/jquery.js"></script>




 <script>
     function addCourseDiv(item) {

     return "<input id='ps' type='hidden' value=''>\n" +
         "\t\t<div class='listcon'>\n" +
         "\t\t\t\t\t<div style='width:130px;height:170px;'>\t\t\t\t\t\t\t\t\t\t\t\n" +
         "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img class='teacher_pic' src='http://image.ots.com/images/avatar/"+item.userId+"_avatar.jpg' onclick=FeedbackUtil.feed("+item.userId+");>  <!-- 头像 -->\n" +
         "\t\t\t\t\t\t\t\t\t\t\t\t\t<p style='margin-left:25px;'>ID:<span style='color:red;' id='userId'>"+item.userId+"</span></p>\n" +
         "\t\t\t\t\t</div>\t\n" +
         "\t\t\t\t\t\t\n" +
         "\t\t\t\t\t<input class='hidd' type='hidden' value='103319'>\n" +
         "                    <div class='teacher_mx'>\n" +
         "                        \n" +
         "                        <div class='t_mx'>\n" +
         "                            <div class='fl'>\n" +
         "                                <div class='t_name' onclick='data(this)'><span>"+item.realName+"</span></div>\n" +
         "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class='sch'>"+item.grade+"年级-"+item.courseName+"</div>\n" +
         "\t\t\t\t\t\t\t\t\n" +
         "\t\t\t\t\t\t\t</div>\n" +
         "                            <div><strong style='color:orange'>"+item.coursePrice+" </strong>元/小时起</div>\n" +
         "                        </div>\n" +
         "                \n" +
         "                        <div class='t_mx'>\n" +
         "                            <div class='fl keyword'>\n" +
         "                                <p class='brief_1'></p>\n" +
         "                                <p class='brief_2'>海淀区某市属重点高中一线在职高级教师。毕业于北京大学生物物理专业，研究生学历。从事高中物理教学10余年，多年高考把关经验，多次参与高三年级期中、期末、一模、二模命题工作，参加高考阅卷工作。对北京高考物理命题趋势有深刻的把握。有丰富的高考物理辅导经验，对不同层次的学生均能有效提高成绩。物理学科知识系统性非常强，学好物理要善于构建知识网络，通过典型题型以点带面进行模块化复习学习；力学模块、电学模块、光热原不同模块特点不同，学习策略也各有特点，掌握学习方法和技巧会事半功倍。善于根据学生的具体学习情况个性化辅导，授课轻松幽默，快乐教学。</p>\n" +
         "                            </div>\n" +
         "                            <div class='fr'>\n" +
                                       "<div><img/src='images/im.jpg' onclick='alert("+item.userId+")'></div>"+
         "                                <div class='comment'>\t\t\t\t\t\t\t\t\t\n" +
         "                                <!--240人看过  &nbsp;  0条评价</br>-->\n" +
         "\t\t\t\t\t\t\t\t<p style='width:94px;'>\n" +
         "                                 2019-05-11   \n" +
         "\t\t\t\t\t\t\t\t </p>\n" +
         "                                </div>\n" +
         "\t\t\t\t\t\t\t\t\n" +
         "                            </div>\n" +
         "                        </div>\n" +
         "                \n" +
         "                    </div>\n" +
         "                </div>"
     }
  
     function createCoursesList(dataList) {
         var con = "";
         $.each(dataList,function (index,item) {
//             con += "<p>"+item.realName+"</p>";
             con += addCourseDiv(item);
         });
         $(".teacher_list").html(con);
     }
 </script>

<script>
 function ajaxQuery() {

     var courseName = document.getElementById("selectedCourseName").innerHTML;
     var courseGrade = document.getElementById("selectedCourseGrade").innerHTML;

     $.ajax(
         {
             url:"/ots/teacher/course/queryTeacherCourses",
             method:"POST",
             data:{"courseName":courseName,"grade":courseGrade},
             success:function(result){
                 createCoursesList(result.data);
             }
         }
     );
 }

</script>

 <script>
     function selectCourseName(item) {
         document.getElementById("courseNameInput").value = item;
     }

     function addCondition(targetId,item){

         document.getElementById(targetId).innerHTML = item;
         ajaxQuery();
     }


 </script>


 <script>
     $.ajax({
         url:"/ots/course/getGrade",
         method:"GET",
         success:function (result) {
             $.each(result.data,function (index,item) {
                 var option = addCourseGradeOptions(item.grade);
                 $("#select1").append(option);
             });

         }
     })

 </script>


 <script>
     function selectCourseName(item) {
         document.getElementById("courseNameInput").value = item;
     }
     function addCourseNameOptions(item) {
         return "<dd><a value='"+item+"' onclick=addCondition('selectedCourseName','"+item+"')>"+item+"</a></dd>";
     }

     function addCourseGradeOptions(item) {
         return "<dd><a value='"+item+"' onclick=addCondition('selectedCourseGrade','"+item+"')>"+item+"</a></dd>";
     }


 </script>
 <script>
     $.ajax({
         url:"/ots/course/getCourseName",
         method:"GET",
         success:function (result) {
             $.each(result.data,function (index,item) {
                 var option = addCourseNameOptions(item.courseName);
                 $("#select3").append(option);
             });
         }
     })

 </script>



</head>

<body onload="ajaxQuery()">
 <div class="hrader" id="header">
  <div class="top">
   <a href="/ots/frontsite/login" style="color:#C94E13;">请登录</a>
   <a href="reg.html">注册</a>
   <ul class="topNav">
     <li><a href="order.html">我的订单 </a></li>
    <li class="gouwuche"><a href="car.html">购物车</a> <strong style="color:#C94E13;">3</strong></li>
    <li class="shoucangjia"><a href="shoucang.html">收藏夹</a></li>
    <li class="kefus"><a href="#">联系客服</a></li>
    <li><a href="#" class="lan">中文</a></li>
    <li><a href="#" class="lan">English</a></li>
    <div class="clears"></div>
   </ul><!--topNav/-->
  </div><!--top/-->
 </div><!--hrader/-->
 <div class="mid">
  <h1 class="logo" style="text-align:left;">
  <a href="index.html"><img src="images/logo.png" width="304" height="74" /></a>
  </h1>

  <div class="ding-gou">
   <div class="ding">
    <a href="order.html"><img src="images/dingdan.jpg" width="106" height="32" /></a>
   </div><!--ding/-->
   <div class="gou">
    <a href="car.html"><img src="images/gouwuche.jpg" width="126" height="32" /></a>
   </div><!--gou/-->
   <div class="clears"></div>
  </div><!--ding-gou/-->
 </div><!--mid-->
 <div class="navBox navBg1">
  <ul class="nav">
   <li><a href="index.html">首页</a></li>
   <li><a href="buy.html">买家</a></li>
   <li><a href="sell.html">卖家</a></li>
   <li><a href="vip.jsp">会员中心</a></li>
   <li><a href="xuanshang.html">悬赏榜</a></li>
   <li><a href="luntan.html" class="luntan">论坛</a></li>
   <li><a href="help.html">帮助</a></li>
   <div class="clears"></div>
  </ul><!--nav/-->
 </div><!--navBox/-->
 <div>
  <div></div>


 <div class='main' style="margin-left: 200px">

  <div class="top_px">
   <ul class="select">
    <li class="select-list">
     <dl id="select3" class="select3">
     <dt>课程名称：</dt>

    </dl>


     <%--<select class="kecheng" name="" id="courseNameSelectId">--%>

     <%--</select>--%>


    </li>
    <li class="select-list">
     <dl id="select1" class="select3">
      <dt>课程年级：</dt>
     </dl>


    </li>
    <li class="select-result">
     <dl>
      <dt>已选条件：</dt>
      <dd class="select-no" id="selectedCourseName" onclick="addCondition('selectedCourseName','')"></dd>
      <dd class="select-no" id="selectedCourseGrade" onclick="addCondition('selectedCourseGrade','')"></dd>
     </dl>
    </li>
   </ul>

  </div>


  <div class="teacher_list">
  </div>

 </div>

 <div class="footBox">
  <div class="footers">
   <div class="footersLeft">
    <a href="index.html"><img src="images/ftlogo.jpg" width="240" height="64" /></a>
    <h3 class="ftphone">400 000 0000 </h3>
    <div class="ftKe">
     客服 7x24小时(全年无休)<br />
     <span>客服邮箱：kefu@webqin.net </span>
    </div><!--ftKe/-->
   </div><!--footersLeft/-->
   <div class="footersRight">
    <ul>
     <li class="ftTitle">新手指南</li>
     <li><a href="#">购物流程</a></li>
     <li><a href="#">会员计划及划分</a></li>
     <li><a href="#">优惠券规则</a></li>
     <li><a href="#">联系客服</a></li>
     <li><a href="#">常见问题</a></li>
    </ul>
    <ul>
     <li class="ftTitle">付款方式</li>
     <li><a href="#">在线支付</a></li>
     <li><a href="#">礼品卡支付</a></li>
     <li><a href="#">货到付款</a></li>
     <li><a href="#">银行付款</a></li>
    </ul>
    <ul>
     <li class="ftTitle">配送服务</li>
     <li><a href="#">配送时效及运费</a></li>
     <li><a href="#">超时赔付</a></li>
     <li><a href="#">验货与签收</a></li>
     <li><a href="#">配货信息跟踪</a></li>
    </ul>
    <ul>
     <li class="ftTitle">售后服务</li>
     <li><a href="#">退换货政策</a></li>
     <li><a href="#">退换货区域</a></li>
     <li><a href="#">退款时限</a></li>
     <li><a href="#">先行赔付</a></li>
     <li><a href="#">发票说明</a></li>
    </ul>
    <ul>
     <li class="ftTitle">特色服务</li>
     <li><a href="#">礼品卡</a></li>
     <li><a href="#">产品试用</a></li>
     <li><a href="#">花粉中心</a></li>
     <li><a href="#">快速购物</a></li>
     <li><a href="#">推荐好友</a></li>
    </ul>

    <div class="clears"></div>
   </div><!--footersRight/-->
   <div class="clears"></div>
  </div><!--footers/-->
 </div><!--footBox/-->
 <div class="footer" style="text-align:left;">
  <a href="#">关于我们</a>
  <a href="#">友情链接</a>
  <a href="#">版权声明</a>
  <a href="#">网站地图</a>
  <br />
  <span>&copy; 2014 Unqezi 使用前必读 更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></span>
 </div><!--footer/-->
</body>
</html>
