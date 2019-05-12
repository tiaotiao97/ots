package com.ots.dao;

import com.ots.entity.Course;

import java.util.List;

public interface CourseDao {
    public int insert(Course course);
    public List<Course> selectCourseName();
    public List<Course> selectGrade();

}
