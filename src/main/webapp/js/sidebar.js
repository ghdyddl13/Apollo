$(function() {
	
	MakeSideProjectDir();
	
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
/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 사이드바에 프로젝트 리스트디렉토리 구조 생성함수
 작성자명 : 박 민 식
 */
function selectProjectList(){
	var dfd = $.Deferred(); // 비동기 함수의 순서를 정해주기 위해(동기화) Defferred의 객체의 Promise를 활용한다.
	var pids=[];
	$.ajax({
		url:"selectProjectList.htm",
		dataType:"json",
		method:"post",
		success:function(data){
			if(data!=null){ /// 참여중인 프로젝트가 있을 경우 
				$(data.projectlist).each(function(index,el){
					pids.push(el.pid);
					var a = jQuery("<a>",{"class":"side-project","text":el.pname})
					var span = jQuery("<span>",{"class":"glyphicon glyphicon-duplicate", 
						"data-toggle":"collapse",
						"data-target":"#p"+el.pid})
						var div = jQuery("<div>",{"class":"side-dir collapse",
							"id": "p"+el.pid});
							
							console.log(el.pstatuscode);
					
					//////프로젝트의 상태에 따라 진행, 완료, 휴지통에 구분하여 append
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
			}else{ // 참여중인 프로젝트가 없을 경우 
			
			}
			dfd.resolve(pids); // 요청을 통해 받은 결과를 resolve에 담아 리턴할 수 있다. 
		}
	});
	return dfd.promise(); // 함수가 종료될 때, Promise로 리턴해주면 
}

/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 참여한 모든 프로젝트에 속해있는 Folder의 리스트를 불러오는 함수
 작성자명 : 박 민 식
 */
function selectFolderList(pids){
	if(pids==null) return;
	jQuery.ajaxSettings.traditional = true;
	
	var ajax =$.ajax({ // ajax는 내부적으로 Deffered와 호환이 되어 있는 함수이기 때문에 따로 선언을 해주지 않아도 된다. 
		               // 그저, ajax의 결과값을 저장한 변수를 return해주면 끝
		url:"selectFolderList.htm",
		method:"post",
		dataType:"json",
		data: {'pids':pids},
		success:function(data){
			console.log(data);
		},
		error:function(error){
			console.log(error);
		}
	})
	return ajax;
}

/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 참여한 모든 프로젝트에 속해있는 Step의 리스트를 불러오는 함수
 작성자명 : 박 민 식
 */
function selectStepList(pids){
	if(pids==null) return;
	jQuery.ajaxSettings.traditional = true;
	var data;
	var ajax = $.ajax({
		url:"selectStepList.htm",
		method:"post",
		dataType:"json",
		data: {'pids':pids},
		success:function(data){
			console.log(data);
		},
		error:function(error){
			console.log(error);
		}
	})
	
	return ajax;
}
/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 사이드바의 구조를 만들어주는 함수 
 작성자명 : 박 민 식
 */
function MakeSideProjectDir(){
	
	 $.when(selectProjectList()).done(function(data){ //먼저 프로젝트의 리스트를 가져와 뿌려준 후, 
		if(data==null) return;
		var pids = data;
		MakeSideSubDir(pids); // 각 프로젝트의 내부 구조를 채워줄 요소들을 가져오는 함수를 실행한다. 
	})

}
/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 프로젝트 내부의 디렉토리구조를 만들어주는 함수  
 작성자명 : 박 민 식
 */	
function MakeSideSubDir(pids){
	
	$.when(selectFolderList(pids), selectStepList(pids)).done(function(folders,steps){ //먼저, 프로젝트들에 속한 폴더와 스텝들의 정보를 가져온 후 디렉토리 구조를 구성

		var folders = folders[0].folderlist; //폴더 list
		var steps= steps[0].steplist; // 스텝 list
		console.log(folders);
		console.log(steps);
		///// 먼저 폴더를 화면에 뿌려준다. 
		if(folders!=null){ //폴더가 하나라도 있다면
			
			$(folders).each(function(index,folder){
				var a =jQuery("<a>",{"class":"side-folder","text":folder.fname});
				var span = jQuery("<span>",{"class":"glyphicon glyphicon-folder-close", 
											"data-toggle":"collapse",
											"data-target":"#f"+folder.fid});
				var div = jQuery("<div>",{"class":"side-dir collapse",
										  "id": "f"+folder.fid});
				console.log(folder.pid);
				$(a).prepend(span).appendTo($('#p'+folder.pid));
				$(div).appendTo($('#p'+folder.pid));		
			})
			
		}
		
		
	})
}	
	
	
