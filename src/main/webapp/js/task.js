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
        var date = $(this).val();
    	var tid = $('#tidhidden').attr('value');
        
        $.ajax(
 		       {
 		           type : "post",
 		           url  : "changesdayoftask.htm",
 		           data : {
 		        	   'tid': tid,
 		        	   'sday' : date
 		           },
 		           success : function(rdata){
 		        	   
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
    	
    	
    	if(eday_year < sday_year){
    		alert('종료일을 시작일 이전으로 설정할 수 없습니다');
    		return;
    	}
    	
    	if(eday_month < sday_month) {
			alert('종료일을 시작일 이전으로 설정할 수 없습니다');
			return;
		}
    	
    	if(eday_day < sday_day) {
			alert('종료일을 시작일 이전으로 설정할 수 없습니다');
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
 		        	   
 		           } // end-success
 		        }); // end-ajax
        
   }
});






