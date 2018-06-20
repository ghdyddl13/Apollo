$(function() {
   
   makeSideProjectDir();
   
   //스텝 추가 클릭시 이벤트   
   $(document).on("click","#side-insert-step",function(){ 
      var custom_menu =  $(this).parents("ul.custom-menu")[0];
      var pid =  $(custom_menu).find("input[name=pid]").val();
      var methodologyid =  $(custom_menu).find("input[name=methodologyid]").val();
      var fid =  $(custom_menu).find("input[name=fid]").val();
      
      $.ajax(
             {
                 type : "post",
                 url  : "getprojectmembers.htm",
                 data : "pid="+ pid, 
                 success : function(data){
                    $("#insert-step-methodologyid").val(methodologyid);
                    $("#insert-step-pid").val(pid);
                    $("#insert-step-fid").val(fid);
                     console.log(data.memberlist);
                     var optiondefault = jQuery("<option>",{
                        "text":"책임자를 선택하세요", //default 값 
                        "value":""
                     })
                     //해당 pid 에 참여한 멤버 불러오기
                     $('#add-step-mgr-assignee').empty().append(optiondefault);
                     $(data.memberlist).each(function (){
                        var option = jQuery("<option>",{
                           "value":this.mid,
                           "text":this.mname
                        })
                           console.log(option);
                        $('#add-step-mgr-assignee').append(option);
                     });
                    
                 } // end-success
              }); // end-ajax
   });
   
   // 스텝 생성 버튼 클릭시  alert 창 화면
   $("#insert-step-btn").click(function(){    
       if($(".add-step-name").val().trim() == ""){
         alert("스텝명을 입력하세요.");
         $(".add-step-name").focus();   
         return false;
       } else if($('#add-step-mgr-assignee').val() == ""){
          alert("책입자를 선택하세요.");
          return false;
       }
       var newstep = $('#step-add-form').serialize();
       
       $.ajax({
                type:"POST",
                url:"insertstep.htm",
                data:newstep,
                dataType:"json",
                success:function(data){
                   console.log(data);
                   if(data.stepresult > 0){
                   alert("스텝 생성이 완료되었습니다!");
                }else {
                   alert("스텝 생성에 실패했습니다");
                }   
                $('.add-step-name').val("");
                $('#insert-step-sday-id').val("");
                $('#insert-step-eday-id').val("");
                $('#step-detail').val("");
                //$('#project-insert').close();
                } // end - success
                ,error:function(error){
                   console.log(error);
                
                } // end - error
             });// end-ajax   

   
   }); //end-step   

   
   
   
   
      // 사이드바 프로젝트 우클릭 
      $(document).on("contextmenu",".side-project",function() {
         var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
         var pid = project_wrapper.id.substr(1);
         var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
         console.log("프로젝트 우 클릭시 pid " + pid);
         console.log("프로젝트 우 클릭시 methodologyid " + methodologyid);
         event.preventDefault();
         var dropdown_ul = document.createElement("ul");
         var dropdown = '<input type="hidden" name="pid" value='+pid+'>';
         dropdown += '<input type="hidden" name="methodologyid" value='+methodologyid+'>'
         dropdown +=   '<li class="dropdown-submenu"><a data-toggle="dropdown" class="dropdown-toggle">추가 <span class="glyphicon glyphicon-menu-right"></span></a>'
         dropdown += '<ul class="dropdown-menu "><li id="side-add-folder" data-toggle="modal" data-target="#add-folder">Folder추가</li><li id="side-insert-step" data-toggle="modal" data-target="#insert-step">Step추가</li></ul></li>'
         dropdown += '<li data-toggle="modal" data-target="#complete-project" id="side-complete-project">완료</li>'
         dropdown += '<li data-toggle="modal" data-target="#update-project">수정</li>'
         dropdown += '<li data-toggle="modal" data-target="#delete-project">삭제</li>'
         
         $(dropdown_ul).attr("class", "custom-menu").append(
               dropdown);
         $(dropdown_ul).css({
            top : event.pageY + "px",
            left : event.pageX + "px"
         }).appendTo("body");
         
         
      });
      
      //사이드 드롭다운에서 프로젝트 완료 버튼 클릭시 모달창 생성
      $(document).on("click","#side-complete-project",function(){
         var custom_menu =  $(this).parents("ul.custom-menu")[0];
         var pid =  $(custom_menu).find("input[name=pid]").val();
         
         $("#complete-project-pid").val(pid);
      })
      
      //프로젝트 완료 모달창에서 완료 버튼을 클릭할 경우 실행되는 비동기 함수 
      $(document).on("click","#complete-project-btn",function(){
         var pid= $(custom_menu).find("input[name=pid]").val();
         
         $.ajax({
            url:"completeProject.htm",
            data:{pid:pid},
            dataType:"json",
            success:function(data){
               
            }
         })
      })
      
      
      

      // 사이드바 폴더 우클릭  >> 추후 폴더 id(DB상 기본키)를 받아와 li 태그에 넣어주는 작업 필요
      $(document).on("contextmenu",".side-folder",function() {
         var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
         var pid = project_wrapper.id.substr(1);
         var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
         var fid= this.id.substr(1);
         console.log("폴더 우 클릭시 pid " + pid);
         console.log("폴더 우 클릭시 methodologyid " + methodologyid);
         console.log("폴더 우 클릭시 fid " + fid);
         console.log($(this).closest("input"));
         event.preventDefault();
         var dropdown_ul = document.createElement("ul");
         var dropdown = '<input type="hidden" name="pid" value='+pid+'>';
         dropdown +=   '<input type="hidden" name="methodologyid" value='+methodologyid+'>';
         dropdown +=   '<input type="hidden" name="fid" value='+fid+'>';
         dropdown +=   '<li data-toggle="modal" id="side-insert-step" data-target="#insert-step">Step 추가</li>';
         dropdown += '<li >수정</li>';
         dropdown += '<li >삭제</li>';
         $(dropdown_ul).attr("class", "custom-menu").append(dropdown);
         console.log(dropdown_ul)
         $(dropdown_ul).css({
            top : event.pageY + "px",
            left : event.pageX + "px"
         }).appendTo("body");
      });

      // 사이드바 스텝 우클릭  >> 추후 스텝 id(DB상 기본키)를 받아와 li태그에 넣어주는 작업 필요

      $(document).on("contextmenu",".side-step",function(event) {
         var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
         var pid = project_wrapper.id.substr(1);
         var sid= this.id.substr(1);
         console.log("스텝 우 클릭시 sid " + sid);
         console.log("스텝 우 클릭시 pid " + pid);
         event.preventDefault();
         var dropdown_ul = document.createElement("ul");
         var dropdown ='<input type="hidden" name="pid" value='+pid+'>';
         dropdown +=   '<input type="hidden" name="sid" value='+sid+'>';
         dropdown += '<li data-action="second">수정</li>'
         dropdown += '<li data-action="third">이동</li>'
         dropdown += '<li data-action="third">삭제</li>'
         $(dropdown_ul).attr("class", "custom-menu").append(dropdown);
         console.log(dropdown_ul)
         $(dropdown_ul).css({
            top : event.pageY + "px",
            left : event.pageX + "px"
         }).appendTo("body");

      });
      // 사이드 우클릭 메뉴 닫는 함수
      $(document).bind("mousedown", function(e) {

         // If the clicked element is not the menu
         if (!$(e.target).parents(".custom-menu").length > 0) {

            // Hide it
            $(".custom-menu").remove();
         }
      });
   

   
   
   /////////////////////////// 비동기 화면전환 - 프로젝트 /////////////////////////
      
      $(document).on("click",".side-project",function(){
         console.log("사이드바~~!~!~!~!~!~!~!~!~!");
         
         var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
         
         var pid = project_wrapper.id.substr(1);
         var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
         console.log("프로젝트 클릭시 pid " + pid);
         console.log("프로젝트 클릭시 methodologyid " + methodologyid);
         // mid도 필요하므로 일단 가정
         var mid = 'testid1';
         
          var send_data = new Array();
          send_data[0] = pid;
          send_data[1] = mid;
         
         $.ajax({
            url:"information.htm",
            data: "data=" + send_data,
            dataType:"html",
            success:function(data){
                $("#main-box").empty();
                $("#main-box").append(data);          
                
            }
         })
       });

   
      ////// 사이드바 스텝 클릭 시 
      $(document).on("click",".side-step",function(){
         var project_wrapper =  $(this).parents("div.side-project-wrapper")[0];
         var pid = project_wrapper.id.substr(1);
         var methodologyid = $(project_wrapper).children("input[name='methodologyid']").val();
         var sid= this.id.substr(1);
         $.ajax({
            url:"list.htm",
            data:{sid:sid},
            dataType:"html",
            success:function(data){
                $("#main-box").empty();
                $("#main-box").append(data);          
                
            }
         })
       });
      
      
   /* modal 창(project, step) dateficker */
      $(".sdate-img").datepicker({
          showOn: "button",
          buttonImage: "img/calendar.png",
          buttonImageOnly: true,
          dateFormat: 'yy-mm-dd'


      });
      $(".edate-img").datepicker({
          showOn: "button",
          buttonImage: "img/calendar.png",
          buttonImageOnly: true,
          dateFormat: 'yy-mm-dd',
          minDate: 0

      });   
      
   // 프로젝트 생성 버튼 클릭시 alert 창 화면       
   $("#insert-project-btn").click(function(evt){
       if($("#add-project-name").val().trim() == ""){
         alert("프로젝트명을 입력해주세요.");
         $("#add-project-name").focus();   
         return false;
       }
       var newproject = $("#project-add-form").serialize(); //serialize() : input 값이 있는 tag 들을 직렬화하여 가져온다 (ex.a=1&b=2&c=3&d=4&e=5)
       console.log(newproject);

       $.ajax({
          url:"insertproject.htm",
          data:newproject,
          type:"POST",
          dataType:"json",
          success: function(data){
             if(data.result > 0){
                alert("프로젝트 생성이 완료되었습니다!");
             }else {
                alert("프로젝트 생성에 실패했습니다");
             }   
             $('#add-project-name').val("");
             $('#insert-project-sday-id').val("");
             $('#insert-proejct-eday-id').val("");
             $('#proejct-detail').val("");
             //$('#project-insert').close();
          }

       });   
   });
   
   //사이드바에서 폴더생성 버튼을 클릭할 때
   $(document).on("click","#side-add-folder",function(){
      var custom_menu =  $(this).parents("ul.custom-menu")[0];
      var pid =  $(custom_menu).find("input[name=pid]").val();
      $("#add-folder-pid").val(pid);
      
   })
   

   //폴더 생성 클릭 버튼시 alert 창 화면
   $('#insert-folder-btn').click(function() {
      if($('#add-folder-name').val().trim() == ""){
         alert('폴더명을 입력해주세요');
         $("#add-folder-name").focus();   
         return false;
      }
      var newfolder = $('#insert-folder-form').serialize();
       console.log(newfolder);
       
       $.ajax({
                type:"POST",
                url:"insertfolder.htm",
                data:newfolder,
                dataType:"json",
                success:function(data){
                   console.log(data);
                   if(data.folderresult > 0){
                   alert("폴더 생성이 완료되었습니다!");
                }else {
                   alert("폴더 생성에 실패했습니다");
                }   
                $('.add-step-name').val("");
                //$('#add-folder').close();
                } // end - success
                ,error:function(error){
                   console.log(error);
                
                } // end - error
             });// end-ajax   
   });
   
}); // end - doc.on.ready


