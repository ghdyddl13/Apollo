$(function() {
	
	$('#testbtn1').click(function(){
			alert('testbtn1 작동');
			
	});
	
	
	$('#testbtn2').click(function(){
		
	var ctx = document.getElementById('DonutChart').getContext('2d');
	var chart = new Chart(ctx, {
		        // The type of chart we want to create
		        type: 'line',

		        // The data for our dataset
		        data: {
		            labels: ["January", "February", "March", "April", "May", "June", "July"],
		            datasets: [{
		                label: "My First dataset",
		                backgroundColor: 'rgb(255, 99, 132)',
		                borderColor: 'rgb(255, 99, 132)',
		                data: [0, 10, 5, 2, 20, 30, 45],
		            }]
		        },

		        // Configuration options go here
		        options: {}
		    });
	});
	
	
	$('#testbtn3').click(function(){
   
	    var ctx = document.getElementById('DonutChart').getContext('2d');
	    var myDoughnutChart = new Chart(ctx, {

	        type: 'doughnut',
	        data: {
	                datasets: [{
	                    data: [10, 20, 30, 20, 50],
	                    backgroundColor: [

	                                    'rgba(190, 190, 190, 1)',

	                                    'rgba(241, 196, 15, 1)',

	                                    'rgba(244, 7, 7, 1)',

	                                    'rgba(52, 152, 219, 1)',

	                                    'rgba(46, 204, 113, 1)'

	                                ],

	                }],

	                labels:
	           [
	             '미지정','다음주 이후','이번주 까지','완료','기한 만료'
	           ]
	            },

	        options: {
	         cutoutPercentage: 50,
	         legend: {
	            display: true,
	            position: 'left',
	            labels: {
	                fontSize: 12,
	                fontFamily: 'sans-serif',
	                fontColor: '#000000',
	                fontStyle: 'bold'
	                  }
	            }
	      }
	    });
	    
	});
	
	
	$('#testbtn4').click(function(){
		var pid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "donutChart.htm",
           data : "pid="+pid,
           success : function(data){
               console.log(data);
           } 
        } 
      ) // end-ajax
        
	});
	
	$('#testbtn5').click(function(){
		var pid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "getSteps.htm",
           data : "pid="+pid,
           success : function(data){
               console.log(data);
           } 
        } 
      ) // end-ajax
        
	});
	
	$('#testbtn6').click(function(){
		var sid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "getTasksInSteps.htm",
           data : "sid="+sid,
           success : function(data){
               console.log(data);
           } 
        } 
      ) // end-ajax
        
	});
	
	$('#testbtn7').click(function(){
		var pid = 1;
		
		$.ajax(
        {
           type : "post",
           url  : "getProjectMembers.htm",
           data : "pid="+pid,
           success : function(data){
               console.log(data);
           } 
        } 
      ) // end-ajax
        
	});
	
});