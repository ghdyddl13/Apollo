$(function() {
	/*
	var loading = $('<div id="loading" class="loading"><img id="loading_img" alt="loading" src="img/loader.gif" /></div>')
					.appendTo(".main-box-panel").hide();

	$(window)	
	.ajaxStart(function(){
		console.log("a")
	loading.show();
	})
	.ajaxStop(function(){
		console.log("b")
		loading.hide();
	});*/



	
	 unload();
	/////////////  프로젝트, 폴더, 스텝 생성 모달창 도움말 설정////////////////////
	 $(".modal-question").tooltip({
		 classes: {
			    "ui-tooltip": "apollo-tooltip"
		 },
		 content: function() {
		       return $(this).prop('title');
		      }
	 });   
	
	 $(".question-project").attr(
			 "title",
			 "프로젝트를 생성하여 여러명의 동료를 초대해 함께 협업할 수 있습니다. 같은 회사의 Apollo Code를 등록한 " +
			 "멤버들만 초대할 수 있습니다.<br><br> " +
			 "프로젝트는 진행 상태에 따라 'Working Projects', 'Completed Projects','Recycle Bin'에" +
			 "따로 관리할 수 있습니다. 다만, 'Trash Bin'에 보관할 경우 14일 이후 영구 삭제되니 주의하세요."
	 );
	 
	  $(".question-methodology").attr(
			 "title",
			 "아폴로는 애자일과 폭포수 개발 방법론에 최적화 되어있는 프로젝트 템플릿을 제공합니다. " +
			 "각각의 템플릿은 해당 방법론에 적합한 폴더와 스텝의 구조, 그리고 서로 다른 칸반보드를 제공합니다. <br><br>" +
			 "어떤 구조를 설정하든 스텝과 폴더를 이용자 마음대로 생성/수정/삭제 할 수 있습니다."
			 
	 );
	  
	  $(".question-step").attr(
				 "title",
				 "스텝에서 Task(업무)를 생성 및 관리할 수 있습니다. 스텝은 리스트, 칸반보드, 간트차트로 구성되어 있어 " +
				 "Task들을 효과적으로 관리할 수 있습니다.<br><br> 또한 스텝간 Task를 공유 혹은 이동함으로써 유동적인 업무관리가 가능합니다.<br><br>" +
				 "스텝은 해당 프로젝트 내에서 폴더 간 위치를 변경할 수 있습니다. "
	  );
	  
	  $(".question-folder").attr(
				 "title",
				 "폴더는 프로젝트에 속한 여러 스텝들을 그룹으로 묶어 관리할 수 있습니다. 스텝을 특징별로 묶어 프로젝트를 좀 더 체계적으로 관리하고 싶을 때 활용해 보세요." +
				 "<br><br> " +
				 "폴더를 삭제할 경우 폴더에 속한 Step도 모두 삭제되어 복구할 수 없으니 주의하시기 바랍니다." 
				
	  );
/////////////  프로젝트, 폴더, 스텝 생성 모달창 도움말 설정 끝////////////////////
		
	// 사이드바 구조 생성  
	makeSideProjectDir();
	
	// 사이드바 디렉토리 화살표 변경
	$(document).on("click",".side-dir-arrow",function(){
		console.log($(this).attr("aria-expanded"));
		if($(this).attr("aria-expanded") =="true"){
			$(this).removeClass("fa-angle-right").addClass("fa-angle-down");
		}else{
			$(this).removeClass("fa-angle-down").addClass("fa-angle-right");
		}
	});
	
	$(document).on("click",".side-project",function(){
		$(".main-header-menu").css("border-top","none");
		$(".side-project").css("background-color","transparent");
		$(".side-step").css("background-color","transparent");
		$(this).css("background-color","#e0e0e0");
	});
	
	$(document).on("click",".side-step",function(){
		$(".main-header-menu").css("border-top","none");
		$(".side-project").css("background-color","transparent");
		$(".side-step").css("background-color","transparent");
		$(this).css("background-color","#e0e0e0");
	})
	
	
	//스텝 추가 클릭시 프로젝트 멤버 리스트 가져오기
	$(document).on("click","#side-insert-step",function(){
		$('.add-step-name').val("");
		$('#insert-step-sday-id').val("");
		$('#insert-step-eday-id').val("");
		$('#step-detail').val("");
		$("#add-step-mgr-assignee-btn").show();
		$(".step-assigned-member-wrapper").remove();
		$("#add-step-mgr-assignee").val("");
		var custom_menu =  $(this).parents("ul.custom-menu")[0];
		var pid =  $(custom_menu).find("input[name=pid]").val();
		var methodologyid =  $(custom_menu).find("input[name=methodologyid]").val();
		var fid =  $(custom_menu).find("input[name=fid]").val();
		
		$.ajax(
		       {
		           type : "post",
		           url  : "getprojectmembers.htm",
		           data : "pid="+ pid, 
		           success : function(data){
		        	   $("#insert-step-methodologyid").val(methodologyid);
		        	   $("#insert-step-pid").val(pid);
		        	   $("#insert-step-fid").val(fid);
		        	   $('#add-step-mgr-assignee-options').empty();
		               $(data.memberlist).each(function (){
		            	   var li = jQuery("<li>",{
		            		   "class":"select-step-assignee-option",
		            		   "role":"presentation"
		            	   })
		            	   var member_div = jQuery("<div>",{"class":"step-assignee-wrapper"});
		            	   var member_img_wrapper = jQuery("<div>",{"class":"step-assignee-img"});
		            	   var memeber_img = makeProfileIcon(this,"30");
		            	   var member_info = jQuery("<div>",{"class":"step-assignee-info-wrapper"});
		            	   var member_name = jQuery("<div>",{"class":"step-assignee-info-name",
 								 							"text":this.mname});
		            	   var member_email = jQuery("<div>",{"class":"step-assignee-info-email",
	 													      "text":this.mid});
		            	   $(member_img_wrapper).append(memeber_img);
		            	   $(member_info).append(member_name,member_email);
		            	   $(member_div).append(member_img_wrapper,member_info).appendTo(li);
		            	   $('#add-step-mgr-assignee-options').append(li);
		               });
		              
		           } // end-success
		        }); // end-ajax
	});
	
	//스텝 담당자 선택시 dropdown hide시킨 후, 선택된 담당자 아이콘 뿌리기
	$(document).on("click",".select-step-assignee-option",function(){
		$("#add-step-mgr-assignee-btn").hide();
		$("#add-step-mgr-assignee").val($($(this).find(".step-assignee-info-email")[0]).text());
		var stepassignee_wrapper= jQuery("<div>",{"class":"step-assigned-member-wrapper"});
		var stepassignee = $($(this).find(".step-assignee-wrapper")[0]).clone(); //드롭다운에서 선택된 멤버의 div태그를 복사
		var i= jQuery("<i>",{"class":"fas fa-times cancel-step-assignee"});
		$(stepassignee_wrapper).append(stepassignee,i);
		$("#add-step-assignee").append(stepassignee_wrapper);
		
	})
	
	/// step 담당자 호버 시 취소버튼 생성
	$(document).on("mouseenter",".step-assigned-member-wrapper",function(){
		$(".cancel-step-assignee").css("visibility","visible")
	}).on("mouseleave",".step-assigned-member-wrapper",function(){
		$(".cancel-step-assignee").css("visibility","hidden")
	});
	
	//// 스텝 담당자 취소시 초기화
	$(document).on('click',".cancel-step-assignee",function(){
		$(".step-assigned-member-wrapper").remove();
		$("#add-step-mgr-assignee").val("");
		$("#add-step-mgr-assignee-btn").show();
	})
	
	
	
	// 스텝 생성 버튼 클릭시  alert 창 화면
	$("#insert-step-btn").click(function(){	 
		 if($(".add-step-name").val().trim() == ""){
			alert("스텝명을 입력하세요.");
			$(".add-step-name").focus();	
			return false;
		 } else if($('#add-step-mgr-assignee').val() == ""){
			 alert("책임자를 선택하세요.");
			 return false;
		 }
		 /*if($('#insert-step-sday-id').val() > $('#insert-step-eday-id').val()) {
			 alert("시작일이 종료일보다 앞설 수 없습니다.");
			return false;
		 }*/
		 var newstep = $('#step-add-form').serialize();
		 $.ajax({
			 
	             type:"POST",
	             url:"insertstep.htm",
	             data:newstep,
	             dataType:"json",
	             success:function(data){
	            	 if(data.stepresult > 0){
						alert("스텝 생성이 완료되었습니다!");
						var step = makeSideStep(data.stepDTO);
						console.log(step);
												
						if($('#insert-step-fid').val() == "") {
							(data.stepDTO.sname=="백로그")?$(step).prependTo("#p-dir"+step.pid):$(step).appendTo("#p-dir"+data.stepDTO.pid);
						} else {
							$(step).addClass("side-step-in-folder").appendTo("#f-dir"+data.stepDTO.fid);
						}
					 }else {
						 
						 alert("스텝 생성에 실패했습니다");
					 }	
					 $('.add-step-name').val("");
					 $('#insert-step-sday-id').val("");
					 $('#insert-step-eday-id').val("");
					 $('#step-detail').val("");
					 $(".close").click();

	             } // end - success
	          	,error:function(error){
	          		console.log(error);
	          	
	          	} // end - error
	          });// end-ajax	

	
	}); //end-step	

	
		// 사이드바 프로젝트 우클릭 
		$(document).on("contextmenu",".side-project",function() {
			var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
			var pid = project_wrapper.id.substr(1);
			var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
			console.log("프로젝트 우 클릭시 pid " + pid);
			console.log("프로젝트 우 클릭시 methodologyid " + methodologyid);
			event.preventDefault();
			
			var p_location = $(this).parents("div.project-status-wrapper")["0"].id;
			
			var dropdown_ul = document.createElement("ul");
			var dropdown = '<input type="hidden" name="pid" value='+pid+'>';
			dropdown += '<input type="hidden" name="methodologyid" value='+methodologyid+'>'
			
			if(p_location == "working-project"){
				dropdown += '<li class="dropdown-submenu"><a data-toggle="dropdown" class="dropdown-toggle">추가 <span class="glyphicon glyphicon-menu-right"></span></a>'
				dropdown += '<ul class="dropdown-menu "><li id="side-add-folder" data-toggle="modal" data-target="#add-folder">Folder추가</li><li id="side-insert-step" data-toggle="modal" data-target="#insert-step">Step추가</li></ul></li>'
				dropdown += '<li data-toggle="modal" data-target="#move-project" id="side-complete-project">완료</li>'
				dropdown += '<li data-toggle="modal" data-target="#update-project" id="side-update-project">수정</li>'
				dropdown += '<li data-toggle="modal" data-target="#move-project" id="side-delete-project">삭제</li>'
			}else if(p_location == "finished-project"){
				dropdown += '<li data-toggle="modal" data-target="#move-project"  id="side-restart-project">재진행</li>'
				dropdown += '<li data-toggle="modal" data-target="#update-project"  id="side-update-project">수정</li>'
				dropdown += '<li data-toggle="modal" data-target="#move-project" id="side-delete-project">삭제</li>'
			}else if(p_location == "trash-bin"){
				dropdown += '<li data-toggle="modal" data-target="#move-project"  id="side-restart-project">진행중인 프로젝트로 복구</li>'
				dropdown += '<li data-toggle="modal" data-target="#move-project" id="side-complete-project">완료 프로젝트로 복구</li>'
					
			}
			
		
			
			
			$(dropdown_ul).attr("class", "custom-menu").append(
					dropdown);
			$(dropdown_ul).css({
				top : event.pageY + "px",
				left : event.pageX + "px"
			}).appendTo("body");
			
			
		});
		
		//사이드 드롭다운에서 프로젝트 완료 버튼 클릭시 모달창 생성
		
		$(document).on("click","#side-complete-project",function(){
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			$("#move-project-header").text("Project 완료");
			$("#move-project-pid").val(pid);
			$("#move-project-pstatuscode").val("2");
			$("#move-project-message").html("해당 프로젝트를 완료 프로젝트로<br> 이동하시겠습니까?");
			$("#move-project-submessage").text("");
		});
		
		//사이드 드롭다운에서 프로젝트 삭제 버튼 클릭시 모달창 생성
		
		$(document).on("click","#side-delete-project",function(){
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			$("#move-project-header").text("Project 삭제");
			$("#move-project-pid").val(pid);
			$("#move-project-pstatuscode").val("3");
			$("#move-project-message").text("해당 프로젝트를 삭제하시겠습니까?");
			$("#move-project-submessage").text("(삭제시 해당 프로젝트는 14일 동안 휴지통에 보관되며 이후 영구 삭제됩니다.)");
		});
		
		//사이드 드롭다운에서 프로젝트 재진행 버튼 클릭시 모달창 생성
		$(document).on("click","#side-restart-project",function(){
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			$("#move-project-header").text("Project 재진행");
			$("#move-project-pid").val(pid);
			$("#move-project-pstatuscode").val("1");
			$("#move-project-message").text("해당 프로젝트를 재진행 하시겠습니까?");
			$("#move-project-submessage").text("");
		});
		
		////사이드 드롭다운에서 프로젝트 수정 버튼 클릭시 모달창 생성
		$(document).on("click","#side-update-project",function(){
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			console.log("클릭");
			console.log(pid);
			$.ajax({
				url:"sideSelectProject.htm",
				type:"post",
				data:{pid:pid},
				dataType:"json",
				success:function(data){
					var project = data.project;
					$("#update-project-pid").val(project.pid);
					if(project.sday !=null) $("#update-project-sday").val(project.sday.split(" ")[0]);
					if(project.eday !=null) $("#update-project-eday").val(project.eday.split(" ")[0]);
					$("#update-project-detail").val(project.detail);
					$("#update-project-detail-"+project.methodologyid).attr("checked","checked");
					$("#update-project-pname").val(project.pname);
					$("#update-project-methodologyid-"+project.methodologyid).attr("checked","true")
					
					
				},
				error:function(error){
					console.log(error);
				}
			})
		});
		
		/// 프로젝트 수정 모달창에서 수정 버튼 클릭 시 
		$(document).on("click","#update-project-btn",function(){
			
			 if($("#update-project-pname").val().trim() == ""){
					alert("프로젝트명을 입력해주세요.");
					$("#update-project-name").focus();	
					return false;
			}
			 if($("#update-project-sday").val() > $("#update-project-eday").val()) {
				 alert("시작일이 종료일보다 앞설 수 없습니다.");
				return false;
			 }
			var project = $("#update-project-form").serialize();
			console.log(project);
			
			$.when(updateProject(project)).done(function(data){
				console.log(data.projectDTO);
				if(data.result=="1"){
					$($("#p"+data.projectDTO.pid).find(".side-content-name")[0]).text(data.projectDTO.pname);
					alert("프로젝트 정보가 수정되었습니다");
				}else{
					alert("프로젝트 정보 수정에 실패하였습니다.");
				}
				$("#update-project-pid").val("");
				$("#update-project-sday").val("");
				$("#update-project-eday").val("");
				$("#update-project-detail").val("");
				$("#update-project-detail-3").attr("checked","checked");
				$("#update-project-pname").val("");
				$(".close").click();
			})
		})
		
		
		//프로젝트 완료/삭제/재진행 모달창에서 완료 버튼을 클릭할 경우 실행되는 비동기 함수 
		$(document).on("click","#move-project-btn",function(){
			var pdata = new Object();
			pdata.pid= $("#move-project-pid").val();
			pdata.pstatuscode = $("#move-project-pstatuscode").val();
			console.log(pdata);	
			
			$.when(updateProject(pdata)).done(function(data){
				console.log(data);
				if(data.result=="1"){
					var moveto;
					switch(pdata.pstatuscode){
					case "1": moveto="#working-project"; break;
					case "2": moveto="#finished-project"; break;
					case "3": moveto="#trash-bin"; break;	 
					}
					$("#p"+pdata.pid).remove().appendTo(moveto);
				}else{
					alert("프로젝트 상태변경에 실패하였습니다.");
				}
				$(".close").click();
			});
		});
		
		// 사이드바 폴더 우클릭  
		$(document).on("contextmenu",".side-folder",function() {
			var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
			var pid = project_wrapper.id.substr(1);
			var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
			var fid= this.id.substr(1);
			console.log("폴더 우 클릭시 pid " + pid);
			console.log("폴더 우 클릭시 methodologyid " + methodologyid);
			console.log("폴더 우 클릭시 fid " + fid);
			console.log($(this).closest("input"));
			event.preventDefault();
			var dropdown_ul = document.createElement("ul");
			var dropdown = '<input type="hidden" name="pid" value='+pid+'>';
			dropdown +=	'<input type="hidden" name="methodologyid" value='+methodologyid+'>';
			dropdown +=	'<input type="hidden" name="fid" value='+fid+'>';
			dropdown +=	'<li data-toggle="modal" id="side-insert-step" data-target="#insert-step">Step 추가</li>';
	        dropdown += '<li id="side-update-folder" data-toggle="modal" data-target="#update-folder">수정</li>';
	        dropdown += '<li id="side-delete-folder" data-toggle="modal" data-target="#delete-folder">삭제</li>';
			$(dropdown_ul).attr("class", "custom-menu").append(dropdown);
			console.log(dropdown_ul)
			$(dropdown_ul).css({
				top : event.pageY + "px",
				left : event.pageX + "px"
			}).appendTo("body");
		});

		// 사이드바 스텝 우클릭  

		$(document).on("contextmenu",".side-step",function(event) {
			var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
			var folder_wrapper= $(this).parents("div.side-folder-wrapper")[0];
			/*var fid = folder_wrapper.id.substr(7);*/
			var fid= (folder_wrapper)?folder_wrapper.id.substr(8):"";
			console.log(fid);
			var pid = project_wrapper.id.substr(1);
			var sid= this.id.substr(1);
			console.log("스텝 우 클릭시 sid " + sid);
			console.log("스텝 우 클릭시 pid " + pid);
			event.preventDefault();
			var dropdown_ul = document.createElement("ul");
			var dropdown ='<input type="hidden" name="pid" value='+pid+'>';
			dropdown +=	'<input type="hidden" name="sid" value='+sid+'>';
			dropdown +=	'<input type="hidden" name="fid" value='+fid+'>';
			dropdown += '<li id="side-update-step" data-action="second" data-toggle="modal" data-target="#update-step">수정</li>'
			dropdown += '<li data-toggle="modal" data-target="#move-step" id="side-step-move">이동</li>'
			dropdown += '<li id="side-delete-step" data-action="third" data-toggle="modal" data-target="#delete-step">삭제</li>'
			$(dropdown_ul).attr("class", "custom-menu").append(dropdown);
			console.log(dropdown_ul)
			$(dropdown_ul).css({
				top : event.pageY + "px",
				left : event.pageX + "px"
			}).appendTo("body");

		});
		
		// 사이드바 스텝 이동 버튼 클릭시
		$(document).on("click","#side-step-move",function(){
			$("#move-step-folder-options").empty();
			$("#move-step-selected-fid").val("no-folder");
			$("#move-step-folder-name").empty().text("이동할 위치를 선택하세요");
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			var pids = [pid];
			var sid =  $(custom_menu).find("input[name=sid]").val();
			var fid =  $(custom_menu).find("input[name=fid]").val();
			
			$("#move-step-sid").val(sid);
			
			$.when(selectFolderList(pids)).done(function(data){
				console.log(data.folderlist);
				
				if(data.folderlist.length ==0){
					var li = jQuery("<li>",{
						"class":"move-step-folder-nofolder",
						"role":"presentation",
						"text":"폴더가 없습니다",
					
					})
					$(li).appendTo("#move-step-folder-options");
					return false;
				}
				
				var li = jQuery("<li>",{
					"class":"move-step-folder-option",
					"role":"presentation",
					"value":""
				})
				var def = $("<div>",{"class":"move-step-folder-name", 
					"text":"폴더 밖으로 이동"});
				$(li).append(def).appendTo("#move-step-folder-options");
				
				$(data.folderlist).each(function(index,folder){
					if(folder.fid!=fid){
						var li = jQuery("<li>",{
							"class":"move-step-folder-option",
							"role":"presentation",
							"value":folder.fid
						})
						//	var folderwrapper= $("<div>",{"class":"move-step-folder-item"});
						var foldername = $("<div>",{"class":"move-step-folder-name", 
							"text":folder.fname});
						var foldericon = $("<i>",{"class":"side-dir-folder-icon fas fa-folder"});					
						$(li).append(foldericon,foldername).appendTo("#move-step-folder-options");
					}
										
				});
			});
		});
		
		
		$(document).on("click",".move-step-folder-option",function(){
			var fid = ($(this).val() ==0)? "":$(this).val();
			console.log(fid);
			$("#move-step-selected-fid").val(fid);
			$("#move-step-folder-name").text($($(this).find(".move-step-folder-name")).text());
		})
		
		
		///// 스텝이동 모달창에서 확인버튼 클릭 시 
		$(document).on("click","#move-step-btn",function(){
			if($("#move-step-selected-fid").val()=="no-folder"){
				alert("이동할 위치를 선택해 주세요");
				return false;
			}
			
			var movestep = $("#move-step-form").serialize();
			console.log(movestep);
			$.ajax({
				url:"moveStep.htm",
				data:movestep,
				type:'post',
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.result==1){
						var step = data.stepDTO;
						if(step.fid !=""){
							$("#s"+step.sid).addClass("side-step-in-folder").remove().appendTo("#f-dir"+step.fid);
						}else{
							var default_ = $("#s"+step.sid).parents("div.side-dir-project")[0];
							console.log(default_);
							$("#s"+step.sid).removeClass("side-step-in-folder").remove().appendTo(default_);
						}
					}else{
						alert("Step이동에 실패하였습니다.");
					}
					$(".close").click();
				}
			})
		})
		
		
		// 사이드 우클릭 메뉴 닫는 함수
		$(document).bind("mousedown", function(e) {

			// If the clicked element is not the menu
			if (!$(e.target).parents(".custom-menu").length > 0) {

				// Hide it
				$(".custom-menu").remove();
			}
			
			if (!($(e.target).parents("#search-nav").length > 0) &&!($(e.target).attr("id")=="search-nav")) {
				document.getElementById("search-nav").style.width = "0";
			    $("#search-content-box").empty();
				$("#search-bar").val("")
			}
			
		});
	

	
	/////////////////////////// 비동기 화면전환 - 프로젝트 /////////////////////////
		
		$(document).on("click",".side-project",function(){
	
			
			var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
			var pid = project_wrapper.id.substr(1);
			var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
			
			$('#pidhidden').attr('value', pid);
			
			$.ajax({
				url:"information.htm",
				data: "pid=" + pid,
				beforeSend:function(){
					$('#main-box').html(loadingpage);
				},
				dataType:"html",
				success:function(data){
					$("#main-box").empty();
					$("#main-box").append(data);	 		
					console.log("a");
					$("#information-page").removeClass("main-body-onepannel-header-top-selector").addClass("main-body-onepannel-header-top-selected"); 
				}
			})
	    });

		
		
	/* modal 창(project, step) dateficker */
		$(".sdate-img").datepicker({
		    showOn: "button",
		    buttonImage: "img/calendar.png",
		    buttonImageOnly: true,
		    dateFormat: 'yy-mm-dd',
		    onSelect: function(dateText, inst) {
		    	var origin_date = $('.sdate-img').val();
		    	var date = $(this).val();
		    	
		    	var sday_year = parseInt(date.substring(0,4));
		    	var sday_month = parseInt(date.substring(5,7));
		    	var sday_day = parseInt(date.substring(8,10));
		    	
		    	var eday = $('.edate-img').val();
		    	var eday_year = parseInt(eday.substring(0,4));
		    	var eday_month = parseInt(eday.substring(5,7));
		    	var eday_day = parseInt(eday.substring(8,10));
		    	
		    	if(eday_year < sday_year){
		    		alert('시작일을 종료일 이후로 설정할 수 없습니다');
		    		$('.sdate-img').val(origin_date);
		    		return false;
		    	}
		    	
		    	if(eday_month < sday_month) {
		    		alert('시작일을 종료일 이후로 설정할 수 없습니다');
		    		$('.sdate-img').val(origin_date);
		    		return false;
				}
		    	
		    	if(eday_day < sday_day) {
		    		alert('시작일을 종료일 이후로 설정할 수 없습니다');
		    		$('.sdate-img').val(origin_date);
		    		return false;
				} 
		    }	
		}); // end sdate- datepicker
		
		$(".edate-img").datepicker({
		    showOn: "button",
		    buttonImage: "img/calendar.png",
		    buttonImageOnly: true,
		    dateFormat: 'yy-mm-dd',
		    minDate: 0,
		    onSelect: function(dateText, inst) {
		    	var origin_date = $('.edate-img').val();
		    	var date = $(this).val();
		    	
		    	var sday = $('.sdate-img').val();
		    	var sday_year = parseInt(sday.substring(0,4));
		    	var sday_month = parseInt(sday.substring(5,7));
		    	var sday_day = parseInt(sday.substring(8,10));
		    	console.log('테스트 sday : ' + sday_year + '/' + sday_month + '/' + sday_day);
		    	
		    	var eday_year = parseInt(date.substring(0,4));
		    	var eday_month = parseInt(date.substring(5,7));
		    	var eday_day = parseInt(date.substring(8,10));
		    	console.log('테스트 eday : ' + eday_year + '/' + eday_month + '/' + eday_day);
		    	
		    	
		    	if(eday_year < sday_year){
		    		alert('종료일을 시작일 이전으로 설정할 수 없습니다');
		    		$('.edate-img').val(origin_date);
		    		return false;
		    	}
		    	
		    	if(eday_month < sday_month) {
					alert('종료일을 시작일 이전으로 설정할 수 없습니다');
					$('.edate-img').val(origin_date);
					return false;
				}
		    	
		    	if(eday_day < sday_day) {
					alert('종료일을 시작일 이전으로 설정할 수 없습니다');
					$('.edate-img').val(origin_date);
					return false;
				} 
		    }

		});	
		
		
	$("#side-bar-add-project").click(function(){
		 $('#add-project-name').val("");
		 $('#insert-project-sday-id').val("");
		 $('#insert-proejct-eday-id').val("");
		 $('#project-detail').val("");
		 $("#method1").prop("checked", true);

	})	
		
	// 프로젝트 생성 버튼 클릭시 alert 창 화면 		
	$("#insert-project-btn").click(function(evt){
		 if($("#add-project-name").val().trim() == ""){
			alert("프로젝트명을 입력해주세요.");
			$("#add-project-name").focus();	
			return false;
		 }
		 /*if($('#insert-project-sday-id').val() > $('#insert-proejct-eday-id').val()) {
			 alert("시작일이 종료일보다 앞설 수 없습니다.");
			return false;
		 }
		 */
		 var newproject = $("#project-add-form").serialize(); //serialize() : input 값이 있는 tag 들을 직렬화하여 가져온다 (ex.a=1&b=2&c=3&d=4&e=5)

		 $.ajax({
			 url:"insertproject.htm",
			 data:newproject,
			 type:"POST",
			 dataType:"json",
			 success: function(data){

				 if(data.result > 0){
					 alert("프로젝트 생성이 완료되었습니다!");
					 $(".close").click();
					 makeSideProjectDir();
				 }else {
					 alert("프로젝트 생성에 실패했습니다");
				 }	

				 $('.close').click();
			 }

		 });	
	});
	
	//사이드바에서 폴더생성 버튼을 클릭할 때
	$(document).on("click","#side-add-folder",function(){
		$('#add-folder-name').val("");
		var custom_menu =  $(this).parents("ul.custom-menu")[0];
		var pid =  $(custom_menu).find("input[name=pid]").val();
		$("#add-folder-pid").val(pid);
		
	})
	

	//폴더 생성 클릭 버튼시 alert 창 화면
	$('#insert-folder-btn').click(function() {
		if($('#add-folder-name').val().trim() == ""){
			alert('폴더명을 입력해주세요');
			$("#add-folder-name").focus();	
			return false;
		}
		var newfolder = $('#insert-folder-form').serialize();
		 
		 $.ajax({

                type:"POST",
                url:"insertfolder.htm",
                data:newfolder,
                dataType:"json",
                success:function(data){
                   if(data.folderresult > 0){
                   alert("폴더 생성이 완료되었습니다!");
                   makeSideFolder(data.folderDTO);
                   //console.log(folder);
                   $(".close").click();
                }else {
                   alert("폴더 생성에 실패했습니다");
                }   
             
                $('.close').click();
               
                } // end - success
		 		
             	,error:function(error){
             		console.log(error);
             	
             	} // end - error
             });// end-ajax	
	});
	
	
	
	   //폴더 우클릭하여 수정 버튼 클릭시 이벤트
	   $(document).on("click","#side-update-folder",function(){
	       var custom_menu =  $(this).parents("ul.custom-menu")[0];
	       var fid = $(custom_menu).find("input[name=fid]").val();
	       var pid = $(custom_menu).find("input[name=pid]").val();
	       $.ajax({
	           type:"post",
	           url:"selectfolder.htm",
	            data:{fid:fid},
	            dataType:"json",
	            success:function(data){
	                $('#update-folder-fid').val(fid);
	                $('#update-folder-pid').val(pid);
	                $('#update-folder-name').val(data.selectfolder.fname);
	            }
	       });
	   });
	   
	   //폴더 modal 에서 수정 버튼 클릭시
	   $('#update-folder-btn').click(function() {
	       var updatefolder = $('#update-folder-form').serialize();       
	       
	       $.ajax({
	           type:"post",
	           url:"updatefolder.htm",
	           data:updatefolder,
	           dataType:"json",
	           success:function(data){
	               
	               if(data.updatefolder > 0){
	                   alert('폴더 수정이 완료되었습니다!');
	                   $($("#f"+data.folderDTO.fid).find(".side-content-name")).text(data.folderDTO.fname);
	                   $(".close").click();
	               }else {
	                   alert('폴더 수정이 실패되었습니다');
	               }
	               $('.close').click();
	           }
	       }); // end - ajax
	   }); //end - event
	   
	   //폴더 우클릭하여 삭제 버튼 클릭시 실행되는 이벤트
	   $(document).on("click","#side-delete-folder",function(){
	       var custom_menu =  $(this).parents("ul.custom-menu")[0];
	       var fid = $(custom_menu).find("input[name=fid]").val();
	       $.ajax({
	           type:"post",
	           url:"selectfolder.htm",
	            data:{fid:fid},
	            dataType:"json",
	            success:function(data){
	                $('#delete-folder-fid').val(fid);
	                
	            }
	       }); // end-ajax
	   });
	   
	   //폴더 modal 에서 삭제 버튼 클릭시 이벤트
	   $('#delete-folder-btn').click(function() {
		   var fid = $('#delete-folder-fid').val();
	       var deletefolder = $('#delete-folder-form').serialize();
	       $.ajax({
	           type:"post",
	           url:"deletefolder.htm",
	           data:deletefolder,
	           dataType:"json",
	           success:function(data){
	               console.log(data.deletefolder);
	               if(data.deletefolder == 0){
	                   alert('폴더 삭제가 실패되었습니다');
	               }else {
	                   alert('폴더 삭제가 완료되었습니다!');
	                   $("#fwrapper"+fid).remove();
	               }
	               $('.close').click();
	           }
	       }); // end - ajax       
	   })

	 //  loadCurrentPage();
	   
	   
	  /////////////툴팁 설정
	  
	   
	   
	   
}); // end - doc.on.ready