/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 참여한 모든 프로젝트에 속해있는 Folder의 리스트를 불러오는 함수
 작성자명 : 박 민 식
 */
function selectFolderList(pids){
   if(pids==null) return false;
   jQuery.ajaxSettings.traditional = true;
   
   var ajax =$.ajax({ // ajax는 내부적으로 Deffered와 호환이 되어 있는 함수이기 때문에 따로 선언을 해주지 않아도 된다. 
                     // 그저, ajax의 결과값을 저장한 변수를 return해주면 끝
      url:"selectFolderList.htm",
      method:"post",
      dataType:"json",
      data: {'pids':pids},
      success:function(data){
         console.log(data);
      },
      error:function(error){
         console.log(error);
      }
   })
   return ajax;
}

/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 참여한 모든 프로젝트에 속해있는 Step의 리스트를 불러오는 함수
 작성자명 : 박 민 식
 */
function selectStepList(pids){
   if(pids==null) return false;
   jQuery.ajaxSettings.traditional = true;
   var data;
   var ajax = $.ajax({
      url:"selectStepList.htm",
      method:"post",
      dataType:"json",
      data: {'pids':pids},
      success:function(data){
         console.log(data);
      },
      error:function(error){
         console.log(error);
      }
   })
   
   return ajax;
}




