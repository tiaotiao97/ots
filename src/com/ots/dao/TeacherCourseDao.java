package com.ots.dao;

import com.ots.entity.TeacherCourse;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseDao {
    public int insert(TeacherCourse teacherCourse);
    public TeacherCourse selectOne(TeacherCourse teacherCourse);
}
