<%--
  Created by IntelliJ IDEA.
  User: Guanyufen
  Date: 2019/5/1
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://j2.58cdn.com.cn/js/jquery-1.8.3.js"></script>
</head>
<body>

<script>
    var websoket = null;
    if('WebSocket' in window){
        websocket = new WebSocket("ws://"+document.location.host+"/ots"+"/webSocketServer/31");
    }else {
        alert("当前浏览器不支持websocket")
    }

    websocket.onerror = function () {
        console.log("websocket连接错误.")
    }

    //连接成功的回调方法
    websocket.onopen = function () {
        console.log("websocket连接成功.")
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        var acceptedMessage = event.data.split(",")[0];
        $("#showmessage").append("<p>"+$("#targetId").val()+":"+acceptedMessage+"</p>");
    }

    //连接关闭的回调方法
    websocket.onclose = function() {
        closeWebSocket();
        console.log("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function() {
        closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        console.log("close");
        websocket.close();
    }
</script>


发送给：<input id="targetId"><br>
内容是：<textarea id="info" class=""></textarea>
<button onclick="send1()"  cmd="sendMsg">
    <span class="btn_text">发送</span>
</button>
<br><br><br>
<div id="showmessage"></div>



<script type="text/javascript">
    function send1(){
        var messageContent = $("#info").val();
        var employees ={"messageContent":messageContent,"targetId":$("#targetId").val()}
        websocket.send(JSON.stringify(employees));
        $("#showmessage").append("<p>"+"me:"+messageContent+"</p>")
        $("#info").val("");
    }
</script>



</body>
</html>
