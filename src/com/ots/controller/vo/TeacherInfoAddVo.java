package com.ots.controller.vo;

import com.ots.entity.TeacherInfo;
import org.springframework.web.multipart.MultipartFile;

public class TeacherInfoAddVo {
    private TeacherInfo teacherInfo;
    private MultipartFile avatar;

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(TeacherInfo teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
