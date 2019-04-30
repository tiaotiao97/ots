package com.ots.service;

import com.ots.dao.ReplyDao;
import com.ots.entity.Reply;

import java.util.List;

public interface ReplyService {
    public List<Reply> showMsgReply(Reply reply);
    public void replyMsg(Reply reply);
}
