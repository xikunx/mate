<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册账号</title>
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
	input{
		margin-top: 10px;
	}
</style>
</head>
<body>
	<div id="all">
	<h1>注册账号</h1>
		<form action="addUser" method="get">
			用户姓名：<input type="text" name="uName"><br>
			手机号：<input type="text" name="phone"><br>
			年龄：<input type="text" name="age"><br>
			性别：<select name="sex">
				<option value="女">女</option>
				<option value="男">男</option>
			</select><br>
			<input type="submit" value="注册">
		</form>
	</div>
</body>
</html>