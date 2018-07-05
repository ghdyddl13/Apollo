$(function() {
		var msg = $("#login-msg").val();
		if (msg != null) {
			alert(msg);
			
		}
		
		
		$("#pwdreset").click(function() {
			$("#findpwd")[0].reset();
		});
		$("#keybtn").click(function() {
			$("#apollokey").submit();
		});
		$("#keyreset").click(function() {
			$("#apollokey")[0].reset();
		});

		$('#pwdbtn').click(function() {

			var data = {
				mname : $('input[name=mname2]').val(),
				mid : $('input[name=mid2]').val()
			}
			console.log(data)
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
				}
			})
		})
	     var input = document.getElementById("pwd");
	     input.addEventListener("keyup", function(event) {
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