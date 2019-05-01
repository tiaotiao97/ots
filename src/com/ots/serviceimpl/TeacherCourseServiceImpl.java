package com.ots.serviceimpl;

import com.ots.dao.TeacherCourseVoDao;
import com.ots.service.TeacherCourseService;
import com.ots.entity.TeacherCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    private TeacherCourseVoDao teacherCourseVoDao;

    @Override
    public List<TeacherCourseVo> showTeacherCourse(TeacherCourseVo teacherCourseVo) {
        return this.teacherCourseVoDao.select(teacherCourseVo);
    }
}
