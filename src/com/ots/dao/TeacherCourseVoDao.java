package com.ots.dao;

import com.ots.entity.TeacherCourseVo;

import java.util.List;

public interface TeacherCourseVoDao {
    public List<TeacherCourseVo> select(TeacherCourseVo teacherCourseVo);

}
