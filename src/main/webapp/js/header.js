$(function() {
	// 헤더 inbox 페이지
	$("#inbox-page").click(function(evt){
		$.ajax({
			type:"GET",
			url:"inbox.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
			}
		})
	});
	
	//// 헤더 mywork 페이지
	$("#myWork-page").click(function(evt){
		$.ajax({
			type:"GET",
			url:"myWork.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	
	//// 헤더 starred task 페이지
	$("#starredTask-page").click(function(evt){
		$.ajax({
			url:"starredTask.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	
	//// 헤더 report 페이지
	$("#report-page").click(function(evt){
		$.ajax({
			url:"report.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	
	//// 헤더 stream 페이지
	$("#stream-page").click(function(evt){
		$.ajax({
			url:"stream.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	//// 헤더 stream 페이지
	$("#header-memberlist").click(function(evt){
		$.ajax({
			url:"selectmemberlist.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});

	
	// 헤더에서 우상단 개인정보수정 클릭시 실행되는 함수
	// 헤더 개인정보수정 Modal
	$('#header-profile-edit').click(function(evt) {
		var mid = $('#edit-profile-mid').val(mid);
		//console.log(mid);
		
		
		$.ajax({
			url:"updatememberinfo.htm",
			data:{mid:mid},
			dataType:"json",
			contentType:'multipart/form-data',
			contentType:false,
			processData:false,
			success:function(data) {
				//var image = (data.updatememberinfo.image)?data.updatememberinfo.image :"img/user_image.png"
				//$('#edit-profile-modal-img').attr("src",image);
				$('#edit-profile-mname').val(data.updatememberinfo.mname);
				$('#edit-profile-mid').val(data.updatememberinfo.mid);
				$('#edit-profile-apollokey').val(data.updatememberinfo.apollokey);
				$('#edit-profile-pnum').val(data.updatememberinfo.pnum);
				$('#edit-profile-deptname').val(data.updatememberinfo.deptname);
				$('#edit-profile-position').val(data.updatememberinfo.position);
				$('#edit-profile-image').val(data.updatememberinfo.image);
				
			} // end - success
		}); // end- ajax
	}); // end - click
	
	
	// 개인정보수정 Modal 에서 1번째 수정버튼 클릭시 실행되는 함수
	$('#update-edit-profile-btn').click(function() {
		//var form = $('#edit-profile-form')[0];
		//var updateprofile = new FormData(form); 
		//var updateprofile = $('#edit-profile-form').serialize();
		//console.log(updateprofile);
		var formData = new FormData($('#edit-profile-form')[0]);
		formData.append('iamge', $('input[type=file]')[0].files[0]);
		
		$('#edit-profile-form').ajaxForm({
			type:"post",
			url:"updatemember.htm",
			data:formData,
			//dataType:"json",
			enctype:"multipart/form-data",
			processData: false,
			contentType: false,
			success:function(data){
				console.log(data);
				
				if(data.updatemember > 0) {
					alert('개인정보수정이 완료되었습니다!');
					$(".close").click();
				}else {
					alert('개인정보수정에 실패되었습니다');
					$(".close").click();
				}
				
			} // end - success
		}); // end- ajax
		$("#edit-profile-form").submit();
	}); // end - click
	
	//개인정보수정 Modal에서 인증확인 버튼 클릭시 실행되는 함수
	$('#profile-apollokey-check').click(function() {
		var apollokey = $('#edit-profile-apollokey').val();
		
		$.ajax({
			type:"post",
			url:"updatekeycheck.htm",
			data:{apollokey:apollokey},
			success:function(data){
				if(data.result == "success"){
					alert('인증키가 확인되었습니다!');
					
				}else {
					alert('등록되지 않은 인증키입니다');
					$('#edit-profile-apollokey').focus();
				}
			} // end - success
		}); // end - ajax
	});
	
	
	$('#update-pwd-btn').click(function() {
		var cpwd = $('#edit-profile-cpwd').val();
		var upwd = $('#edit-profile-upwd').val();
		var Regexpwd = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;
		
		// 비밀번호 유효성 검증
		if($('#edit-profile-cpwd').val() == "") {
			alert('현재 비밀번호를 입력해주세요');
			$('#edit-profile-cpwd').focus();
			return false;
		}
		else if($('#edit-profile-upwd').val() == "") {
			alert('비밀번호를 입력해주세요');
			$('#edit-profile-upwd').focus();
			return false;
		}
		else if($('#edit-profile-upwd2').val() == "") {
			alert('비밀번호를 입력해주세요');
			$('#edit-profile-upwd2').focus();
			return false;
		}
		else if($('#edit-profile-upwd').val() != $('#edit-profile-upwd2').val()) {
			alert("변경 비밀번호가 일치하지 않습니다.");
			return false;
		}
		else if(!Regexpwd.test($.trim($("#edit-profile-upwd").val()))) {
			alert("비밀번호 형식이 잘못되었습니다.");
			$("#edit-profile-upwd").focus();
			return false;
		}
		
		$.ajax({
			type:"post",
			url:"updatepwd.htm",
			data:{cpwd:cpwd,upwd:upwd},
			success:function(data){
				console.log(data.result); //현재비번과 DB 비번 비교 

				// 개인정보수정 modal에서 입력한 현재 비밀번호와 DB에 저장된 비밀번호 비교
				if(data.result > 0) {
					console.log("현재 비밀번호 일치");
				}else {
					alert('현재 비밀번호가 다릅니다');
					$('#edit-profile-cpwd').focus();
					return false;
				}

				// 비밀번호 변경
				if(data.count > 0){
					alert('비밀번호 변경이 완료되었습니다');
				}else {
					alert('비밀번호 변경이 실패되었습니다');
				}
			}
		}); // end - ajax
		

	});

	
	
});