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
    <script src="http://127.0.0.1:8080/ots/frontsite/js/jquery.js"></script>
</head>
<body>

我是31

    <script>
        var websoket = null;
        if('WebSocket' in window){
            websocket = new WebSocket("ws://"+document.location.host+"/ots"+"/webSocketServer/31/1");
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
            console.log(event);
            var acceptedMessage = event.data.replace(",null","");
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
            alert("会话结束.订单生成中.");
            websocket.close();
        }
    </script>


    发送给：<input id="targetId"><br>
    内容是：<textarea id="info" class=""></textarea>
    <button onclick="send1()"  cmd="sendMsg">
        <span class="btn_text">发送</span>
    </button>
    <button onclick="closeWebSocket()">
        <span class="btn_text">结束会话</span>
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
