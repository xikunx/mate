<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主界面</title>
</head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<div>
		<span>
			<form action="sign" method="get">
			<input type="hidden" value="${uName}" name="uName">
			<input type="hidden" value="${uId}" name="uId">
			<input type="hidden" value="${num}" name="num">
			用户${uName}<input type="submit" value="签到"> 一共签到${num}次
			<c:if test="${over!=null}">
				请不要重复签到！
			</c:if>
			<a href="exit"><input type="button" value="退出"></a>
		</span>
		<span style="display: inline-block;margin-left: 200px">
			<a href="recharge?uId=${uId}"><input type="button" value="充值会员"></a>
			<c:if test="${vipexpire!=null&&vip==null}">
				会员已过期！
			</c:if>
			<c:if test="${vipexpire==null&&notvip==null}">
				会员到期时间：${vipdate}
			</c:if>
		</span>
		</form>
		<form action="mate" method="get">
		<div style="margin-top:30px">
		<input type="hidden" value="${uId}" name="uId">
			选择匹配的用户年龄：
			<select name="state">
				<option value="1">18-25</option>
				<option value="2">25-35</option>
				<option value="3">35-45</option>
				<option value="4">45-55</option>
			</select>
			<input type="submit" value="匹配">
		</div>
		</form>
	</div>
</body>
</html>