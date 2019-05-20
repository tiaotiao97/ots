package com.ots.controller;

import com.ots.controller.vo.TeacherInfoAddVo;
import com.ots.entity.TeacherCourse;
import com.ots.entity.UserInfo;
import com.ots.resultbean.GetResultBean;
import com.ots.resultbean.ResultBean;
import com.ots.service.LoginService;
import com.ots.service.UserInfoService;
import com.ots.utils.ContextUtil;
import com.ots.vo.TeacherLoginVo;
import com.ots.vo.UserLoginVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/teacher/user/")
@Controller
public class TeacherUserController {


	
	@Autowired
	private UserInfoService userInfoService;
	
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

	@RequestMapping(value = "addcourseinfo",method = RequestMethod.GET)
	public String addcourse(){
		return "addcourseinfo";
	}


	
	/*
	 * 检测数据是否可用
	 * */
	@RequestMapping(value="/check/{param}/{type}")
	public ResponseEntity<Boolean> check(@PathVariable("param") String param,
                                         @PathVariable("type") String type){
		
		try {
			Boolean bool = this.userInfoService.check(param, type);
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
	

	


	@Autowired
	private LoginService loginService;

	@RequestMapping(value="queryTeacherUserInfo",method=RequestMethod.GET)
	@ResponseBody
	//这个函数查的是UserDao里的字段
	public ResultBean<UserLoginVo> queryTearcherUserInfoByToken(){
		UserLoginVo userLoginVo = ContextUtil.getUserLoginInfo();

		ResultBean<UserLoginVo> resultBean = GetResultBean.getResultBean();
		try {

			resultBean.setResult(200,"查询成功.",userLoginVo);
		} catch (Exception e) {
			resultBean.setResult(500,"用户信息不存在.",userLoginVo);
		}
		if(null == userLoginVo){
			resultBean.setResult(500,"用户信息不存在.",userLoginVo);
			//不存在
		}
		return resultBean;

	}

	@RequestMapping(value="addTeacherUserInfo",method= RequestMethod.POST)
	public ResponseEntity<TeacherLoginVo> addInfo(@CookieValue(name="login_token",required = false) String loginToken, TeacherInfoAddVo teacherInfoAddVo) throws IOException {
		TeacherLoginVo teacherLoginVo = this.userInfoService.queryUserLoginVo(loginToken);
		Long userId = teacherLoginVo.getUser().getUserId();
		UserInfo userInfo = teacherInfoAddVo.getUserInfo();
		userInfo.setUserId(userId);
		String avatarPath = this.userInfoService.saveIamge(teacherInfoAddVo.getAvatar());
		userInfo.setAvatar(avatarPath);
		Boolean bool = this.userInfoService.saveUserInfo(userInfo,avatarPath);
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
				this.userInfoService.changeUserStatus(loginToken,status);
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
		TeacherLoginVo teacherLoginVo = this.userInfoService.queryUserLoginVo(loginToken);
		return ResponseEntity.ok(teacherLoginVo);

	}

	@ResponseBody
	@RequestMapping("addCourse")
	public ResultBean<TeacherCourse> addCourseInfo(TeacherCourse teacherCourse){ Long teacherId = ContextUtil.getTeacherLoginInfo().getUser().getUserId();
		ResultBean<TeacherCourse> resultBean = GetResultBean.getResultBean();
		resultBean.setCode(500);
		resultBean.setMsg("系统错误.");
		if(teacherCourse.getCourseId()==null || teacherCourse.getCoursePrice()==null){
			return resultBean;
		}
		teacherCourse.setTeacherId(teacherId);
		int flag = this.userInfoService.addCourse(teacherCourse);
		if(flag==1){
			resultBean.setCode(200);
			resultBean.setMsg("add course successfully.");
			resultBean.setData(teacherCourse);
		}
		return resultBean;
	}



	@RequestMapping(value="updateTeacherUserInfo",method= RequestMethod.POST)
	@ResponseBody
	public ResultBean<UserLoginVo> updateInfo(TeacherInfoAddVo teacherInfoAddVo) throws IOException {
		UserLoginVo userLoginVo = ContextUtil.getUserLoginInfo();
		UserInfo userInfoCondition = new UserInfo();
		Long userId = userLoginVo.getUser().getUserId();
		userInfoCondition.setUserId(userId);
		UserInfo userInfo = teacherInfoAddVo.getUserInfo();
		userInfo.setUserId(userId);
		String avatarPath = this.userInfoService.saveIamge(teacherInfoAddVo.getAvatar());
		userInfo.setAvatar(avatarPath);

		if(this.userInfoService.queryUserByUserId(userInfoCondition)==null){
			this.userInfoService.saveUserInfo(userInfo,avatarPath);
			return null;
		}

		Boolean bool = this.userInfoService.updateUserInfo(userInfo,avatarPath);
		if(bool==true){
			return null;
		}
		return null;
	}

}
