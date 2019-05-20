package com.ots.vo;

import com.ots.entity.UserInfo;
import com.ots.entity.User;

public class TeacherLoginVo {
    private User user;
    private UserInfo userInfo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
