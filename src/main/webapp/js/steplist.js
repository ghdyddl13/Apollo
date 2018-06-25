$(function() {
	////// 사이드바 스텝 클릭 시 
	$(document).on("click",".side-step",function(){
		var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
		var pid = project_wrapper.id.substr(1);
		var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
		var sid= this.id.substr(1);
		checkbox=[];
		$.ajax({
			url:"list.htm",
			data:{sid:sid},
			dataType:"html",
			success:function(data){
				 $("#main-box").empty();
				 $("#main-box").append(data);
				 	
					/////////////////////////////////STATUS OF TASK GRAPH/////////////////////////////////////////
					let ctx = document.getElementById('Step-list-DonutChart').getContext('2d');
					let myDoughnutChart = new Chart(ctx, {
					
					      type: 'doughnut',
					      data: {
					              datasets: [{
					                  data: [3, 4, 5, 6, 7],
					                  backgroundColor: [
					                                    'rgba(190, 190, 190, 1)',
					                                    'rgba(241, 196, 15, 1)',
					                                    'rgba(244, 7, 7, 1)',
					                                    'rgba(52, 152, 219, 1)',
					                                    'rgba(46, 204, 113, 1)'
					                  ],
					                  borderColor:[
					                                'rgba(190, 190, 190, 1)',
					                                'rgba(241, 196, 15, 1)',
					                                'rgba(244, 7, 7, 1)',
					                                'rgba(52, 152, 219, 1)',
					                                'rgba(46, 204, 113, 1)'
					                  ],
					                  borderWidth: 1
					              }],
					              labels:
					        ['미지정','다음주 이후','이번주 까지','완료','기한 만료']
					      },
					      options: {
					                maintainAspectRatio: false,
					                cutoutPercentage: 50,
					                legend: {
					                          display: true,
					                          position: 'right',
					                          labels: {
					                                    fontSize: 12,
					                                    fontFamily: 'sans-serif',
					                                    fontColor: '#ffffff',
					                                    fontStyle: 'bold'
					                }
					      }
					      }
					});

			}
		})
    });
	/////////////////////////////////태스크 새로 입력하는 부분/////////////////////////////////////////
	$(document).on("click","#list-task-adder",function() {
	  $("#list-task-adder").remove();
	  let inputtag="<div class='list-task-adder-addmode'><input class='form-control' id='insert-task' name='tname' type='text' placeholder='새로운 작업을 입력하세요'></div>"
	  $("#body-start").prepend(inputtag);
	  $("#insert-task").focus();
	})
	$(document).on("keyup","#insert-task",function(event) {
	  let addtag="<div class='list-task-adder' id='list-task-adder'>New task</div>";
	  if(event.which==13){
		let tstatus =$(".list-header-filter-status-items").children(".list-header-menu-list-item").attr("id");
	    let newtask=$.trim($(this).val());
	    if(newtask===""){
	      //문자열이 빈값이면 발생하는 함수가 아무것도 없음
	    }else{
	   	  $("#insert-task").val("");
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
	  }else if(event.which==27){
	    $(".list-task-adder-addmode").remove();
	    $("#body-start").prepend("<div class='list-task-adder' id='list-task-adder'>New task</div>");
	  }
	});
	$(document).on("focusout","#insert-task",function() {
	  let newtask = $.trim($(this).val());
	  let addtag="<div class='list-task-adder' id='list-task-adder'>New task</div>";
	  if(newtask===""){//빈 문자일 경우 그냥 바로 나온다
	    $(".list-task-adder-addmode").remove();
	    $("#body-start").prepend(addtag);
	  }else{
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
	    $(".list-task-adder-addmode").remove();
	    $("#body-start").prepend(addtag);
	  }
	})
	
	

	/////////////////////////////////TASK CONTAINER HOVER/////////////////////////////////////////
	let checkbox=[];
	$(document).on("mouseenter",".list-task-container",function() {//마우스 호버 하면 checkbox가 보임
	  $(this).children(".list-task-checkbox-container").children(".list-task-checkbox").css("visibility","visible");
	}).on("mouseleave",".list-task-container",function() {//마우스 호버 아웃 하면 checkbox가 다시 안보이게 함
	  $(this).children(".list-task-checkbox-container").children(".list-task-checkbox").css("visibility","hidden");
	})
	$(document).on("click",".list-task-checkbox",function() {//선택되지 않은 select box를 않은 눌렀을 시에
	  $(this).removeClass("list-task-checkbox").addClass("list-task-checkbox-selected");
	  $(".list-section-third-default").css({"visibility":"hidden","position":"absolute"});
	  $(".list-section-third-selectpage").css({"visibility":"visible","position":"relative"})
	  checkbox.push($(this).parent().parent().attr('id'));
	  console.log(checkbox);
	  if(checkbox.length>0){
	    $("#selected-tag-count").text(checkbox.length+"개 선택됨");
	  };
	})
	$(document).on("click",".list-task-checkbox-selected",function () {//선택된 select box를 클릭했을 시에
	  $(this).removeClass("list-task-checkbox-selected").addClass("list-task-checkbox");
	  checkbox.splice(checkbox.indexOf($(this).parent().parent().attr('id')),1);// 선택을 풀면서 거기에 담겨있는 id를 가지고와 중복되는 값을 제거한다
	  console.log(checkbox);
	  $("#selected-tag-count").text(checkbox.length+"개 선택됨");
	  if(checkbox.length==0){
	    $(".list-section-third-selectpage").css({"visibility":"hidden","position":"absolute"});
	    $(".list-section-third-default").css({"visibility":"visible","position":"relative"})
	  }
	})
	$(document).on("click","#list-closebutton",function() {//닫기 버튼을 누를시
	  $(".list-section-third-selectpage").css({"visibility":"hidden","position":"absolute"});
	  $(".list-section-third-default").css({"visibility":"visible","position":"relative"})
	  checkbox=[];
	  $(".list-task-checkbox-selected").removeClass("list-task-checkbox-selected").addClass("list-task-checkbox");
	  console.log(checkbox);
	  $(".list-task-checkbox").css("visibility","hidden");
	})
	$(document).on("click","#selectpage-complete-button",function() {//완료 버튼 선택시에
	  alert("완료되었습니다")
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
	  checkbox=[];
	  console.log(checkbox);
	})
	$(document).on("click","#different-status",function() {//다른 선택 영역을 눌렀을 시에
	  alert("상태를 추가합니다")
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
	  checkbox=[];
	  console.log(checkbox);
	})
	$(document).on("click","#selectpage-addasignee-button",function() {//추가 할당자들을 눌렀을 시에
	  alert("할당자들을 추가합니다")
	
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
	    checkbox=[];
	  console.log(checkbox);
	})
	$(document).on("click","#selectpage-addsteps-button",function() {// 추가 버튼을 눌렀을 시에
	  alert("할당자들을 추가합니다")
	
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
	  checkbox=[];
	  console.log(checkbox);
	})
	$(document).on("click","#selectpage-deletetask-button",function() {//삭제 버튼을 눌렀을 시에
	  alert("삭제합니다")
	
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
	  checkbox=[];
	  console.log(checkbox);
	})
	
	
	
	
	
	
	
	
	/////////////////////////////////STATUS SELECTING BOTTON/////////////////////////////////////////
	$(document).on("click","#status-button",function() {
	  /**/
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
	  let status = $(this).attr("id");
	  $(".list-header-filter-status").css("background-color","")
	  $(".list-header-filter-status-selecting").css({"visibility":"hidden","left":"-10000px","top":"-10000px"});
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
	});
	
	/////////////////////////////////PEOPLE SELECTING BOTTON/////////////////////////////////////////
	$(document).on("click","#people-button",function(){
	  $(".list-header-filter-people").css("background-color","#dfe6f0")
	
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