<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<script type="text/javascript" src="js/header.js"></script>


<style>
.search-bar-imo{
  margin-right: 3px;
  color:white;

}

.search-bar-container{
  height: 100%;
}
.search-nav-head{
	height: 50px;
	min-height:50px;
	max-height:50px;
	  display: flex;
  align-items: center;
  white-space: nowrap;
  border-bottom: 1px solid rgba(0, 0, 0, 0.16)
}

#header-right-wrapper{
  display: flex;
  align-items: center;
  white-space: nowrap;
}

#open-right-nav{
  background-color: transparent;
  border-top: 0px;
  border-left: 0px;
  border-right: 0px;
  border-bottom: 1px  ;
  color: white;


}
#search-bar{
	border: none;
	width:100%
}

#search-bar:focus{
	outline: none;

}
.search-nav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1031;
    top: 0;
    right: 0;
    background-color: white;
  	bottom:0;
  	display:flex;
  	flex-direction: column;
    transition: 0.5s;
    box-shadow: 0 2px 8px 0 rgba(0, 0, 0, 0.16), 0 0 1px 0 rgba(0, 0, 0, 0.16);
}

#search-content-box{
	padding-top: 15px;
	padding-bottom:10px;
	
	
	
	}

.search-nav-body{
	flex-glow:1;
	overflow: auto;
	display:flex;
	flex-direction: column;
	bottom:0;
}

.search-item-box{
	display: flex;
 	align-items: center;
  	white-space: nowrap;
	width:100%;
	box-sizing: border-box;
	height: 48px;
	padding-left:10px;
	margin: 0
}

.search-item-box:hover{
	background-color: rgba(0, 0, 0, 0.04);
	cursor:pointer
}

.search-result-bundle{
	margin-bottom: 20px
}

.search-item-left{
	padding:0;
	padding-left:10px
}



.search-result-text{
	margin-left: 15px;
}

.search-info-div{
	font-size:11px
}
.search-task-pname{
	color:rgba(0, 0, 0, 0.5);
	margin-left:10px;
	padding: 1px 5px 1px 5px;

	border-radius: 5px;
	border: 0.5px solid rgba(0, 0, 0, 0.04);
}
</style>

<script>

