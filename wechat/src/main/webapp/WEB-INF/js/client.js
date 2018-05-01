$(function(){
	$(".client_a .but").click(function(){
		if($(this).find("ul").is(":hidden")){
			$(this).find("ul").slideDown(300);
		}else{
			$(this).find("ul").slideUp(300);
		}
	})
	$(".client_a .but li").click(function(){  //改变状态  标签  群组
		var txt = $(this).text();
		var data_id = $(this).attr("data-id");
		$(this).parent().siblings(".cli_txt").text(txt).attr("data-id",data_id);
		$(".edit-btns1").show();
	})
	$(".client_score input").bind('input propertychange', function() {  //监测输入框有无变化
		$(".edit-btns1").show();
	});
	$(".edit-btns .cancel").click(function(){  //取消保存
		$(this).parent().hide();
	})
	
	var ttops = false;
//	$.each($(".client_btn>.client_rela"),function(i){
//		$(".client_btn>.client_rela").eq((i+5)).slideUp(500);
//	})
	$(".markils_more").click(function(){
		if(ttops){
			$(".client_btn>.client_rela").slideDown(300);
			ttops = false;
		}else{
			$.each($(".client_btn>.client_rela"),function(i){
				$(".client_btn>.client_rela").eq((i+5)).slideUp(300);
			})
			ttops = true;
		}
	})
	$(".client_change").click(function(){
		$(".edit-btns1").show();
	})
	$(".client_label_add1 span").click(function(){
		$(".client_label_add_list1").show()
	})
	$(".client_label_add2 span").click(function(){
		$(".client_label_add_list2").show()
	})
	$(".shutdown").click(function(){
		$(this).parent().parent().hide();
	})
	$(".head_ils input").click(function(){
		$(".edit-btns0").show();
	})
	$(".client_app1 span i").click(function(){  //删除群组列表
		var data_id = $(this).parent().attr("data-ID");
		$(this).parent().remove();
		removeGroup(data_id);
	})
})
