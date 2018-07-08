$(function() {
	/**
	 * 
	 날   짜 : 2018. 6. 25.
	 기   능 : SIDESTEP에서 STEP을 클릭할 시에 발생하는 함수
	 작성자명 : 이 진 우
	 */
	var list_memberlist=[];
	$(document).on("click",".side-step",function(){
		var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
		var sid= this.id.substr(1);
		checkbox=[];
		list_memberlist=[];
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
    });
	
	
	/**
	 * 
	 날   짜 : 2018. 6. 25.
	 기   능 : TASK ADD를 할 시에 입력해주는 함수
	 작성자명 : 이 진 우
	 */
	$(document).on("click","#list-task-adder",function() {//클릭할 시에 새로운 입력 태그가 생성
	  $("#list-task-adder").remove();
	  let inputtag="<div class='list-task-adder-addmode'><input class='form-control' id='insert-task' name='tname' type='text' placeholder='새로운 작업을 입력하세요'></div>"
	  $("#body-start").prepend(inputtag);
	  $("#insert-task").focus();

	})
	$(document).on("keyup","#insert-task",function(event) {//키업함수를 통해 
	  let addtag="<div class='list-task-adder' id='list-task-adder'>New task</div>";
	  if(event.which==13){
		let tstatus =$(".list-header-filter-status-items").children(".list-header-menu-list-item").attr("id");
	    let newtask=$.trim($(this).val());
	    if(newtask===""){
	      //문자열이 빈값이면 발생하는 함수가 아무것도 없음
	    }else{
	   	  $("#insert-task").val("");
	   	 $("#insert-task").attr("readonly", true);
	      $.ajax(
	              {
	                url:"listtaskcreate.htm",
	                data:{
	                	tstatusid : tstatus, 
                        tname : newtask
                        },
	                dataType:"html",
	                success:function(data) {
						$("#main-box").empty();
						$("#main-box").append(data);
						$("#list-task-adder").remove();
						$("#body-start").prepend("<div class='list-task-adder-addmode'><input class='form-control' id='insert-task' name='tname' type='text' placeholder='새로운 작업을 입력하세요'></div>");
						$("#insert-task").attr("readonly", false);
						$("#insert-task").focus();
						
	                }
	              }
	      )
	    }
	  }else if(event.which==27){
	    $(".list-task-adder-addmode").remove();
	    $("#body-start").prepend("<div class='list-task-adder' id='list-task-adder'>New task</div>");
	  }
	});
	/**
	 * 
	 날   짜 : 2018. 6. 25.
	 기   능 : TASK ADD를 하는 INPUT TAG에서 나오면 빈문자일시 그냥 다시 태그생성, 빈문자가 아니면 새로 로드됨
	 작성자명 : 이 진 우
	 */
	$(document).on("focusout","#insert-task",function() {
	  let newtask = $.trim($(this).val());
	  let addtag="<div class='list-task-adder' id='list-task-adder'>New task</div>";
	  if(newtask===""){//빈 문자일 경우 그냥 바로 나온다
	    $(".list-task-adder-addmode").remove();
	    $("#body-start").prepend(addtag);
	  }else{
		  let tstatus =$(".list-header-filter-status-items").children(".list-header-menu-list-item").attr("id");
		  $(".list-task-adder-addmode").remove();
		  $("#body-start").prepend(addtag);
	      $.ajax(
	              {
	                url:"listtaskcreate.htm",
	                data:{
	                	tstatusid : tstatus, 
                        tname : newtask
                        },
	                dataType:"html",
	                success:function(data) {
						$("#main-box").empty();
						$("#main-box").append(data);
	                }
	              }
	      )
	  }
	})
	/**
	 * 
	 날   짜 : 2018. 6. 25.
	 기   능 : TASK CONTAINER을 HOVER하거나 마우스로 클릭할시 발생하는 함수
	 작성자명 : 이 진 우
	 */
	let checkbox=[];
	$(document).on("mouseenter",".list-task-container",function() {//마우스 호버 하면 checkbox가 보임
	  $(this).children(".list-task-checkbox-container").children(".list-task-checkbox").css("visibility","visible");
	}).on("mouseleave",".list-task-container",function() {//마우스 호버 아웃 하면 checkbox가 다시 안보이게 함
	  $(this).children(".list-task-checkbox-container").children(".list-task-checkbox").css("visibility","hidden");
	})
	$(document).on("click",".list-task-checkbox",function() {//선택되지 않은 select box를 않은 눌렀을 시에
	  $(this).removeClass("list-task-checkbox").addClass("list-task-checkbox-selected");
	  $(".list-section-third-default").css({"visibility":"hidden","position":"absolute","height":"50%"});
	  $(".list-section-third-selectpage").css({"visibility":"visible","position":"relative","height":"100%"})
	  checkbox.push($(this).parent().parent().children(".center-container").attr('id'));
	  console.log(checkbox);
	  if(checkbox.length>0){
	    $("#selected-tag-count").text(checkbox.length+"개 선택됨");
	  };
	})
	$(document).on("click",".list-task-checkbox-selected",function () {//선택된 select box를 클릭했을 시에
	  $(this).removeClass("list-task-checkbox-selected").addClass("list-task-checkbox");
	  checkbox.splice(checkbox.indexOf($(this).parent().parent().children(".center-container").attr('id')),1);// 선택을 풀면서 거기에 담겨있는 id를 가지고와 중복되는 값을 제거한다
	  console.log(checkbox);
	  $("#selected-tag-count").text(checkbox.length+"개 선택됨");
	  if(checkbox.length==0){
	    $(".list-section-third-selectpage").css({"visibility":"hidden","position":"absolute","height":"50%"});
	    $(".list-section-third-default").css({"visibility":"visible","position":"relative","height":"100%"})
	  }
	})
	$(document).on("click","#list-closebutton",function() {//닫기 버튼을 누를시
	  $(".list-section-third-selectpage").css({"visibility":"hidden","position":"absolute","height":"50%"});
	  $(".list-section-third-default").css({"visibility":"visible","position":"relative","height":"100%"})
	  checkbox=[];
	  $(".list-task-checkbox-selected").removeClass("list-task-checkbox-selected").addClass("list-task-checkbox");
	  console.log(checkbox);
	  $(".list-task-checkbox").css("visibility","hidden");
	})
	$(document).on("click",".selectpage-body-statusbutton",function() {//상태 버튼을 눌렀을 시에
	  let tstatusid = $(this).attr("id");
	  let checkboxcount= checkbox.length;
	  $("#list-task-status-ment").html(+checkboxcount+"개 Task의 상태를 변경 하시겠습니까?")
	  $(document).on("click","#list_Status_Tasks_btn",function(){
		  if(checkbox.length==0){
			  alert("왜 테스크가 없지..?")
			  $('#list_status_tasks_dismiss_btn').click();
		  }else{
			    $.ajax(
			            {
			              type:"POST",
			              url:"liststatustasks.htm",
			              data:{tstatusid:tstatusid,tasks:checkbox},
			              success:function(data) {
								$("#main-box").empty();
								$("#main-box").append(data);
								$('#list_status_tasks_dismiss_btn').click();
								checkbox=[];
			              }
			    })
		  }
	   });
	})

	$(document).on("click","#selectpage-addasignee-button",function() {//추가 할당자들을 눌렀을 시에
		let checkboxcount= checkbox.length;
		let mid ="jinwoo@naver.com" ;
		$("#list-task-assign-ment").html("에게 "+checkboxcount+"개 Task를 할당하시겠습니까?")
	});
	$(document).on("click","#list_Assign_Tasks_btn",function(){
		if(checkbox.length==0){
			alert("왜 테스크가 없지..?")
			$('#list_assign_tasks_dismiss_btn').click();
		}else{
			$.ajax({
				type : "POST",
				url : "listassigntasks.htm",
				data :{mid:mid,tasks:checkbox},
				success : function(data) {
					$("#main-box").empty();
					$("#main-box").append(data);
					$('#list_assign_tasks_dismiss_btn').click();
					checkbox = [];
				}
			})
		}
	})
	
	$(document).on("click","#selectpage-addsteps-button",function() {// 추가 스텝 버튼을 눌렀을 시에
		let checkboxcount= checkbox.length;
		$("#list-addstep-tasks-ment").html("에 "+checkboxcount+"개 Task를 추가하시겠습니까?")
	});
	$(document).on("click","#list_AddStep_Tasks_btn",function(){
		let stepid=8;
		if(checkbox.length==0){
			alert("왜 테스크가 없지..?")
			$('#list_addstep_tasks_dismiss_btn').click();
		}else{
			$.ajax({
				type : "POST",
				url : "listaddsteptasks.htm",
				data :{stepid:stepid,tasks:checkbox},
				success : function(data) {
					$("#main-box").empty();
					$("#main-box").append(data);
					$('#list_addstep_tasks_dismiss_btn').click();
					checkbox = [];
				}
			})
		}
	})
	
	$(document).on("click", "#selectpage-deletetask-button", function(){// 삭제 버튼을 눌렀을 시에
		let checkboxcount= checkbox.length;
		$("#list-delete-tasks-ment").html(checkboxcount+"개 Task를 삭제하시겠습니까?<br><h5 style='color:red'>(삭제 후 복구 불가능합니다)</h5>")

	})
	$(document).on("click","#list_Delete_Tasks_btn",function(){
		if(checkbox.length==0){
			alert("왜 테스크가 없지..?")
		}else{
			$.ajax({
				type : "POST",
				url : "listdeletetasks.htm",
				data :{tasks:checkbox},
				success : function(data) {
					$("#main-box").empty();
					$("#main-box").append(data);
					$('#list_delete_tasks_dismiss_btn').click();
					checkbox = [];
				}
			})
		}
	})

	
	
	
	
	/**
	 * 
	 * 날 짜 : 2018. 6. 25. 기 능 : STATUS SELECTING BUTTON을 클릭할시 발생하는 함수들 작성자명 : 이
	 * 진 우
	 */
	//상태 버튼을 눌렀을시 발생하는 함수
	$(document).on("click","#status-button",function() {
	  let p =$("#status-button");
	  let position = p.position();
	  $(".list-header-filter-status").css("background-color","#dfe6f0")
	  $(".list-header-filter-status-selecting").css({"visibility":"visible","left":position.left,"top":position.top+24});
	});
	
	// 사이드 우클릭 메뉴 닫는 함수
	$(document).bind("mousedown", function(e) {
	    // If the clicked element is not the menu
	    if (!$(e.target).parents(".list-header-filter-status-selecting").length > 0) {
	    // Hide it
	      $(".list-header-filter-status").css("background-color","")
	      $(".list-header-filter-status-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
	  }
	});
	$(document).on("click",".list-header-menu-list-item",function () {
	  let tstatusid = $(this).attr("id");
	  let sid = parseInt($(".list-header-title").attr("id").substring(1));
	  let statusname = $.trim($(this).text());
	  console.log(tstatusid +"/"+sid+"/"+statusname);
	  $(".list-header-filter-status").css("background-color","")
	  $(".list-header-filter-status-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
      $.ajax(
              {
                type:"POST",
                url:"liststatusfilter.htm",
                data:{tstatusid:tstatusid,
                	  sid:sid
                },
                success:function(data) {
                	$(".list-header-filter-status").empty();
                	$(".list-header-filter-status").append("<span class='list-header-filter-status-tag' id='status-button'>"+statusname+"</span><span class='list-header-filter-status-remove' id='task-status-remove'></span>");
                	$(".list-header-filter-status").css("background-color","#dfe6f0")
                	$(".list-task-containers").empty();
                	$(".list-task-containers").append(data);
                }
              }
      )
	});
	$(document).on("click",'#task-status-remove',function(){
		let sid = parseInt($(".list-header-title").attr("id").substring(1));
	    $.ajax(
	              {
	                type:"POST",
	                url:"liststatusfilter.htm",
	                data:{sid:sid},
	                success:function(data) {
	                	$(".list-header-filter-status").empty();
	                	$(".list-header-filter-status").append("<span class='list-header-filter-status-tag' id='status-button'>STATUS:ALL</span>");
	                	$(".list-header-filter-status").css("background-color","")
	                	$(".list-task-containers").empty();
	                	$(".list-task-containers").append(data);
	                }
	            }
	    )
	});
	
	/**
	 * 
	 날   짜 : 2018. 6. 25.
	 기   능 : PEOPLE SELECTING BOTTONN을 클릭할시 발생하는 함수들
	 작성자명 : 이 진 우
	 */
	//people button을 누르면 로드시 가지고 왔던 데이터를 뿌려준다
    $(document).on("click","#people-button-tag",function(){
        let p =$("#people-button");
        let position = p.position();
        $(".list-header-filter-people").css("background-color","#dfe6f0")
        $(".list-header-filter-people-selecting").css({"visibility":"visible","left":position.left,"top":position.top+24});
        let nametag="";
        $(".list-header-filter-people-tagscontainer").empty();
        $.each(list_memberlist,function(index,element) {
              nametag+= '<div class="list-header-filter-people-tagwrap"><div class="list-header-filter-people-tag"><div class="list-header-filter-people-image">';
              nametag+='<img class="list-people-image"src="img/user.png" alt=""></div><div class="list-header-filter-people-info"><div class="list-people-name">'
              nametag+= element.mname;
              nametag+= '</div><div class="list-people-email">';
              nametag+= element.mid;
              nametag+= '</div></div></div></div>';
         })
         $(".list-header-filter-people-tagscontainer").append(nametag);
     });

      // 사이드 우클릭 메뉴 닫는 함수
	  $(document).bind("mousedown", function(e) {
	    // If the clicked element is not the menu
	    if (!$(e.target).parents(".list-header-filter-people-selecting").length > 0) {
	    // Hide it
	      $("#people-button").css("background-color","");
	      $("#filter-people-input").val("");
	      $(".list-header-filter-people-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
	    }
	  });
	  //people button이 생성 되고나서 remove 버튼을 눌렀을 시에 
      $(document).on("click",".list-header-filter-people-remove",function(params) {
      	let sid = parseInt($(".list-header-title").attr("id").substring(1));
        $("#people-button").empty();
        $("#people-button").removeClass("list-header-filter-people-selected").addClass("list-header-filter-people");
        $("#people-button").css("background-color","")
        $("#people-button").append("<span class='list-header-filter-people-tag' id='people-button-tag'>TO:ALL</span>");
	    $.ajax(
	            {
	              type:"POST",
	              url:"listpeoplefilter.htm",
	              data:{sid:sid},
	              success:function(data) {
	                	$(".list-task-containers").empty();
	                	$(".list-task-containers").append(data);
	              }
	            }
	    )
      })
      //태그를 눌렀을시 발생하는 함수
      $(document).on("click",".list-header-filter-people-tagwrap",function() {
    	let sid = parseInt($(".list-header-title").attr("id").substring(1));
        let mid= $.trim($(this).children(".list-header-filter-people-tag").children(".list-header-filter-people-info").children(".list-people-email").text());
        let mname=$.trim($(this).children(".list-header-filter-people-tag").children(".list-header-filter-people-info").children(".list-people-name").text());
        console.log(mid+"/"+mname);
        $(".list-header-filter-people-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
        $("#people-button").css("background-color","#dfe6f0")
        $("#people-button").empty();
        $("#people-button").append("<span class='list-header-filter-people-tag-selected list-header-filter-people-tag' id='people-button-tag'>TO: "+mname+"</span><span class='list-header-filter-people-remove'></span>");
        $("#people-button").removeClass("list-header-filter-people").addClass("list-header-filter-people-selected")
	    $.ajax(
	            {
	              type:"POST",
	              url:"listpeoplefilter.htm",
	              data:{mid:mid,sid:sid},
	              success:function(data) {
	                	$(".list-task-containers").empty();
	                	$(".list-task-containers").append(data);
	              }
	            }
	    )
      })
      //INPUT TAG에 값을 입력하면 KEYUP해주는 함수
      $(document).on("keyup","#filter-people-input",function(event) {
        var piece=$.trim($(this).val());
        let nametag='';
        $(".list-header-filter-people-tagscontainer").empty();
        $.each(list_memberlist,function(index,element) {
          let name = element.mname;
          let id = element.mid;
          let namecheck=name.indexOf(piece);
          let emailcheck=id.indexOf(piece);
          if(namecheck==-1&&emailcheck==-1){
            //다른 연산 없이 그냥 패스
          }else{
            name = name.replace(piece,"<b>"+piece+"</b>");
            id= id.replace(piece,"<b>"+piece+"</b>");
            nametag+= '<div class="list-header-filter-people-tagwrap"><div class="list-header-filter-people-tag"><div class="list-header-filter-people-image">';
            nametag+='<img class="list-people-image"src="img/user.png" alt=""></div><div class="list-header-filter-people-info"><div class="list-people-name">'
            nametag+= name;
            nametag+= '</div><div class="list-people-email">';
            nametag+= id;
            nametag+= '</div></div></div></div>';
          }

        })
        $(".list-header-filter-people-tagscontainer").append(nametag);
        
        if(event.which==13){
          if(piece===""){
            //문자열이 빈값이면 발생하는 함수가 아무것도 없음
          }else{
            console.log(piece);
          $("#filter-people-input").val("");
            /*
            $.ajax(
                    {
                      type:"POST",
                      url:"",
                      date:"",
                      success:function(data) {

                      }
                    }
            )
            */
          }
        }else if(event.which==27){
          $("#people-button").css("background-color","");
          $("#filter-people-input").val("");
          $(".list-header-filter-people-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
        }
      });
	
	/////////////////////////////////SORTING BOTTON//////////////////////////////////////////////////
	$(document).on("click","#sorting-button",function() {
	  let p =$("#sorting-button");
	  let position = p.position();
	  $(".list-header-sorting-button").css("background-color","#dfe6f0")
	  $(".list-header-sorting-selecting").css({"visibility":"visible","left":position.left,"top":position.top+26});
	});
	$(document).bind("mousedown", function(e) {
	    // If the clicked element is not the menu
	    if (!$(e.target).parents(".list-header-sorting-selecting").length > 0) {
	    // Hide it
	      $(".list-header-sorting-button").css("background-color","") //
	      $(".list-header-sorting-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
	  }
	});
	$(document).on("click",".list-header-sorting-textcontainer",function() {
	  let sorting = $(this).attr("id");
	  console.log(sorting);
	  $(".list-header-sorting-button").css("background-color","")
	  $(".list-header-sorting-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
	  $("#sorting-button").empty();
	  $("#sorting-button").append("<span class='list-header-sorting-tag'>By "+ sorting+"</span>");
	})
})