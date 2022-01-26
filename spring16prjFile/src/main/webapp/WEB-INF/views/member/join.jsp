<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>

	<form action="join" method="post" enctype="multipart/form-data">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="pwd"><br>
		닉네임 : <input type="text" name="nick"><br>
		프로필 사진 : <input type="file" name="profileList" multiple accept=".jpg, .png"><br>
		<input type="submit" value="가입하기">	
	</form>
</body>
</html>