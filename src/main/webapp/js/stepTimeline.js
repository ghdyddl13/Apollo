
/**
 * 
 날      짜 : 2018. 6. 12.
 기      능 : 간트차트에 뿌릴 데이터 가져오는 함수
 작성자명 : 박 민 식
 */
function getGanttItems() {
	
	var ajax =	$.ajax({
		url : "step/getTimelineTasks.htm",
		dataType : "json",
		success : function(data) {
			tasks = data;
		},
		
	});
	return ajax;
};

/**
 * 
 * 날 짜 : 2018. 6. 12.
 * 기 능 : 타임라인 간트차트 생성 
 * 작성자명 : 박 민 식
 * 
 * https://github.com/frappe/gantt 참고
 * 
 * options
 * 
 *  var gantt = new Gantt("#gantt", tasks, {
    header_height: 50,
    column_width: 30,
    step: 24,
    view_modes: ['Quarter Day', 'Half Day', 'Day', 'Week', 'Month'],
    bar_height: 20,
    bar_corner_radius: 3,
    arrow_curve: 5,
    padding: 18,
    view_mode: 'Day',   
    date_format: 'YYYY-MM-DD',
    custom_popup_html: null
});
 */

function makeTimelineGantt(tasks) {
	var ganttdatabundle =[];
	// 간트차트에 들어 갈 데이터 가공 작업
	$(tasks).each(function(index,item){
		console.log(item.tstatusid);
		var start = (item.sday==null)?new Date().toISOString().substr(0,10):item.sday;
		var end = (item.eday==null)?new Date().toISOString().substr(0,10):item.eday;
		var custom_class= "timeline-tstatus-"+item.tstatusid;
		
		console.log(custom_class);
		
		
		var ganttdata={
				start:start,	
				end:end,
				name:item.tname,
				id: 'Task '+item.tid,
				custom_class: custom_class
				
		 };

		ganttdatabundle.push(ganttdata);
	})
	/// 데이터가 부족할 경우 간트차트의 row 수를 채워주기 위해 샘플 데이터를 추가해준다
	for(var count = ganttdatabundle.length; count<=15;count++){
		var sample={
				start:new Date().toISOString().substr(0,10),	
				end:new Date().toISOString().substr(0,10),
				name:" ",
				id: 'Task 0',
				custom_class: "sample-task"
				
		 };
		ganttdatabundle.push(sample);
	}
	
	$(".gantt-target").empty();
	//간트차트 객체 생성
	var gantt_chart = new Gantt(".gantt-target", ganttdatabundle, {
		
		on_click : function(task) {
			var array =task.id.split(" ");
			console.log("test")
			console.log(array[1]);
		},
		on_date_change : function(task, sday, eday) {
			var array =task.id.split(" ");
		
			////////////////// 드래그로 Task 날짜 변경하기

			var data = {tid: array[1],
						sday: sday.toLocaleDateString(),
						eday: eday.toLocaleDateString()
						}
			$.ajax({
				url: "updateTask.htm",
				data:data,
				dataType:"html",
				success:function(data){
					console.log(data);					
				}				
			})
		},
		on_progress_change : function(task, progress) {

		},
		on_view_change : function(mode) {
			console.log(mode);
		}
	});
	/// 추가한 샘플데이터 역시 드래그앤 드랍을 할 수 있기 때문에, 이를 막기 위해 삭제해준다
	$(".bar-wrapper[data-id='Task 0']").remove();
	
	
	 $('.gantt').draggable(
	          {
	             axis: "x"
	        },{
	            stop: function() {
	                
	                var gantt = $('.gantt').offset();
	                var gantt_container = $($('.gantt').parents()[0]).offset();
	                var gantt_width=$('.gantt').width();
	                var gantt_container_width=$($('.gantt').parents()[0]).width();
	                var right_border= gantt_width - gantt_container_width;
	                var diff_left= gantt_container.left -gantt.left;
	                //console.log(gantt.left)
	                //console.log(gantt_container.left)
	                //console.log("gantt_width - gantt_container_width : " +(gantt_width - gantt_container_width));
	                //console.log(" gantt_container.left - gantt.left  : " + (gantt_container.left -gantt.left ));
	                if(gantt_container.left <=gantt.left){
	                	 $('.gantt').offset({left:gantt_container.left});
	                	console.log("here");
	                }else if(diff_left >= right_border){
	                	console.log("else");
	                	$('.gantt').offset({left:gantt_container.left-right_border});
	                }
	            }
	        }
	    ) 	
	    $(".bar-group").mouseup(
	    		function(){
	    			console.log("dd");
	    			$('.gantt').draggable("enable")}
	    	
	    )
        $(".bar-group").mousedown(
        		function(){
        			$('.gantt').draggable({disabled:true})
        		}
	    	
	    )
	    		
	  
	   /* $(".bar-group").draggable(
	    	 {
	             axis: "x" ,
	        }
	    );*/
}

/**
 * 
 날   짜 : 2018. 6. 15.
 기   능 : 타임라인 task table 구성하는 함수
 작성자명 : 박 민 식
 */
function makeTimelineTable(tasks){
	var no = 1;
	$("#timeline-table > tbody").empty();
	$(tasks).each(function(index,item){
		
		//좌측 테이블에 들어갈 데이터 작업
	
		var row = jQuery("<tr>");
		var td_no = jQuery("<td>",{"text": no++,"align":"center"}) 
		var td_tname = jQuery("<td>",{"text": item.tname,"class":"timeline-task-edit","id":"t" + item.tid})
		var td_tstatus = jQuery("<td>",{"text": item.tstatus,
										"css":{"background-color":item.color,
											   "color":"white",
											   "font-size":"10px",
											   "padding-left":"0px",
											   "padding-right":"0px",
											   "text-align":"center"},
										"class":"timeline-task-status"});
		var td_assingee= jQuery("<td>",{"css":{"padding":"1px"}}).append(getTaskAssignees(item.tid,"27px"));
		$(row).append(td_no);
		$(row).append(td_tstatus);
		$(row).append(td_tname);
		$(row).append(td_assingee);
		$("#timeline-table > tbody").append(row);
	});
	var rows = $("#timeline-table > tbody").children();
	console.log(rows.length);
	
	for(var rowcount =rows.length;rowcount<=15;rowcount++){
		var row = jQuery("<tr>");
		var td1 = jQuery("<td>");
		var td2 = jQuery("<td>");
		var td3 = jQuery("<td>");
		var td4 = jQuery("<td>");
		$(row).append(td1).append(td2).append(td3).append(td4);
		$("#timeline-table > tbody").append(row);
	}
}


/**
 * 
 날   짜 : 2018. 6. 15.
 기   능 : 타임라인 Task 상태로 필터걸어주는 함수 
 작성자명 : 박 민 식
 */


$(document).on("change","#timeline-tstatus-filter",function(){
	var selectedstatus= $(this).val();
	$.when(getGanttItems()).done(function(data){
		
		var tasks = data.tasks;
		console.log(tasks);
		if(selectedstatus=="all"){
			makeTimelineTable(tasks);
			makeTimelineGantt(tasks);
		}else{
			var selecedtasks = [];
			$(tasks).each(function(index,item){
				if(item.tstatusid==$("#timeline-tstatus-filter").val()){
					selecedtasks.push(item);
				}
			})
			console.log(selecedtasks);
			if(selecedtasks.length==0){
				alert("해당 상태의 task가 없습니다.");
			}else{
				makeTimelineTable(selecedtasks);
				makeTimelineGantt(selecedtasks);
			}
		}
	})
})



