$(function() {


	makeSideProjectDir();
	
	//스텝 추가 클릭시 프로젝트 멤버 리스트 가져오기
	$(document).on("click","#side-insert-step",function(){ 
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
	
	// 스텝 생성 버튼 클릭시  alert 창 화면
	$("#insert-step-btn").click(function(){	 
		 if($(".add-step-name").val().trim() == ""){
			alert("스텝명을 입력하세요.");
			$(".add-step-name").focus();	
			return false;
		 } else if($('#add-step-mgr-assignee').val() == ""){
			 alert("책입자를 선택하세요.");
			 return false;
		 }
		 var newstep = $('#step-add-form').serialize();
		 
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
					 $(".close").click();
					 //$('#project-insert').close();
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
			$("#move-project-message").text("해당 프로젝트를 완료 프로젝트로 이동하시겠습니까?");
		});
		
		//사이드 드롭다운에서 프로젝트 삭제 버튼 클릭시 모달창 생성
		
		$(document).on("click","#side-delete-project",function(){
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			$("#move-project-header").text("Project 삭제");
			$("#move-project-pid").val(pid);
			$("#move-project-pstatuscode").val("3");
			$("#move-project-message").text("해당 프로젝트를 삭제하시겠습니까? \n 삭제시 해당 프로젝트는 14일 후 휴지통에서 영구 삭제됩니다.");
		});
		
		//사이드 드롭다운에서 프로젝트 재진행 버튼 클릭시 모달창 생성
		$(document).on("click","#side-restart-project",function(){
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			$("#move-project-header").text("Project 재진행");
			$("#move-project-pid").val(pid);
			$("#move-project-pstatuscode").val("1");
			$("#move-project-message").text("해당 프로젝트를 재진행 하시겠습니까?");
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
					$("#update-project-sday").val(project.sday.split(" ")[0]);
					$("#update-project-eday").val(project.eday.split(" ")[0]);
					$("#update-project-detail").val(project.detail);
					$("#update-project-detail-"+project.methodologyid).attr("checked","checked");
					$("#update-project-pname").val(project.pname);
				
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
			 
			var project = $("#update-project-form").serialize();
			console.log(project);
			
			$.when(updateProject(project)).done(function(data){
				if(data.result=="1"){
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
			$("#move-step-select").selectmenu({
											width:"70%",
											open: function(event, ui){	
												console.log("here"),
												$(".ui-front").css("zIndex","1200")}
											
											});
												
			var custom_menu =  $(this).parents("ul.custom-menu")[0];
			var pid =  $(custom_menu).find("input[name=pid]").val();
			var pids = [pid];
			var sid =  $(custom_menu).find("input[name=sid]").val();
			var fid =  $(custom_menu).find("input[name=fid]").val();
			
			$("#move-step-sid").val(sid);
			$.when(selectFolderList(pids)).done(function(data){
				console.log(data.folderlist);
				var optiondefault = jQuery("<option>",{"text":"폴더 밖으로 이동","value":""})
				$("#move-step-select").empty();
				$(optiondefault).appendTo($("#move-step-select"));	
				$(data.folderlist).each(function(index,folder){
					var option = jQuery("<option>",{"text":folder.fname,
													"value":folder.fid});
					if(folder.fid==fid){
						option.attr("selected",true);

					}
					$(option).appendTo($("#move-step-select"));								
				});
			});
		});
		
		///// 스텝이동 모달창에서 확인버튼 클릭 시 
		$(document).on("click","#move-step-btn",function(){
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
							$("#s"+step.sid).remove().appendTo("#f-dir"+step.fid);
						}else{
							var default_ = $("#s"+step.sid).parents("div.side-dir-project")[0];
							console.log(default_);
							$("#s"+step.sid).remove().appendTo(default_);
						}
					}
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
			
		});
	

	
	
	/////////////////////////// 비동기 화면전환 - 프로젝트 /////////////////////////
		
		$(document).on("click",".side-project",function(){
	
			
			var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
			var pid = project_wrapper.id.substr(1);
			var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
			
			$.ajax({
				url:"information.htm",
				data: "pid=" + pid,
				dataType:"html",
				success:function(data){
					 $("#main-box").empty();
					 $("#main-box").append(data);	 		
					 
				}
			})
	    });

	
		////// 사이드바 스텝 클릭 시 
		$(document).on("click",".side-step",function(){
			var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
			var pid = project_wrapper.id.substr(1);
			var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
			var sid= this.id.substr(1);
			$.ajax({
				url:"list.htm",
				data:{sid:sid},
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
		    dateFormat: 'yy-mm-dd'


		});
		$(".edate-img").datepicker({
		    showOn: "button",
		    buttonImage: "img/calendar.png",
		    buttonImageOnly: true,
		    dateFormat: 'yy-mm-dd',
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
				 if(data.result > 0){
					 alert("프로젝트 생성이 완료되었습니다!");
				 }else {
					 alert("프로젝트 생성에 실패했습니다");
				 }	
				 $('#add-project-name').val("");
				 $('#insert-project-sday-id').val("");
				 $('#insert-proejct-eday-id').val("");
				 $('#proejct-detail').val("");
				 $('.close').click();
			 }

		 });	
	});
	
	//사이드바에서 폴더생성 버튼을 클릭할 때
	$(document).on("click","#side-add-folder",function(){
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
	                console.log(data);
	                console.log(data.selectfolder);
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
	               console.log(data);
	               console.log(data.updatefolder);
	               
	               if(data.updatefolder > 0){
	                   alert('폴더 수정이 완료되었습니다!');
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
	       console.log(fid);
	       $.ajax({
	           type:"post",
	           url:"selectfolder.htm",
	            data:{fid:fid},
	            dataType:"json",
	            success:function(data){
	                console.log(data);
	                console.log(data.selectfolder);
	                $('#delete-folder-fid').val(fid);
	                
	            }
	       }); // end-ajax
	   });
	   
	   //폴더 modal 에서 삭제 버튼 클릭시 이벤트
	   $('#delete-folder-btn').click(function() {
	       var deletefolder = $('#delete-folder-form').serialize();
	       console.log(deletefolder);
	       $.ajax({
	           type:"post",
	           url:"deletefolder.htm",
	           data:deletefolder,
	           dataType:"json",
	           success:function(data){
	               console.log(data);
	               console.log(data.deletefolder);
	               
	               if(data.updatefolder > 0){
	                   alert('폴더 삭제가 실패되었습니다');
	               }else {
	                   alert('폴더 삭제이 완료되었습니다!');
	               }
	               $('.close').click();
	           }
	       }); // end - ajax       
	   })

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
   if(pids==null) return false;
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
 날      짜 : 2018. 6. 19.
 기      능 : 프로젝트 생성이 없을 떄 실행되는 함수
 작성자명 : 김 래 영
 */
function noProjectPage() {
   $.ajax({
      url:"noproject.htm",
      dataType:"html",
      success:function(data){
          $("#main-box").empty();
          $("#main-box").append(data);          
          
      }
   })
}



/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 사이드바에 프로젝트 리스트디렉토리 구조 생성함수
 작성자명 : 박 민 식
 */

function selectProjectList(){
   console.log("프로젝트 실행");
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
         if(data!=null){ /// 참여중인 프로젝트가 있을 경우 
            $(data.projectlist).each(function(index,el){
               pids.push(el.pid);
               var wrapper = jQuery("<div>",{"class":"side-project-wrapper","id":"p"+el.pid});
               var a = jQuery("<a>",{"class":"side-project","text":el.pname})
               var hidden = jQuery("<input>",{"type":"hidden",
                                       "name":"methodologyid",
                                       "value":el.methodologyid});
               var span = jQuery("<span>",{"class":"glyphicon glyphicon-duplicate", 
                  "data-toggle":"collapse",
                  "data-target":"#p-dir"+el.pid})
               var div = jQuery("<div>",{"class":"side-dir-project  collapse",
                  "id": "p-dir"+el.pid});
                  
               console.log(el.pstatuscode);
               $(a).prepend(span);
               $(wrapper).append(a).append(div).append(hidden);
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
 날   짜 : 2018. 6. 19.
 기   능 : 사이드바의 구조를 만들어주는 함수 
 작성자명 : 박 민 식
 */
function makeSideProjectDir(){
   
    $.when(selectProjectList()).done(function(data){ //먼저 프로젝트의 리스트를 가져와 뿌려준 후, 
      if(data.length==0) {
         //프로젝트가 전혀 없는 경우 실행될 함수 
         noProjectPage();
      } else{         
         var pids = data;
         makeSideSubDir(pids); // 각 프로젝트의 내부 구조를 채워줄 요소들을 가져오는 함수를 실행한다. 
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
      console.log(folders);
      console.log(steps);
      ///// 먼저 폴더를 화면에 뿌려준다. 
      if(folders!=null){ //폴더가 하나라도 있다면 만들어 붙혀주세요
         
         $(folders).each(function(index,folder){
        	var wrapper_div = jQuery("<div>",{"class":"side-folder-wrapper","id":"fwrapper"+folder.fid});
            var a =jQuery("<a>",{"class":"side-folder","text":folder.fname,"id":"f"+folder.fid});
            var span = jQuery("<span>",{"class":"glyphicon glyphicon-folder-close", 
                                 "data-toggle":"collapse",
                                 "data-target":"#f-dir"+folder.fid});
            var div = jQuery("<div>",{"class":"side-dir collapse",
                                "id": "f-dir"+folder.fid});
            console.log(folder.pid);
            
            $(a).prepend(span).appendTo(wrapper_div);
            $(div).appendTo(wrapper_div);
            $(wrapper_div).appendTo($('#p-dir'+folder.pid));
         })
         
      }
      
      //// 폴더를 뿌려준 후, Step을 뿌려준다.
      if(steps!=null){
         
         $(steps).each(function(index,step){ // Step관련 태그 생성
            var a =jQuery("<a>",{
                           "class":"side-step",
                           "id":"s"+step.sid,
                           "text":step.sname
                           });
            var span = jQuery("<span>",{"class":"glyphicon glyphicon glyphicon-list-alt"})

            $(a).prepend(span);
      
            if(step.fid !=null){ // 스텝이 속해있는 폴더가 있다면 폴더 밑에 넣어주고 
               $(a).appendTo("#f-dir"+step.fid);
            }else{ //속해있는 폴더가 없다면, Default경로, 즉 프로젝트 밑으로 넣어준다.
               (step.sname=="백로그")?$(a).prependTo("#p-dir"+step.pid):$(a).appendTo("#p-dir"+step.pid);
            }
         })
      }
      
   })
}   


///// 프로젝트 업데이트시 사용하는 함수
function updateProject(data){
   var ajax = $.ajax({
      url:"updateProject.htm",
      type:"POST",
      data:data,
      dataType:"json",
      success:function(data){
         console.log(data);
      }
   })
   return ajax;
}
   
   