package com.ots.interceptor;

import com.ots.service.TeacherUserService;
import com.ots.serviceimpl.RedisService;
import com.ots.vo.TeacherLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{


    @Autowired
    private TeacherUserService teacherUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //通过request取出cookie，在redis中查询（调用redisService），如果返回空，就让登陆。
        String loginToken = null;
        Cookie[] cookies =  httpServletRequest.getCookies();
        if(cookies != null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("login_token")){
                    loginToken = cookie.getValue();
                }
            }
        }
        TeacherLoginVo teacherLoginVo = this.teacherUserService.queryTeacherLoginVo(loginToken);
        if(teacherLoginVo != null){
            httpServletRequest.setAttribute("teacherLoginVo",teacherLoginVo);
            return true;
        }
        //如果不为空，取到用户信息teacherLoginVo
        //request.setAttribute("自己指定个名字",teacherLoginVo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
