$(function() {
	
	/// 헤더 메뉴 클릭 시 상단 border 색 바꿔주기
	$(".main-header-menu").click(function(){
		$(".main-header-menu").css("border-top","none");
		$(".side-project").css("background-color","transparent");
		$(".side-step").css("background-color","transparent");
		$(this).css("border-top","3px solid #ffc30d");
	})
	
	// 헤더 inbox 페이지
	$("#inbox-page").click(function(evt){
		$("#inbox_count").hide();
		$.ajax({
			type:"GET",
			url:"inbox.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				$('#incomming-page').css('border-bottom','2px solid transparent');
				$('#incomming-page').css('border-color','#286cb0');
				
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
				
				if($(".stream-select-list").length!=0){
					$("#stream-current-project").text($($(".stream-select-list")[0]).find(".stream-project-select-info-pname").text());
					if($(".stream_main").children().length==0){
						var msg =  $("<div>", {"class":"no-stream-div-msg",
							"text":"No Stream!"});
						$(".stream_main").append(msg);
					}
				}else{
					var msg =  $("<div>", {"class":"no-stream-div-msg",
						"text":"No Project!"});
					$(".stream_main").append(msg);
				}
			}
		})
	});
	
	
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

	//////////////////////////// 래영 /////////////////////////////////

		
	$('#header-introduce').click(function() {
		location.href="index.htm";
	});
	
	// 헤더에서 우상단 개인정보수정 클릭시 실행되는 함수
	// 헤더 개인정보수정 Modal
	$('#header-profile-edit').click(function(evt) {
		
		$.ajax({
			url:"updatememberinfo.htm",
			dataType:"json",
			success:function(data) {
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
		var updateprofile = $('#edit-profile-form').serialize();
		console.log(updateprofile);
		$('#edit-profile-form').ajaxForm({
			type:"post",
			url:"updatemember.htm",
			data:updateprofile,
			dataType:"json",
			success:function(data){
				console.log(data);
				
				if(data.updatemember > 0) {
					alert('개인정보수정이 완료되었습니다!');
				}else {
					alert('개인정보수정에 실패되었습니다');
				}
				$(".close").click();
				window.location.reload();
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
				if($('#edit-profile-apollokey').val() == ""){
					alert('인증키를 입력해주세요');
					$('#edit-profile-apollokey').focus();
					return false;
				}
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
				//console.log(data.result); //현재비번과 DB 비번 비교 

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
				$("#edit-profile-cpwd").val("");
				$("#edit-profile-upwd").val("");
				$("#edit-profile-upwd2").val("");
				$(".close").click();
				
			}
		}); // end - ajax
		$("#edit-profile-upwd2").on("keyup", function(event){
			event.preventDefault();
			if (event.keyCode === 13) {
				document.getElementById("update-pwd-btn").click();
			}
		
		
		});
	});
	////////////////////////////래영  end/////////////////////////////////
	
});



////////////////////////// Search바 관련 //////////////////////////////////////////

/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 최근 생성된 혹은 변경된 task의 10건을 가져오는 함수 
 작성자명 : 박 민 식
 */
function getRecentTasks(){
	var ajax= $.ajax({
		url:"getRecentTasks.htm",
		type:"post",
		dataType:"json",
		success:function(data){
			
		}
	}); // end ajax
	return ajax;
}; // end 


/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 우측 상단 검색 input 태그 클릭 시 사이드바 생성
 작성자명 : 박 민 식
 */
$(document).on("focus","#open-right-nav",function(){
	var inboxkind = $("#inboxkind").val();
   document.getElementById("search-nav").style.width = $("#header-right-wrapper").width()+10+"px";
    $("#search-bar").focus();
   $.when(getRecentTasks()).done(function(data){ //Default로 최근 생성 혹은 변경된 태스크 10건을 가져옴
	   var p = jQuery("<p>",{"class":"search-result-text","text":"Recent Tasks"});
	   var div = jQuery("<div>");
	   $(div).append(p);
	   $(data.rcttasks).each(function(index,el){
		  $(makeSearchTaskDiv(el,inboxkind)).appendTo(div);
	   })// each
	   $(div).appendTo($("#search-content-box"));  
	   
   });//done
});// end



/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 검색결과를 비동기로 결과를 가져오는 기능, Timer 설정으로 타이핑이 끝났을 경우에 데이터 로딩
 작성자명 : 박 민 식
 */
	var typingTimer;
	var doneTypingInterval = 500; // 타이빙 interval이 0.5초 이상일 경우 실행
	$(document).on("input","#search-bar" ,function () {
		
		if(getBytes($("#search-bar").val()) <2 ||$("#search-bar").val().trim() == "") return false; //타이핑한 문자가 2바이트 이상이고 공백이 아닐 경우에 다음단계로 넘어감
	    clearTimeout(typingTimer);
	    typingTimer = setTimeout(function() {
    	$.when(getSearchResult()).done(function(data){
    			console.log(data);
    			$("#search-content-box").empty();
    			var searchsteps = data.searchsteps;
    			var searchprojects = data.searchprojects;
    			var searchtasks = data.searchtasks;
    			var searchmembers = data.searchmembers;
    			// 검색된 각각의 결과를 화면에 뿌려주는 부분
    			if(searchprojects.length != 0) $(resultSearchProject(searchprojects)).appendTo($("#search-content-box"));  
    			if(searchsteps.length != 0) $(resultSearchStep(searchsteps)).appendTo($("#search-content-box")); 
    			if(searchtasks.length != 0) $(resultSearchTask(searchtasks)).appendTo($("#search-content-box"));  
    			if(searchmembers.length != 0) $(resultSearchMember(searchmembers)).appendTo($("#search-content-box"));  
    		})// end when
   		}, 2000); // end Timeout
    
	}); // end function

	
/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 문자열의 바이트값을 계산해 주는 함수
 작성자명 : 박 민 식
 */	
function getBytes(text) {
    var contents = text;
    var str_character;
    var int_char_count;
    var int_contents_length;
 
    int_char_count = 0;
    int_contents_length = contents.length;
 
    for (k = 0; k < int_contents_length; k++) {
        str_character = contents.charAt(k);
        if (escape(str_character).length > 4)
            int_char_count += 2;
        else
            int_char_count++;
    }
    return int_char_count;
} // end

/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 검색결과를 리턴해주는 비동기 함수
 작성자명 : 박 민 식
 */
function getSearchResult(){
	var input = $("#search-bar").val();
	var ajax = $.ajax({
		url:"getSearchResult.htm",
		type:"post",
		data:{input:input},
		dataType:"json",
		success:function(data){
			
		}
	}) // end ajax
	return ajax;
} // end

	
	
/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 검색된 프로젝트 만들어주는 함수
 작성자명 : 박 민 식
 */
 function makeSearchProjectDiv(project){
	 var pstatus;
	 var pstatuscolor;
	 switch (project.pstatuscode){
		 case 1: 
			 pstatus= "진행중인 프로젝트";
			 pstatuscolor= "rgb(46, 204, 113)";
			 break;
		 case 2:
			 pstatus= "완료된 프로젝트";
			 pstatuscolor= "rgb(52, 152, 219)";
			 break;
		 case 3:	 
			 pstatus= "휴지통";
			 
			 pstatuscolor= "rgb(127, 127, 127)";
			 break;
	 }
	 var methodology;
	 switch (project.methodologyid){
	 case 1: 
		 methodology="W";
		 break;
	 case 2:
		 methodology="A";
		 break;
	 case 3:	 
		 methodology="C";
		 break;
 	}
	 var div =jQuery("<div>",{"class":"search-item-box search-item-project row" ,"id":"srch-p"+project.pid});  
	 var projecticon= jQuery("<div>",{"class":"search-item-left col-sm-2"});
	 var b = jQuery("<b>",{"text":methodology, "css":{"font-size":"20px","color":"white"}});
	 var iconwrapper = jQuery("<div>",{"class":"search-item-left-icon "});
	 $(iconwrapper).append(b).appendTo(projecticon);
	 var project_info_div= jQuery("<div>",{"class":"search-item-right col-sm-10"});
	 var project_name_div= jQuery("<div>",{"class":"search-info-div","text":project.pname});
	 var project_status_div= jQuery("<div>",{"class":"search-info-div","text":pstatus,"css":{"color":pstatuscolor}});
	 $(project_info_div).append(project_name_div,project_status_div)
	 $(div).append(projecticon,project_info_div);
	 return div;
 } // end
	
/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 프로젝트 검색결과 wrapper를 만들어 주는 함수
 작성자명 : 박 민 식
 */
function resultSearchProject(projectlist){
	   var p = jQuery("<p>",{"class":"search-result-text","text":"Projects ("+projectlist.length+")"});
	   var resulsSearchproject = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resulsSearchproject).append(p);	
	   $(projectlist).each(function(index,project){
		   var projectdiv =  makeSearchProjectDiv(project);
		   $(projectdiv).appendTo(resulsSearchproject);
	   })
	return resulsSearchproject;   
}// end	
	
/**
 * 	
 날   짜 : 2018. 6. 26.
 기   능 : 스텝 검색 결과를 만들어주는 함수 
 작성자명 : 박 민 식
 */	
 function makeSearchStepDiv(step){
	 var div =jQuery("<div>",{"class":"search-item-box search-item-step row" ,"id":"srch-s"+step.sid});  
	 var stepicon= jQuery("<div>",{"class":"search-item-left col-sm-2"});
	 var i = jQuery("<i>",{"class":"far fa-file", "css":{"font-size":"20px","color":"white"}});
	 var iconwrapper = jQuery("<div>",{"class":"search-item-left-icon "});
	 $(iconwrapper).append(i);
	 $(stepicon).append(iconwrapper);
	 var step_info_div= jQuery("<div>",{"class":"search-item-right col-sm-10 container-fluid"});
	 var step_name_div= jQuery("<div>",{"class":"search-info-div","text":step.sname});
	 var step_dir_div= jQuery("<div>",{"class":"search-info-div"});
	 var pname= jQuery("<span>",{"class":"search-item-pname","text":$($("#p"+step.pid).find(".side-content-name")[0]).text()});
	 $(step_dir_div).append(pname);
	 if(step.fid!=null){
		 var fname= jQuery("<span>",{"class":"search-item-pname","text":$("#f"+step.fid).text()});
		 var arrow = jQuery("<i>",{"class":"fas fa-chevron-right search-arrow"});
		 $(step_dir_div).append(arrow,fname);
	 }
	 $(step_info_div).append(step_name_div,step_dir_div)
	 $(div).append(stepicon,step_info_div);
	 return div;
}
 
 /**
  * 
  날   짜 : 2018. 6. 26.
  기   능 : 스텝 검색결과 wrapper를 만들어 주는 함수
  작성자명 : 박 민 식
  */
 function resultSearchStep(steplist){
	  var p = jQuery("<p>",{"class":"search-result-text","text":"Steps ("+steplist.length+")"});
	   var resultsearchStep = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resultsearchStep).append(p);	
	   $(steplist).each(function(index,step){
		   var stepdiv =  makeSearchStepDiv(step);
		   $(stepdiv).appendTo(resultsearchStep);
	   })
	return resultsearchStep;   
	 
 } //end
 
 
 /**
  * 
  날   짜 : 2018. 6. 26.
  기   능 : 멤버 검색 결과 만들어주는 함수ㄴ
  작성자명 : 박 민 식
  */
 function makeSearchMemberDiv(member){
	 var div =jQuery("<div>",{"class":"search-item-box search-item-member row" ,"id":"srch-m"+member.mid});  
	 var memberimg= jQuery("<div>",{"class":"search-item-left col-sm-2"});
	 $(makeProfileIcon(member,"35")).appendTo(memberimg); // 본 함수는 utils 참고
	 var member_info_div= jQuery("<div>",{"class":"search-item-right col-sm-10 container-fluid"});
	 var member_name_div= jQuery("<div>",{"class":"search-info-div ","text":member.mname});
	 var member_email_div= jQuery("<div>",{"class":"search-info-div","text":member.mid});
	 $(member_info_div).append(member_name_div,member_email_div);
	 $(div).append(memberimg,member_info_div);
	 return div;
 }
 
 /**
  * 
  날   짜 : 2018. 6. 26.
  기   능 : 멤버검색 결과 wrapper를 만들어주는 함수
  작성자명 : 박 민 식
  */
 function resultSearchMember(memberlist){
	  var p = jQuery("<p>",{"class":"search-result-text","text":"Members ("+memberlist.length+")"});
	   var resultsearchmember = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resultsearchmember).append(p);	
	   $(memberlist).each(function(index,member){
		   var memberdiv =  makeSearchMemberDiv(member);
		   $(memberdiv).appendTo(resultsearchmember);
	   })
	return resultsearchmember;   
}

	

 
/**
 * 
 날   짜 : 2018. 6. 26.
 기   능 : 태스크 검색결과를 만들어주는 함수
 작성자명 : 박 민 식
 */
 function makeSearchTaskDiv(task,inboxkind){
		 var div =jQuery("<div>",{"class":"search-item-box search-item-task row "});

	 	

		 var pid = $("<input>", {"type":"hidden", "value": "p"+task.pid});
		 var assignee_div= jQuery("<div>",{"class":"search-item-left col-sm-2"});
		 getTaskAssignees(task.tid,'35').appendTo(assignee_div);
		 
		 
		 if(inboxkind == 'incomming' || inboxkind == 'sent' || inboxkind == 'archive' || inboxkind == 'starredtask'){
			 var task_info_div= jQuery("<div>",{"class":"search-item-right Task_RUD_Modal col-sm-10 container-fluid",
					"id":"srch-t"+task.tid});
		 	}else{
		 		 var task_info_div= jQuery("<div>",{"class":"search-item-right Task_RUD_Modal col-sm-10 container-fluid",
						"id":"srch-t"+task.tid,
					    "data-toggle":"modal",
					    "data-target":"#Task_RUD_Modal"});
		 	}
		 
		 
		
		 
		 var task_name_div= jQuery("<div>",{"class":"search-info-div ","text":task.tname});
		 var task_status_div= jQuery("<div>",{"class":"search-info-div","text":task.tstatus,"css":{"color":task.color}});
		 var pname= jQuery("<span>",{"class":"search-item-pname","text":$($("#p"+task.pid).find(".side-content-name")[0]).text(),"css":{"margin-left":"5px"}})
		 $( task_status_div).append(pname);
		 $(task_info_div).append( task_name_div,task_status_div);
		 $(div).append(assignee_div,task_info_div,pid);
		return div;
	} //end
 /**
  * 
  날   짜 : 2018. 6. 26.
  기   능 : 태스크 wrapper만들어주는 함수
  작성자명 : 박 민 식
  */
 function resultSearchTask(tasklist){
	  var p = jQuery("<p>",{"class":"search-result-text","text":"Tasks ("+tasklist.length+")"});
	   var resultsearchtask = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resultsearchtask).append(p);	
	   $(tasklist).each(function(index,task){
		   var taskdiv =  makeSearchTaskDiv(task);
		   $(taskdiv).appendTo(resultsearchtask);
	   })
	return resultsearchtask;   
} //end
 
 
 
 
 ///검색 결과에서 스텝항목 클릭 시 해당 스텝으로 이동
 $(document).on("click",".search-item-step",function(){
	checkbox=[];
	list_memberlist=[]; 
	var sid = this.id.substr(6);
	console.log(sid);
	$.ajax({
		url:"list.htm",
		data:{sid:sid},
		dataType:"html",
		success:function(data){
			 $("#main-box").empty();
			 $("#main-box").append(data);
			 $.ajax({
				 url:"memberlist.htm",
				 data:{sid:sid},
				 type:"POST",
				 dataType:"JSON",
				 success:function(memberlist){
					 list_memberlist = memberlist.memberlist;
				 }
			 })
		}
	})
 }) //end Func
	
 
 
 /// 검색결과에서 프로젝트 클릭 시 이동
 $(document).on("click",".search-item-project",function(){
	 var pid = this.id.substr(6);
		console.log(pid);	
	 $.ajax({
			url:"information.htm",
			data: "pid=" + pid,
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);	 		
				 
			}
		}) // end ajax
 }) //end function
 
 

