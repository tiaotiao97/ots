package com.ots.controller;

import com.ots.entity.TeacherCourse;
import com.ots.entity.User;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.TeacherCourseService;
import com.ots.entity.TeacherCourseVo;
import com.ots.utils.ContextUtil;
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

    @RequestMapping(value = "querycourses", method = RequestMethod.GET)
    public String showteachercourse() {
        return "querycourses";
    }


    @RequestMapping("queryTeacherCourses")
    @ResponseBody
    public ResultBean<List<TeacherCourseVo>> queryTeacherCourseList(TeacherCourseVo teacherCourseVo) {
        List<TeacherCourseVo> teacherCourseList = this.teacherCourseService.showTeacherCourse(teacherCourseVo);
        ResultBean<List<TeacherCourseVo>> resultBean = GetResultBean.getResultBean();
        resultBean.setCode(200);
        resultBean.setMsg("查询成功.");
        resultBean.setData(teacherCourseList);
        return resultBean;
    }
    @RequestMapping("queryCourses")
    @ResponseBody
    public ResultBean<List<TeacherCourseVo>> queryCourseList(TeacherCourseVo teacherCourseVo) {
        User user = ContextUtil.getUserLoginInfo().getUser();
        teacherCourseVo.setUserId(user.getUserId());
        List<TeacherCourseVo> teacherCourseList = this.teacherCourseService.showTeacherCourse(teacherCourseVo);
        ResultBean<List<TeacherCourseVo>> resultBean = GetResultBean.getResultBean();
        resultBean.setResult(200,"",teacherCourseList.size(),teacherCourseList);
        return resultBean;
    }

    @RequestMapping("deleteOne")
    @ResponseBody
    public int deleteOne(TeacherCourse teacherCourse) {
        User user = ContextUtil.getUserLoginInfo().getUser();
        teacherCourse.setTeacherId(user.getUserId());
        int i = this.teacherCourseService.deleteTeacherCourseByCourseId(teacherCourse);
        return 0;
    }

}
