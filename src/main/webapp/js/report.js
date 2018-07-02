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



/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : 5개 버튼중 어느 하나라도 클릭하면 실행되는 함수로 모달을 띄워주면서
 			  해당 유저가 속한 프로젝트를 select의 option들로 채워준다
 작성자명 : 김 정 권
 */
$(document).on("click",".btn_div",function(){
	
	var report_kind = $(this).attr('id');
	
	  $.ajax(
		       {
		           type : "post",
		           url  : "gerusersprojects.htm",
		           data : {
		           },
		           success : function(rdata){
		        	   
		        	   console.log(rdata);
		        	   console.log(rdata.projectlist);
		        	   
		        	   $('#user_projectlist_selectbox').empty();
		        	   
		        	   var options = '';
		        	   $(rdata.projectlist).each(function(){
		        		   options +='<option value="' + this.pid + '">';
		        		   options +=this.pname;
		        		   options +='</option>';
		        	   });
		        	   $('#user_projectlist_selectbox').append(options);
		        	   
		        	   $('#report_kind').attr('value', report_kind);
		        	   
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : 리포트 모달에서 '다운로드' 버튼을 누르면 작동하는 함수로 해당 버튼에 맞는 리포트를 만들어서 엑셀로 추출
 작성자명 : 김 정 권
 */
$(document).on("click","#download_report_btn",function(){
	
	var pid = $('#user_projectlist_selectbox').val();
	var report_kind = $('#report_kind').attr('value');
	var report_title = $('#report_file_name').val().replace(/(\s*)/g,"");
	
    var stringRegx = /[~!@\#$%<>^&*\()\-=+_\’]/gi;
    if(stringRegx.test(report_title)) {
      alert('특수문자를 사용 하실 수 없습니다');
      return;
    }
	
	  $.ajax(
		       {
		           type : "post",
		           url  : "downloadreport.htm",
		           data : {
		        	   'pid' : pid,
		        	   'report_kind' : report_kind,
		        	   'report_title' : report_title
		           },
		           success : function(rdata){
		        	   		
		        	   console.log(rdata);
		        	   alert('Report를 다운로드 하였습니다.\nC:\\Apollo_Reports\\를 확인해주십시오.');
		        	   $('#download_report_cancel_btn').click();
		        	   $('#report_file_name').val('');
		           } // end-success
		        }); // end-ajax
	
});


/**
 * 
 날      짜 : 2018. 7. 1.
 기      능 : '다운로드' 버튼을 엔터키로도 작동하게 하는 함수 
 작성자명 : 김 정 권
 */
$(document).on("keyup","#report_file_name",function(){
	
	  if (event.keyCode === 13) {
		  
		  $('#download_report_btn').click();
		  
	  }
});
