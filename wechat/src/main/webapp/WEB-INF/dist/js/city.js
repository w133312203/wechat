;(function($){
	
	var createCtiy = function(obj) {
		
		
		var args = 
		{
		    "title":["hot","A,B,C,D","E,F,G,H","J,K,L,M","N,O,P,Q,R,S","T,U,V,W","X,Y,Z"],
		    "sx":["hot","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","W","X","Y","Z"],
		    "hot":["北京","成都","长沙","大连","广州","杭州","南京","上海","深圳","沈阳","苏州","天津","武汉","厦门","西安"],
		    "A":["澳门","安庆","安顺","安阳","鞍山","安康","阿坝","阿拉善","阿里","阿克苏","阿勒泰","阿拉尔"],
		    "B":["北京","蚌埠","白银","毕节","保定","白山","本溪","滨州","宝鸡","巴中","保山","北海","百色","包头","巴彦淖尔","白沙县","保亭县","博尔塔拉","巴音郭楞","白城"],
		    "C":["成都","长沙","滁州","巢湖","池州","潮州","承德","沧州","常德","郴州","长春","常州","朝阳","长治","楚雄","崇左","赤峰","澄迈","昌江县","昌都","昌吉","重庆"],
		    "D":["大连","定西","东莞","大庆","大兴安岭","丹东","德州","东营","大同","德阳","达州","德宏","迪庆","大理","东方","定安"],
		    "E":["鄂州","恩施","恩施"],
		    "F":["阜阳","佛山","福州","抚州","阜新","抚顺","防城港","非中国地区"],
		    "G":["广州","甘南","贵阳","赣州","广元","广安","甘孜","桂林","贵港","固原","果洛州","高雄"],
		    "H":["杭州","淮北","淮南","黄山","亳州","河源","惠州","邯郸","衡水","黑河","鹤岗","鹤壁","黄冈","黄石","衡阳","怀化","葫芦岛","菏泽","汉中","红河","湖州","河池","贺州","呼伦贝尔","海口","喀什","哈密","和田","海东","海贝","黄南","海南","海西","淮安","合肥","呼和浩特","哈尔滨"],
		    "J":["嘉峪关","金昌","酒泉","揭阳","江门","佳木斯","鸡西","焦作","济源","荆门","荆州","吉首","吉林","江阴","九江","吉安","锦州","济南","济宁","晋城","晋中","嘉兴","金华","基隆","嘉义","景德镇"],
		    "K":["昆明","克拉玛依","克州"],
		    "L":["六安","兰州","陇南","临夏","六盘水","廊坊","洛阳","漯河","龙岩","娄底","辽源","连云港","辽阳","聊城","临沂","莱芜","吕梁","临汾","乐山","泸州","凉山","丽江","临沧","丽水","柳州","来宾","兴安","临高","乐东县","陵水县","拉萨","林芝"],
		    "M":["马鞍山","梅州","茂名","牡丹江","绵阳","眉山"],
		    "N":["南京","南阳","南平","宁德","南通","南充","内江","怒江","宁波","南宁","南沙群岛","那曲","南昌"],
		    "P":["平凉","平顶山","濮阳","莆田","萍乡","盘锦","攀枝花"],
		    "Q":["庆阳","清远","黔西南州","黔东南州","黔南州","秦皇岛","七台河","潜江","泉州" ,"忻州","曲靖","衢州","钦州","琼海","琼中县","青岛","齐齐哈尔"],
		    "R":["日照","日喀则"],
		    "S":["上海","深圳","沈阳","苏州","宿州","韶关","汕头","汕尾","石家庄","双鸭山","绥化","三门峡","商丘","十堰","随州","神农架","三明","邵阳","松原","四平","宿迁","上饶","商洛","朔州","遂宁","思茅","绍兴","石嘴山","石河子","山南","三亚"],
		    "T":["天津","铜陵","天水","铜仁","唐山","天门","通化","泰州","铁岭","泰安","铜川","太原","台州","通辽","屯昌","樟木口岸","塔城","图木舒克","台中","台南","台北","吐鲁番"],
		    "W":["武汉","芜湖","武威","无锡","潍坊","威海","渭南","文山","温州","梧州","乌海","乌兰察布","吴忠","五指山","文昌","万宁","乌鲁木齐","五家渠"],
		    "X":["厦门","西安","香港","宣城","邢台","新乡","许昌","信阳","襄樊","孝感","咸宁","仙桃","湘潭","湘西","徐州","新余","咸阳","西双版纳","锡林郭勒","西沙群岛","西宁","新竹"],
		    "Y":["云浮","阳江","伊春","宜昌","益阳","岳阳","永州","延边","盐城","扬州","鹰潭","宜春","营口","烟台","延安","榆林","阳泉","运城","宜宾","雅安","玉溪","玉林","银川","伊犁哈萨克","玉树"],
		    "Z":["张掖","珠海","中山","肇庆","湛江","遵义","张家口","周口","驻马店","漳州","张家界","株洲","镇江","淄博","枣庄","自贡","资阳","昭通","舟山","中卫","儋州","中沙群岛","郑州"]
		}
		
		var html = "<div class=\"search_city\" style=\"left:0%\" id=\"search_city\"><div class=\"city_sx_div\"><ul id=\"city_sx\">";
		
		for(var i=0;i<args.title.length;i++) {
			if(args.title[i]=="hot") {
				html += "<li value=\"hot\">热门</li>";
			}else {
				var text = args.title[i].replace(/[,]/g,"");
				html += "<li value=\""+args.title[i]+"\">"+text+"</li>";
			}
		}
		html +="</ul><div class=\"city_close\"><img src=\"../img/share_qr_close3_20.png\"></div></div><div class=\"city_alldiv\" id=\"cityArea\">";
		for(var i=0;i<args.sx.length;i++) {
			
			html += "<div class=\"city_div\" id=\"C_"+args.sx[i]+"\">"+
						"<span>"+args.sx[i]+"</span>"+
						"<ul>";
		    			for(var n=0;n<args[args.sx[i]].length;n++) {
		    				html += "<li>"+args[args.sx[i]][n]+"</li>";
		    			}
			html +=	"</ul></div>";
		}
		
		html +="</div><div class=\"county_div\"><ul id=\"cityArea_child\"></ul></div></div>";  
		
		$(obj).append(html);
	}
	
	$.fn.extend({
		"city":function() {
			return this.each(function(){
				createCtiy(this);
				$(document).click(function() {
					$("#search_city").hide();    	
				});
				    	
				$("#search_city").click(function(e) {
				    e?e.stopPropagation():event.cancelBubble = true;
				});
				    	
				$(".city_close").click(function() {
				    $("#search_city").hide();    
				});
				    	
				$("#city_sx li").click(function() {
					$("#city_sx li").removeClass("select_city_li");
				    $(this).addClass("select_city_li");
				    var sx = $(this).attr("value").split(",");
				    $(".city_div").hide();
				    for(var i=0;i<sx.length;i++) {
				    	$("#C_"+sx[i]).show();
				    }
				    $(".county_div").hide();
				});
				    	
				$("#city").click(function(e) {
					$("#search_city").show();    	
					$("#city_sx li").eq(0).click();
					e?e.stopPropagation():event.cancelBubble = true;
				});
				
				$("#cityArea li").click(function() {
				    var areaName = $(this).html();
				    $("#countys").val(areaName);
				    $.ajax({

				    	type: "post",
					
					    url: "../findChildArea",
					
					    data: {'areaName':areaName},
					
					    dataType: "json",
					
					    success: function(data){
					    	var obj = eval(data);
					    	 console.info(obj);
					        if(obj.RCODE=='SUCCESS') {
					        	var htmlStr = "";
					            for(var i=0;i<obj.DATA_LIST.length;i++) {
					            	htmlStr = htmlStr+"<li value=\""+areaName+"\" >"+obj.DATA_LIST[i].areaName+"</li>";
					            }
					            $("#cityArea_child").html(htmlStr);
					            $(".county_div").show();
					            $("#cityArea_child").find("li").click(function(){
					            	$("#city").val(areaName+" "+$(this).html());
					            	$("#search_city").hide();
					           		$("#citys").val($(this).html());
					            })
					            
					         }
					    }
					});
				}); 
			});
		}
	});
	
})(jQuery);

