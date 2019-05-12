package com.ots.utils;

import com.ots.serviceimpl.RedisService;
import com.ots.vo.TeacherLoginVo;
import com.ots.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class ContextUtil {

    public static TeacherLoginVo getTeacherLoginInfo(){
        HttpServletRequest currentRequest = ContextUtil.getCurrentRequest();
        return (TeacherLoginVo)currentRequest.getAttribute("teacherLoginVo");
    }

    public static HttpServletRequest getCurrentRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static UserLoginVo getUserLoginInfo(){
        HttpServletRequest currentRequest = ContextUtil.getCurrentRequest();
        return (UserLoginVo) currentRequest.getAttribute("userLoginVo");
    }

}
