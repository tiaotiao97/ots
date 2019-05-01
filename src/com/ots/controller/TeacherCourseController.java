package com.ots.controller;

import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.TeacherCourseService;
import com.ots.entity.TeacherCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/teacher/course/")
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    @RequestMapping(value = "querycourses",method = RequestMethod.GET)
    public String showteachercourse(){
        return "querycourses";
    }


    @RequestMapping("queryTeacherCourses")
    @ResponseBody
    public ResultBean<List<TeacherCourseVo>> queryTeacherCourseList(TeacherCourseVo teacherCourseVo){
        List<TeacherCourseVo> teacherCourseList = this.teacherCourseService.showTeacherCourse(teacherCourseVo);
        ResultBean<List<TeacherCourseVo>> resultBean = GetResultBean.getResultBean();
        resultBean.setCode(200);
        resultBean.setMsg("查询成功.");
        resultBean.setData(teacherCourseList);
        return resultBean;
    }

}
