/**
 * 
 */

document.addEventListener('DOMContentLoaded',function(){
	var yourEmoji = $("#pick-your-emoji")[0];
	console.log(yourEmoji);
	
	$("iframe#emoji").hide();
	
	$(yourEmoji).click(function(ev){
		var emoji_picker = $("iframe#emoji")[0];
		if($(emoji_picker).is(':visible') == false){
			$(emoji_picker).show();
		}else{
			$(emoji_picker).hide();
		}
	});
	
//	$(yourEmoji).click(function(ev){
//		var emoji_picker = $("iframe#emoji")[0];
//		if($(emoji_picker).is(':visible')){
//			$(emoji_picker).hide();
//			ev.stopPropagation();
//		}
//	});
});