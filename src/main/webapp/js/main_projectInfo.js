
   
  
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
	           beforeSend:function(){
					$("#main-box").html(loadingpage);
				},
                 success : function(rdata){
                                
                	 $("#main-box").empty();
					 $("#main-box").append(rdata);
                	 
                     } // end - success
                 }
                );
   });

   
   /**
    날      짜 : 2018. 7. 10.
    기      능 : 프로젝트 멤버 삭제하는 기능들을 모아 놓았습니다.
    작성자명 : 박 민 식
    
    */
   var prow;
   
   //멤버이름 마우스 호버시 아이콘 생성
   $(document).on("mouseenter",".information-pmember",function(){
   	   console.log("1");
   	   if($("#project_infopage_manager").val()==$("#header-mid").val()){
   		   prow=$($(this).parents(".project_member_table_tr")).find(".information-pmember-mid");
   		   if($(prow).text()!=$("#header-mid").val()){
   			   var i = $("<i>",{"class":"delete-pmember fas fa-times",
   				   				   "data-toggle":"modal",
   				   				   "data-target":"#information-pmember-delete"});
   			   $(i).appendTo(this);
   		   }
   	   };
   });
   
   
   // 멤버 이름 마우스리브 시 아이콘 삭제
   $(document).on("mouseleave",".information-pmember",function(){
	 	$(this).find("i").remove();
	 	
	 });
	 
   // 멤버이름 아이콘 클릭 시 모달창 생성
	 $(document).on("click",".delete-pmember",function(e){
	 	
	 	prow=$($(this).parents(".project_member_table_tr")).find(".information-pmember-mid");
	 	var mid =$(prow).text();
	 	$("#delete-pmember-mid").val(mid);
	 	
	 });
	 
	 // 멤버 삭제 모달에서 확인 클릭 시 멤버 삭제
	 $(document).on("click","#information-pmember-delete-btn",function(){
	 	
	 	var mid = $("#delete-pmember-mid").val();
	 	console.log(mid);
	 	
	 	$.ajax({
	 		url:"deletePmember.htm",
	 		data:{mid:mid},
	 		beforeSend:function(){
				$("#main-box").html(loadingpage);
			},
	 		dataType:"html",
	 		success:function(data){
	 			$("#main-box").empty();
	 			$("#main-box").append(data);
	 			$(".close").click();
	 		}
	 	})
	 })