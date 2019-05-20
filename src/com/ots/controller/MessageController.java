package com.ots.controller;

import com.ots.entity.Message;
import com.ots.entity.MessageUserVo;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.MessageService;
import com.ots.service.UserInfoService;
import com.ots.utils.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/message/")
@Controller
public class MessageController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value="addmessage",method= RequestMethod.GET)
    public String addinfo(){
        return "addmessage";
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public void sendMessage(Message message){
        //暂时先写成queryTeacher...之后再增加学生的情况.
        Long currentUserId = ContextUtil.getUserLoginInfo().getUser().getUserId();

        message.setFromUserId(currentUserId);
        this.messageService.sendMessage(message);
    }

    @RequestMapping(value = "showMyMessage")
    @ResponseBody
    public ResultBean<List<MessageUserVo>> showMyMessage(){
        ResultBean<List<MessageUserVo>> resultBean = GetResultBean.getResultBean();
        Long currentUserId = ContextUtil.getUserLoginInfo().getUser().getUserId();
        Message message = new Message();
        message.setFromUserId(currentUserId);
        message.setTargetUserId(currentUserId);
        List<MessageUserVo> result = this.messageService.showMyMessage(message);
        resultBean.setResult(200,"查询成功.",result);
        return resultBean;
    }

}
