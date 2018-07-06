<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="main-body-container">
    <div class="main-body-twopannel">
      <div class="main-body-twopannel-left">
        <div class="main-body-twopannel-left-wrapper">
          <div class="main-body-twopannel-left-header">
            <div class="main-body-twopannel-left-header-wrapper">
              <div class="main-body-twopannel-left-header-title-textwrapper">
                <div class="main-body-twopannel-left-header-title">
                  <span class="main-body-twopannel-left-header-title-text">Report</span>
                </div>
              </div>
            </div>
          </div>
          <div class="main-body-twopannel-left-body">
            <div class="main-body-twopannel-left-body-wrapper">
            	<div id="background_div">
					<div class="row">
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
						<div class="col-lg-1" >
						</div>
					
						<!-- 진행중인 task -->
						<div class="col-lg-2 btn_div report_progress" data-toggle="modal" data-target="#report_info_modal" id="report_progress">
							<br>진행중인 Task<br>
							<img style="margin-right: 10px" class="jpg_images" src="img/report/report_progress_stop.jpg">
							<img class="gif_images" src="img/report/report_progress.gif">
						</div>
						
						<!-- Task 상태별 현황 -->
						<div class="col-lg-2 btn_div report_status" data-toggle="modal" data-target="#report_info_modal" id="report_status">
							<br>Task 상태별 현황<br>
							<img style="margin-right: 10px" class="jpg_images" src="img/report/report_status_stop.jpg">
							<img class="gif_images" src="img/report/report_status.gif">
						</div>
						
						<!-- 마감기한 지난 Task -->
						<div class="col-lg-2 btn_div report_expired" data-toggle="modal" data-target="#report_info_modal" id="report_expired">
							<br>마감기한이 지난 Task<br>
							<img style="margin-right: 10px" class="jpg_images" src="img/report/report_expired_stop.jpg">
							<img class="gif_images" src="img/report/report_expired.gif">
						</div>
						
						<!-- 마감기한이 임박한 Task -->
						<div class="col-lg-2 btn_div report_drawnear" data-toggle="modal" data-target="#report_info_modal" id="report_drawnear">
							<br>마감기한이 임박한 Task<br>
							<img style="margin-right: 10px" class="jpg_images" src="img/report/report_drawnear_stop.jpg">
							<img class="gif_images" src="img/report/report_drawnear.gif">
						</div>
						
						<!-- 담당자 미정 Task -->
						<div class="col-lg-2 btn_div report_unassigned" data-toggle="modal" data-target="#report_info_modal" id="report_unassigned">
							<br>담당자 미정 Task<br>
							<img style="margin-right: 10px" class="jpg_images" src="img/report/report_unassigned_stop.jpg">
							<img class="gif_images" src="img/report/report_unassigned.gif">
						</div>
						
						<div class="col-lg-1" >
						</div>
						
					</div> <!-- end/row -->
					
				</div> <!-- end/background_div -->
            </div>
          </div>
        </div>
      </div>
    </div>
</div>






