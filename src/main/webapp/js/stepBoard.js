
/**
 * 
 날      짜 : 2018. 6. 22.
 기      능 : task 생성하기 위해 이름 입력하는 text 창 생성
 작 성 자 명 : 이 창 훈
 */
function addTaskView(tstatusid){
   $("#task-adder"+tstatusid).remove();
   let inputtag="<div class='board-task-adder-addmode'><input class=' board-add-task-input' id='insert-task"+tstatusid+"' name='tname' type='text' placeholder='새로운 작업을 입력하세요' onkeyup='addTask_keyup("+tstatusid+")' onfocusout='addTask_focusout("+tstatusid+")'></div>"
   $("#body-start"+tstatusid).prepend(inputtag);
  
   $("#insert-task"+tstatusid).focus().css("outline","none");
}

/**
 * 
 날      짜 : 2018. 6. 22
 기      능 : keyup이벤트로 task 생성하기
 작 성 자 명 : 이 창 훈
 */
function addTask_keyup(tstatusid){
      if(event.which==13){
    	  console.log("a");
    	  $('#insert-task'+tstatusid).blur();
      } 
}

/**
 * 
 날      짜 : 2018. 6. 22
 기      능 : focusout으로 task 생성 하기
 작 성 자 명 : 이 창 훈
 */
function addTask_focusout(tstatusid){
	  console.log("b");
   let newtask=$.trim($('#insert-task'+tstatusid).val());
   let addtag="<div class='board-task-adder' id='task-adder"+tstatusid+"' onclick='addTaskView("+tstatusid+")'>New task</div>";
   if(newtask===""){
	   $(".board-task-adder-addmode").remove();
      $("#body-start"+tstatusid).prepend(addtag);
   }else{
         $.ajax({
        	 
              url : "boardInsertTask.htm",
              data : {
                    tstatusid : tstatusid, 
                    tname : newtask
                    },
              success:function(data){
                 $("#main-box").empty();
                 $("#main-box").append(data);
                 doDraggable();
                 $("#task-adder"+tstatusid).remove();
                 let inputtag="<div class='board-task-adder-addmode'><input class=' board-add-task-input' id='insert-task"+tstatusid+"' name='tname' type='text' placeholder='새로운 작업을 입력하세요' onkeyup='addTask_keyup("+tstatusid+")' onfocusout='addTask_focusout("+tstatusid+")'></div>"
                 $("#body-start"+tstatusid).prepend(inputtag);
                 $("#insert-task"+tstatusid).focus().css("outline","none");
              }  
           })
              
   }
}
   

 
   /**
    * 
    날      짜 : 2018. 6. 18
    기      능 : sortable이용하여 task 위치 옮기기
    작 성 자 명 : 이 창 훈
    */
   function sortable(){
      var i = 1;
      var tstatus;
      var tid;
      var tname;
      $("ul.tstatuslist").sortable({
         connectWith : "ul",
         update: function(event, ui) {      
           if (i++ == 2){
           tstatusid = $(event.target).parents(".board-item-wrapper").children("p").find("input").val();
            console.log($(event.target).parents(".board-item-wrapper").children("p").find("input").val())
            tid =  ui.item[0].value;
         //   console.log("tsts : " + tstatusid);
            
    /*     $.ajax({
            url : 'boardTaskStatusUpdate.htm',
            data : { 
               tstatusid : tstatusid,
               tid : tid
               } 
         }) 
            */
            //update event 발생시 이동전 위치와 이동후 위치를 나타내주는 변수를 초기화함
            i = 1;
             
         } 
           
         
         }
      });
   }

   /**
    * 
    날      짜 : 2018. 6. 20.
    기      능 : board page 좌우(x축)으로 이동하게 하는 dragable 함수
    작 성 자 명 : 이 창 훈
    */
   function doDraggable(){

	     sortable();
	     
	    
	     $('#board-content-md').draggable(
	            {
	               axis: "x"
	          },{
	              stop: function() {
	            	  	var board = $('#board-content-md').offset();
		                var board_container = $($('#board-content-md').parents()[0]).offset();
		                var board_width=$('#board-content-md').width();
		                var board_container_width=$($('#board-content-md').parents()[0]).width();
		                console.log("board_width  : " + board_width);
		                console.log("board_container_width : " + board_container_width);
		                var right_border= board_width - board_container_width;
		                var diff_left= board_container.left -board.left;
		                console.log("right_border : " + right_border);
		                console.log("diff_left : " + diff_left);
		                console.log("board_container.left : " + board_container.left);
		                console.log("board.left :" + board.left)
	                	console.log("be pa right : " +( board_container.left + board_container_width));
	                	console.log("be board right : " + (board.left + board_width));
		                if(board_container.left <=board.left){
		                	 $('#board-content-md').offset({left:board_container.left});
		                	  console.log("af board_container.left : " + board_container.left);
		                	  console.log(" af board.left :" + board.left)
		                }else if(diff_left >= right_border){
		                	$('#board-content-md').offset({left:(board_container.left-right_border)});
		                	console.log("pa right : " +( board_container.left + board_container_width));
		                	console.log("board right : " + (board.left + board_width));
		                }
	          
	              }
	          }
	      ) 
   }
   
  
      
     