package com.ots.controller;

import com.ots.entity.Reply;
import com.ots.service.ReplyService;
import com.ots.service.TeacherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RequestMapping(value = "/reply/")
@Controller
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private TeacherUserService teacherUserService;

    @RequestMapping(value="replymsg",method= RequestMethod.GET)
    public String addinfo(){
        return "replymsg";
    }

    @RequestMapping("showReplyByMsgId")
    public void showReplyByMsgId(Long msgId){
        Reply reply = new Reply();
        reply.setMsgId(msgId);
        List<Reply> result = this.replyService.showMsgReply(reply);
    }

    @RequestMapping(value = "replyMsg",method= RequestMethod.POST)
    public void replyMsg(@CookieValue(name = "login_token")String loginToken, Reply reply){
        //暂时先写成queryTeacher...之后再增加学生的情况.
        Long currentUserId = this.teacherUserService.queryTeacherLoginVo(loginToken).getUser().getUserId();
        reply.setFromUserId(currentUserId);
        this.replyService.replyMsg(reply);

    }


}
