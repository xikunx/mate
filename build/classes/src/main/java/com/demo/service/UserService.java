package com.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.UserDao;
import com.demo.model.Sign;
import com.demo.model.User;

@Component
public class UserService {
	@Autowired 
	UserDao userDao;
	
	public User getInformation(Integer uId) {
		return userDao.getInformation(uId);
	}
	public User getUser(@Param("phone") String phone) {
		return userDao.getUser(phone);
	}
	public int addUser(@Param("uName") String uName,@Param("sex") String sex,@Param("age") Integer age,@Param("phone") String phone) {
		return userDao.addUser(uName, sex, age, phone);
	}
	public int addsign(Integer uId) {
		return userDao.addsign(uId);
	}
	public Sign Signnum(Integer uId) {
		return userDao.Signnum(uId);
	}
	public int sgin(Integer num,Integer uId) {
		return userDao.sgin(num, uId);
	}
	public int vip(@Param("vipdate") String vipdate,@Param("uId") Integer uId) {
		return userDao.vip(vipdate, uId);
	}
	public int vipexpire(Integer uId) {
		return userDao.vipexpire(uId);
	}
	public int recharge(@Param("uId") Integer uId,@Param("vipdate") String vipdate) {
		return userDao.recharge(uId, vipdate);
	}
	public List<User> mate(@Param("sex") String sex,@Param("max") Integer max,@Param("min") Integer min,@Param("age") Integer age){
		return userDao.mate(sex, max, min, age);
	}
}
