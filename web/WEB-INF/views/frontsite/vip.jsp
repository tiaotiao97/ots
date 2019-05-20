<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>unique</title>
<link type="text/css" href="css/css.css" rel="stylesheet" />
<script type="text/javascript" src="js/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<script>
    $.ajax({
        url:"/ots/teacher/user/queryTeacherUserInfo",
        method:"GET",
        success:function (result) {
            debugger
            user=result.data.user;
          Info=result.data.userInfoObject;
          if(Info!=null){
              $("#name").val(Info.realName);
              $("#name1").text(Info.realName);
              $("#BankCard").val(Info.bankCardNo);
              $("#IdCard").val(Info.idCardNum);
              $('#image').attr("src","http://image.ots.com/"+Info.avatar);
              $('#image1').attr("src","http://image.ots.com/"+Info.avatar);
          }
            $("#phone").val(user.phone);
            $("#identity").text(selectType(user.identity));

        }
    });
function selectType(item) {
    if(item==1){
        return "老师";
    }
    else if(item==2){
        return "学生";
    }
}

</script>
</head>

<body>
 <div class="hrader" id="header">
  <div class="top">
   <a href="login.jsp" style="color:#C94E13;">请登录</a>
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
  <form action="#" method="get" class="subBox">
   <div class="subBox2">
    <input type="text" class="subText" />
    <input type="image" src="images/sub.jpg" width="95" height="32" class="subImg" />
    <div class="hotci">
    <a href="#">酷派大神</a>
    <a href="#">三星s5</a>
    <a href="#">诺基亚1020</a>
    <a href="#">Iphone 6</a>
    <a href="#">htc one</a>
   </div><!--hotci/-->
   </div><!--subBox2/-->
  </form><!--subBox/-->
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
 <div class="navBox navBg3">
  <ul class="nav">
   <li><a href="index.html">首页</a></li>
   <li><a href="buy.html">买家</a></li>
   <li><a href="sell.html">卖家</a></li>
   <li class="navCur"><a href="vip.jsp">会员中心</a></li>
   <li><a href="xuanshang.html">悬赏榜</a></li>
   <li><a href="luntan.html" class="luntan">论坛</a></li>
   <li><a href="help.html">帮助</a></li>
   <div class="clears"></div>
  </ul><!--nav/-->
 </div><!--navBox/-->
 <div class="vipBox">
  <div class="vipLeft">
   <h2 class="headImg"><img src="" width="183" height="169" id="image1" /></h2>
   <h3 class="vipName" id="name1"></h3>
   <dl class="vipNav">
    <dt class="vip_1 vipCur">买家中心</dt>
     <dd><a href="vipOrder.html">我的订单</a></dd>
     <dd><a href="vipShoucang.html">收藏关注</a></dd>
    <dt class="vip_2">账户设置</dt>
     <dd class="ddCur"><a href="#">个人信息</a></dd>
     <dd><a href="vipPwd.html">密码修改</a></dd>
     <dd><a href="vipAddress.jsp">收货地址</a></dd>
     <dd><a href="vipXiaofei.html">消费记录</a></dd>
    <dt class="vip_3">客户服务</dt>
     <dd><a href="vipQuxiao.html">取消订单/退货</a></dd>
     
     <dd><a href="vipTousu.html">我的投诉</a></dd>
   </dl><!--vipNav/-->
  </div><!--vipLeft/-->
  <div class="vipRight">
   <h2 class="vipTitle">个人中心</h2>
   
   <form action="/ots/teacher/user/updateTeacherUserInfo" method="post" enctype="multipart/form-data" class="registerform">
      <table class="grzx" width="705" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="90"><span>*</span>真实姓名：</td>
          <td width="430"><input name="userInfo.realName" type="text" class="text inputxt" id="name" /></td>
          <td rowspan="8" valign="top"><div id="tx"><img src="" id="image"/></div>
            <span class="file"><input name="avatar" type="file" class="file1" /></span></td>
        </tr>
          <tr>
              <td width="90">&nbsp;用户类型：</td>
              <td width="430" id="identity">
              </td>
          </tr>
        <tr>
          <td>&nbsp;电话:</td>
          <td><input type="text" class="text1" datatype="e" id="phone"/></td>
        </tr>
        <tr>
          <td><span>*</span>身份证:</td><td><input name="userInfo.idCardNum" type="text" class="text1 inputxt" datatype="idcard" nullmsg="请填写身份证号码！" errormsg="您填写的身份证号码不对！必须位数字且不低于18位" id="IdCard" /></td>
        </tr>
          <tr>
              <td><span>*</span>银行卡:</td>
              <td><input  name="userInfo.bankCardNo" type="text" class="text1" datatype="e"  id="BankCard"  /></td>

          </tr>
        <tr>
          <td>&nbsp;个人简介：</td>
          <td><textarea tip="请在这里输入您的简介。" altercss="gray" class="gray" name="msg"  datatype="*"value="">请在这里输入您的简介。</textarea></td>
        </tr>

        <tr>
          <td>&nbsp;</td>
          <td><input name="" value="保存资料" type="submit" class="submit" /></td>
        </tr>
      </table>
      </form>
  </div><!--vipRight/-->
  <div class="clears"></div>
 </div><!--vipBox/-->
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
