package com.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.domain.Data;
import com.demo.dao.UserDao;
import com.demo.model.User;
import com.demo.service.UserService;

import redis.clients.jedis.Jedis;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/login")
	public String login(String phone,Model model,HttpServletRequest request, HttpServletResponse response) {
		User user=userService.getUser(phone);
		model.addAttribute("phone",phone);
		if(phone=="") {
			model.addAttribute("phonenot","phonenot");
			
		}else if(user==null){
			model.addAttribute("Not","Not");
		}else {
			response.addHeader("Access-Control-Allow-Origin", "*");
			//生成验证码
			StringBuilder builder=new StringBuilder();
			Random random=new Random();
			for(int i=0;i<6;i++) {
				builder.append(random.nextInt(10));
			}
			String code=builder.toString();
			//将验证码保存到redis，并发送到运营商
			Jedis jedis=new Jedis("127.0.0.1",6379);
			jedis.expire(phone, 60);
			jedis.set(phone,code);
			System.out.println("您的手机尾号为"+phone.substring(7)+"的验证码为"+code+",打死也不要告诉别人");
		}
		return "login.jsp";
	}
	
	@RequestMapping("/denglu")
	public String denglu(String phone,String code,Model model,HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("phone",phone);
		response.addHeader("Access-Control-Allow-Origin", "*");
		Jedis jedis=new Jedis("127.0.0.1",6379);
		String redisCode=jedis.get(phone);
		if(phone==""||code=="") {
			model.addAttribute("Notcode","Notcode");
			return "login.jsp";
		}else if(redisCode.equals(code)) {
			String uName=userService.getUser(phone).getuName();
			Integer uId=userService.getUser(phone).getuId();
			Integer num=userService.Signnum(uId).getNum();
			String vipdate=userService.getUser(phone).getVipdate();
			model.addAttribute("num",num);
			model.addAttribute("uId",uId);
			model.addAttribute("uName",uName);
			model.addAttribute("vipdate",vipdate);
			if(vipdate!=null) {
				Date date=new Date();
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				String nowdate=format.format(date);
				if(vipdate.compareTo(nowdate)<0) {
					userService.vipexpire(uId);
					model.addAttribute("vipexpire","vipexpire");
				}
			}else {
				model.addAttribute("notvip","notvip");
			}
			return "index.jsp";
		}else {
			model.addAttribute("errocode","errocode");
			return "login.jsp";
		}
	}
	
	@RequestMapping("/add")
	public String add() {
		return "addUser.jsp";
	}
	
	@RequestMapping("/addUser")
	public String addUser(String uName,String phone,String sex,Integer age) {
		userService.addUser(uName, sex, age, phone);
		Integer uId=userService.getUser(phone).getuId();
		userService.addsign(uId);
		return "login.jsp";
	}
	
	@RequestMapping("/sign")
	public String sign(String uName,Integer uId,Model model,Integer num) {
		String lasttime=userService.Signnum(uId).getLasttime();
		if(lasttime==null) {
			lasttime="1999-01-01";
		}
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String nowdate=format.format(date);
		if(lasttime.equals(nowdate)) {
			model.addAttribute("over","over");
		}else {
			userService.sgin(num, uId);
			num=userService.Signnum(uId).getNum();
		}
		model.addAttribute("uName",uName);
		model.addAttribute("uId",uId);
		model.addAttribute("num",num);
		String vipdate=userService.getInformation(uId).getVipdate();
		model.addAttribute("vipdate",vipdate);
		if(vipdate!=null) {
			if(vipdate.compareTo(nowdate)<0) {
				userService.vipexpire(uId);
				model.addAttribute("vipexpire","vipexpire");
			}
		}else {
			model.addAttribute("notvip","notvip");
		}
		return "index.jsp";
	}
	@RequestMapping("/recharge")
	public String recharge(Integer uId,Model model) {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale=Calendar.getInstance();
		Integer vip=userService.getInformation(uId).getVip();
		if(vip==0) {
			cale.setTime(date);
		}else {
			String vipdate=userService.getInformation(uId).getVipdate();
			Date vDate = null;
			try {
				vDate = format.parse(vipdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cale.setTime(vDate);
		}
		cale.add(Calendar.MONTH, 1);
		String vipdate=format.format(cale.getTime());
		userService.recharge(uId, vipdate);
		String uName=userService.getInformation(uId).getuName();
		Integer num=userService.Signnum(uId).getNum();
		model.addAttribute("vipdate",vipdate);
		model.addAttribute("uName",uName);
		model.addAttribute("num",num);
		model.addAttribute("uId",uId);
		model.addAttribute("vip","vip");
		return "index.jsp";
	}
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login.jsp";
	}
	@RequestMapping("/mate")
	public String mate(Integer uId,Integer state,Model model) {
		Integer max=0;
		Integer min=0;
		String sex=userService.getInformation(uId).getSex();
		Integer age=userService.getInformation(uId).getAge();
		List<User> users = null;
		switch (state) {
		case 1:
			max=25;
			min=18;
			if(sex.equals("女")) {
				sex="男";
			}else {
				sex="女";
			}
			users=userService.mate(sex, max, min, age);
			break;
		case 2:
			max=35;
			min=25;
			if(sex.equals("女")) {
				sex="男";
			}else {
				sex="女";
			}
			users=userService.mate(sex, max, min, age);
			break;
		case 3:
			max=45;
			min=35;
			if(sex.equals("女")) {
				sex="男";
			}else {
				sex="女";
			}
			users=userService.mate(sex, max, min, age);
			break;
		case 4:
			max=55;
			min=45;
			if(sex.equals("女")) {
				sex="男";
			}else {
				sex="女";
			}
			users=userService.mate(sex, max, min, age);
			break;
		}
		model.addAttribute("users",users);
		return "mate.jsp";
	}
}
