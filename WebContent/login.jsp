<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<style type="text/css">
	*{
		margin: 0px;
		padding: 0px;
	}
	#all{
		width: 400px;
		height: 200px;
		border: 1px solid black;
		margin-top: 200px;
		margin-left: 500px;
		padding-left: 40px;
		padding-top: 20px;
		position: absolute;
	}
</style>
</head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<form action="login" method="get">
		<div id="all">
			<h1>登录界面</h1>
			手机号:<input type="text" name="phone" style="margin-bottom: 20px;margin-top: 20px;" value="${phone}" ><br>
			<input type="submit" value="发送" style="position:relative ;top: 20px;left: 220px">
			<c:if test="${Not!=null}">
				<span>改手机号未注册！</span><br>
			</c:if>
			<c:if test="${phonenot!=null}">
				<span>请填写手机号!</span><br>
			</c:if>
			<c:if test="${Notcode!=null}">
				<span>请先进行验证码的验证!</span><br>
			</c:if>
			<c:if test="${errocode!=null}">
				<span>验证码错误!</span><br>
			</c:if>
	</form>
	<form action="denglu">
			<input type="hidden" name="phone" value="${phone}">
			验证码:<input type="text" name="code"><br>
			<input type="submit" value="登录">
	</form>
	<a href="add" style="text-decoration: none;position: relative;top: -20px;left: 40px">注册</a>
	</div>
</body>
</html>