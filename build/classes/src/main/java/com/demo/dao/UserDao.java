package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.model.Sign;
import com.demo.model.User;

public interface UserDao {
	@Select("select * from users where uId=#{uId}")
	public User getInformation(Integer uId);
	//检验手机号是否注册方法
	@Select("select * from users where phone=#{phone}")
	public User getUser(@Param("phone") String phone);
	//注册账号方法
	@Insert("insert into users values(null,#{uName},#{sex},#{age},#{phone},0,null)")
	public int addUser(@Param("uName") String uName,@Param("sex") String sex,@Param("age") Integer age,@Param("phone") String phone);
	@Insert("insert into sign values(#{uId},0,null)")
	public int addsign(Integer uId);
	//查询签到次数
	@Select("select * from sign where uId=#{uId}")
	public Sign Signnum(Integer uId);
	//签到方法
	@Update("update sign set num=#{num}+1,lasttime=Now() where uId=#{uId}")
	public int sgin(@Param("num") Integer num,@Param("uId") Integer uId);
	//充值会员方法
	@Update("update users set vip=1,vipdate=#{vipdate} where uId=#{uId}")
	public int vip(@Param("vipdate") String vipdate,@Param("uId") Integer uId);
	//会员到期方法
	@Update("update users set vip=0 where uId=#{uId}")
	public int vipexpire(Integer uId);
	//会员充值方法
	@Update("update users set vip=1,vipdate=#{vipdate} where uId=#{uId}")
	public int recharge(@Param("uId") Integer uId,@Param("vipdate") String vipdate);
	//匹配方法
	@Select("select * from users where sex=#{sex} and age<#{max} and age>=#{min} ORDER BY ABS(age-#{age})")
	public List<User> mate(@Param("sex") String sex,@Param("max") Integer max,@Param("min") Integer min,@Param("age") Integer age);
}