/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 참여한 모든 프로젝트에 속해있는 Folder의 리스트를 불러오는 함수
 작성자명 : 박 민 식
 */
function selectFolderList(pids){
   if(pids==null) return false;
   jQuery.ajaxSettings.traditional = true;
   
   var ajax =$.ajax({ // ajax는 내부적으로 Deffered와 호환이 되어 있는 함수이기 때문에 따로 선언을 해주지 않아도 된다. 
                     // 그저, ajax의 결과값을 저장한 변수를 return해주면 끝
      url:"selectFolderList.htm",
      method:"post",
      dataType:"json",
      data: {'pids':pids},
      success:function(data){
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
   if(pids==null) return false;
   jQuery.ajaxSettings.traditional = true;
   var data;
   var ajax = $.ajax({
      url:"selectStepList.htm",
      method:"post",
      dataType:"json",
      data: {'pids':pids},
      success:function(data){
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
 기   능 : 사이드바에 프로젝트 리스트디렉토리 구조 생성함수
 작성자명 : 박 민 식
 */

function selectProjectList(){
   $("#working-project").empty();
   $("#finished-project").empty();
   $("#trash-bin").empty();
   var dfd = $.Deferred(); // 비동기 함수의 순서를 정해주기 위해(동기화) Defferred의 객체의 Promise를 활용한다.
   var pids=[];
   $.ajax({
      url:"selectProjectList.htm",
      dataType:"json",
      type:"post",
      success:function(data){
    	  console.log(data);
    	  console.log("make side")
         if(data!=null){ /// 참여중인 프로젝트가 있을 경우 
            $(data.projectlist).each(function(index,el){
               pids.push(el.pid);
                var wrapper = MakeprojectWrapper(el);
               //////프로젝트의 상태에 따라 진행, 완료, 휴지통에 구분하여 append
               if(el.pstatuscode ==1){
                  $(wrapper).appendTo("#working-project");
                  
               }else if(el.pstatuscode ==2){
                  $(wrapper).appendTo("#finished-project");
               }else if(el.pstatuscode==3){
                  $(wrapper).appendTo("#trash-bin");
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
 날   짜 : 2018. 6. 24.
 기   능 : 사이드바에 들어갈 프로젝트 구성 태그들을 만들어주는 함수
 작성자명 : 박 민 식
 */
function MakeprojectWrapper(project){
    var wrapper = jQuery("<div>",{"class":"side-project-wrapper","id":"p"+project.pid});
    var a = jQuery("<div>",{"class":"side-content side-project"})
    var projectname = jQuery("<div>",{"class":"side-content-name","text":project.pname});
    var hidden = jQuery("<input>",{"type":"hidden",
                            "name":"methodologyid",
                            "value":project.methodologyid});
    var i = jQuery("<i>",{"class":"side-dir-arrow fas fa-angle-right", 
       "data-toggle":"collapse",
       "data-target":"#p-dir"+project.pid})

     var methodology;
	 switch (project.methodologyid){
	 case 1: 
		 methodology="waterfallicon.png";
		 break;
	 case 2:
		 methodology="agileicon.png";
		 break;
	 case 3:	 
		 methodology="customicon.png";
		 break;
	}   
       
	var projecticon= jQuery("<div>",{"class":"side-dir-project-icon"});
    var img = jQuery("<img>",{"src":"img/"+methodology, "css":{"width":"15px","height":"15px"}});

    $(img).appendTo(projecticon);   
       
    var div = jQuery("<div>",{"class":"side-dir-project  collapse",
       "id": "p-dir"+project.pid});
       
    $(a).append(i,projecticon,projectname);
    $(wrapper).append(a).append(div).append(hidden);
    return wrapper;
}



/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 사이드바의 구조를 만들어주는 함수 
 작성자명 : 박 민 식
 */
function makeSideProjectDir(){
   
    $.when(selectProjectList()).done(function(data){ //먼저 프로젝트의 리스트를 가져와 뿌려준 후, 
      if(data.length!=0) {
    	  var pids = data;
          makeSideSubDir(pids); 
      } 
   })
}


/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 프로젝트 내부의 디렉토리구조를 만들어주는 함수  
 작성자명 : 박 민 식
 */   
function makeSideSubDir(pids){
   
   $.when(selectFolderList(pids), selectStepList(pids)).done(function(folders,steps){ //먼저, 프로젝트들에 속한 폴더와 스텝들의 정보를 가져온 후 디렉토리 구조를 구성

      var folders = folders[0].folderlist; //폴더 list
      var steps= steps[0].steplist; // 스텝 list
      ///// 먼저 폴더를 화면에 뿌려준다. 
      if(folders!=null){ //폴더가 하나라도 있다면 만들어 붙혀주세요
         
         $(folders).each(function(index,folder){
        	var wrapper_div = jQuery("<div>",{"class":"side-folder-wrapper","id":"fwrapper"+folder.fid});
            var a =jQuery("<div>",{"class":"side-content side-folder","id":"f"+folder.fid});
            var foldername = jQuery("<div>",{"class":"side-content-name","text":folder.fname});
            var i = jQuery("<i>",{"class":"side-dir-arrow fas fa-angle-right", 
                                 "data-toggle":"collapse",
                                 "data-target":"#f-dir"+folder.fid});
            var foldericon = jQuery("<i>",{"class":"side-dir-folder-icon fas fa-folder"});
            var div = jQuery("<div>",{"class":"side-dir collapse",
                                "id": "f-dir"+folder.fid});
            
            $(a).prepend(i,foldericon,foldername).appendTo(wrapper_div);
            $(div).appendTo(wrapper_div);
            $(wrapper_div).appendTo($('#p-dir'+folder.pid));
         })
         
      }
      
      //// 폴더를 뿌려준 후, Step을 뿌려준다.
      if(steps!=null){
         
         $(steps).each(function(index,step){ // Step관련 태그 생성
            var a =jQuery("<div>",{
                           "class":"side-content side-step",
                           "id":"s"+step.sid });
            var stepname = jQuery("<div>",{"class":"side-content-name","text":step.sname});
            
            var i = jQuery("<i>",{"class":"side-dir-step-icon far fa-file-alt"});

            $(a).append(i,stepname);
            if(step.fid !=null){ // 스텝이 속해있는 폴더가 있다면 폴더 밑에 넣어주고 
            	
               $(a).addClass("side-step-in-folder").appendTo("#f-dir"+step.fid);
            }else{ //속해있는 폴더가 없다면, Default경로, 즉 프로젝트 밑으로 넣어준다.
               (step.sname=="백로그")?$(a).prependTo("#p-dir"+step.pid):$(a).appendTo("#p-dir"+step.pid);
            }
         })
      }
      
   })
}   
/**
 * 
 날      짜 : 2018. 6. 25.
 기      능 : 폴더 업데이트 후 바로 반영되는 함수
 작성자명 : 김 래 영
 */
function makeSideFolder(folder) {

    var wrapper_div = jQuery("<div>",{"class":"side-folder-wrapper","id":"fwrapper"+folder.fid});
    var a =jQuery("<div>",{"class":"side-content side-folder","id":"f"+folder.fid});
    var foldername = jQuery("<div>",{"class":"side-content-name","text":folder.fname});
    var i = jQuery("<i>",{"class":"side-dir-arrow fas fa-angle-right", 
                         "data-toggle":"collapse",
                         "data-target":"#f-dir"+folder.fid});
    var foldericon = jQuery("<i>",{"class":"side-dir-folder-icon fas fa-folder"});
    var div = jQuery("<div>",{"class":"side-dir collapse",
                        "id": "f-dir"+folder.fid});
    
    $(a).prepend(i,foldericon,foldername).appendTo(wrapper_div);
    $(div).appendTo(wrapper_div);
    $(wrapper_div).appendTo($('#p-dir'+folder.pid));
}

/**
 * 
 날      짜 : 2018. 6. 25.
 기      능 : 스텝 업데이트 후 바로 반영되는 함수
 작성자명 : 김 래 영
 */
function makeSideStep(step) {
    var a =jQuery("<div>",{"class":"side-content side-step",
    					   "id":"s"+step.sid });
    var stepname = jQuery("<div>",{"class":"side-content-name","text":step.sname});
    var i = jQuery("<i>",{"class":"side-dir-step-icon far fa-file-alt"});
    $(a).append(i,stepname);
	return a;
}


/**
 * 
 날   짜 : 2018. 6. 22.
 기   능 : 프로젝트 업데이트 함수
 작성자명 : 박 민 식
 */
function updateProject(data){
   var ajax = $.ajax({
      url:"updateProject.htm",
      type:"POST",
      data:data,
      dataType:"json",
      success:function(data){
      }
   })
   return ajax;
}

/**
 * 
 날   짜 : 2018. 7. 5.
 기   능 : 페이지  reload시 화면 구성함수 
 작성자명 : 박 민 식
 */
function unload() {
	/*console.log("aa");
	switch (document.readyState){
	case "complete":
	*/	
		$.ajax({
			url:"pageReloadEvent.htm",
			dataType:"html",
			beforeSend:function(){
					$("#main-box").html(loadingpage);
			},
			success:function(data){
				 $("#main-box").empty();
		         $("#main-box").append(data);
		         checkCrtPage();
			}
		})
		
	
}




