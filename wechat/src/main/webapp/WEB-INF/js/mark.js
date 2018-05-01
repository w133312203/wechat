$(function(){
	var mark_arrt = '';
	$(".mark_tianjia").click(function(){
		if($(".mark_list").is(":hidden")){
			$(".mark_list").slideDown(300);
		}else{
			$(".mark_list").slideUp(300);
		}
	})
	$(".select_stage").show();
	$(".mark_net>li").click(function(){
		if($(this).find(".mark_ops").is(":hidden")){
			$(this).find(".mark_ops").slideDown(300);
			$(this).siblings("li").removeClass("off").find(".mark_ops").slideUp(300);
			$(this).addClass("off");
		}else{
			$(this).find(".mark_ops").slideUp(300);
			$(this).removeClass("off");
			$(".mark_filter").hide();
		}
		$(".select_score li").removeClass("off").find("input").attr("disabled","disabled").val("").blur();
	})
	$(".select_stage li,.select_group li").click(function(event){
		event.stopPropagation();
		$(".mark_filter").show();
		var parent_ = $(this).parent();
		var data_status = $(this).attr("data-status");
		var data_All = $(this).attr("data-All");
		if(data_status != "true"){
			if(parent_.find("li").eq(0).hasClass("off")){
				removeClass(parent_);
				$(this).addClass("off");
			}else{
				if($(this).hasClass("off")){
					$(this).removeClass("off");
				}else{
					removeClass(parent_);
					$(this).addClass("off");
				}
			}
		}
	})
	function removeClass(parent){
		$.each(parent.find("li"), function(i) {
			if(parent.find("li").eq(i).attr("data-status") != "true"){
				parent.find("li").eq(i).removeClass("off");
			}
		});
	}
	$(".select_score").click(function(event){
		event.stopPropagation();
	})
	$(".select_score li label").click(function(event){
		event.stopPropagation();
		$(this).parent().addClass("off").siblings("li").removeClass("off");		
		$(this).find("input").attr("disabled",false).focus();
		$(this).parent().siblings("li").find("input").attr("disabled","disabled").val("");
		$(".mark_filter").show();
	})
	$(".select_stage li").click(function(){
		select_ok($(this),".select_stage","stage");
	})
	$(".select_group li").click(function(){
		select_ok($(this),".select_group","group");
	})
	function select_ok(this_,objClass,flag){
		mark_arrt = '';
		var txt = this_.text();
		var data_id = this_.attr("data-id");
		var ops_index = this_.index();
		var data_status = this_.attr("data-status");
		if(data_status != "true"){
			var data_list = {
				"id":ops_index,
				"text":txt,
				"class":objClass,
				"data_ID":data_id,
				"flag_name":flag,
				"flag_score":'',
				score:''
			}
			mark_arrt = data_list;
		}
		return mark_arrt
	}
	$(".mark_filter").click(function(){
		var click_true = true;
		if($(".mark_score").is(":hidden")){
		}else{
			var ind = $('.select_score li');
			$.each(ind, function(i) {
				if(ind.eq(i).find("input").val() != ""){
					var o_txt = ind.eq(i).find("input").val();
					if(!/^\d+$/.test(o_txt)){
						alert("请输入数字");
						click_true = false;
						return false;
					}else{
						if(i == 0){
							var txt = "评分大于<span class='txt'>"+o_txt+"</span>分";
							var score_ = 1;
						}else if(i == 1){
							var txt = "评分小于<span class='txt'>"+o_txt+"</span>分";
							var score_ = 2;
						}else if(i == 2){
							var txt = "评分等于<span class='txt'>"+o_txt+"</span>分";
							var score_ = 3;
						}
						ind.eq(i).find("input").attr("data-zt","true");
						var data_list = {
							id:i,
							text:txt,
							class:".select_score",
							data_ID:'',
							flag_name:"score",
							flag_score:score_,
							score:o_txt
						}
						mark_arrt = data_list;
					}
				}
			});
		}
		if(click_true){
			if(mark_arrt != ""){
				if(mark_arrt.flag_score != ""){
					var mark_divs = mark_arrt.text+
					'<span class="mark_remove_filter" data-id="'+mark_arrt.data_ID+
					'" data-score="'+mark_arrt.flag_score+
					'" data-name="'+mark_arrt.flag_name+'" onclick="remove_list(this)">x</span>';
					if($(".mark_filter_list .data-score"+mark_arrt.id).length>0){
						$(".mark_filter_list .data-score"+mark_arrt.id).html(mark_divs);
					}else{
						$(".mark_filter_list").append('<div class="data-score'+mark_arrt.id+'">'+mark_divs+'</div>');
					}
				}else{
					if($(".fifter_"+mark_arrt.flag_name).length>0){
						var mark_divs = "<span class='txt'>"+mark_arrt.text+"</span>"+
						'<span class="mark_remove_filter" data-id="'+mark_arrt.data_ID+
						'" data-score="'+mark_arrt.flag_score+
						'" data-name="'+mark_arrt.flag_name+'" onclick="remove_list(this)">x</span>';
						$(".fifter_"+mark_arrt.flag_name).html(mark_divs)
					}else{
						var mark_divs = '<div class="fifter_'+mark_arrt.flag_name+'">'+"<span class='txt'>"+mark_arrt.text+"</span>"+
						'<span class="mark_remove_filter" data-id="'+mark_arrt.data_ID+
						'" data-score="'+mark_arrt.flag_score+
						'" data-name="'+mark_arrt.flag_name+'" onclick="remove_list(this)">x</span></div>';
						$(".mark_filter_list").append(mark_divs);
					}
				}
				var $table = $('#table');
				if(mark_arrt.flag_name == "stage" || mark_arrt.flag_name == "group"){
					var mark_id = mark_arrt.data_ID;
				}else{
					var mark_id = mark_arrt.flag_score;
				}
				
			}
			
			$(".mark_list .mark_ops").slideUp(300);
			$(".mark_net>li").removeClass("off");
			$(".select_score li label input").attr("disabled","disabled").val("");
			$(this).hide();
		}
		fifters();
})
})

function fifters(){
	var fifter_stage = $(".fifter_stage .mark_remove_filter").attr("data-id");
	var fifter_group = $(".fifter_group .mark_remove_filter").attr("data-id");
	var data_score0 = $(".data-score0 .txt").text();
	var data_score1 = $(".data-score1 .txt").text();
	var data_score2 = $(".data-score2 .txt").text();
	if(fifter_stage == undefined){
		fifter_stage = ""
	}
	if(fifter_group == undefined){
		fifter_group = ""
	}
	//console.log(fifter_stage,fifter_group,data_score0,data_score1,data_score2);
	$table.bootstrapTable('refresh',{url:''+rootPath+'/showCustomerListJson?Id1='+
		fifter_stage+'&Id5='+fifter_group+'&Id3='+data_score1+'&Id4='+data_score2+'&Id2='+data_score0
		});
}


function remove_list(obj){
	$(obj).parent().remove();
	fifters()
}