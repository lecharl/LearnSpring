<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>home page</h1>
<%-- 	<h1>로그인 성공 여부 : ${sessionScope.loginUser != null}</h1> --%>
<%-- 	<h1>로그인 성공 여부 : ${loginUser != null}</h1> --%>
	<h1>로그인 성공 여부 : ${not empty loginUser}</h1>
	
	<h1>${loginUser.nick}님 환영합니다!</h1>
</body>
</html>