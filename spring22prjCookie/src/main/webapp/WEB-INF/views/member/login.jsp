<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<!-- 아이디 저장하기 -->
	<!-- 쿠키가 있으면 아이디를 넣어서 보여주기 -->
	<!-- 쿠키가 없으면 그냥 로그인 화면 -->
	<c:choose>
		<c:when test="${empty cookie.k01 }">
		    <form action = "" method = "post">
		        아이디 : <input type ="text" name = "id" placeholder="아이디"/><br>
		        아이디 기억하기 : <input type="checkbox">
		    </form>	
		</c:when>
	<%-- 쿠키가 있으면 아이디를 입력해서 보여주기 --%>
        <c:otherwise>
            <form >
                아이디 : <input type = "text" name = "id" placeholder="아이디" value="${cookie.k01.value}" /> <br>
                아이디 기억하기 <input type="checkbox" checked/>                
            </form>
        </c:otherwise>
	</c:choose>
</body>
</html>