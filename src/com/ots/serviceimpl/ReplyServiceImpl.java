package com.ots.serviceimpl;

import com.ots.dao.ReplyDao;
import com.ots.entity.Reply;
import com.ots.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired(required = false)
    private ReplyDao replyDao;

    @Override
    public List<Reply> showMsgReply(Reply reply) {
        return this.replyDao.select(reply);
    }

    @Override
    public void replyMsg(Reply reply) {
        this.replyDao.insert(reply);
    }


}
