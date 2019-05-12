package com.ots.service;

import com.ots.entity.Course;

import java.util.List;

public interface CourseService {

    public int save(Course course);
    public List<Course> queryCourseName();
    public List<Course> queryGrade();

}
