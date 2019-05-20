package com.ots.service;

import com.ots.entity.TeacherCourse;
import com.ots.entity.TeacherCourseVo;

import java.util.List;

public interface TeacherCourseService {
    public List<TeacherCourseVo> showTeacherCourse(TeacherCourseVo teacherCourseVo);
    //为了查询TeacherCoursePrice而查询teacherCourse对象
    public TeacherCourse showTeacherOne(TeacherCourse teacherCourse);
}
