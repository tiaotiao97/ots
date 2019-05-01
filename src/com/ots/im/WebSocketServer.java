package com.ots.im;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ots.entity.Message;
import com.ots.entity.User;
import com.ots.service.TeacherUserService;
import com.ots.utils.ContextUtil;
import com.ots.utils.GetHttpSessionConfigurator;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/webSocketServer/{userId}",configurator=GetHttpSessionConfigurator.class)
public class WebSocketServer {

    public static Map<ImUser,WebSocketServer> clients = new ConcurrentHashMap<ImUser,WebSocketServer>();
    private ImUser imUser;
    private Session session;
    private static int id = 0;
    @Autowired
    private TeacherUserService teacherUserService;

    @OnOpen
    public void onOpen(@PathParam("userId")String userId, Session session){

        User user = new User();
        user.setUserId(Long.valueOf(userId));
        this.session = session;
        this.imUser = new ImUser();
        this.imUser.setUser(user);
        List<ImMessage> list=new ArrayList<ImMessage>();
        imUser.setMsgs(list);

        clients.put(imUser,this);
        System.out.println("已连接..");

    }

    @OnClose
    public void onClose(){
        clients.remove(this.imUser);
    }

    @OnMessage
    public void onMessage(String messageJsonVoString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageJsonVo messageJsonVo = objectMapper.readValue(messageJsonVoString,MessageJsonVo.class);

        String messageContent = messageJsonVo.getMessageContent();
        Long targetId = messageJsonVo.getTargetId();

        System.out.println(messageContent);
        ImMessage imMessage = new ImMessage();
        imMessage.setImMessageFrom(this.imUser.getUser().getUserId());
        imMessage.setImMessageContent(messageContent);
        //targetId
        imMessage.setImMessageTarget(targetId);
        this.imUser.getMsgs().add(imMessage);
        sendMessage(messageContent,targetId);
    }

    @OnError
    public void onError(Throwable error){

    }

    public void sendMessage(String messageContent,Long targetId){
        for(WebSocketServer item:clients.values()){
            if(item.imUser.getUser().getUserId().equals(targetId)){
                item.session.getAsyncRemote().sendText(messageContent+","+imUser.getUser().getUsername());
            }
        }
    }

    public static synchronized Map<ImUser, WebSocketServer> getClients() {
        return clients;
    }





}
