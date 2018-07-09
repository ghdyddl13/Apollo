var Regexemail = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
var Regexpwd =  /^(?=.*[A-Za-z0-9])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z0-9\d$@$!%*#?&]{8,}$/;

$(function() {

		$("#pwdreset").click(function() {
			$("#findpwd")[0].reset();
		});
			
		
		// apollo 인증키 구매시 실행되는 함수 
		$("#keybtn").click(function() {
			if ($('#company-name-content').val() == "") {
				alert('회사명을 입력해주세요.');
				$("#company-name-content").focus();
				return false;
			}
			if ($('#user-email-content').val() == "") {
				alert('이메일을 입력해주세요.');
				$("#user-email-content").focus();
				return false;
			}
			if (!Regexemail.test($.trim($("#user-email-content").val()))) {
				alert("이메일 형식이 아닙니다.");
				$("#user-email-content").focus();
				return false;
			}
			var data = {
				cname : $('input[name=cname]').val(),
				email : $('input[name=email]').val()
			}
			$.ajax({
				type : 'post',
				url : 'apollokey.htm',
				data : data,
				success : function(data) {
					$('input[name=cname]').val('')
					$('input[name=email]').val('')
	
					if (data.result == '성공') {
						alert('입력하신 Email로 인증키를 발송했습니다.');
					} else {
						alert('입력하신  Email이 존재하지 않습니다.\n다시한번 확인 하시기 바랍니다.');
					}
					$(".close").click();
				}
			})
		}) // end - keybtn
		
		
		$("#keyreset").click(function() {
			$("#apollokey")[0].reset();
		});

		$('#pwdbtn').click(function() {

			var data = {
				mname : $('input[name=mname2]').val(),
				mid : $('input[name=mid2]').val()
			}
			$.ajax({
				type : 'post',
				url : 'findpwd.htm',
				data : data,
				success : function(data) {
					$('input[name=mname2]').val('')
					$('input[name=mid2]').val('')
					if (data.result == '성공') {
						alert('입력하신 Email로 임시비밀번호를 발송했습니다.')
					} else if (data.result == '실패') {
						alert('입력하신 이름과 Email이 일치하지 않습니다.\n다시한번 확인 하시기 바랍니다.')
					} else {
						alert('입력하신  Email이 존재하지 않습니다.\n다시한번 확인 하시기 바랍니다.')
					}
					$(".close").click();
				}
			})
		})
	     var input = document.getElementById("pwd");
		
		$("#pwd").on("keyup", function(event){
			event.preventDefault();
			if (event.keyCode === 13) {
				document.getElementById("login_btn").click();
			}
		
		
	});
});

	function login() {
		if ($("#mid").val() == "") {
			alert("아이디를 입력하지 않았습니다.");
			$("#mid").focus();
			return false;
		}

		if ($("#pwd").val() == "") {
			alert("비밀번호 입력하지 않았습니다.");
			$("#pwd").focus();
			return false;
		}
		$("#login-form").submit();
		$("#mid").val("");
		$("#pwd").val("");
	}
	
	
	///////////////////////////////////////// 회원가입 관련 
	

	var idcheck = false;
	$(function() {
		$('#btnCheckUid').click(function(){
			if ($("#mid").val()== "") {
	            alert("아이디를 입력하지 않았습니다.");
	            $("#mid").focus();
	            return false;
	        }
			if (!Regexemail.test($.trim($("#mid").val())) ){
				alert("이메일 형식이 아닙니다.");
				$("#mid").focus();
				return false;

			}
			$.ajax(
				{
					type:"post",
					url:"midcheck.htm",
					data:{"mid" : $('#mid').val()},
					success:function(data){
						if(data.result=="fail"){
							$("#idcheck").text("중복된 아이디 입니다.").css("color","red");
							$("#mid").focus();
						}else{
							$("#idcheck").text("사용 가능한 아이디입니다.").css("color","#1153ed");
							idcheck = true;
						}
					}
				}	
			);
		});
		
		$("#mid").keyup(function(){
			idcheck = false;
			$("#idcheck").text("");
		})
		
		
		var keycheck = false;
		$('#btnCheckkey').click(function(){
			if ($("#apollokey").val()== "") {
	            alert("인증키를 입력하지 않았습니다.");
	            $("#apollokey").focus();
	            return false;
	        }
			$.ajax(
				{
					type:"post",
					url:"keycheck.htm",
					data:{"apollokey" : $('#apollokey').val()},
					success:function(data){
						if(data.result=="fail"){
							$("#keycheck").text('존재하지 않는 인증키 입니다.').css("color","red");
							$("#apollokey").focus();
						}else{
							$("#keycheck").text('인증 되었습니다.').css("color","#1153ed");
							
							keycheck = true;
						}
					}
				}	
			);
		});
		
		$("#apollokey").keyup(function(){
			keycheck = false;
			$("#keycheck").text("");
		})

	});
	
	
	

		

		function sendit() {
			if ($("#mid").val()== "") {
	            alert("아이디를 입력해 주세요.");
	            $("#mid").focus();
	            return false;
	        }
			if (!idcheck) {
	            alert("아이디 중복확인을 해주세요");
	            $("#mid").focus();
	            return false;
	        }
			
			if ($("#join-pwd").val()== "") {
	            alert("비밀번호를 입력헤 주세요.");
	            $("#join-pwd").focus();
	            return false;
	        }

			if ($("#join-pwd").val()!=$("#join-pwd-check").val()) {
	            alert("비밀번호 확인이 일치하지 않습니다.");
				$("#join-pwd").val();
				$("#join-pwd-check").val();
	            $("#join-pwd").focus();
	            return false;
	        }
			
			if ($("#mname").val()== "") {
	            alert("이름을 입력해 주세요.");
	            $("#mname").focus();
	            return false;
	        }
			if (!Regexemail.test($.trim($("#mid").val())) ){
				alert("이메일 형식이 아닙니다.");
				$("#mid").focus();
				return false;

			}
			if (!Regexpwd.test($.trim($("#join-pwd").val())) ){
				alert("비밀번호 형식이 잘못되었습니다.");
				$("#pwd").focus();
				return false;

			}
			if ($("#apollokey").val()!="" ){
				
				if(keycheck ==false){
					alert("인증키를 확인해 주세요.");
					return false;
				}
			}

			$("#f").submit();
		}
		
		
$(function(){
	$(document).on("keyup","#join-pwd",function(){
		if (!Regexpwd.test($("#join-pwd").val()) ){
			$("#regexpwd-check").css("color","red");
		}else{
			$("#regexpwd-check").css("color","#1153ed");
		}
		
		if($("#join-pwd-check").val()!=""){
			if ($("#join-pwd").val()!=$("#join-pwd-check").val()){
				$("#pwdcheck").text("비밀번호가 일치하지 않습니다.").css("color","red");;
			}else{
				$("#pwdcheck").text("비밀번호가 일치합니다.").css("color","#1153ed");
			}
		}
	});

$(document).on("keyup","#join-pwd-check",function(){
		
		if ($("#join-pwd").val()!=$("#join-pwd-check").val()){
			$("#pwdcheck").text("비밀번호가 일치하지 않습니다.").css("color","red");;
		}else{
			$("#pwdcheck").text("비밀번호가 일치합니다.").css("color","#1153ed");
		}
	});
	
})
		
		