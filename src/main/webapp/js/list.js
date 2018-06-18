$(function() {
   
	alert('스크립트 정상작동함');
	
	$(document).on("click","#testmodal_btn",function(){
		
		$(task_test_area).empty();
		$(task_test_area).append("append 되었습니다");
		
		var testarr = [];
		
		$.ajax(
		        {
		           type : "post",
				   url:"donutChart.htm",
				   data: "data=" + send_data,
		           success : function(rdata){
		        	  
		        	   $(rdata.unassignedtasklist).each(function(index, el) {
		        		   testarr.push(el.tid);
	            		});
		        	   
		        	   $(testarr).each(function(index, el) {
		        		   $(db_area).append(teatarr[index] + '<br>');
	            		});
		              
		           		} // end - success
			        }
			       );
			});
	
}); // end-document.onready
