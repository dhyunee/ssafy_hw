<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import=" com.mtcom.myboard.dto.*" %>
<%
    String contextPath = request.getContextPath();
	String staticPath = contextPath + "/resources/static/";
	UserDto userDto = (UserDto) session.getAttribute("userDto");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

    <!-- JavaScript -->
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

    <!-- CSS -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css" />
    <!-- Default theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css" />
    <!-- Semantic UI theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css" />
    <!-- Bootstrap theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css" />

	
	<!-- CKEditor -->
	<script src="https://cdn.ckeditor.com/ckeditor5/35.2.1/classic/ckeditor.js"></script>
	<link rel="stylesheet" href="<%= staticPath %>css/common.css" />
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#"><img src="<%= staticPath + userDto.getUserProfileImageUrl()%>" 
            	style="width: 24px; height: 24px; border-radius: 50%;"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" id="btnLogout" href="#">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <h4 class="text-center mt-3">게시판</h4>

        <div class="input-group mb-3  mt-3">
            <input type="text" class="form-control" id="inputSearchWord" placeholder="Search"/>
            <button class="btn btn-success" type="button" id="btnSearchWord">Search</button>
        </div>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">작성자</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성일자</th>
                    <th scope="col">조회수</th>
                </tr>
            </thead>
            <tbody id="boardTbody">                    
            </tbody>
        </table>

        <div id="paginationWrapper"></div>

        <button class="btn btn-primary" type="button" id="btnInsertPage">글쓰기</button>
    </div>

    <!-- Modal Insert-->
    <div class="modal fade" id="boardInsertModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">글 쓰기</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">            
            <div class="mb-3">
                <label for="titleInsert" class="form-label">제목</label>
                <input type="text" class="form-control" id="titleInsert">
              </div>
              <div class="mb-3">
                <label for="contentInsert" class="form-label">내용</label>
                <!-- CKEditor insert -->
                <div id="divEditorInsert"></div>
              </div>
              <div class="mb-3">
              	<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert">
				  <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
				</div>
              </div>
              <div class="mb-3" style="display: none;" id="imgFileUploadInsertWrapper">
              	<input type="file" id="inputFileUploadInsert" multiple >
              	<div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper"></div>
				</div>
              <button id="btnBoardInsert" type="button" class="btn btn-sm btn-primary float-end">등록</button>
        </div>            
      </div>
    </div>
  </div>

    <!-- Modal Detail-->
    <div class="modal fade" id="boardDetailModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">글 상세</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">            
      <table class="table table-hover">
            	<tbody>
            		<tr><td>글 번호</td><td id="boardIdDetail">#</td></tr>
            		<tr><td>제목</td><td id="titleDetail">#</td></tr>
            		<tr><td>내용</td><td id="contentDetail">#</td></tr>
            		<tr><td>작성자</td><td id="userNameDetail">#</td></tr>
            		<tr><td>작성일시</td><td id="regDtDetail">#</td></tr>
            		<tr><td>조회수</td><td id="readCountDetail">#</td></tr>                    
            	</tbody>
        	</table>
        	
        	<button id="btnBoardUpdateForm" type="button" class="btn btn-sm btn-primary">수정</button>
        	<button id="btnBoardDeleteConfirm" type="button" class="btn btn-sm btn-warning">삭제</button>
        </div>

      </div>
    </div>
  </div>  
  
  <!-- Modal Update-->
    <div class="modal fade" id="boardUpdateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">글 수정하기</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">            
            <div class="mb-3">
                <label for="titleUpdate" class="form-label">제목</label>
                <input type="text" class="form-control" id="titleUpdate">
              </div>
              <div class="mb-3">
                <label for="contentUpdate" class="form-label">내용</label>
                <!-- CKEditor update -->
                <div id="divEditorUpdate"></div>
              </div>
              <div class="mb-3">
              	<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="chkFileUploadUpdate">
				  <label class="form-check-label" for="chkFileUploadUpdate">파일 변경</label>
				</div>
              </div>
              <div class="mb-3" style="display:none;" id="imgFileUploadUpdateWrapper">
              	<input type="file" id="inputFileUploadUpdate" multiple >
              	<div id="imgFileUploadUpdateThumbnail" class="thumbnail-wrapper"></div>
				</div>
              <button id="btnBoardUpdate" type="button" class="btn btn-sm btn-primary float-end">수정</button>
        </div>            
      </div>
    </div>
  </div>
    
