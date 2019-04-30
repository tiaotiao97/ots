package com.ots.dao;

import com.ots.entity.Message;

import java.util.List;

public interface MessageDao {
    public void insert(Message message);
    public List<Message> select(Message message);
}
