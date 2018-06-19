$(function() {
 	
	// 현황 물어보기
	
	// tid를 어디에 매기고 있는지?
	// 대책 1) direct children으로 div를 하나 감싸고 거기 tid 먹인다
    //			>>> var mid = $(this).children().attr("id"); 
	// 대책 2) 현재 태그에 아이디로 tid를 먹인다
	//			>>> var mid = $(this).attr("id"); 

	// 즐겨찾기 토글 준수한테 조언 얻기
	
	$(document).on("click",".Task_RUD_Modal",function(){

		$.ajax(
			       {
			           type : "post",
			           url  : "getProgressData.htm",
			           data : "pid="+ 1,
			           success : function(rdata){
			        	   
			        	   
			        	   
			           } // end-success
			        } 
			      ); // end-ajax
	});
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 18.
	 기      능 : Task 수정/ 삭제 페이지에 날짜 클릭으로 넣는 datepicker 넣는 코드
	 작성자명 : 김 정 권
	 */
	$( ".date-img" ).datepicker({
	    showOn: "button",
	    buttonImage: "img/calendar.png",
	    buttonImageOnly: true,
	    dateFormat: 'yy-mm-dd'


	});
	
}); // end-document.onready
