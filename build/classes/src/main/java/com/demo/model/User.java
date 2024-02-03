package com.demo.model;

public class User {
	private Integer uId;
	private String uName;
	private String sex;
	private Integer age;
	private String phone;
	private Integer vip;
	private String vipdate;
	public String getVipdate() {
		return vipdate;
	}
	public void setVipdate(String vipdate) {
		this.vipdate = vipdate;
	}
	public Integer getuId() {
		return uId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getVip() {
		return vip;
	}
	public void setVip(Integer vip) {
		this.vip = vip;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
