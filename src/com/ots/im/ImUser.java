package com.ots.im;

import com.ots.entity.User;

import java.util.List;

public class ImUser {
    private User user;
    private List<ImMessage> msgs;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ImMessage> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<ImMessage> msgs) {
        this.msgs = msgs;
    }
}
