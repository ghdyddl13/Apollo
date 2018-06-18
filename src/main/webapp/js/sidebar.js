$(function() {

		// 사이드바 프로젝트 우클릭 >> 추후 Project id(DB상 기본키)를 받아와 li태그에 넣어주는 작업 필요
		$(".side-project")
				.contextmenu(
						function(event) {
							event.preventDefault();
							var dropdown_ul = document.createElement("ul");
							var dropdown = '<li class="dropdown-submenu"><p data-toggle="dropdown" class="dropdown-toggle">추가 <span class="glyphicon glyphicon-menu-right"></span></p>'
							dropdown += '<ul class="dropdown-menu "><li data-toggle="modal" data-target="#add-folder">Folder추가</li><li data-toggle="modal" data-target="#step-add-modal">Step추가</li></ul></li>'
							dropdown += '<li data-action="second">완료</li>'
							dropdown += '<li data-action="third" data-toggle="modal" data-target="#update-project">수정</li>'
							dropdown += '<li data-action="fourth" data-toggle="modal" data-target="#delete-project">삭제</li>'

							$(dropdown_ul).attr("class", "custom-menu").append(
									dropdown);
							console.log(dropdown_ul)
							$(dropdown_ul).css({
								top : event.pageY + "px",
								left : event.pageX + "px"
							}).appendTo("body");
						});

		// 사이드바 폴더 우클릭  >> 추후 폴더 id(DB상 기본키)를 받아와 li 태그에 넣어주는 작업 필요
		$(".side-folder").contextmenu(function(event) {
			event.preventDefault();
			var dropdown_ul = document.createElement("ul");
			var dropdown = '<li data-action="first" data-toggle="modal" data-target="#step-add-modal">Step 추가</li>'
			dropdown += '<li data-action="second">수정</li>'
			dropdown += '<li data-action="third">삭제</li>'
			$(dropdown_ul).attr("class", "custom-menu").append(dropdown);
			console.log(dropdown_ul)
			$(dropdown_ul).css({
				top : event.pageY + "px",
				left : event.pageX + "px"
			}).appendTo("body");

		});

		// 사이드바 스텝 우클릭  >> 추후 스텝 id(DB상 기본키)를 받아와 li태그에 넣어주는 작업 필요

		$(".side-step").contextmenu(function(event) {
			event.preventDefault();
			var dropdown_ul = document.createElement("ul");
			var dropdown = '<li data-action="second">수정</li>'
			dropdown += '<li data-action="third">이동</li>'

			dropdown += '<li data-action="third">삭제</li>'
			$(dropdown_ul).attr("class", "custom-menu").append(dropdown);
			console.log(dropdown_ul)
			$(dropdown_ul).css({
				top : event.pageY + "px",
				left : event.pageX + "px"
			}).appendTo("body");

		});
		// 사이드 우클릭 메뉴 닫는 함수
		$(document).bind("mousedown", function(e) {

			// If the clicked element is not the menu
			if (!$(e.target).parents(".custom-menu").length > 0) {

				// Hide it
				$(".custom-menu").hide(100);
			}
		});
	
/*	$(".step1").click(function(event) {
		console.log(event.target.Id);
		$.ajax(
			{
				url:"list.htm",
				dataType:"html",
				success:function(data){
					console.log(data);
					$("#main-box").empty();
					$("#main-box").append(data);
					
				}
			}
		)
	});*/
	
	
	/////////////////////////// 비동기 화면전환 - 프로젝트 /////////////////////////
	

		$(".side-project").click(function(evt){
			// 여기서 누르면 pid 받아오는 로직을 처리해서 요청 주소에 붙여 보낸다
			// 지금은 pid가 1이라고 가정하고 실시
			var pid = '1';
			
			// mid도 필요하므로 일단 가정
			var mid = 'testid1';
			
			 var send_data = new Array();
			 send_data[0] = pid;
			 send_data[1] = mid;
			
			$.ajax({
				url:"information.htm",
				data: "data=" + send_data,
				dataType:"html",
				success:function(data){
					 $("#main-box").empty();
					 $("#main-box").append(data);	 		
					 
				}
			})
	    });
	
		
		$(".side-step").click(function(evt){
			
			console.log(evt.target.id);
			console.log("여기")
			$.ajax({
				url:"list.htm",
				data:{sid:evt.target.id},
				dataType:"html",
				success:function(data){
					 $("#main-box").empty();
					 $("#main-box").append(data);	 		
					 
				}
			})
	    });
	/* modal 창(project, step) dateficker */
		$(".date-img").datepicker({
		    showOn: "button",
		    buttonImage: "img/calendar.png",
		    buttonImageOnly: true,
		    dateFormat: 'yy/mm/dd'


		});
		
	//프로젝트 생성 버튼 클릭시 alert 창 화면 		
	$("#insert-project-btn").click(function(evt){
		 if($("#add-project-name").val().trim() == ""){
			alert("프로젝트명을 입력해주세요.");
			$("#add-project-name").focus();	
			return false;
		 }
		 var newproject = $("#project-add-form").serialize(); //serialize() : input 값이 있는 tag 들을 직렬화하여 가져온다 (ex.a=1&b=2&c=3&d=4&e=5)
		 console.log(newproject);
		 $.ajax({
			 url:"insertproject.htm",
			 data:newproject,
			 type:"POST",
			 dataType:"json",
			 success: function(data){
				 console.log(data); //data+"data" 로 하면 Object 타입으로 변환되므로 json 형태로 받아볼 경우 data만 찍어보면 된다.
				 if(data.result > 0){
					 alert("프로젝트 생성이 완료되었습니다!");
				 }else {
					 alert("프로젝트 생성에 실패했습니다");
				 }	
				 $('#add-project-name').val("");
				 $('#method').val("");
				 $('#sday-id').val("");
				 $('#eday-id').val("");
				 $('#project-detail').val("");
				 $('.close');
			 }

		 });	
	});
	
	
});


