package com.ots.controller;

import com.ots.entity.User;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login")
    public String login(){
        return "frontsite/login";
    }

    @RequestMapping(value = "doLogin")
    @ResponseBody
    public ResultBean<String> doUserLogin(HttpServletRequest request, HttpServletResponse response,@CookieValue(name = "login_token",required = false)String existedToken, User user){
        ResultBean<String> resultBean = GetResultBean.getResultBean();
        String loginToken;
        try {
            loginToken = this.loginService.doUserLogin(existedToken,user.getUsername(),user.getPassword(),user.getIdentity());
            if(loginToken==null){
                resultBean.setResult(500,"登陆失败.token获取失败",null);
                return resultBean;
            }
            Cookie cookie = new Cookie("login_token",loginToken);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
            resultBean.setResult(200,"登陆成功.",loginToken);
        } catch (Exception e) {
            resultBean.setResult(500,"登陆失败.token获取失败2",null);
            e.printStackTrace();
        }
        return resultBean;
    }

}
