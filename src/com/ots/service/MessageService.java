package com.ots.service;

import com.ots.entity.Message;

import java.util.List;

public interface MessageService {
    public void sendMessage(Message message);
    public List<Message> showMyMessage(Message message);
}
