$(function() {

	
	
	/// 프로젝트 information 페이지
	$("#information-page").click(function(evt){
		
		var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
		
		var pid = project_wrapper.id.substr(1);
		var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
		console.log("프로젝트 클릭시 pid " + pid);
		console.log("프로젝트 클릭시 methodologyid " + methodologyid);
		
		$.ajax({
			url:"information.htm",
			data: "pid=" + pid,
			dataType:"html",
			success:function(data){
				 $("#main-box").empty();
				 $("#main-box").append(data);	 		
				 
			}
		})
		
	})
	
	
	// 프로젝트 table 페이지
	$("#table-page").click(function(evt){
		$.ajax({
			url:"table.htm",
			dataType:"html",
			success:function(data){
				//console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
				
			}
		})
	})
	
	
	// 프로젝트 files 페이지
	$("#files-page").click(function(evt){

		$.ajax({
			url:"files.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	})
	
	// 스텝 list 페이지
	
	
	
	
});