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
		<h1>회원가입 페이지</h1>
		
		<form action = "" method = "post" enctype="multipart/form-data">
	        아이디 : <input type = "text" name = "userId"/><br>
	        비밀번호 : <input type = "password" name = "userPwd"/><br>
	        닉네임 : <input type = "text" name = "userNick"/><br>
	        나이 : <input type = "number" min="0" name = "userAge"/><br>
	        성별 : 
	        <select name="userGender">
	        	<option value="m">남자</option>
	        	<option value="f">여자</option>
	        </select><br>
	        사진 : <input type = "file" name = "f" multiple="multiple" accept=".jpg, .png"/><br>
	        <img id="profileImg"><br><hr>
	        <div id="div-preview">
	        
	        </div>
	        <input type = "submit" value ="회원가입"/>
	    </form>
	    
	    <!-- <button onclick="createImg();">이미지 태그 생성</button> -->
	</div>
	
	<script type="text/javascript">
	
		let fileTag = document.querySelector("input[name=f]");
		let divPreview = document.querySelector("#div-preview");
		
		fileTag.onchange = function(){
			
			//파일 올렸을 때 : fileTag.files.length > 0
			if(fileTag.files.length>0){
				//이미지 src에 들어갈 데이터 구하기
				for(let i=0; i<fileTag.files.length; i++){
					let reader = new FileReader();
					reader.onload = function(data){
						let src = data.target.result;
						//이미지 태그를 만들어서 넣어줄거임
						//1. 이미지 태그 만들기
						let imgTag = document.createElement('img');
						
						//2. 이미지 태그 속성들 세팅하기
						imgTag.setAttribute('src', src);
						imgTag.setAttribute('width', '100');
						imgTag.setAttribute('height', '100');
						
						//3. 이미지 태그 div안에 넣기
						divPreview.appendChild(imgTag);
					}
					reader.readAsDataURL(fileTag.files[i]);
					
				}//for end
				
			}else{
			//취소 버튼을 눌렀을 때
				//div 안에 싹 다 비우기
				divPreview.innerHTML = "";
			}
			
		}
		/* 
		fileTag.onchange = function(){
			let imgTag = document.querySelector("#profileImg");
			//파일 있는지 확인 : 
			//console.log(fileTag.files.length);
			if(fileTag.files.length > 0){
				imgTag.style.display = "block";
				//미리보기 작업 실행
				// 이미지 태그 src에 데이터(파일태그에서 선택한 파일 데이터)를 넣어주면 됨
				let reader = new FileReader();
				reader.onload = function(data){
					console.log(data);
					imgTag.src = data.target.result;
					//imgTag.width = 200;
					imgTag.setAttribute('width', '500vw' );
					//imgTag.height = 200;
				}
				//읽는 작업
				reader.readAsDataURL(fileTag.files[0]);
			}else{
				imgTag.style.display = "none";
			}
		}
		
		function createImg(){
			//div안쪽에다가 img태그 생성하기
			
			//1. 이미지 태그 자체를 만들기
			let imgTag2 = document.createElement('img');
			
			//2. 이미지 태그 속성 세팅하기
			imgTag2.setAttribute('src', "http://placehold.it/100x100");
			//imgTag2.setAttribute('src', 'https://placeimg.com/200/200/animals');
			//imgTag2.setAttribute('width', '300vw');
			//imgTag2.setAttribute('height', '');
			
			//3. 이미지 태그 화면에 붙이기
			let divPreview = document.querySelector("#div-preview");
			divPreview.appendChild(imgTag2);
		} */
	
	</script>

</body>
</html>