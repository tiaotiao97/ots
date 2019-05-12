package com.ots.service;

import com.ots.entity.Message;
import com.ots.entity.MessageUserVo;

import java.util.List;

public interface MessageService {
    public void sendMessage(Message message);
    public List<MessageUserVo> showMyMessage(Message message);
}
