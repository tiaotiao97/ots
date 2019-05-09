package com.ots.service;

import com.ots.vo.UserLoginVo;

public interface LoginService {
    public String doUserLogin(String loginToken,String username,String password,Integer identity) throws Exception;
    public UserLoginVo getUserLoginVo(String loginToken) throws Exception;

}
