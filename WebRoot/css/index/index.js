$(function(){
	//选择查询条件
	$(".choice span").each(function(){
		$(this).click(function(){
			$(this).addClass("check_span");
			$(this).siblings().removeClass("check_span");
		});
	});
});