/**
 * 
 날      짜 : 2018. 6. 19.
 기      능 : 프로젝트 생성이 없을 떄 실행되는 함수
 작성자명 : 김 래 영
 */
function noProjectPage() {
   $.ajax({
      url:"noproject.htm",
      dataType:"html",
      success:function(data){
          $("#main-box").empty();
          $("#main-box").append(data);          
          
      }
   })
}



/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 사이드바에 프로젝트 리스트디렉토리 구조 생성함수
 작성자명 : 박 민 식
 */

function selectProjectList(){
   $("#working-project").empty();
   $("#finished-project").empty();
   $("#trash-bean").empty();
   var dfd = $.Deferred(); // 비동기 함수의 순서를 정해주기 위해(동기화) Defferred의 객체의 Promise를 활용한다.
   var pids=[];
   $.ajax({
      url:"selectProjectList.htm",
      dataType:"json",
      method:"post",
      success:function(data){
         if(data!=null){ /// 참여중인 프로젝트가 있을 경우 
            $(data.projectlist).each(function(index,el){
               pids.push(el.pid);
               var wrapper = jQuery("<div>",{"class":"side-project-wrapper","id":"p"+el.pid});
               var a = jQuery("<a>",{"class":"side-project","text":el.pname})
               var hidden = jQuery("<input>",{"type":"hidden",
                                       "name":"methodologyid",
                                       "value":el.methodologyid});
               var span = jQuery("<span>",{"class":"glyphicon glyphicon-duplicate", 
                  "data-toggle":"collapse",
                  "data-target":"#p-dir"+el.pid})
               var div = jQuery("<div>",{"class":"side-dir  collapse",
                  "id": "p-dir"+el.pid});
                  
               console.log(el.pstatuscode);
               $(a).prepend(span);
               $(wrapper).append(a).append(div).append(hidden);
               //////프로젝트의 상태에 따라 진행, 완료, 휴지통에 구분하여 append
               if(el.pstatuscode ==1){
                  $(wrapper).appendTo("#working-project");
                  
               }else if(el.pstatuscode ==2){
                  $(wrapper).appendTo("#finished-project");
               }else if(el.pstatuscode==3){
                  $(wrapper).appendTo("#trash-bean");
               }
            })
         }else{ // 참여중인 프로젝트가 없을 경우 
         
         }
         dfd.resolve(pids); // 요청을 통해 받은 결과를 resolve에 담아 리턴할 수 있다. 
      }
   });
   return dfd.promise(); // 함수가 종료될 때, Promise로 리턴해주면 
}


