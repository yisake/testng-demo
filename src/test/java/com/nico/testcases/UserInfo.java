package com.nico.testcases;

public class UserInfo {
	public String userName="";
	public String password="";
	
	public void setUserName(String name){
		this.userName=name;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setPassword(String pwd) {
		this.password=pwd;
	}
	
	public String getPassword() {
		return this.password;
	}
}
