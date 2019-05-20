package com.ots.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ots.dao.UserInfoDao;
import com.ots.dao.UserDao;
import com.ots.entity.UserInfo;
import com.ots.entity.User;
import com.ots.service.LoginService;
import com.ots.vo.UserLoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserInfoDao userInfoDao;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //暂时返回String，后期改为UserLoginVo
    public UserLoginVo getUserLoginVo(String loginToken) throws Exception{
        if(StringUtils.isEmpty(loginToken)){
            return null;
        }
        String userJsonData = this.redisService.get("loginToken_"+loginToken);
        if(StringUtils.isEmpty(userJsonData)){
            return null;
        }
        try{
            return MAPPER.readValue(userJsonData,UserLoginVo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public String doUserLogin(String loginToken, String username, String password,Integer identity) throws Exception{

        User inputUser = new User();
        inputUser.setUsername(username);
        //inputUser.setIdentity(identity);
        User user = this.userDao.selectOne(inputUser);
        if(null == user){
            //用户不存在
            return null;
        }
        if(!StringUtils.equals(DigestUtils.md5Hex(password), user.getPassword())){
            //密码不正确
            return null;
        }
        UserLoginVo userLoginVo = getUserLoginVo(loginToken);
        UserLoginVo userLoginVo2 = new UserLoginVo();
        //判断当前cookie是否登陆，如果已经登陆
        if(loginToken!=null && userLoginVo!=null){
            //避免客户端有token，也通过该token从redis中拿到了user。但这个token不是当前输入用户名对应的token。这个token是之前的用户登陆留下的
            //比如：客户端原有用户A的token。但目前B用户在执行登陆操作。redis中通过token-A取到了userA。
            //如果不判断一下用户名，就会把userA的token返回去。判断用户名，确保从redis中取到的，就是现在客户端要登陆的用户。
            if(userLoginVo.getUser().getUsername().equals(username)){
                return loginToken;
            }
        }

        //如果没有登陆
        //如果是老师


        UserInfo userInfoCondition = new UserInfo();
        userInfoCondition.setUserId(user.getUserId());
        UserInfo userInfo = this.userInfoDao.selectOne(userInfoCondition);

        userLoginVo2.setUser(user);
        userLoginVo2.setUserInfoObject(userInfo);



        String newLoginToken = DigestUtils.md5Hex(username + System.currentTimeMillis());
        this.redisService.set("loginToken_"+newLoginToken, MAPPER.writeValueAsString(userLoginVo2));

        return newLoginToken;
    }
}
