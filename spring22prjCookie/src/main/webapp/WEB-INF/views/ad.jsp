<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>광고 페이지</h1>
	
	<!-- 광고 띄우기 -->
	<!-- 쿠키가 있으면 광고 띄우기 -->
	
	<!-- 쿠키가 없으면 광고 띄우기 -->
	<c:if test="${empty cookie.k01}">
		<script>
			window.open("http://www.naver.com", "test", "width=300, height=300");
		</script>
	
	</c:if>
	
</body>
</html>