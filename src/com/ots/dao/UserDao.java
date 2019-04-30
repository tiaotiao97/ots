package com.ots.dao;

import com.ots.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	public User selectOne(User user);

	public Integer insertUser(User user);

}
