function NavToggle() {
    $("#listMenu").trigger("click");
}

function SmoothlyMenu(){
	if($("body").hasClass("mini-navbar")) {
		$("#side-menu").hide(),setTimeout(function(){$("#side-menu").fadeIn(500)},300)
	}else {
		$("#side-menu").hide(),setTimeout(function(){$("#side-menu").fadeIn(500)},100)
	}
}
$(function() {
	$('#side-menu').metisMenu();
	$("#listMenu").click(function () {
        $("body").toggleClass("mini-navbar")
        SmoothlyMenu()
    });
	$("#side-menu>li").click(function() {
		$("body").hasClass("mini-navbar") && NavToggle()
	});
	$("#side-menu>li li a").click(function() {
		$(window).width() < 927 && NavToggle()
	});
    $(window).bind("load resize", function() {
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        width < 927 && ($("body").addClass("mini-navbar"), $(".navbar-static-side").fadeIn())
    });
    $(".nav-close").click(NavToggle);
    var url = window.location;
    //选定菜单
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    console.log(element)
    if (element.is('li')) {
        element.addClass('active');
        if(element.parent().parent().is("li")){
        	element.parent().parent().addClass('active');
        	element.parent().addClass('in');
        }
    }
    
});
