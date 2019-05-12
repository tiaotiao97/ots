package com.ots.dao;

import com.ots.entity.TeacherInfo;

public interface TeacherInfoDao {

    public int insert(TeacherInfo teacherInfo);
    public TeacherInfo selectOne(TeacherInfo teacherInfo);
    public int update(TeacherInfo teacherInfo);

}
