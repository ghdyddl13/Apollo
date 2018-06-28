
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
	            },
	        
	        }
	    ) 	
	    
	    //// 태스크 사이즈 조절 시, 간트차트의 드래그기능 일시 중지
        $(".handle").mousedown(
        		function(event){
        			$('.gantt').draggable({disabled:true})
        		}
	    	
	    )
	    ///다시 간트차트 내부를 클릭하게 되면 간트차트 드래그 가능하게 만듦
	      $(".grid-row").mousedown(
        		function(event){
        			$('.gantt').draggable({disabled:false});
        		}
	    	
	    )
	    		
	    //// 간트차트에 드래그를 입힘으로써 후순위가 되어 버린 태스크의 드래그 기능을 다시 살리기 위한 코드
	    $(".bar-group").draggable(
	    	 {
	             axis: "x" ,
	        }
	    );
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
		var td_tname = jQuery("<td>",{"text": item.tname,"class":"timeline-task-edit Task_RUD_Modal",
														 "id":"t" + item.tid,
														 "data-toggle" : "modal",
														 "data-target" : "#Task_RUD_Modal"
														 })
		var td_tstatus = jQuery("<td>",{"text": item.tstatus,
										"css":{
											   "color":item.color,
											   "font-size":"12px",
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
 기   능 : 타임라인 Task 상태로 필터걸어주기
 작성자명 : 박 민 식
 */


$(document).on("change","#timeline-tstatus-filter",function(){
	var mid = $("#timeline-assignee-filter").val();
	assigneeFilter(mid);
})



/**
 * 
 날   짜 : 2018. 6. 24.
 기   능 : 타임라인 태스크 담당자로 필터걸어주기
 작성자명 : 박 민 식
 */

$(document).on("change","#timeline-assignee-filter",function(){
	var mid = $(this).val();
	assigneeFilter(mid);
})	

/**
 * 
 날   짜 : 2018. 6. 24.
 기   능 : assigneeFilter를 적용하는 함수
 작성자명 : 박 민 식
 */
function assigneeFilter(mid){
	if(mid=="all"){ // 모든 태스크
		$.when(getGanttItems()).done(function(ajax){
			makeFilterdTimeline(ajax.tasks);	
		});
	}else if(mid=="No"){ //어사인되지 않은 태스크 
		$.when(selectNotAssignedTasks()).done(function(ajax){
			makeFilterdTimeline(ajax.tasks);	
		});
	}else{ // 특정 mid가 할당받고있는 태스크
	
	$.when(selectTasksByMidAndSid(mid)).done(function(ajax){	
		makeFilterdTimeline(ajax.tasksbymid);
	});
};
		
		
};


/**
 * 
 날   짜 : 2018. 6. 24.
 기   능 : 특정 멤버가 할당받은 task를 가져오는 함수
 작성자명 : 박 민 식
 */
function selectTasksByMidAndSid(mid){
	var ajax = $.ajax({
		url:"step/selectTasksByMidAndSid.htm",
		type:"post",
		data:{mid:mid},
		dataType:"json",
		success:function(data){
			console.log("selectTasksByMidAndSid 후")
			console.log(data);
		}
	});
	return ajax;
};

/**
 * 
 날   짜 : 2018. 6. 28.
 기   능 : 어사인 되지 않은 task를 가져오는 함수
 작성자명 : 박 민 식
 */
function selectNotAssignedTasks(){
	var ajax = $.ajax({
		url:"step/selectNotAssignedTasksBySid.htm",
		type:"post",
		dataType:"json",
		success:function(data){
			console.log("selectNotAssignedTasks 후")
			console.log(data);
		}
	});
	return ajax;
}


/**
 * 
 날   짜 : 2018. 6. 24.
 기   능 : 상태 및 담당자 필터를 통해 새로운 간트차트를 생성해주는 함수
 작성자명 : 박 민 식
 */
function makeFilterdTimeline(tasks){
	var selectedstatus= $("#timeline-tstatus-filter").val();
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
}

$(function(){
	
	$("#timeline").on(function(){
		console.log("만들어짐");
	})
})








