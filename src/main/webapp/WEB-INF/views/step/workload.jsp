<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<link rel="stylesheet"href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="js/frappe-gantt.js"></script>
	<script>
  $(function(){
      $('[rel="popover"]').popover({
          container: 'body',
          html: true,
          content: function () {
              var clone = $($(this).data('popover-content')).clone(true).removeClass('hide');
              return clone;
          }
      }).click(function(e) {
          e.preventDefault();
      });
  });
		//console.log(gantt_chart); //gantt_chart 세부정보
	</script>
	<div>

    <a href="#" rel="popover" data-popover-content="#myPopover">My Popover</a>


    <div id="myPopover" class="hide">
    	This is a popover list:
    	<ul>
    		<li>List item 1</li>
    		<li>List item 2</li>
    		<li>List item 3</li>
    	</ul>
    </div>
	</div>