/**
 * 최근 생성된 혹은 변경된 task의 10건을 가져오는 함수 
 */
	function getRecentTasks(){
		var ajax= $.ajax({
			url:"getRecentTasks.htm",
			type:"post",
			dataType:"json",
			success:function(data){
				
			}
		});
		return ajax;
	};

	$(document).on("focus","#open-right-nav",function(){
	    document.getElementById("search-nav").style.width = $("#header-right-wrapper").width()+10+"px";
	    $("#search-bar").focus();
	   $.when(getRecentTasks()).done(function(data){
		   console.log(data.rcttasks);
		   var p = jQuery("<p>",{"class":"search-result-text","text":"Recent Tasks"});
		   var div = jQuery("<div>");
		   $(div).append(p);
		   $(data.rcttasks).each(function(index,el){
			  $(makeSearchTaskDiv(el)).appendTo(div);
		   })// each
		   $(div).appendTo($("#search-content-box"));  
		   
	   });//done
	});// end

	

	var typingTimer;
	var doneTypingInterval = 500;
	$(document).on("input","#search-bar" ,function () {
		
		if(getBytes($("#search-bar").val()) <2 ||$("#search-bar").val() == " ") return false; 
	    clearTimeout(typingTimer);
	    typingTimer = setTimeout(function() {
    	$.when(getSearchResult()).done(function(data){
    			console.log(data);
    			$("#search-content-box").empty();
    			var searchsteps = data.searchsteps;
    			var searchprojects = data.searchprojects;
    			var searchtasks = data.searchtasks;
    			var searchmembers = data.searchmembers;
    			
    			if(searchprojects.length != 0) $(resultSearchProject(searchprojects)).appendTo($("#search-content-box"));  
    			if(searchsteps.length != 0) $(resultSearchStep(searchsteps)).appendTo($("#search-content-box")); 
    			if(searchtasks.length != 0) $(resultSearchTask(searchtasks)).appendTo($("#search-content-box"));  
    			if(searchmembers.length != 0) $(resultSearchMember(searchmembers)).appendTo($("#search-content-box"));  
    		})// end when
   		}, 2000); // end Timeout
    
	}); // end function

	function getBytes(text) {
	    var contents = text;
	    var str_character;
	    var int_char_count;
	    var int_contents_length;
	 
	    int_char_count = 0;
	    int_contents_length = contents.length;
	 
	    for (k = 0; k < int_contents_length; k++) {
	        str_character = contents.charAt(k);
	        if (escape(str_character).length > 4)
	            int_char_count += 2;
	        else
	            int_char_count++;
	    }
	    return int_char_count;
	}

	function getSearchResult(){
		var input = $("#search-bar").val();
		var ajax = $.ajax({
			url:"getSearchResult.htm",
			type:"post",
			data:{input:input},
			dataType:"json",
			success:function(data){
				
			}
		})
		return ajax;
	}
	
	
	

	//검색된 프로젝트 검색 결과 만들어주는 함수
 function makeSearchProjectDiv(project){
	 var pstatus;
	 var pstatuscolor;
	 switch (project.pstatuscode){
		 case 1: 
			 pstatus= "진행중인 프로젝트";
			 pstatuscolor= "green";
			 break;
		 case 2:
			 pstatus= "완료된 프로젝트";
			 pstatuscolor= "blue";
			 break;
		 case 3:	 
			 pstatus= "휴지통";
			 pstatuscolor= "red";
			 break;
	 }
	 var methodology;
	 switch (project.methodologyid){
	 case 1: 
		 methodology="W";
		 break;
	 case 2:
		 methodology="A";
		 break;
	 case 3:	 
		 methodology="C";
		 break;
 	}
	 var div =jQuery("<div>",{"class":"search-item-box row" ,"id":"srch-p"+project.pid});  
	 var projecticon= jQuery("<div>",{"class":"search-item-left col-sm-2"});
	 var span = jQuery("<span>",{"text":methodology, "css":{"font-size":"35px"}});
	 $(projecticon).append(span);
	 var project_info_div= jQuery("<div>",{"class":" col-sm-10 container-fluid"});
	 var project_name_div= jQuery("<div>",{"class":"search-info-div row ","text":project.pname});
	 var project_status_div= jQuery("<div>",{"class":"search-info-div row","text":pstatus,"css":{"color":pstatuscolor}});
	 $(project_info_div).append(project_name_div,project_status_div)
	 $(div).append(projecticon,project_info_div);
	 return div;
 }
	

