<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>홈 화면</h1>
	<%	if(session.getAttribute("loginUser") != null){	%>
		<h1>${loginUser.id}님 환영합니다!</h1>
		<h1>${loginUser.nick}님 환영합니다!</h1>
		<h1>나이 : ${loginUser.age}</h1>
		<a href="member/logout">로그아웃</a>
	<%	}else{ %>
			로그인 해주세요!
			<a href="member/login">로그인페이지</a>
	<%	} %>	
	<br>
	<a href="board/insert">게시글 등록</a>	
</body>
</html>