<script src="<%=staticPath %>/js/util.js"></script>
<script>
	
	var OFFSET = 0;
	var SEARCH_WORD = '';
	
	// pagination
	var LIST_ROW_COUNT = 10; // limit
	var PAGE_LINK_COUNT = 10; // pagination link 갯수    	
	var CURRENT_PAGE_INDEX = 1;
	var TOTAL_LIST_ITEM_COUNT = 0; // 총 건수
	
	const SUCCESS = 1; // response 성공
	
	/*
	CKEditor 전역변수
	*/
	var CKEditorInsert;
	var CKEditorUpdate;
	
	window.onload = function(){

		initCKEditor(); // CKEditor 초기화
		
		boardList();
		
		document.querySelector("#btnSearchWord").onclick = function(){
			SEARCH_WORD = document.querySelector("#inputSearchWord").value;
			// 초기화
			OFFSET = 0;
			CURRENT_PAGE_INDEX = 1;
			
			boardList();
		}
		
		
		// insert page
		// javascript 로 bootstrap modal 제어
		document.querySelector("#btnInsertPage").onclick = function(){
			
			document.querySelector("#titleInsert").value = "";
			CKEditorInsert.setData("");
			
			let modal = new bootstrap.Modal(
					document.querySelector("#boardInsertModal")
			);
			
			modal.show();
		}
		
		// insert
		document.querySelector("#btnBoardInsert").onclick = function(){
			if( validateInsert() ){
				boardInsert();
			}else{
				alertify.error("입력을 확인해 주세요.")
			}
		}
		
		// update
		document.querySelector("#btnBoardUpdateForm").onclick = function(){
		// detail => update 글 상세 내용을 복사 ( key 인 boardId 를 복사 포함)
		// detail modal 닫고 update modal 을 띄운다.
		let boardId = document.querySelector("#boardDetailModal").getAttribute("data-boardId");
		document.querySelector("#boardUpdateModal").setAttribute("data-boardId", boardId);
		
		document.querySelector("#titleUpdate").value = document.querySelector("#titleDetail").innerHTML;
		
		CKEditorUpdate.setData(document.querySelector("#contentDetail").innerHTML);
		
		let modalDetail = new bootstrap.Modal(
							document.querySelector("#boardDetailModal")
					);
					console.dir( modalDetail );
					
		modalDetail.hide();

			let modalUpdate = new bootstrap.Modal(
					document.querySelector("#boardUpdateModal")
			);
			
			modalUpdate.show();
		}
		
		document.querySelector("#btnBoardUpdate").onclick = function(){
			if( validateUpdate() ){
				boardUpdate();
			}else{
				alertify.error("입력을 확인해 주세요.")
			}
		}
		
		// delete
		document.querySelector("#btnBoardDeleteConfirm").onclick = function(){
			alertify.confirm("삭제 확인", "글을 삭제하시겠습니까?", 
					function(){
						boardDelete();
					}, 
					function(){
						console.log('user cancel');
					});
		}
		
		// logout
		document.querySelector("#btnLogout").onclick = function(){
			logout();
		}
		
		//파일 업로드 checkbox
		document.querySelector("#chkFileUploadInsert").onchange=function(){
			if(this.checked){
				document.querySelector("#imgFileUploadInsertWrapper").style.display="block";
			}else{
				document.querySelector("#inputFileUploadInsert").value="";
				document.querySelector("#imgFileUploadInsertWrapper").style.display="none";
				document.querySelector("#imgFileUploadInsertThumbnail").innerHTML="";
			}
		}
		
		
		//파일 업로드 checkbox
		document.querySelector("#chkFileUploadUpdate").onchange=function(){
			if(this.checked){
				document.querySelector("#imgFileUploadUpdateWrapper").style.display="block";
			}else{
				document.querySelector("#inputFileUploadUpdate").value="";
				document.querySelector("#imgFileUploadUpdateWrapper").style.display="none";
				document.querySelector("#imgFileUploadUpdateThumbnail").innerHTML="";
			}
		}
		
		//thumbnail imate 처리
		document.querySelector("#inputFileUploadInsert").onchange=function(){
			const fileArray=Array.from(this.files);//첨부파일 여러 개가 javascript 객체의 배열로 변환
			fileArray.forEach(file =>{
				let reader=new FileReader();
				reader.readAsDataURL(file);//파일을 읽어들인다.
				reader.onload=function(e){//파일을 다 읽으면
					let thumbnailHTML=`<img src="\${e.target.result}">`;
					document.querySelector("#imgFileUploadInsertThumbnail").insertAdjacentHTML("beforeend",thumbnailHTML);
					
				}
			})
		}
		
		//thumbnail imate 처리
		document.querySelector("#inputFileUploadUpdate").onchange=function(){
			const fileArray=Array.from(this.files);//첨부파일 여러 개가 javascript 객체의 배열로 변환
			fileArray.forEach(file =>{
				let reader=new FileReader();
				reader.readAsDataURL(file);//파일을 읽어들인다.
				reader.onload=function(e){//파일을 다 읽으면
					let thumbnailHTML=`<img src="\${e.target.result}">`;
					document.querySelector("#imgFileUploadUpdateThumbnail").insertAdjacentHTML("beforeend",thumbnailHTML);
					
				}
			})
		}
	}
	
	
	
	// CKEditor 초기화
	async function initCKEditor() {
		try {
			CKEditorInsert = await ClassicEditor.create(document.querySelector( '#divEditorInsert' ));
			CKEditorUpdate = await ClassicEditor.create(document.querySelector( '#divEditorUpdate' ));
			
		} catch (error) {
			console.error(error);
		}
	}
	// GET
	async function boardList(){
		let url = '<%= contextPath %>/boards';
		let urlParams = '?limit=' + LIST_ROW_COUNT + '&offset=' + OFFSET + '&searchWord=' + SEARCH_WORD;
		//let urlParams = `?limit=${LIST_ROW_COUNT}&offset=${OFFSET}`; // jsp el 표기법과 javascript es6 literal template 과 충돌
		let fetchOptions = {
			method: 'GET',
		}
		
		try{
			let response = await fetch( url + urlParams, fetchOptions);
			let data = await response.json();
			console.log( data );
			
			if (data.result == SUCCESS) {
				makeListHtml(data.list);
				// 총 건수를 이미 가져왔으므로 바로 처리
    			TOTAL_LIST_ITEM_COUNT = data.count;
    			makePaginationHtml( LIST_ROW_COUNT, PAGE_LINK_COUNT, CURRENT_PAGE_INDEX, TOTAL_LIST_ITEM_COUNT, "paginationWrapper" );
			}
			
			
		}catch( error ){
			console.log(error);
			alertify.error('글 조회 과정에서 문제가 생겼습니다.')
		}
	}
	
	// javascript array 를 이용해서 테이블 <tr> 을 반복적으로 만들어서 목록을 완성
	function makeListHtml( list ){
		let listHtml = ``;
		
		list.forEach( el => {
			let boardId = el.boardId;
			let userName = el.userName;
			let title = el.title;
			let regDt = el.regDt;
			let regDtStr = makeDateStr( regDt.date.year, regDt.date.month, regDt.date.day, '/');
			let readCount = el.readCount;
			
			listHtml +=
				`<tr style="cursor:pointer" data-boardId=\${boardId}><td>\${boardId}</td><td>\${userName}</td><td>\${title}</td><td>\${regDtStr}</td><td>\${readCount}</td></tr>`;
		});
		
		document.querySelector("#boardTbody").innerHTML = listHtml;
		
		makeListHtmlEventHandler();
		
		// boardListTotalCnt(); - 이미 boardList()에서 가져옴
	}
	
	function makeListHtmlEventHandler(){
		document.querySelectorAll("#boardTbody tr").forEach( el => {
			el.onclick = function(){
				let boardId = this.getAttribute("data-boardId");
				boardDetail(boardId);
			}
		})
	}
	
	// GET
	async function boardListTotalCnt(){
		let url = '<%= contextPath %>/board/boardListTotalCnt';
		let urlParams = '?searchWord=' + SEARCH_WORD;
		//let urlParams = `?limit=${LIST_ROW_COUNT}&offset=${OFFSET}`; // jsp el 표기법과 javascript es6 literal template 과 충돌
		let fetchOptions = {
			method: 'GET',
		}
		
		try{
			let response = await fetch( url + urlParams, fetchOptions);
			let data = await response.json();
			console.log( data );
			TOTAL_LIST_ITEM_COUNT = data.totalCnt;
			makePaginationHtml( LIST_ROW_COUNT, PAGE_LINK_COUNT, CURRENT_PAGE_INDEX, TOTAL_LIST_ITEM_COUNT, "paginationWrapper" );
			
		}catch( error ){
			console.log(error);
			alertify.error('글 조회 과정에서 문제가 생겼습니다.')
		}
	}
	
	function movePage(pageIndex){
		OFFSET = (pageIndex - 1) * LIST_ROW_COUNT;
		CURRENT_PAGE_INDEX = pageIndex;
		boardList();
	}
	
	
	// GET
	async function boardDetail(boardId){
		let url = '<%= contextPath %>/boards/' + boardId; // path variable

 		let fetchOptions = {
 			method: 'GET',
 		}
 		
 		try{
 			let response = await fetch( url, fetchOptions);
 			let data = await response.json();
 			console.log( data );
 			
 			if (data.result == SUCCESS) {
 				console.log(data.dto);
 				makeDetailHtml( data.dto );
 			} else {
 				alertify.error('글 조회 과정에서 문제가 생겼습니다.');
 			}
 		}catch( error ){
 			console.log(error);
 			alertify.error('글 조회 과정에서 문제가 생겼습니다.')
 		}
 	}
 	
 	function makeDetailHtml( detail ){
		let boardId = detail.boardId;
		let userName = detail.userName;
		let title = detail.title;
		let content = detail.content;
		let regDt = detail.regDt;
		let regDtStr = 
			makeDateStr( regDt.date.year, regDt.date.month, regDt.date.day, '/') + ' ' +
			makeTimeStr( regDt.time.hour, regDt.time.minute, regDt.time.second, ':');
		let readCount = detail.readCount;
		let sameUser = detail.sameUser;
		
		document.querySelector("#boardDetailModal").setAttribute("data-boardId", boardId );
		 		document.querySelector("#boardIdDetail").innerHTML = boardId;
		 		document.querySelector("#titleDetail").innerHTML = title;
		 		document.querySelector("#contentDetail").innerHTML = content;
		 		document.querySelector("#userNameDetail").innerHTML = userName;
		 		document.querySelector("#regDtDetail").innerHTML = regDtStr;
		 		document.querySelector("#readCountDetail").innerHTML = readCount;
		 		
		 		if( sameUser ){
		 			document.querySelector("#btnBoardUpdateForm").style.display = "inline-block";
		 			document.querySelector("#btnBoardDeleteConfirm").style.display = "inline-block";
		 		}else{
		 			document.querySelector("#btnBoardUpdateForm").style.display = "none";
		 			document.querySelector("#btnBoardDeleteConfirm").style.display = "none";
		 		}
		 		
		 		let modal = new bootstrap.Modal(
				document.querySelector("#boardDetailModal")
		);
		
		modal.show();
 	}
 	
 	///////////////////////////////////
     function validateInsert(){
         // return true / false
         let isTitleInsertValid = false;
         let isContentInsertValid = false;

         let titleInsertValue = document.querySelector("#titleInsert").value;
         if( titleInsertValue.length > 0 ){
         	isTitleInsertValid = true;
         }

         let contentInsertValue = CKEditorInsert.getData();
         if( contentInsertValue.length > 0 ){
         	isContentInsertValid = true;
         }

         if( isTitleInsertValid && isContentInsertValid ){
           return true;
         }
         return false;
       }
     
 		async function boardInsert(){
 			//기존의 x-www...파라미터 처리 대신 multipart/form-date
 			let formData=new Formdata();
 			formData.append("title",document.querySelector("#titleInsert").value);
 			formData.append("content",CKEditorInsert.getData());
 			
 			//file upload
 			let files=document.querySelector("#inputFileUploadInsert").files;
 			
 			//#1
//  			for(let i=0;i<files.length;i++){
//  				formData.append("file",files[i]);
//  			}
 			
 			//#2
 			const fileArray=Array.from(files);
 			fileArray.forEach(file=>formData.append("file",file));
 			
 			let url = "<%= contextPath%>/boards";
 			
 		// fetch options
	         let fetchOptions = {
	           method: "POST",
	           body: formData
	         }
 		
	         try{
		          let response = await fetch( url, fetchOptions);
		          let data = await response.json(); // json => javascript object <= JSON.parse()
		          if( data.result == SUCCESS){ // login.jsp => boardMain.jsp 로 페이지 이동 ( 새로운 페이지(html....) 요청)
		            alertify.success('글이 등록되었습니다.');
		            boardList();
		          } else {
		        	  alertify.error('글 등록 과정에서 오류가 발생했습니다.');
		          }            	
		      }catch(error){
		      	alertify.error('글 등록 과정에서 오류가 발생했습니다.');
		     }		
 		}
 	
