package com.ots.service;

import com.ots.entity.TeacherCourse;
import com.ots.entity.TeacherInfo;
import com.ots.entity.User;
import com.ots.vo.TeacherLoginVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface TeacherUserService {

	public Boolean check(String param, String type);
	public Boolean doRegister(User user);
	public Map<String,Object> checkUsername(User user);
	public Map<String,Object> checkPassword(User user);
	public Map<String,Object> checkPhone(User user);
	public Map<String,Object> checkRegisterInfo(User user);
	public String doTeacherLogin(String cookieLoginToken,String username, String password) throws Exception;
	public TeacherLoginVo queryTeacherLoginVo(String loginToken);
//	public User queryTeacherUserInfoByToken(String loginToken);
	public Boolean saveTeacherInfo(TeacherInfo teacherInfo,String avatarName);
	public Boolean updateTeacherInfo(TeacherInfo teacherInfo,String avatarName);
	public String saveIamge(MultipartFile multipartFile) throws IOException;
	public void changeTeacherStatus(String loginToken,Integer status);
	public int addCourse(TeacherCourse teacherCourse);
	public TeacherInfo queryTeacherByUserId(TeacherInfo teacherInfo);

}
