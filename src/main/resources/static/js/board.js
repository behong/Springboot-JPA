let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){} 사용하지 않는 이유는 ()=>{} this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});		
				
	},

	save: function() {
		//alert('save 호출');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		// default 가 비동기 호출
		// Ajax 통신을 통해 3개 파라미터를 json 으로 변경하여 insert 요청!!
		$.ajax({
			//회원가입 수행요청이 (100초 가정이라 해도) 아래 수행 하고 돌아온다..
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),  // http body JSON 문자열 전달
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" 	//서버에서 응답이 왔을때  json 형태이면 javascript 오브젝트롤 변환한다.
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},
	
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		// default 가 비동기 호출
		// Ajax 통신을 통해 3개 파라미터를 json 으로 변경하여 insert 요청!!
		$.ajax({
			//회원가입 수행요청이 (100초 가정이라 해도) 아래 수행 하고 돌아온다..
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),  // http body JSON 문자열 전달
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" 	//서버에서 응답이 왔을때  json 형태이면 javascript 오브젝트롤 변환한다.
		}).done(function(resp) {
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},

	deleteById: function() {

		let id = $("#id").text();
		//console.log(id);
		$.ajax({
			//회원가입 수행요청이 (100초 가정이라 해도) 아래 수행 하고 돌아온다..
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" 
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},
	
	replySave: function() {
		
		let data = {
			userId:$("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};

		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),  // http body JSON 문자열 전달
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" 	//서버에서 응답이 왔을때  json 형태이면 javascript 오브젝트롤 변환한다.
		}).done(function(resp) {
			alert("댓글 작성 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});

	},
	replyDelete: function(boardId,replyId) {

		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json" 	//서버에서 응답이 왔을때  json 형태이면 javascript 오브젝트롤 변환한다.
		}).done(function(resp) {
			alert("댓글 삭제 성공 되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});

	}			

}

index.init();