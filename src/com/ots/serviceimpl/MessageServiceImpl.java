package com.ots.serviceimpl;

import com.ots.dao.MessageDao;
import com.ots.entity.Message;
import com.ots.entity.MessageUserVo;
import com.ots.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired(required = false)
    private MessageDao messageDao;
    public void sendMessage(Message message){
        this.messageDao.insert(message);

    }

    public List<MessageUserVo> showMyMessage(Message message){

        return this.messageDao.select(message);
    }
}
