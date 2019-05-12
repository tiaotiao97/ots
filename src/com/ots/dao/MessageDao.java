package com.ots.dao;

import com.ots.entity.Message;
import com.ots.entity.MessageUserVo;

import java.util.List;

public interface MessageDao {
    public void insert(Message message);
    public List<MessageUserVo> select(Message message);
}
