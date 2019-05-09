package com.ots.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ots.entity.User;
import com.ots.resultbean.ResultBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginVo {
    private User user;
    private Object userInfoObject;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getUserInfoObject() {
        return userInfoObject;
    }

    public void setUserInfoObject(Object userInfoObject) {
        this.userInfoObject = userInfoObject;
    }
}
