let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){} 사용하지 않는 이유는 ()=>{} this를 바인딩하기 위해서
			this.save();
		});
	},

	save: function() {
		//alert('save 호출');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		// default 가 비동기 호출
		// Ajax 통신을 통해 3개 파라미터를 json 으로 변경하여 insert 요청!!
		$.ajax({
			//회원가입 수행요청이 (100초 가정이라 해도) 아래 수행 하고 돌아온다..
			type:"POST",
			url: "/blog/api/user",
			data: JSON.stringify(data),  // http body JSON 문자열 전달
			contentType:"application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType:"json" 	//서버에서 응답이 왔을때  json 형태이면 javascript 오브젝트롤 변환한다.
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href ="/blog";
		}).fail(function(err){
			alert(JSON.stringify(err))
		});  
	}
}

index.init();