/**
*ÒÆÈëÊÂ¼þ
*/
$(document).ready(function(){
	$("form input").mousemove(function(){
			var q = this.type;
			var w = this.name;
			if(q == "text" && w != "show"){
				$(this).css("background","#D7FFFF");
			}
	});
	$("form input").mouseout(function(){
		var q = this.type;
		var w = this.name;
		if(q == "text" && w != "show"){
			$(this).css("background","#F0EEF0");
		}
	});
});