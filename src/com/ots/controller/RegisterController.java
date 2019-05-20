package com.ots.controller;

import com.ots.entity.User;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user/")
@Controller
public class RegisterController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value="/register",method= RequestMethod.POST)
    @ResponseBody
    public ResultBean<Map<String, Object>> doRegisger(User user){

        Map<String, Object> result = new HashMap<String, Object>();
        ResultBean<Map<String, Object>> resultBean = GetResultBean.getResultBean();

        try {
            result = this.userInfoService.checkRegisterInfo(user);
            if(result.get("status").equals("200")){
                User registerUser = (User)(result.get("data"));

                Boolean bool = this.userInfoService.doRegister(registerUser);
                resultBean.setResult(200,"注册成功.",result);
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            result.put("status", "500");
            result.put("data", "程序异常,即将跳转到注册界面.");
            resultBean.setResult(500,"注册失败.",result);
        }
        return resultBean;
    }

}
