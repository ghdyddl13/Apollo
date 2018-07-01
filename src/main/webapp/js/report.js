$(document).on("click","#testbtn",function(){
	
	  $.ajax(
		       {
		           type : "post",
		           url  : "testprint.htm",
		           data : {
		           },
		           success : function(rdata){
		        	   	
		        	   alert('Report 생성이 완료되었습니다\nC:\\Apollo_Reports\\ 를 확인하세요')
		        	   
		           } // end-success
		        }); // end-ajax
	
});

/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : report '진행중인 task' 버튼 hover시 이미지 움직이는 gif로 변경
 작성자명 : 김 정 권
 */
$(document).on("mouseenter",".report_progress",function() {
	
    $(this).children().eq(2).css("display","none");
    $(this).children().eq(2).next().css("display","block");
  }).on("mouseleave",".report_progress",function() {
	  $(this).children().eq(2).next().css("display","none");
	    $(this).children().eq(2).css("display","block");
  });

/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : report 'Task 상태별 현황' 버튼 hover시 이미지 움직이는 gif로 변경
 작성자명 : 김 정 권
 */
$(document).on("mouseenter",".report_status",function() {
	
    $(this).children().eq(2).css("display","none");
    $(this).children().eq(2).next().css("display","block");
  }).on("mouseleave",".report_status",function() {
	    $(this).children().eq(2).css("display","block");
	    $(this).children().eq(2).next().css("display","none");
  });


/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : report '마감기한 지난 Task' 버튼 hover시 이미지 움직이는 gif로 변경
 작성자명 : 김 정 권
 */
$(document).on("mouseenter",".report_expired",function() {
	
    $(this).children().eq(2).css("display","none");
    $(this).children().eq(2).next().css("display","block");
  }).on("mouseleave",".report_expired",function() {
	    $(this).children().eq(2).css("display","block");
	    $(this).children().eq(2).next().css("display","none");
  });

/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : report '마감기한이 임박한 Task' 버튼 hover시 이미지 움직이는 gif로 변경
 작성자명 : 김 정 권
 */
$(document).on("mouseenter",".report_drawnear",function() {
	
    $(this).children().eq(2).css("display","none");
    $(this).children().eq(2).next().css("display","block");
  }).on("mouseleave",".report_drawnear",function() {
	    $(this).children().eq(2).css("display","block");
	    $(this).children().eq(2).next().css("display","none");
  });

/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : report '담당자 미정 Task' 버튼 hover시 이미지 움직이는 gif로 변경
 작성자명 : 김 정 권
 */
$(document).on("mouseenter",".report_unassigned",function() {
	
    $(this).children().eq(2).css("display","none");
    $(this).children().eq(2).next().css("display","block");
  }).on("mouseleave",".report_unassigned",function() {
	    $(this).children().eq(2).css("display","block");
	    $(this).children().eq(2).next().css("display","none");
  });
