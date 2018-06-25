$(function() {
	// 헤더 inbox 페이지
	$("#inbox-page").click(function(evt){
		$.ajax({
			type:"GET",
			url:"inbox.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
			}
		})
	});
	
	//// 헤더 mywork 페이지
	$("#myWork-page").click(function(evt){
		$.ajax({
			type:"GET",
			url:"myWork.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	
	//// 헤더 starred task 페이지
	$("#starredTask-page").click(function(evt){
		$.ajax({
			url:"starredTask.htm",
			dataType:"html",
			success:function(data){
				console.log(data)
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	
	//// 헤더 report 페이지
	$("#report-page").click(function(evt){
		$.ajax({
			url:"report.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	
	//// 헤더 stream 페이지
	$("#stream-page").click(function(evt){
		$.ajax({
			url:"stream.htm",
			dataType:"html",
			success:function(data){
				$("#main-box").empty();
				$("#main-box").append(data);
				
			}
		})
	});
	
	// 헤더 개인정보수정 Modal
	$('#header-profile-edit').click(function(evt) {
			
		var mid = $('#edit-profile-mid').val(mid);
		console.log(mid);
		
		$.ajax({
			url:"updatememberinfo.htm",
			data:{mid:mid},
			dataType:"json",
			success:function(data) {
				console.log(data);
				console.log(data.updatememberinfo);
				var image = (data.updatememberinfo.image)?data.updatememberinfo.image :"img/user_image.png"
				$('#edit-profile-modal-img').attr("src",image);
				$('#edit-profile-mname').val(data.updatememberinfo.mname);
				$('#edit-profile-mid').val(data.updatememberinfo.mid);
				$('#edit-profile-apollokey').val(data.updatememberinfo.apollokey);
				$('#edit-profile-pnum').val(data.updatememberinfo.pnum);
				$('#edit-profile-deptname').val(data.updatememberinfo.deptname);
				$('#edit-profile-position').val(data.updatememberinfo.position);
				
				
			} // end - success
		
		}); // end- ajax
		
	});
});