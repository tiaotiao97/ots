package com.ots.serviceimpl;

import com.ots.dao.UserDao;
import com.ots.entity.User;
import com.ots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUser(User user) {
        return this.userDao.selectOne(user);
    }
}
