package com.ots.controller;

import com.ots.entity.Message;
import com.ots.service.MessageService;
import com.ots.service.TeacherUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/message/")
@Controller
public class MessageController {

    @Autowired
    private TeacherUserService teacherUserService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value="addmessage",method= RequestMethod.GET)
    public String addinfo(){
        return "addmessage";
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public void sendMessage(@CookieValue(value = "login_token")String loginToken, Message message){
        //暂时先写成queryTeacher...之后再增加学生的情况.
        Long currentUserId = this.teacherUserService.queryTeacherLoginVo(loginToken).getUser().getUserId();
        message.setFromUserId(currentUserId);
        this.messageService.sendMessage(message);
    }

    @RequestMapping(value = "showMyMessage")
    public void showMyMessage(@CookieValue(value = "login_token")String loginToken){
        Long currentUserId = this.teacherUserService.queryTeacherLoginVo(loginToken).getUser().getUserId();
        Message message = new Message();
        message.setFromUserId(currentUserId);
        List<Message> result = this.messageService.showMyMessage(message);

        System.out.println(result);
    }

}