/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 사이드바의 구조를 만들어주는 함수 
 작성자명 : 박 민 식
 */
function makeSideProjectDir(){
   
    $.when(selectProjectList()).done(function(data){ //먼저 프로젝트의 리스트를 가져와 뿌려준 후, 
      if(data.length==0) {
         //프로젝트가 전혀 없는 경우 실행될 함수 
         noProjectPage();
      } else{         
         var pids = data;
         makeSideSubDir(pids); // 각 프로젝트의 내부 구조를 채워줄 요소들을 가져오는 함수를 실행한다. 
      }
   })
}


/**
 * 
 날   짜 : 2018. 6. 19.
 기   능 : 프로젝트 내부의 디렉토리구조를 만들어주는 함수  
 작성자명 : 박 민 식
 */   
function makeSideSubDir(pids){
   
   $.when(selectFolderList(pids), selectStepList(pids)).done(function(folders,steps){ //먼저, 프로젝트들에 속한 폴더와 스텝들의 정보를 가져온 후 디렉토리 구조를 구성

      var folders = folders[0].folderlist; //폴더 list
      var steps= steps[0].steplist; // 스텝 list
      console.log(folders);
      console.log(steps);
      ///// 먼저 폴더를 화면에 뿌려준다. 
      if(folders!=null){ //폴더가 하나라도 있다면 만들어 붙혀주세요
         
         $(folders).each(function(index,folder){
            var a =jQuery("<a>",{"class":"side-folder","text":folder.fname,"id":"f"+folder.fid});
            var span = jQuery("<span>",{"class":"glyphicon glyphicon-folder-close", 
                                 "data-toggle":"collapse",
                                 "data-target":"#f-dir"+folder.fid});
            var div = jQuery("<div>",{"class":"side-dir collapse",
                                "id": "f-dir"+folder.fid});
            console.log(folder.pid);
            $(a).prepend(span).appendTo($('#p-dir'+folder.pid));
            $(div).appendTo($('#p-dir'+folder.pid));      
         })
         
      }
      
      //// 폴더를 뿌려준 후, Step을 뿌려준다.
      if(steps!=null){
         
         $(steps).each(function(index,step){ // Step관련 태그 생성
            var a =jQuery("<a>",{
                           "class":"side-step",
                           "id":"s"+step.sid,
                           "text":step.sname
                           });
            var span = jQuery("<span>",{"class":"glyphicon glyphicon glyphicon-list-alt"})

            $(a).prepend(span);
      
            if(step.fid !=null){ // 스텝이 속해있는 폴더가 있다면 폴더 밑에 넣어주고 
               $(a).appendTo("#f-dir"+step.fid);
            }else{ //속해있는 폴더가 없다면, Default경로, 즉 프로젝트 밑으로 넣어준다.
               (step.sname=="백로그")?$(a).prependTo("#p-dir"+step.pid):$(a).appendTo("#p-dir"+step.pid);
            }
         })
      }
      
   })
}   
   
