package com.ots.controller.vo;

import com.ots.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

public class TeacherInfoAddVo {
    private UserInfo userInfo;
    private MultipartFile avatar;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
