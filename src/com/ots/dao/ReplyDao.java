package com.ots.dao;

import com.ots.entity.Reply;

import java.util.List;

public interface ReplyDao {
    public List<Reply> select(Reply reply);
    public void insert(Reply reply);
}
