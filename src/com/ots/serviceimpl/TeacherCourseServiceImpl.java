package com.ots.serviceimpl;

import com.ots.dao.TeacherCourseDao;
import com.ots.dao.TeacherCourseVoDao;
import com.ots.entity.TeacherCourse;
import com.ots.service.TeacherCourseService;
import com.ots.entity.TeacherCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    private TeacherCourseVoDao teacherCourseVoDao;

    @Autowired
    private TeacherCourseDao teacherCourseDao;

    @Override
    public List<TeacherCourseVo> showTeacherCourse(TeacherCourseVo teacherCourseVo) {
        return this.teacherCourseVoDao.select(teacherCourseVo);
    }

    @Override
    public TeacherCourse showTeacherOne(TeacherCourse teacherCourse) {
        return this.teacherCourseDao.selectOne(teacherCourse);
    }


}
