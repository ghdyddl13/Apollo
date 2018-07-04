
   
  
   /*
    날      짜 : 2018. 6. 15.
    기      능 : 프로젝트 멤버 초대 모달에서 초대하기 누르면 멤버가 초대되고 redirect
    작성자명 : 김 정 권
    */
   // 동적 생성 태그에 대한 이벤트이므로
   // 일반적으로 위에서 써오던 함수와 형태가 다르다
   $(document).on("click","#pmember_add_btn",function(){

	  $('#add_project_member').click(); 
      var mid = $(this).children().attr("id");
      
      $.ajax(
              {
               type : "post",
               url:"insertMidToPmember.htm",
               data : {
	        	   'mid': mid
	           },
                 success : function(rdata){
                                
                	 $("#main-box").empty();
					 $("#main-box").append(rdata);
                	 
                     } // end - success
                 }
                );
   });
