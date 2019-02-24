/**
 * 
 */

$(document).ready(function() { 
	
	$("tr").hover(function(){
		$(this).addClass("hover");
		$(this).removeClass("not-hover");
	});
	
	$("tr").mouseleave(function(){
		$(this).removeClass("hover");
		$(this).addClass("not-hover");
	});
	
});