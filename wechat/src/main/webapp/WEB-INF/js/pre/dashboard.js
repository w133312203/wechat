$(document).bind("click",function(e){
	var target = $(e.target);
	if(target.closest("#search-time").length == 0){
		$("#search-time").hide();
		$("#dash-time").css({"border":"none"});
	}else{
		$("#dash-time").css({"border":"1px solid #969696"});
	}
});

$(function(){
	$("#dash-time").find("button").click(function(){
		if($("#dash-time").find(".dropdown-menu").is(":hidden")){
			$("#search-time").show();
			$("#dash-time").css({"border":"1px solid #969696"});
		}else{
			$("#search-time").hide();
			$("#dash-time").css({"border":"none"});
		}
	});
	
	$(".tabnav-deal li").click(function(){
		var flag = $(this).index();
		$("p.bottom").hide();
		$(this).addClass("active").siblings("li").removeClass("active");
		$(".tabBox .tablist").eq(flag).show(200).siblings(".tablist").hide();
	});
	
});