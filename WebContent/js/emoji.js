/**
 * 
 */

function iframebodyload(){
	var frame = $("#emoji")[0];
	//Cause the event must be handled in bubbling phase, the only way to add the right
	// event listener is by using Dom Element native function
	var triggerInCapture = false;

	frame.addEventListener('click',function(){
		/*In bubbling phase, if an emoji has been actually selected,
		 * the global variable "lastEmoji" value must be different from undefined
		 * */
		console.log("event click");
		if(lastEmoji!=undefined){
			var rendered = renderEmoji(lastEmoji);
			$("input#new-message-content").val($("input#new-message-content").val()+""+rendered);
			lastEmoji = undefined;
		}
	},triggerInCapture);//Click in Iframe
}

function renderEmoji(text){
	var a = document.createElement('div');
	a.innerHTML = text;
	return a.innerHTML;
}