package com.ots.serviceimpl;

import com.ots.dao.CourseDao;
import com.ots.entity.Course;
import com.ots.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDao courseDao;


    @Override
    public int save(Course course) {
        return 0;
    }

    @Override
    public List<Course> queryCourseName() {
        return this.courseDao.selectCourseName();
    }

    @Override
    public List<Course> queryGrade() {
        return this.courseDao.selectGrade();
    }
}
