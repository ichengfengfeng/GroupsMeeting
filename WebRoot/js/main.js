$(function(){	//时间段选中
				/*如果时间为10:20到11:20，
				 * 则startTime=(10-8)*6+20/10+1
				 * endTime=(11-8)*6+20/10
				*/
				var startTime=15;
				var endTime=20;
				var start=startTime-1;
				var end=endTime+1;
				var start1=5;
				var end2=9;
				for(var i=0;i<60;i++){
					$(".time_list").append("<div></div>");
				}
				$("tbody tr:nth-child(2) .time_list div").each(function(index,el){
					if(((index+1)>start) && ((index+1)<end)){
						$(this).addClass("check");
					}
				});
				$("tbody tr:nth-child(2) .time_list div").each(function(index,el){
					if(((index+1)>start1) && ((index+1)<end2)){
						$(this).addClass("check1");
					}
				});
				$("tbody tr:nth-child(1) .time_list div").each(function(index,el){
					if(((index+1)>start) && ((index+1)<end)){
						$(this).addClass("check");
					}
				});
				$("tbody tr:nth-child(1) .time_list div").each(function(index,el){
					if(((index+1)>start1) && ((index+1)<end2)){
						$(this).addClass("check1");
					}
				});
			})