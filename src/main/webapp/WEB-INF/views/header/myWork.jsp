<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	
	<div class ="main-container container">
		<div class ="main-section-left" id="left">
			<div class ="main-section-header">
				<div>TODAY</div>
				<div>Jun 12</div>
			</div>
			<div class="main-section-task"> TASK1</div>
		</div>
		<div class ="main-section-center" id="center"></div>
		<div class ="main-section-right" id="right">
			<div class ="main-section-header">
				<div>THIS WEEK</div>
				<div>Jun 12</div>
			</div>
			<div class ="main-section-header">
				<div>NEXT WEEK</div>
				<div>Jun 12</div>
			</div>
			<div class ="main-section-header">
				<div>LATER</div>
				<div>Jun 12</div>
			</div>
		</div>
	
	</div>
<script type="text/javascript">
	$(function() {
		/////////////////////////////DIV TAG 이동시키기 /////////////////////////////////////
		var startpos, diffpos = 0, range = 400;
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
					document.getElementById("left").style.width = width -20 - diffpos + "px";
					document.getElementById("right").style.width = width - 20+ diffpos + "px";
				}
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////
	});
</script>