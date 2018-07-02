/**
 * 
 날      짜 : 2018. 6. 25.
 기      능 : table 각 tr에 마우스 호버하면 x 표시가 보이도록 한다.
 작 성 자 명 : 이 창 훈
 */
	$(document).on("mouseenter",".file-table-tr-td-fname",function() {//마우스 호버 하면 'x'가 보임
	  $(this).children(".file-table-tr-td-delete").children(".file-deleteicon").css("visibility","visible");
	}).on("mouseleave",".file-table-tr-td-fname",function() {//마우스 호버 아웃 하면 'x'가 다시 안보이게 함
	  $(this).children(".file-table-tr-td-delete").children(".file-deleteicon").css("visibility","hidden");
	})
	
	$(document).on("click", ".file-deleteicon", function(){
		var fileid = $(this).attr('id');
	 	$('#starbucks').attr('value', fileid) 
	})
	
/**
 * 
 날      짜 : 2018. 6. 27.
 기      능 : file table에서 각 file 삭제 하는 함수
 작 성 자 명 : 이 창 훈
 */	
   $(document).on("click", "#file_delete_btn", function(){
    	 	
		var fileid = $('#starbucks').attr('value');
		 $.ajax({
             url : "filesDeleteByFileId.htm",
             data : {
                	  fileid : fileid
                   },
             success:function(data){
            	$('#file_delete_dismiss_btn').click();
                $("#main-box").empty();
                $("#main-box").append(data);
             }  
          })
		
	})  