function resultSearchProject(projectlist){
	   var p = jQuery("<p>",{"class":"search-result-text","text":"Projects ("+projectlist.length+")"});
	   var resulsSearchproject = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resulsSearchproject).append(p);	
	   $(projectlist).each(function(index,project){
		   var projectdiv =  makeSearchProjectDiv(project);
		   $(projectdiv).appendTo(resulsSearchproject);
	   })
	return resulsSearchproject;   
}	
	
	
 function makeSearchStepDiv(step){
	 var div =jQuery("<div>",{"class":"search-item-box row" ,"id":"srch-s"+step.sid});  
	 var stepicon= jQuery("<div>",{"class":"search-item-left col-sm-2"});
	 var span = jQuery("<span>",{"text":"S", "css":{"font-size":"35px"}});
	 $(stepicon).append(span);
	 var step_info_div= jQuery("<div>",{"class":" col-sm-10 container-fluid"});
	 var step_name_div= jQuery("<div>",{"class":"search-info-div row ","text":step.sname});
	 var step_dir_div= jQuery("<div>",{"class":"search-info-div row "});
	 var pname= jQuery("<span>",{"class":"search-task-pname","text":$($("#p"+step.pid).find("a")[0]).text()});
	 $(step_dir_div).append(pname);
	 if(step.fid!=null){
		 var fname= jQuery("<span>",{"class":"search-task-pname","text":$("#f"+step.fid).text()});
		 $(step_dir_div).append(" > ",fname);
	 }
	 $(step_info_div).append(step_name_div,step_dir_div)
	 $(div).append(stepicon,step_info_div);
	 return div;
 
 }
 
 function resultSearchStep(steplist){
	  var p = jQuery("<p>",{"class":"search-result-text","text":"Steps ("+steplist.length+")"});
	   var resultsearchStep = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resultsearchStep).append(p);	
	   $(steplist).each(function(index,step){
		   var stepdiv =  makeSearchStepDiv(step);
		   $(stepdiv).appendTo(resultsearchStep);
	   })
	return resultsearchStep;   
	 
 }
 
 
 function makeSearchMemberDiv(member){
	 var div =jQuery("<div>",{"class":"search-item-box row" ,"id":"srch-m"+member.mid});  
	 var memberimg= jQuery("<div>",{"class":"search-item-left col-sm-2"});
	 $(makeProfileIcon(member,"35")).appendTo(memberimg);
	 var member_info_div= jQuery("<div>",{"class":" col-sm-10 container-fluid"});
	 var member_name_div= jQuery("<div>",{"class":"search-info-div row ","text":member.mname});
	 var member_email_div= jQuery("<div>",{"class":"search-info-div row","text":member.mid});
	 $(member_info_div).append(member_name_div,member_email_div);
	 $(div).append(memberimg,member_info_div);
	 return div;
 }
 
 function resultSearchMember(memberlist){
	  var p = jQuery("<p>",{"class":"search-result-text","text":"Members ("+memberlist.length+")"});
	   var resultsearchmember = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resultsearchmember).append(p);	
	   $(memberlist).each(function(index,member){
		   var memberdiv =  makeSearchMemberDiv(member);
		   $(memberdiv).appendTo(resultsearchmember);
	   })
	return resultsearchmember;   
}

	

 
	/// 검색을 통해 탐색된 태스크의 정보를 담은 div생성
 function makeSearchTaskDiv(task){
		 var div =jQuery("<div>",{"class":"search-item-box row" ,"id":"srch-t"+task.tid});  
		 var assignee_div= jQuery("<div>",{"class":"search-item-left col-sm-2"});
		 makeProfileIcon(task,'35').appendTo(assignee_div);
		 var task_info_div= jQuery("<div>",{"class":" col-sm-10 container-fluid"});
		 var task_name_div= jQuery("<div>",{"class":"search-info-div row ","text":task.tname});
		 var task_status_div= jQuery("<div>",{"class":"search-info-div row","text":task.tstatus,"css":{"color":task.color}});
		 var pname= jQuery("<span>",{"class":"search-task-pname","text":$($("#p"+task.pid).find("a")[0]).text()})
		 $( task_status_div).append(pname);
		 $(task_info_div).append( task_name_div,task_status_div);
		 $(div).append(assignee_div,task_info_div);
		return div;
	}
	
 function resultSearchTask(tasklist){
	  var p = jQuery("<p>",{"class":"search-result-text","text":"Tasks ("+tasklist.length+")"});
	   var resultsearchtask = jQuery("<div>",{"class":"search-result-bundle"});
	   $(resultsearchtask).append(p);	
	   $(tasklist).each(function(index,task){
		   var taskdiv =  makeSearchTaskDiv(task);
		   $(taskdiv).appendTo(resultsearchtask);
	   })
	return resultsearchtask;   
}	
	
</script>
<nav class="navbar navbar-default navbar-fixed-top" id="header">

	<div class="container-fluid">
		<div class="navbar-header container-fluid">
			<a href="index.htm"><img id="gohome" class="header_logo"
				src="img/apollo_logo.png" /></a>
		</div>
		<ul class="nav navbar-nav ">
			<li class="nav-item"><a class="nav-link header-menu"
				id="inbox-page" style="color: white">Inbox</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="myWork-page" style="color: white">My work</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="starredTask-page" style="color: white">Starred Task</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="report-page" style="color: white">Report</a></li>
			<li class="nav-item"><a class="nav-link header-menu"
				id="stream-page" style="color: white">Stream</a></li>
		</ul>
		<ul id = "header-right-wrapper" class="nav navbar-nav navbar-right">
			<div class="container-fluid search-bar-container">
  	    		<span  class="search-bar-imo glyphicon glyphicon-search"></span>
          		<input id ="open-right-nav" type="text">
       		</div>
			<li class="nav-item"><a class="nav-link" href="#"
				style="color: white"><span class="glyphicon glyphicon-user"
					id="loginimg"></span>김래영님</a>
			</li>
		</ul>
	</div>
</nav>


<div id="search-nav" class="search-nav">
  <div class="container-fluid search-nav-head row">
  	<div class="col-sm-2">
		<span  class=" glyphicon glyphicon-search"></span>
  	</div>
  	<div class="col-sm-10 container-fluid">
		<input id ="search-bar" type="text">
  	</div>
  </div>
  <div class= "search-nav-body">
	  <div id="search-content-box">
	  </div>
  </div>
</div>

