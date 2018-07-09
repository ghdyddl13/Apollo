/**
 * 
 날      짜 : 2018. 6. 26.
 기      능 : sday 변경
 작성자명 : 김 정 권
 */
$( ".date_sday" ).datepicker({
    showOn: "button",
    buttonImage: "img/calendar.png",
    buttonImageOnly: true,
    dateFormat: 'yy-mm-dd',
    onSelect: function(dateText, inst) {
    	var origin_date = $('#origin_sday').val();
        var date = $(this).val();
    	var tid = $('#tidhidden').attr('value');
    	
    	var sday_year = parseInt(date.substring(0,4));
    	var sday_month = parseInt(date.substring(5,7));
    	var sday_day = parseInt(date.substring(8,10));
    	console.log('테스트 sday : ' + sday_year + '/' + sday_month + '/' + sday_day);
    	
    	var eday = $('#Task_Modal_eday').val();
    	var eday_year = parseInt(eday.substring(0,4));
    	var eday_month = parseInt(eday.substring(5,7));
    	var eday_day = parseInt(eday.substring(8,10));
    	console.log('테스트 eday : ' + eday_year + '/' + eday_month + '/' + eday_day);
   	
    	var yeartest = true;
    	
    	if(sday_year <= eday_year){
    		yeartest = false;
    		
    		if(sday_year < eday_year){
    			yeartest = false;
    		
    		}else if((sday_month <= eday_month) && (sday_year == eday_year)){
    			yeartest = false;
    			
    			if((sday_month < eday_month)){
    				yeartest = false;
    			}else if((sday_day <= eday_day) && (sday_month == eday_month)){
    				yeartest = false;
    			}else{
    				yeartest = true;
    			}
    		}else{
				yeartest = true;
			}
    	}
    	
    	if(yeartest){
    		alert('시작일을 종료일 이후로 설정할 수 없습니다');
    		$('#Task_Modal_sday').val(origin_date);
    		return;
    	}
    	
        $.ajax(
 		       {
 		           type : "post",
 		           url  : "changesdayoftask.htm",
 		           data : {
 		        	   'tid': tid,
 		        	   'sday' : date
 		           },
 		           success : function(rdata){
 		        	   
 		        	   $('#origin_sday').val(date);
 		        	   
		        	   // comment
		        	   getCommentAndMemberlist();
		        	   
		        	     $('#task_dismiss_btn').click();
		        	     
	                	 $("#main-box").empty();
						 $("#main-box").append(rdata);
						 checkCrtPage();
 		           } // end-success
 		        }); // end-ajax
        
   }
});


/**
 * 
 날      짜 : 2018. 6. 26.
 기      능 : eday 변경
 작성자명 : 김 정 권
 */
$( ".date_eday" ).datepicker({
    showOn: "button",
    buttonImage: "img/calendar.png",
    buttonImageOnly: true,
    dateFormat: 'yy-mm-dd',
    onSelect: function(dateText, inst) {
    	var origin_date = $('#origin_eday').val();
    	var date = $(this).val();
    	var tid = $('#tidhidden').attr('value');
    	
    	var sday = $('#Task_Modal_sday').val();
    	var sday_year = parseInt(sday.substring(0,4));
    	var sday_month = parseInt(sday.substring(5,7));
    	var sday_day = parseInt(sday.substring(8,10));
    	console.log('테스트 sday : ' + sday_year + '/' + sday_month + '/' + sday_day);
    	
    	var eday_year = parseInt(date.substring(0,4));
    	var eday_month = parseInt(date.substring(5,7));
    	var eday_day = parseInt(date.substring(8,10));
    	console.log('테스트 eday : ' + eday_year + '/' + eday_month + '/' + eday_day);
   	
    	var yeartest = true;
    	
    	if(sday_year <= eday_year){
    		yeartest = false;
    		
    		if(sday_year < eday_year){
    			yeartest = false;
    		
    		}else if((sday_month <= eday_month) && (sday_year == eday_year)){
    			yeartest = false;
    			
    			if((sday_month < eday_month)){
    				yeartest = false;
    			}else if((sday_day <= eday_day) && (sday_month == eday_month)){
    				yeartest = false;
    			}else{
    				yeartest = true;
    			}
    		}else{
				yeartest = true;
			}
    	}
    	
    	if(yeartest){
    		alert('종료일을 시작일 이전으로 설정할 수 없습니다');
    		$('#Task_Modal_eday').val(origin_date);
    		return;
    	}
    	
        $.ajax(
 		       {
 		           type : "post",
 		           url  : "changeedayoftask.htm",
 		           data : {
 		        	   'tid': tid,
 		        	   'eday' : date
 		           },
 		           success : function(rdata){
 		        	  $('#origin_eday').val(date);
 		        	  
		        	   // comment
		        	   getCommentAndMemberlist();
		        	   
		        	     $('#task_dismiss_btn').click();
		        	     
	                	 $("#main-box").empty();
						 $("#main-box").append(rdata);
						 checkCrtPage();
 		        	  
 		           } // end-success
 		        }); // end-ajax
        
   }
});