<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>미세먼지 조회 페이지</h1>
	
	
	<input type="text" id="numOfRows" placeholder="numOfRows" >
	<button id="targetBtn">미세먼지 조회하기</button>
	<button id="targetBtnXml">xml미세먼지 조회하기</button>
	
	<hr>
	
	<div id="targetDiv">
		
	</div>
	<!-- <table id="targetTable" border="1">
		<thead>
			<tr>
				<th>districtName</th>
				<th>issueDate</th>
				<th>issueGbn</th>
				<th>issueTime</th>
				<th>moveName</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	-->
	
	<script>
	$('#targetBtnXml').click(function(){
		let numOfRows = $("#numOfRows").val();
		$.ajax({
				url : "api-respxml",
				data : {"numOfRows" : numOfRows},
				success : function(data){
					alert("success~~~");
					//제이쿼리 방식으로 맞춰야
					const itemArr = $(data).find('item');
					//items > item, item, ...
					console.log(itemArr);
					
					//테이블 만들기
					let $table = $('<table id="targetTable" border="1"></table>');
					let $thead = $('<thead></thead>');
					let $theadContent = $('<tr> <th>districtName</th><th>issueDate</th><th>issueGbn</th><th>issueTime</th><th>moveName</th></tr>');
					let $tbody = $('<tbody></tbody>');
					
					$thead.append($theadContent);
					
					let tbodyContent = "";
					
					//itemArr는 자바스크립트 문법? 이고 each()는 제이쿼리라 맞춰줘야함
					$(itemArr).each(function(i, item){
						tbodyContent = '<tr>'
							 + '<td>' + $(item).find('districtName').text() + '</td>'
							 + '<td>' + $(item).find('issueDate').text() + '</td>'
							 + '<td>' + $(item).find('issueGbn').text() + '</td>'
							 + '<td>' + $(item).find('issueTime').text() + '</td>'
							 + '<td>' + $(item).find('moveName').text() + '</td>'
							 + '</tr>';
						
							$tbody.append(tbodyContent);
							
					})//each end
					
					//반복문 안에서 얻은 데이터들을 테이블에 넣기
					//table, theadm, tbody 를 targetdiv에 넣기
					$("#targetDiv").html("");
					$table.append($thead, $tbody).appendTo($("#targetDiv"));
					
/* 					for(let i=0; i<itemArr.length; ++i){
						//console.log(itemArr[i]);
					}
 */					
				},
				error : function(){
					alert("error!!");
				}
			
		})
	})
	
	</script>
	
	<script>
		$("#targetBtn").click(function(){
			let numOfRows = $("#numOfRows").val();
			$.ajax({
				url : "api-resp",
				data : {"numOfRows" : numOfRows},
				success : function(data){
					alert("success~~~");
					//console.log(data);
					console.log(data.response.body.items);
					const itemArr = data.response.body.items;
					
					//테이블 만들기
					let $table = $('<table id="targetTable" border="1"></table>');
					let $thead = $('<thead></thead>');
					let $theadContent = $('<tr> <th>districtName</th><th>issueDate</th><th>issueGbn</th><th>issueTime</th><th>moveName</th></tr>');
					let $tbody = $('<tbody></tbody>');
					
					$thead.append($theadContent);
					
					//itemArr는 자바스크립트 문법? 이고 each()는 제이쿼리라 맞춰줘야함
					let tbodyContent = "";
					$(itemArr).each(function(i, item){
						tbodyContent = '<tr>'
							 + '<td>' + item.districtName + '</td>'
							 + '<td>' + item.issueDate + '</td>'	
							 + '<td>' + item.issueGbn + '</td>'	
							 + '<td>' + item.issueTime + '</td>'	
							 + '<td>' + item.moveName + '</td>'	
							 + '</tr>';
						$tbody.append(tbodyContent);
						
						//반복문 안에서 얻은 데이터들을 테이블에 넣기
						//table, theadm, tbody 를 targetdiv에 넣기
						
						$("#targetDiv").html("");
						$table.append($thead, $tbody).appendTo($("#targetDiv"));
						
							 
							 
							 
						//tbodyContent += `<tr><td>${item.districtName}</td><td>${item.issueDate}</td><td>${item.issueGbn}</td><td>${item.issueTime}</td><td>${item.moveName}</td></tr>`;
 						/* tbodyContent += '<tr>'
							 + '<td>' + item.districtName + '</td>'
							 + '<td>' + item.issueDate + '</td>'	
							 + '<td>' + item.issueGbn + '</td>'	
							 + '<td>' + item.issueTime + '</td>'	
							 + '<td>' + item.moveName + '</td>'	
							 + '</tr>'
  */
						/* tbodyContent += `<tr>
							<td>${item.districtName}</td>
							<td>${item.issueDate}</td>
							<td>${item.issueGbn}</td>
							<td>${item.issueTime}</td>
							<td>${item.moveName}</td>
							</tr>`; */
					});
					//$("#targetTable tbody").html(tbodyContent);
					
					/* for(let i=0; i<itemArr.length; ++i){
						console.log(itemArr[i]);
					} */
					/* 
					let tbodyContent = "";
					for(let i in itemArr){
						//console.log(itemArr[i]);
						let item = itemArr[i];
						
						$('#targetTable')
						
						tbodyContent += `<tr>
						<td>${item.districtName}</td>
						<td>${item.issueDate}</td>
						<td>${item.issueGbn}</td>
						<td>${item.issueTime}</td>
						<td>${item.moveName}</td>
						</tr>`;
					}
						$("#targetTable tbody").html(tbodyContent);
					*/
				},
				error : function(){
					alert("error!!!");
				}
			})
		});
	
	</script>
	
</body>
</html>