//      async function boardInsert(){
//      	let title = document.querySelector("#titleInsert").value;
//          let content = CKEditorInsert.getData();

//          // parameter 
//          let urlParams = new URLSearchParams({
//            title: title,
//            content: content
//          });
//          // fetch options
//          let fetchOptions = {
//            method: "POST",
//            body: urlParams
//          }

<%--          let url = "<%= contextPath%>/boards"; --%>
//         try{
//             let response = await fetch( url, fetchOptions);
//             let data = await response.json(); // json => javascript object <= JSON.parse()
//             if( data.result == SUCCESS){ // login.jsp => boardMain.jsp 로 페이지 이동 ( 새로운 페이지(html....) 요청)
//               alertify.success('글이 등록되었습니다.');
//               boardList();
//             } else {
//           	  alertify.error('글 등록 과정에서 오류가 발생했습니다.');
//             }            	
//         }catch(error){
//         	alertify.error('글 등록 과정에서 오류가 발생했습니다.');
//         }

//     }
    
    function validateUpdate(){
        // return true / false
        let isTitleUpdateValid = false;
        let isContentUpdateValid = false;

        let titleUpdateValue = document.querySelector("#titleUpdate").value;
        if( titleUpdateValue.length > 0 ){
        	isTitleUpdateValid = true;
        }

        let contentUpdateValue = CKEditorUpdate.getData();
        if( contentUpdateValue.length > 0 ){
        	isContentUpdateValid = true;
        }

        if( isTitleUpdateValid && isContentUpdateValid ){
          return true;
        }
        return false;
    }
    
    
		async function boardUpdate(){
			let boardId = document.querySelector("#boardUpdateModal").getAttribute("data-boardId");
 			//기존의 x-www...파라미터 처리 대신 multipart/form-date
 			let formData=new Formdata();
 			formData.append("boardId",boardId);
 			formData.append("title",document.querySelector("#titleUpdate").value);
 			formData.append("content",CKEditorUpdate.getData());
 			
 			//file upload
 			let files=document.querySelector("#inputFileUploadUpdate").files;
 			
 			//#1
//  			for(let i=0;i<files.length;i++){
//  				formData.append("file",files[i]);
//  			}
 			
 			//#2
 			const fileArray=Array.from(files);
 			fileArray.forEach(file=>formData.append("file",file));
 			
 			let url = "<%= contextPath%>/boards"+boardId;
 			
 		// fetch options
	         let fetchOptions = {
	           method: "POST",
	           body: formData
	         }
 		
	         try{
		          let response = await fetch( url, fetchOptions);
		          let data = await response.json(); // json => javascript object <= JSON.parse()
		          if( data.result == SUCCESS){ // login.jsp => boardMain.jsp 로 페이지 이동 ( 새로운 페이지(html....) 요청)
		            alertify.success('글이 등록되었습니다.');
		            boardList();
		          } else {
		        	  alertify.error('글 수정 과정에서 오류가 발생했습니다.');
		          }            	
		      }catch(error){
		      	alertify.error('글 수정 과정에서 오류가 발생했습니다.');
		     }		
 		}
    
