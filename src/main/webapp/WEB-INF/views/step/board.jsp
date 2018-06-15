<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<h3>보드페이지</h3>


<jsp:include page="/WEB-INF/views/inc/stepInsideHeader.jsp"></jsp:include>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
#sortable1, #sortable2, #sortable3 {
	list-style-type: none;
	margin: 0;
	float: left;
	margin-right: 10px;
	background: #eee;
	padding: 5px;
	width: 143px;
}

#sortable1 li, #sortable2 li, #sortable3 li {
	margin: 5px;
	padding: 5px;
	font-size: 1.2em;
	width: 120px;
}

/* #content-md {
	width : 100%;
	overflow-x: scroll;  

}

#test{
	overflow: auto
} */


</style>
<script>
	function autoWidth() {
		var width = (($('.droptrue').length + $('.dropfalse').length) * 160)
				+ "px";
		console.log(width)
		$('#content-md').css("width", width)
	}

	$(function() {

		autoWidth()
		$("ul.droptrue").sortable({
			connectWith : "ul"
		});


		$("#sortable1").disableSelection();
		
		
		 $('#content-md').draggable(
          {
             axis: "x"
        },{
            stop: function() {
                
                var left = $('#content-md')[0].offsetLeft
                console.log(left);
                var maxwidth = $(window).width() - $('#content-md').width()
                
                if(left > 0){
                    $('#content-md').css('left','0px')
                }else if($(window).width() > $('#content-md').width()){
                    if(left < 0){ //화면크기가 div길이보다 크고 left가 0보다 작으면!!
                        $('#content-md').css('left','0px')
                    }
                }else if($(window).width() < $('#content-md').width()){
                    if(left < maxwidth){ //화면크기가 div길이보다 작고 left가 maxwidth보다 작으면!!
                        $('#content-md').css('left',maxwidth-80)
                    }
                }
                $('#content-md').off('mousemove')
            }
        }
    ) 

	});
</script>
</head>
<body>

	<hr>
	<div id="content-md" >
		<div style="overflow-x:scroll; width:100%; height:100%; white-space:nowrap;">
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>

			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">Item 5</li>
			</ul>
			<ul id="sortable1" class="droptrue">

				<li class="ui-state-default">Can be dropped..</li>
				<li class="ui-state-default">..on an empty list</li>
				<li class="ui-state-default">Item 3</li>
				<li class="ui-state-default">Item 4</li>
				<li class="ui-state-default">끝</li>
			</ul>

</div>






	</div>
	<br style="clear: both">