package com.ots.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	
	private Long userId;
	private String username;
	//在序列化时忽略password字段;用途：将user对象信息存放到redis时，将password字段去除
	@JsonIgnore
	private String password;
	private String phone;
	private Integer status;
	private Integer identity;

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
