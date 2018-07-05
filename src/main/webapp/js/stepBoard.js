
/**
 * 
 날      짜 : 2018. 6. 22.
 기      능 : task 생성하기 위해 이름 입력하는 text 창 생성
 작 성 자 명 : 이 창 훈
 */
function addTaskView(tstatusid){
   $("#task-adder"+tstatusid).remove();
   let inputtag="<div class='board-task-adder-addmode'><input class='form-control' id='insert-task"+tstatusid+"' name='tname' type='text' placeholder='새로운 작업을 입력하세요' onkeyup='addTask_keyup("+tstatusid+")' onfocusout='addTask_focusout("+tstatusid+")'></div>"
    $("#body-start"+tstatusid).prepend(inputtag);
    $("#insert-task"+tstatusid).focus();
}

/**
 * 
 날      짜 : 2018. 6. 22
 기      능 : keyup이벤트로 task 생성하기
 작 성 자 명 : 이 창 훈
 */
function addTask_keyup(tstatusid){
   let addtag="<div class='board-task-adder' id='task-adder"+tstatusid+"' onclick='addTaskView("+tstatusid+")'>New task</div>";
      if(event.which==13){
         let newtask=$.trim($('#insert-task'+tstatusid).val());
      
        if(newtask===""){
             $(".board-task-adder-addmode").remove();
             $("#body-start"+tstatusid).prepend(addtag);
         }else{
            console.log(newtask);
            $('#insert-task'+tstatusid).val('');
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
                   }  
                })
         } 
      } 
}

/**
 * 
 날      짜 : 2018. 6. 22
 기      능 : focusout으로 task 생성 하기
 작 성 자 명 : 이 창 훈
 */
function addTask_focusout(tstatusid){
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
              }  
           })
              
   }
}
   
   //board에서 나오는 status 목록 width 크기 지정하는 함수
   function autoWidth() {
      var width = (($('.tstatuslist').length * 310)
            + "px");
      $('#board-content-md').css("width", width)
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
      $("ul.tstatuslist").sortable({
         connectWith : "ul",
         update: function(event, ui) {      
           if (i++ == 2){
            tstatusid =  $(this).closest('div')[0].childNodes[1].childNodes[1].value;
            tid =  ui.item[0].value;

         $.ajax({
            url : 'boardTaskStatusUpdate.htm',
            data : { 
               tstatusid : tstatusid,
               tid : tid
                  } 
         }) 
            
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
	   console.log("test")
	   autoWidth();
	     sortable();
	    $('#board-content-md').draggable(
	            {
	               axis: "x"
	          },{
	              stop: function() {
	                  
	                  var left = $('#board-content-md')[0].offsetLeft
	                  console.log("left : " + left);
	                  var maxwidth = $(window).width() - $('#board-content-md').width()
	                  if(left > 0){
	                      $('#board-content-md').css('left','0px')
	                     
	                  }else if($(window).width() > $('#board-content-md').width()){
	                      if(left < 0){ //화면크기가 div길이보다 크고 left가 0보다 작으면!!
	                          $('#board-content-md').css('left','0px')
	                      }
	                     }else if($(window).width() < $('#board-content-md').width()){
	                      if(left < maxwidth){ //화면크기가 div길이보다 작고 left가 maxwidth보다 작으면!!
	                          $('#board-content-md').css('left',maxwidth-80)
	                      }
	                  }
	                  $('#board-content-md').off('mousemove')
	              }
	          }
	      ) 
   }
   

      
     