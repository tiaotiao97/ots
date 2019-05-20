package com.ots.dao;

import com.ots.entity.UserInfo;

public interface UserInfoDao {

    public int insert(UserInfo userInfo);
    public UserInfo selectOne(UserInfo userInfo);
    public int update(UserInfo userInfo);

}
