package com.ots.controller;

import com.ots.controller.vo.TeacherInfoAddVo;
import com.ots.entity.TeacherInfo;
import com.ots.entity.User;
import com.ots.service.TeacherUserService;
import com.ots.utils.ContextUtil;
import com.ots.vo.TeacherLoginVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/teacher/user/")
@Controller
public class TeacherUserController {


	
	@Autowired
	private TeacherUserService teacherUserService;
	
	@RequestMapping(value="register",method= RequestMethod.GET)
	public String register(){
		return "register";
	}
	
	@RequestMapping(value="login",method= RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value="addinfo",method= RequestMethod.GET)
	public String addinfo(){
		return "addinfo";
	}


	
	/*
	 * 检测数据是否可用
	 * */
	@RequestMapping(value="/check/{param}/{type}")
	public ResponseEntity<Boolean> check(@PathVariable("param") String param,
                                         @PathVariable("type") String type){
		
		try {
			Boolean bool = this.teacherUserService.check(param, type);
			if (bool == null) {
				//参数有误
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			return ResponseEntity.ok(bool);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
	
	@RequestMapping(value="/doTeacherRegister",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doTeacherRegister(User user){
				
		 Map<String, Object> result = new HashMap<String, Object>();
		 
		
		try {
			result = this.teacherUserService.checkRegisterInfo(user);
			if(result.get("status").equals("200")){
				User registerUser = (User)(result.get("data"));
				Boolean bool = this.teacherUserService.doRegister(registerUser);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("status", "500");
			result.put("data", "程序异常,即将跳转到注册界面.");
		}
		return result;
	}
	
	@RequestMapping(value="doTeacherLogin",method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doTeacherLogin(HttpServletRequest request, @CookieValue(required = false,name="login_token")String existedToken, User user, HttpServletResponse response){
		System.out.println("hello");
		Map<String, Object> result = new HashMap<String, Object>();
		
		String loginToken;
		
		try {
			System.out.println("aaaaa");
			loginToken = this.teacherUserService.doTeacherLogin(existedToken,user.getUsername(),user.getPassword());
			System.out.println("token="+loginToken);
			if(StringUtils.isEmpty(loginToken)){
				//登陆失败
				System.out.println("token为空");
				result.put("status", "500");
				return result;
			}

			//登陆成功

			Cookie cookie = new Cookie("login_token",loginToken);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
			result.put("status", "200");
			return result;
		} catch (Exception e) {
			result.put("status", "500");
			return result;
		}
	}

	@RequestMapping(value="queryTeacherUserInfo/{loginToken}",method=RequestMethod.GET)
	//这个函数查的是UserDao里的字段
	public ResponseEntity<TeacherLoginVo> queryTearcherUserInfoByToken(@PathVariable("loginToken") String loginToken){

		TeacherLoginVo teacherLoginVo = this.teacherUserService.queryTeacherLoginVo(loginToken);
		if(null == teacherLoginVo){
			//不存在
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(teacherLoginVo);

	}

	@RequestMapping(value="addTeacherUserInfo",method= RequestMethod.POST)
	public ResponseEntity<TeacherLoginVo> addInfo(@CookieValue(name="login_token",required = false) String loginToken, TeacherInfoAddVo teacherInfoAddVo) throws IOException {
		TeacherLoginVo teacherLoginVo = this.teacherUserService.queryTeacherLoginVo(loginToken);
		Long userId = teacherLoginVo.getUser().getUserId();
		TeacherInfo teacherInfo = teacherInfoAddVo.getTeacherInfo();
		teacherInfo.setUserId(userId);
		String avatarPath = this.teacherUserService.saveIamge(teacherInfoAddVo.getAvatar());
		teacherInfo.setAvatar(avatarPath);
		Boolean bool = this.teacherUserService.saveTeacherInfo(teacherInfo,avatarPath);
		if(bool==true){
			return null;
		}
		return null;
	}

	@RequestMapping("/changeTeacherStatus")
	@ResponseBody
	public Map<String,Integer> changeStatus(@CookieValue(name = "login_token") String loginToken,@Param("status") Integer status){
		try{
			if(status == 1 || status==0){
				this.teacherUserService.changeTeacherStatus(loginToken,status);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		Map<String,Integer> result = new HashMap<String,Integer>();
		result.put("status",status);
		return result;
	}

	@RequestMapping("/showTeacherStatus")
	@ResponseBody
	public Map<String,Integer> showStatus(String loginToken){
		TeacherLoginVo teacherLoginVo = ContextUtil.getTeacherLoginInfo();
		Integer status = teacherLoginVo.getUser().getStatus();
		Map<String,Integer> result = new HashMap<String,Integer>();
		result.put("status",status);
		return result;
	}

	public ResponseEntity<TeacherLoginVo> showTeacherInfos(@CookieValue(name = "login_token") String loginToken){
		TeacherLoginVo teacherLoginVo = this.teacherUserService.queryTeacherLoginVo(loginToken);
		return ResponseEntity.ok(teacherLoginVo);

	}

}
