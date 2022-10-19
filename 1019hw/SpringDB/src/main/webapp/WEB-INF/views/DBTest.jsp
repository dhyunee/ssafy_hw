<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<button id="btnUserDetail">User 상세</button>
	<button id="btnUserList">User 목록</button>
	<button id="btnUserInsert">User 등록</button>

<script>
window.onload=function(){
	document.querySelector("#btnUserDetail").onclick=async function(){
		
		try{
			let response=await fetch('/mydb/UserInfo/songb');
			let data=await response.json();
			console.log(data);
		}catch(error){
			console.error(error);
		}
	}
	
	document.querySelector("#btnUserList").onclick=async function(){
		
		try{
			let response=await fetch('/mydb/userList');
			let data=await response.json();
			console.log(data);
		}catch(error){
			console.error(error);
		}
	}
	
	var RegisterData={
			userId:'gildongss',
			userName:'유길동',
			email:'you@gildong.com',
			userAge:25
	}

	
	document.querySelector("#btnUserInsert").onclick=async function(){
		let urlParams=new URLSearchParams(RegisterData);
		let fetchOptions={
				method:'POST',
				body:urlParams
		}
		
		try{
			let response=await fetch('/mydb/Register',fetchOptions);
			let data=await response.json();
			console.log(data);
		}catch(error){
			console.error(error);
		}
	}



</script>

</body>
</html>