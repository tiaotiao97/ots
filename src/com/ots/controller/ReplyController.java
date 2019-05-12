package com.ots.controller;

import com.ots.entity.Reply;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.ReplyService;
import com.ots.service.TeacherUserService;
import com.ots.utils.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public ResultBean<List<Reply>> showReplyByMsgId(Long msgId){
        ResultBean<List<Reply>> resultBean = GetResultBean.getResultBean();
        Reply reply = new Reply();
        reply.setMsgId(msgId);
        List<Reply> result = this.replyService.showMsgReply(reply);
        resultBean.setResult(200,"查询成功.",result);
        return resultBean;
    }

    @RequestMapping(value = "replyMsg",method= RequestMethod.POST)
    @ResponseBody
    public ResultBean<Reply> replyMsg(Reply reply){
        ResultBean<Reply> replyResultBean = GetResultBean.getResultBean();
        replyResultBean.setResult(200,"回复成功.",null);
        //暂时先写成queryTeacher...之后再增加学生的情况.
        Long currentUserId = ContextUtil.getUserLoginInfo().getUser().getUserId();
        reply.setFromUserId(currentUserId);
        this.replyService.replyMsg(reply);
        return replyResultBean;

    }


}
