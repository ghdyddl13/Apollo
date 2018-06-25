// project table page js

function getpname(pid){

	var custom_menu =  $(this).parents("ul.custom-menu")[0];
    var pid = $(custom_menu).find("input[name=pid]").val();
    console.log(pid);
    
    var pname =     
    $('#project-table-page-pname').val(pid);
    
    $.ajax({
    	url:"table.htm",
    	type:"post",
    	data:{pid:pid},
    });
    
    $('.project-table-day').val().split(" ")[0];
}

