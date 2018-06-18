<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="main-container container">
  <div class="main-section-left" id="left">
    <div class="week-wrapper" id="today">

      <div class="main-section-header">
        <div class="week-section" id="today">TODAY</div>
        <div class="date-section">Jun 12</div>
        <div class="week-count-section" id="todaycount"style="float:right">3</div>
      </div>

      <div class="main-section-task">
        <div class = "task-image-section"></div>
        <div class ="task-date-section task-date-overdue"style="float:right">Jun 02</div>
        <div class = "task-description-section">
          <div class ="task-status">new</div>
          <div class ="task-name-section">task1</div>
          <div class ="task-detail-section">I love you so much</div>
        </div>
        <div class="task-step-section">
          <div class="div-step-section">
            <span class="span-step-section">Completed Sprint</span>
          </div>
          <div class="div-step-section">
            <span class="span-step-section">backlog</span>
          </div>
        </div>

      </div>

      <div class="main-section-task">
        <div class = "task-image-section"></div>
        <div class = "task-description-section">
          <div class ="task-name-section">task1</div>
          <div class ="task-detail-section">I love you so much</div>
        </div>
        <div class = "task-date-section"style="float:right">Jun 12</div>
        <div class="task-step-section">
        </div>
      </div>

    </div>
  </div>
  <div class="main-section-center" id="center"></div>
  <div class="main-section-right" id="right">
    <div class="week-wrapper" id="thisweek">
      <div class="main-section-header">
        <div class="week-section">THIS WEEK</div>
        <div class="date-section">Jun 10 - Jun 16</div>
        <div class="week-count-section" id="todaycount"style="float:right">2</div>
      </div>
    </div>
    <div class="week-wrapper" id="nextweek">
      <div class="main-section-header">
        <div class="week-section">NEXT WEEK</div>
        <div class="date-section">Jun 17 - Jun 23</div>
        <div class="week-count-section" id="todaycount"style="float:right">1</div>
      </div>

    </div>
    <div class="week-wrapper" id="later">
      <div class="main-section-header">
        <div class="week-section">LATER</div>
        <div class="date-section" >After Jun 23</div>
        <div class="week-count-section" id="todaycount"style="float:right">0</div>
      </div>
    </div>
  </div>
</div>
  <script type="text/javascript">
  $(function() {
      $(".main-section-task").click(function() {
        $(".main-section-task").css("background-color", '#a8c6ed');
      });
      ///////////////////////////서버시간 추출해오는 함수////////////////////////////
      /*
       날      짜 : 2018. 6. 13.
       기      능 : 서버시각 추출을 위한 함수 / 호출 형식은 YYYYMMDDHHMMSS / 활용 예시 : var nowdate = serverToday();
       작성자명 : 김 정 권
       */
      function serverToday(){ 
           var xmlHttp; 
           //분기하지 않으면 IE에서만 작동된다. 
           if (window.XMLHttpRequest) { // IE 7.0 이상, 크롬, 파이어폭스일 경우 분기 
             xmlHttp = new XMLHttpRequest(); 
             xmlHttp.open('HEAD',window.location.href.toString(),false);
             xmlHttp.setRequestHeader("Content-Type", "text/html"); 
             xmlHttp.send(''); 
           }else if (window.ActiveXObject) { 
             xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
             xmlHttp.open('HEAD',window.location.href.toString(),false);
             xmlHttp.setRequestHeader("Content-Type", "text/html"); 
             xmlHttp.send(''); 
           } 
           var st = xmlHttp.getResponseHeader("Date");
           var curDate = new Date(st); 
           var curDateFmt; var year = curDate.getFullYear(); 
           var month = curDate.getMonth()+1; 
           var day = curDate.getDate(); 
           var hours = curDate.getHours(); 
           var minutes = curDate.getMinutes(); 
           if(parseInt(month) < 10){ 
             month = 0 + "" + month; 
           } 
           if(parseInt(day) < 10){ 
             day = 0 + "" + day; 
           } 
           if(parseInt(hours) < 10){ 
             hours = 0 + "" + hours; 
           } if(parseInt(minutes) < 10){ 
             minutes = 0 + "" + minutes; 
           } 
           curDateFmt = parseInt(year + "" + month + "" + day + "" + hours + "" + minutes); 
           return curDateFmt; 
         } // end - serverToday()
         
         
      /////////////////////////////DIV TAG 이동시키기 /////////////////////////////////////
      var startpos, diffpos = 0, range = 540;
      var isEnable = false;

      document.getElementById("center").onmousedown = on_mouse_down;
      document.onmouseup = on_mouse_up;
      document.onmousemove = on_mouse_move;
      function on_mouse_down(e) {
        startpos = event.clientX + diffpos;
        isEnable = true;
        return false;
      }

      function on_mouse_up(e) {
        isEnable = false;
        return false;
      }

      function on_mouse_move(e) {
        if (isEnable) {
          pos = event.clientX;
          diffpos = startpos - pos;
          var width = (window.innerWidth-250) / 2;
          if (diffpos > -(width - range) && diffpos < (width - range)) {
            document.getElementById("left").style.width = width -25 - diffpos + "px";
            document.getElementById("right").style.width = width - 25+ diffpos + "px";
          }
        }
      }

      /////////////////////////////////////////////////////////////////////////////////////
      ////////////////////ajax를 이용해 처음 데이터를 뿌려주는 함수////////////////////////
/*        $.ajax({
         type:"POST",
         url:"myWork.htm",
         dataType:"JSON",
         success:function(data){
           console.log(data);
         }
       }) */
      ///////////////////////////////////////////////////////////////////////////////////


      });
  </script>