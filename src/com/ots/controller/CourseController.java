package com.ots.controller;

import com.ots.entity.Course;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("getCourseName")
    @ResponseBody
    public ResultBean<List<Course>> queryCourseName(){
        ResultBean<List<Course>> resultBean = GetResultBean.getResultBean();
        List<Course> resultList = new ArrayList<Course>();
        resultList = this.courseService.queryCourseName();
        if(resultList.isEmpty()){
            resultBean.setResult(500,"查询失败.",resultList);
        }else {
            resultBean.setResult(200,"查询成功.",resultList);
        }
        return resultBean;


    }

    @RequestMapping("getGrade")
    @ResponseBody
    public ResultBean<List<Course>> queryGrade(){
        ResultBean<List<Course>> resultBean = GetResultBean.getResultBean();
        List<Course> resultList = new ArrayList<Course>();
        resultList = this.courseService.queryGrade();
        if(resultList.isEmpty()){
            resultBean.setResult(500,"查询失败.",resultList);
        }else {
            resultBean.setResult(200,"查询成功.",resultList);
        }
        return resultBean;


    }


}
