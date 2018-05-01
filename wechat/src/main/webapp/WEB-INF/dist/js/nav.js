$(function(){
	$(".nav_client").click(function(event){
		event.stopPropagation();
		$(this).find("ul").fadeToggle(300);
		$(this).siblings("li").find("ul").fadeOut(300);
	})
//	$(".nav_client").mouseout(function(event){
//		event.stopPropagation();
//		$(".nav_client").find("ul").fadeOut(300);
//	})
	$(document).click(function(event){
		event.stopPropagation();
		$(".nav_client ul").fadeOut(300);
	})
})
