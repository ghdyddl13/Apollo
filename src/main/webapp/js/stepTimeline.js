
/**
 * 
 날      짜 : 2018. 6. 12.
 기      능 : 간트차트에 뿌릴 데이터 가져오는 함수
 작성자명 : 박 민 식
 */
function getGanttItems(sid) {
	var tasks;
	$.ajax({
		url : "step/getTimelineTasks.htm",
		data : {
			sid : sid
		},
		async:false,
		dataType : "json",
		success : function(data) {
			tasks = data;
		},
		
	});
	return tasks;
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
		
		var start = (item.sday==null)?new Date().toISOString().substr(0,10):item.sday;
		var end = (item.eday==null)?new Date().toISOString().substr(0,10):item.eday;
		var custom_calss= (item.sday==null)?"no-day-task":"day-task";
		
		console.log(custom_calss);
		
		
		var ganttdata={
				start:start,	
				end:end,
				name:item.tname,
				id: 'Task '+item.tid,
				custom_class: custom_calss
				
		 };

		ganttdatabundle.push(ganttdata);
	})
	
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
			console.log(task, progress);
		},
		on_view_change : function(mode) {
			console.log(mode);
		}
	});
	
/*
	$('.gantt-container').draggable(
			{
				axis: "x"
			},{
				stop: function() {
					 var left = $('.gantt-container').offset().left
		                console.log(left);
		                var maxwidth = $(window).width() - $('.gantt-container').width()
		                
		                if(left > 0){
		                    $('.gantt-container').css('left','0px')
		                }else if($(window).width() > $('.gantt-container').width()){
		                    if(left < 0){ //화면크기가 div길이보다 크고 left가 0보다 작으면!!
		                        $('.gantt-container').css('left','0px')
		                    }
		                }else if($(window).width() < $('#content-md').width()){
		                    if(left < maxwidth){ //화면크기가 div길이보다 작고 left가 maxwidth보다 작으면!!
		                        $('.gantt-container').css('left',maxwidth-80)
		                    }
		                }
		                $('.gantt-container').off('mousemove')
				
				}
			}
	) */
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
		var td_no = jQuery("<td>",{"text": no++}) 
		var td_tname = jQuery("<td>",{"text": item.tname,"class":"timeline-task-edit","id":item.tid})
		var td_tstatus = jQuery("<td>",{"text": item.tstatus,
										"css":{"background-color":item.color,
											   "color":"white",
											   "font-size":"10px",
											   "padding-left":"0px",
											   "padding-right":"0px",
											   "text-align":"center"}});
		var td_assingee= jQuery("<td>",{"css":{"padding":"1px"}}).append(getTaskAssignees(item.tid,"27px"));
		$(row).append(td_no);
		$(row).append(td_tstatus);
		$(row).append(td_tname);
		$(row).append(td_assingee);
		$("#timeline-table > tbody").append(row);
	})
}


/**
 * 
 날   짜 : 2018. 6. 15.
 기   능 : 타임라인 Task 상태로 필터걸어주는 함수 
 작성자명 : 박 민 식
 */


$(document).on("change","#timeline-tstatus-filter",function(){
	
	var data = getGanttItems($("#current-sid").val());
	var tasks = data.tasks;
	if($(this).val()=="all"){
		makeTimelineTable(tasks);
		makeTimelineGantt(tasks);
	}else{
		var selecedtasks = [];
		$(tasks).each(function(index,item){
			console.log(item);
			if(item.statusid==$("#timeline-tstatus-filter").val()){
				selecedtasks.push(item);
			}
		})
		
		if(selecedtasks.length==0){
			alert("해당 상태의 task가 없습니다.");
		}else{
			makeTimelineTable(selecedtasks);
			makeTimelineGantt(selecedtasks);
		}
	}
	
})

/**
 * 
 */


