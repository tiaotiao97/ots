package com.ots.service;

import com.ots.entity.TeacherCourse;
import com.ots.entity.TeacherCourseVo;

import java.util.List;

public interface TeacherCourseService {
    public List<TeacherCourseVo> showTeacherCourse(TeacherCourseVo teacherCourseVo);

    public TeacherCourse showTeacherOne(TeacherCourse teacherCourse);

    public int deleteTeacherCourseByCourseId(TeacherCourse teacherCourse);
}
