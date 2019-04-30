package com.ots.vo;

import com.ots.entity.TeacherInfo;
import com.ots.entity.User;

public class TeacherLoginVo {
    private User user;
    private TeacherInfo teacherInfo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(TeacherInfo teacherInfo) {
        this.teacherInfo = teacherInfo;
    }
}
