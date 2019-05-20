package com.ots.service;

import com.ots.entity.TeacherCourse;
import com.ots.entity.UserInfo;
import com.ots.entity.User;
import com.ots.vo.TeacherLoginVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UserInfoService {

	public Boolean check(String param, String type);
	public Boolean doRegister(User user);
	public Map<String,Object> checkUsername(User user);
	public Map<String,Object> checkPassword(User user);
	public Map<String,Object> checkPhone(User user);
	public Map<String,Object> checkRegisterInfo(User user);
	public String doUserLogin(String cookieLoginToken,String username, String password) throws Exception;
	public TeacherLoginVo queryUserLoginVo(String loginToken);
//	public User queryTeacherUserInfoByToken(String loginToken);
	public Boolean saveUserInfo(UserInfo userInfo, String avatarName);
	public Boolean updateUserInfo(UserInfo userInfo, String avatarName);
	public String saveIamge(MultipartFile multipartFile) throws IOException;
	public void changeUserStatus(String loginToken,Integer status);
	public int addCourse(TeacherCourse teacherCourse);
	public UserInfo queryUserByUserId(UserInfo userInfo);

}
