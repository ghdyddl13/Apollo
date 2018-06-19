$(function() {
		
	$(document).on("click",".side-bar-step",function(){ 
		// 가지고 있다고 가정
		var pid = 1;
		
		$.ajax(
		       {
		           type : "post",
		           url  : "getprojectmembers.htm",
		           data : "pid="+ pid,
		           success : function(data){
		               console.log(data.memberlist);
		               
	            	   var optiondefault = jQuery("<option>",{
	            		   "text":"책임자를 선택하세요", //default 값 
	            		   "value":""
	            	   })
	            	   //해당 pid 에 참여한 멤버 불러오기
		               $('#add-step-mgr-assignee').empty().append(optiondefault);
		               $(data.memberlist).each(function (){
		            	   var option = jQuery("<option>",{
		            		   "value":this.mid,
		            		   "text":this.mname
		            	   })
		            	   	console.log(option);
		            	   $('#add-step-mgr-assignee').append(option);
		               });
		              
		           } // end-success
		        }); // end-ajax
	});
	console.log(selectProjectList());

		// 사이드바 프로젝트 우클릭 >> 추후 Project id(DB상 기본키)를 받아와 li태그에 넣어주는 작업 필요
		$(".side-project")
				.contextmenu(
						function(event) {
							var pid = event.target.id;
							event.preventDefault();
							var dropdown_ul = document.createElement("ul");
							var dropdown = '<li class="dropdown-submenu"><p data-toggle="dropdown" class="dropdown-toggle">추가 <span class="glyphicon glyphicon-menu-right"></span></p>'
							dropdown += '<ul class="dropdown-menu "><li data-toggle="modal" data-target="#add-folder">Folder추가</li><li class="side-bar-step" data-toggle="modal" data-target="#insert-step">Step추가</li></ul></li>'
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
			var dropdown = '<li data-action="first" data-toggle="modal" data-target="#insert-step">Step 추가</li>'
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
	

	
	
	/////////////////////////// 비동기 화면전환 - 프로젝트 /////////////////////////
	

		$(".side-project").click(function(evt){

			console.log("사이드바~~!~!~!~!~!~!~!~!~!");
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
			console.log("여기");
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
		$(".sdate-img").datepicker({
		    showOn: "button",
		    buttonImage: "img/calendar.png",
		    buttonImageOnly: true,
		    dateFormat: 'yy/mm/dd'


		});
		$(".edate-img").datepicker({
		    showOn: "button",
		    buttonImage: "img/calendar.png",
		    buttonImageOnly: true,
		    dateFormat: 'yy/mm/dd',
		    minDate: 0

		});	
		
	// 프로젝트 생성 버튼 클릭시 alert 창 화면 		
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
				 $('#insert-project-sday-id').val("");
				 $('#insert-proejct-eday-id').val("");
				 $('#proejct-detail').val("");
				 //$('#project-insert').close();
			 }

		 });	
	});
	
	 
	// 스텝 생성 버튼 클릭시  alert 창 화면
	$("#insert-step-btn").click(function(evt){	 
		 if($(".add-step-name").val().trim() == ""){
			alert("스텝명을 입력하세요.");
			$(".add-step-name").focus();	
			
		 } else if($('#add-folder-name').val().trim() == ""){
			 alert("책입자를 선택하세요.");
		 }
		 var newstep = $('#step-add-form').serialize();
		 console.log(newstep);
		 
		 $.ajax({
                 type:"POST",
                 url:"insertstep.htm",
                 data:newstep,
                 dataType:"json",
                 success:function(data){
                	 console.log(data);
                	 if(data.stepresult > 0){
    					 alert("스텝 생성이 완료되었습니다!");
    				 }else {
    					 alert("스텝 생성에 실패했습니다");
    				 }	
    				 $('.add-step-name').val("");
    				 $('#insert-step-sday-id').val("");
    				 $('#insert-step-eday-id').val("");
    				 $('#step-detail').val("");
    				 //$('#project-insert').close();
                 } // end - success
              	,error:function(error){
              		console.log(error);
              	
              	} // end - error
              });// end-ajax	
	});

	
	//폴더 생성 클릭 버튼시 alert 창 화면
	$('#insert-folder-btn').click(function() {
		if($('#add-folder-name').val().trim() == ""){
			alert('폴더명을 입력해주세요');
			$("#add-folder-name").focus();	
			return false;
		}
		var newfolder = $('#insert-folder-form').serialize();
		 console.log(newfolder);
		 
		 $.ajax({
                type:"POST",
                url:"insertfolder.htm",
                data:newfolder,
                dataType:"json",
                success:function(data){
               	 console.log(data);
               	 if(data.folderresult > 0){
   					 alert("폴더 생성이 완료되었습니다!");
   				 }else {
   					 alert("폴더 생성에 실패했습니다");
   				 }	
   				 $('.add-step-name').val("");
   				 //$('#add-folder').close();
                } // end - success
             	,error:function(error){
             		console.log(error);
             	
             	} // end - error
             });// end-ajax	
	});
	
}); // end - doc.on.ready



function selectProjectList(){
	$.ajax({
		url:"selectProjectList.htm",
		dataType:"json",
		success:function(data){
			console.log(data);
			$(data.projectlist).each(function(index,el){
				console.log(el.pname)
				var a = jQuery("<a>",{"class":"side-project","text":el.pname})
				var span = jQuery("<span>",{"class":"glyphicon glyphicon-duplicate", 
											"data-toggle":"collapse",
											"data-target":"#p"+el.pid})
				var div = jQuery("<div>",{"class":"side-dir collapse",
										  "id": "p"+el.pid})
											
				console.log(el.pstatuscode);
				
				if(el.pstatuscode ==1){
					$(a).prepend(span).appendTo("#working-project");
					$(div).appendTo("#working-project");

				}else if(el.pstatuscode ==2){
					$(a).prepend(span).appendTo("#finished-project");
					$(div).appendTo("#finished-project");
				}else if(el.pstatuscode==3){
					$(a).prepend(span).appendTo("#trash-bean");
					$(div).appendTo("#trash-bean");
				}
			})
		}
	});
	
}


function getSideFolders(pid){
	
}

function getSideSteps(pid){
	
}