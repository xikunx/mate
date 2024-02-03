<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>匹配界面</title>
</head>
<body>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<table border="1">
		<tr>
			<th>名称</th>
			<th>年龄</th>
			<th>性别</th>
			<th>用户等级</th>
		</tr>
		<c:forEach items="${users}" var="users">
		<tr>
			<td>${users.uName}</td>
			<td>${users.age}</td>
			<td>${users.sex}</td>
			<c:if test="${users.vip==1}">
				<td>会员</td>
			</c:if>
			<c:if test="${users.vip==0}">
				<td>普通用户</td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>