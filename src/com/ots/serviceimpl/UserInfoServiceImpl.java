package com.ots.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ots.dao.TeacherCourseDao;
import com.ots.dao.UserInfoDao;
import com.ots.dao.UserDao;
import com.ots.entity.TeacherCourse;
import com.ots.entity.UserInfo;
import com.ots.entity.User;
import com.ots.service.UserInfoService;
import com.ots.utils.ContextUtil;
import com.ots.vo.TeacherLoginVo;
import com.ots.vo.UserLoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Value("${nginx.root}")
	String NGINX_ROOT_LOCATION;

	@Value("${image.root}")
	String IMAGE_ROOT_LOCATION;

	@Value("${image.avatar}")
	String AVATAR_LOCATION;

	@Autowired(required=false)
	private UserDao userDao;

	@Autowired(required = false)
	private UserInfoDao userInfoDao;
	
	@Autowired
	private RedisService redisService;

	@Autowired
	private TeacherCourseDao teacherCourseDao;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public Boolean check(String param, String type) {
		
		User teacherUser = new User();
		
		if(type.equals("username")){
			teacherUser.setUsername(param);
		}else if (type.equals("phone")) {
			teacherUser.setPhone(param);
		}else {
			//参数有误
			return null;
		}
		
		
		User user = this.userDao.selectOne(teacherUser);
	
		
		return user == null;
	}
	
	public Map<String, Object> checkUsername(User user){
		
		Map<String, Object> result = new HashMap<String, Object>();
		String inputUsername = user.getUsername();
		if(inputUsername != null){
			String username = inputUsername.replaceAll("\\s|\t|\r|\n", "");
			if(username.equals("")){
				result.put("status", "500");
				result.put("data", "用户名不可以为空.");
			}else{
				result.put("status", "200");
				//用户名中不可以有空格，因此set值为去掉空格的字符串
				result.put("data", username);
				System.out.println(username);
			}
		
		}else {
			result.put("status", "500");
			result.put("data", "用户名不可以为空.");
		}
		
		return result;
		
	}
	
	
	public Map<String, Object> checkPassword(User user){
		
		Map<String, Object> result = new HashMap<String, Object>();
		String inputPassword = user.getPassword();
		if(inputPassword == null && inputPassword.equals("")){
			result.put("status", "500");
			result.put("data", "密码不可以为空.");
		
		}else {
			result.put("status", "200");
			result.put("data", inputPassword);
		}
		return result;
		
	}
	
	public Map<String, Object> checkPhone(User user){
		
		Map<String, Object> result = new HashMap<String, Object>();
		String inputPhone = user.getPhone();
		String phone = inputPhone.replace("\\s*|\t|\r|\n", "");
		String regex = "[0-9]{3}";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        Boolean isMatch = m.matches();
        if(!isMatch){
        	result.put("status", "500");
        	result.put("data", "请填写11位数字手机号.");
        }else {
        	result.put("status", "200");
        	result.put("data", phone);
		}
        return result;
	}
	
	@Override
	public Map<String,Object> checkRegisterInfo(User user) {
		
		Map<String, Object> usernameMap = checkUsername(user);
		Map<String, Object> passwordMap = checkPassword(user);
		Map<String, Object> phoneMap = checkPhone(user);
		
		Map<String,Object> checkResultMap = new HashMap<String, Object>();
		
		User registerUser = new User();
		
		if(usernameMap.get("status").equals("200")){
			registerUser.setUsername((String)(usernameMap.get("data")));
		}else {
			return usernameMap; 
		}
		
		if(passwordMap.get("status").equals("200")){
			registerUser.setPassword((String)passwordMap.get("data"));
		}else {
			return passwordMap;
		}
		
		if(phoneMap.get("status").equals("200")){
			registerUser.setPhone((String)phoneMap.get("data"));
		}else {
			return phoneMap;
		}
		
		checkResultMap.put("status", "200");
		checkResultMap.put("data", registerUser);
		return checkResultMap;
		
	}

	@Override
	public Boolean doRegister(User user) {
		
			user.setUserId(null);
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			Integer result = this.userDao.insertUser(user);
			return result == 1;
		}

		//登陆时先判断当前用户是否登陆。
	public String doUserLogin(String cookieLoginToken, String username, String password) throws Exception{
		

		User teacherUser = new User();
		teacherUser.setUsername(username);
		
		User user = this.userDao.selectOne(teacherUser);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if(null == user){
			System.out.println("用户不存在.");
			//用户不存在
			return null;
		}
		if(!StringUtils.equals(DigestUtils.md5Hex(password), user.getPassword())){
			//密码错误
			System.out.println("密码错误");
			return null;
		}
		//校验当前cookie是否已经登陆
		if(cookieLoginToken != null && queryUserLoginVo(cookieLoginToken)!=null){
			User cookieUser = MAPPER.readValue(this.redisService.get("loginToken_"+cookieLoginToken),User.class);
			if(cookieUser.getUsername().equals(username)){
				return cookieLoginToken;
			}

		}
		//登陆成功，将用户的信息保存到redis中
		UserInfo userInfoCondition = new UserInfo();
		userInfoCondition.setUserId(user.getUserId());

		UserInfo userInfo = this.userInfoDao.selectOne(userInfoCondition);

		TeacherLoginVo teacherLoginVo = new TeacherLoginVo();
		teacherLoginVo.setUserInfo(userInfo);
		teacherLoginVo.setUser(user);
		System.out.println("正在生成login-token...");
		String newLoginToken = DigestUtils.md5Hex(username + System.currentTimeMillis());
		this.redisService.set("loginToken_"+newLoginToken, MAPPER.writeValueAsString(teacherLoginVo));
		return newLoginToken;
		
	}


	public TeacherLoginVo queryUserLoginVo( String loginToken){
		String userJsonData = this.redisService.get("loginToken_"+loginToken);
		if(StringUtils.isEmpty(userJsonData)){
			//登陆超时
			return null;
		}
		//重新设置用户token在redis中的超时时间。这个点还未实现。
		//……


		try{
			return MAPPER.readValue(userJsonData,TeacherLoginVo.class);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean saveUserInfo(UserInfo userInfo, String avatarName) {

		int flag = this.userInfoDao.insert(userInfo);
		if(flag==1){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateUserInfo(UserInfo userInfo, String avatarName) {

		int flag = this.userInfoDao.update(userInfo);
		if(flag==1){
			return true;
		}
		return false;
	}


	public String saveIamge(MultipartFile multipartFile) throws IOException {
		UserLoginVo userLoginVo = ContextUtil.getUserLoginInfo();
		Long userId = userLoginVo.getUser().getUserId();
		InputStream is=multipartFile.getInputStream();
		String fileNameWithUuid = userId+"_avatar.jpg";
		OutputStream os = new FileOutputStream(NGINX_ROOT_LOCATION+IMAGE_ROOT_LOCATION+AVATAR_LOCATION+fileNameWithUuid);
		byte[] fileBytes = new byte[256];
		int count;
		while ((count=is.read(fileBytes))!= -1){
			os.write(fileBytes,0, count);

		}
		System.out.println(multipartFile.getOriginalFilename());
		return IMAGE_ROOT_LOCATION+AVATAR_LOCATION+fileNameWithUuid;

	}

	public void changeUserStatus(String loginToken,Integer status){
		TeacherLoginVo teacherLoginVo = this.queryUserLoginVo(loginToken);
		teacherLoginVo.getUser().setStatus(status);
		try{
			this.redisService.set("loginToken_"+loginToken, MAPPER.writeValueAsString(teacherLoginVo));
		}catch (Exception e){
			e.printStackTrace();
		}


	}

	@Override
	public int addCourse(TeacherCourse teacherCourse) {
		return this.teacherCourseDao.insert(teacherCourse);
	}

	@Override
	public UserInfo queryUserByUserId(UserInfo userInfo) {
		return  this.userInfoDao.selectOne(userInfo);
	}


}




