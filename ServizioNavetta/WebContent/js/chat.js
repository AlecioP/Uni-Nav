/**
 * 
 */
var sent;
$(function(){
	$("#new-message").click(function(){
		var newMessage = $("#new-message-content")[0];
		var txtStr = $(newMessage).val();
		txtStr = txtStr.replace('"','');
		console.log("VALUE : "+txtStr);
		$(newMessage).val("");
		
		$.ajax({type: "POST", url: "Chat", 
			data : {
				"new-message" : JSON.stringify(txtStr)
			},
			success: function(data){
				sent = JSON.parse(data);
			}
		});
	});
});
