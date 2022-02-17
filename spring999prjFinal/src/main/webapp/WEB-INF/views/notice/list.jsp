<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<div id="div-main">
		<div style="text-align:center;">
			<h1>공지사항</h1>
		</div>
		
		<table border="1" style="margin:auto;">
			<thead>
				<tr>
					<th><input type="checkbox" id="allCheck"></th>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="n">
					<tr>
						<td><input type="checkbox" class="checkbox-del" value="${n.no}"></td>
						<td>${n.no}</td>
						<td>${n.title}</td>
						<td>${n.userNick}</td>
						<td>${n.writeDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a href="${root}/notice/write">공지 작성</a>
		<button onclick="del();">삭제하기</button>
	</div>
	
	<script type="text/javascript">
		//전체 선택
		//let allCheck = document.querySelector('#allCheck');
		let allCheck = document.querySelector('thead input[type=checkbox]');
		let delArr = document.getElementsByClassName('checkbox-del');
		
		allCheck.onchange = function(e){
			//console.log(e.target.checked);
			console.log(this.checked);
			if(this.checked){
				for(let i=0; i<delArr.length; i++){
					delArr[i].checked = true;
				}
			}else{
				for(let i=0; i<delArr.length; i++){
					delArr[i].checked = false;
				}
			}
		}
		
		//삭제하기
		function del() {
			//삭제할 번호(들) 가져오기
			let delArr = document.getElementsByClassName('checkbox-del');
			//가져온 번호(들)을 하나의 문자열로 합치기
			let result = "";
			
			for(let i = 0; i<delArr.length; i++){
				let t = delArr[i];
				if(t.checked){
					//children[1]가 no가 있음
					//console.log(t.parentNode.parentNode.children[1].innerText);					
					console.log(t.value);	
					result += t.value + ',';
				}
			}
			
			$.ajax({
				url : "${root}/notice/delete",
				data : {"str" : result},
				type : "post",
				success : function(data){
					console.log(data);
				}, 
				error : function(error){
					console.log(error)
				},
				complete : function(){
					//새로고침
					window.location.reload();
				}
			});
		}//del()
	</script>
</body>
</html>