//     async function boardUpdate(){
//     	let boardId = document.querySelector("#boardUpdateModal").getAttribute("data-boardId");
//     	let title = document.querySelector("#titleUpdate").value;
//         let content = CKEditorUpdate.getData();

//         // parameter 
//         let urlParams = new URLSearchParams({
//           boardId: boardId,
//           title: title,
//           content: content
//         });
//         // fetch options
//         let fetchOptions = {
//           method: "POST",
//           body: urlParams
//         }

<%--         let url = "<%= contextPath%>/boards/" + boardId; --%>
//         try{
//             let response = await fetch( url, fetchOptions);
//             let data = await response.json(); // json => javascript object <= JSON.parse()
//             if( data.result == SUCCESS){ // login.jsp => boardMain.jsp 로 페이지 이동 ( 새로운 페이지(html....) 요청)
//               alertify.success('글이 수정되었습니다.');
//               boardList();
//             } else {
//           	  alertify.error('글 수정 과정에서 오류가 발생했습니다.');
//             }            	
//         }catch(error){
//         	alertify.error('글 수정 과정에서 오류가 발생했습니다.');
//         }

//     }
    
    async function boardDelete(){
    	let boardId = document.querySelector("#boardDetailModal").getAttribute("data-boardId");

        let url = "<%= contextPath%>/boards/" + boardId;
        let fetchOptions = {
                method: "DELETE"
              }
        try{
            let response = await fetch( url , fetchOptions); // default GET
            let data = await response.json(); // json => javascript object <= JSON.parse()
            if( data.result == SUCCESS){ // login.jsp => boardMain.jsp 로 페이지 이동 ( 새로운 페이지(html....) 요청)
              alertify.success('글이 삭제되었습니다.');
              boardList();
            } else {
          	  alertify.error('글 삭제 과정에서 오류가 발생했습니다.');
            }            	
        }catch(error){
        	alertify.error('글 삭제 과정에서 오류가 발생했습니다.');
        }

    }
    
    async function logout(){
    	// logout 요청 => 벡앤드에서는 session.invalidate(); result:success
    	// result 가 success => login 페이지 이동 (ajax X , window.location.href 로 이동)
    	let url = "<%= contextPath%>/logout";
    	
    	try{
            let response = await fetch( url ); // default GET
            let data = await response.json(); // json => javascript object <= JSON.parse()
            if( data.result == "success"){ // login.jsp => boardMain.jsp 로 페이지 이동 ( 새로운 페이지(html....) 요청)
              window.location.href = "<%= contextPath%>/jsp/login.jsp";
            }else if( data.result == "fail"){
          	  alertify.error('로그인과정에서 오류가 발생했습니다.');
            }            	
        }catch(error){
        	alertify.error('로그인 과정에서 오류가 발생했습니다.');
        }
    }
    
    async function boardInsert(){
        let title = document.querySelector("#titleInsert").value;
        let content = CKEditorInsert.getData();

        // parameter 
        let urlParams = new URLSearchParams({
          title: title,
          content: content
        });
        // fetch options
        let fetchOptions = {
          method: "POST",
          body: urlParams
        }

        let url = "<%= contextPath%>/boards";
        try{
            let response = await fetch( url, fetchOptions);
            let data = await response.json(); // json => javascript object <= JSON.parse()
            if( data.result == SUCCESS){ // login.jsp => boardMain.jsp 로 페이지 이동 ( 새로운 페이지(html....) 요청)
              alertify.success('글이 등록되었습니다.');
              boardList();
            }else {
                alertify.error('글 등록 과정에서 오류가 발생했습니다.');
            }                
        }catch(error){
            alertify.error('글 등록 과정에서 오류가 발생했습니다.');
        }
    }
</script>
</body>

</html>