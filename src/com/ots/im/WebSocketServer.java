package com.ots.im;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ots.entity.Order;
import com.ots.entity.TeacherCourse;
import com.ots.entity.User;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.OrderService;
import com.ots.service.TeacherCourseService;
import com.ots.service.UserService;
import com.ots.utils.GetHttpSessionConfigurator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/webSocketServer/{userId}/{courseId}",configurator=GetHttpSessionConfigurator.class)
public class WebSocketServer {

    public static Map<ImUser,WebSocketServer> clients = new ConcurrentHashMap<ImUser,WebSocketServer>();
    //imUser是自己
    private ImUser imUser;
    private Session session;
    private long startTime;
    private long endTime;
    private Long targetId;
    private Long courseId;

    private static int id = 0;


    @OnOpen
    public void onOpen(@PathParam("userId")String userId, @PathParam("courseId")String courseId,Session session){
        this.courseId = Long.valueOf(courseId);
        User user = new User();
        user.setUserId(Long.valueOf(userId));
        this.session = session;
        //imUser是聊天的对方
        this.imUser = new ImUser();
        this.imUser.setUser(user);
        List<ImMessage> list=new ArrayList<ImMessage>();
        imUser.setMsgs(list);

        clients.put(imUser,this);
        System.out.println("已连接..");
        this.startTime = System.currentTimeMillis();
    }

    @OnClose
    public void onClose(Session session){
        clients.remove(this.imUser);
        this.endTime = System.currentTimeMillis();
        long time = this.endTime-this.startTime;
        //处理订单信息
        this.CreateOrder(time,this.targetId,this.imUser.getUser().getUserId(),this.courseId);
    }

    public Object getService(Class tclass){
        WebApplicationContext wac = (WebApplicationContext) ContextLoader.getCurrentWebApplicationContext().getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        return  wac.getBean(tclass);
    }

    public Double getCoursePrice(Long targetId,Long courseId){
        if(targetId==null || courseId==null){
            return null;
        }
        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setTeacherId(targetId);
        teacherCourse.setCourseId(courseId);
        TeacherCourseService teacherCourseService = (TeacherCourseService) this.getService(TeacherCourseService.class);
        return teacherCourseService.showTeacherOne(teacherCourse).getCoursePrice();
    }

    public int getUserIdentity(Long userId){
        User user = new User();
        user.setUserId(userId);
        UserService userService = (UserService)this.getService(UserService.class);
        return userService.queryUser(user).getIdentity();
    }

    public ResultBean<Order> CreateOrder(Long talkTime,Long targetId,Long fromId,Long courseId){

        ResultBean<Order> orderResultBean = GetResultBean.getResultBean();
        //通过fromId查询身份信息
        int identity = this.getUserIdentity(fromId);
        //如果fromId是老师，说明当前用户是老师。则不生成订单
        if(identity==1L){
            orderResultBean.setResult(500,"创建订单失败.",null);
            return orderResultBean;
        }
        Double coursePrice = this.getCoursePrice(targetId,courseId);
        if(coursePrice==null){
            orderResultBean.setResult(500,"创建订单失败.",null);
        }else {
            Order order = new Order();
            order.setCourseId(courseId);
            order.setTeacherId(targetId);
            order.setStudentId(fromId);
            order.setPrice(talkTime*coursePrice/(1000*60*30));
            OrderService orderService = (OrderService) this.getService(OrderService.class);
            orderService.addOrder(orderService.createOrder(order));
            orderResultBean.setResult(200,"创建订单成功.",order);
        }
        return orderResultBean;
    }

    @OnMessage
    public void onMessage(String messageJsonVoString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageJsonVo messageJsonVo = objectMapper.readValue(messageJsonVoString,MessageJsonVo.class);

        String messageContent = messageJsonVo.getMessageContent();
        Long targetId = messageJsonVo.getTargetId();
        //为了生成订单用
        this.targetId = targetId;
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
        onClose(session);
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
