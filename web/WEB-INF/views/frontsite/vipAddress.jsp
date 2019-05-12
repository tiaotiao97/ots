<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>unique</title>
<link type="text/css" href="css/css.css" rel="stylesheet" />
<script type="text/javascript" src="js/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<link type="text/css" href="css/public_index.css" rel="stylesheet" />
<script type="text/javascript" src="js/js/relay_util.js"></script>
<script type="text/javascript" src="js/js/onlineService.js"></script>
<script>
    function sendReply(targetId,msgId){

        FeedbackUtil.feed(targetId,msgId);

    }


</script>
    <script>
        function showReply(){
            $("#msgTable").attr("style","display:none");
            $("#replyTable").attr("style","");
        }

        function getBack() {
            $("#replyTable").attr("style","display:none");
            $("#msgTable").attr("style","");
            $("#replyTable").text('');
            $("#replyTable").html(" <table class=\"vipAdress\" id=\"msgTable\" style='display: none'><tr>\n" +
                "              <th>发送者</th>\n" +
                "              <th>接收者</th>\n" +
                "              <th>回复内容</th>\n" +
                "              <th>回复id</th>\n" +
                "              <th onclick=getBack()>操作:返回</th>\n" +
                "          </tr></table>");
        }

        function getReply(msgId) {

            $.ajax({
                url:"/ots/reply/showReplyByMsgId.action?msgId="+msgId,
                method:"GET",
                success:function (result2) {

                    $.each(result2.data,function (index,item2) {
                        var option2 =   " <tr>\n" +
                            "     <td>"+item2.fromUserId+"</td>\n" +
                            "     <td>"+item2.targetUserId+"</td>\n" +
                            "     <td>"+item2.content+"</td>\n" +
                            "     <td>"+item2.replyId+"</td>\n" +
                            "     <td><span class=\"green upd\" onclick=getBack()>[返回]</span> | <span class=\"green add\" onclick=sendReply("+item2.fromUserId+","+item2.msgId+")>[回复]</span> </td>\n"+
                            "    </tr>";
                        $("#replyTable").append(option2);
                    });
                }
            });
            showReply();
        }




    </script>

<script>
 $.ajax({
     url:"/ots/message/showMyMessage",
     method:"GET",
     success:function (result) {
     $.each(result.data,function (index,item) {
         var option = " <tr>\n" +
             "     <td>"+item.fromUserName+"</td>\n" +
             "     <td>"+item.targetUserName+"</td>\n" +
             "     <td>"+item.content+"</td>\n" +
             "     <td>"+item.msgId+"</td>\n" +
             "     <td><span class=\"green upd\" onclick=getReply("+item.msgId+")>[查看详情]</span> | <span class=\"green add\" onclick=sendReply("+item.targetUserId+","+item.msgId+")>[回复]</span> </td>\n"+
             "    </tr>";
         $("#msgTable").append(option);

     })
 }
 });

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
 <div class="navBox navBg1">
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
   <h2 class="headImg"><img src="images/vipImg.jpg" width="183" height="169" /></h2>
   <h3 class="vipName">测试webqin</h3>
   <dl class="vipNav">
    <dt class="vip_1 vipCur">买家中心</dt>
     <dd><a href="vipOrder.html">我的订单</a></dd>
     <dd><a href="vipShoucang.html">收藏关注</a></dd>
    <dt class="vip_2">账户设置</dt>
     <dd><a href="vip.jsp">个人信息</a></dd>
     <dd><a href="vipPwd.html">密码修改</a></dd>
     <dd class="ddCur"><a href="vipAddress.jsp">我的留言与回复</a></dd>
     <dd><a href="vipXiaofei.html">消费记录</a></dd>
    <dt class="vip_3">客户服务</dt>
     <dd><a href="vipQuxiao.html">取消订单/退货</a></dd>
     
     <dd><a href="vipTousu.html">我的投诉</a></dd>
   </dl><!--vipNav/-->
  </div><!--vipLeft/-->
  <div class="vipRight">
   <h2 class="vipTitle">我的留言与回复</h2>
   
   <div class="address">
    <div class="addList">
     <label><span class="red">* </span>选择地区:</label>
     <select>
      <option>请选择省</option>
     </select>
     <select>
      <option>请选择市</option>
     </select>
     <select>
      <option>请选择地区</option>
     </select>
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>详细地址:</label>
     <input type="text" />
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>邮政编码:</label>
     <input type="text" />
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>收件人:</label>
     <input type="text" />
    </div><!--addList-->
    <div class="addList">
     <label><span class="red">* </span>手机号码:</label>
     <input type="text" /> 或者固定电话 <input type="text" />
    </div><!--addList--> 
    <div class="addList2">
     <input name="" value=" 确 认 " type="submit" class="submit" />
    </div><!--addList2/-->
   </div><!--address/-->
   <table class="vipAdress" id="msgTable">
    <tr>
     <th>发送者</th>
     <th>接收者</th>
     <th>留言内容</th>

     <th>留言id</th>
     <th>操作</th>
    </tr>

   </table><!--vipAdress/-->

      <table class="vipAdress" id="replyTable" style="display: none">
          <tr>
              <th>发送者</th>
              <th>接收者</th>
              <th>回复内容</th>
              <th>回复id</th>
              <th>操作</th>
          </tr>

      </table><!--vipAdress/-->

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
