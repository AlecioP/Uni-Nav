/**
 * 
 */
var newMessages;

var stopUpdate = false;

var el;
var els ;
var id_box;
var id ;
var type_box ;
var type ;
var txt_box ;
var txt ;
var message;
var array;
var no_messages;

function updateChat(){
	console.log("Updated chat");
	actualUpdate();
	if(!stopUpdate)
		setTimeout(updateChat,5000);
}


setTimeout(updateChat,5000);


function actualUpdate(){
	els = $(".message-box");
	array = $(els).get();
	var dim = array.length;
	if(dim==0)
		no_messages = true;
	else
		no_messages = false;

	if(no_messages==false){

		el = array[dim-1];
		id_box = $(el).find(".user-id")[0];
		id = id_box.innerText;
		type_box = $(el).find(".user-type")[0];
		type = type_box.innerText;
		txt_box = $(el).find(".message-content")[0];
		txt = txt_box.innerText;
	}
	message = new JSMessage(id,type,txt);
	$.ajax({type: "GET", url: "Updater", 
		data : {
			"last-message" : JSON.stringify(message),
			"no-messages" : no_messages
		},
		success: function(data){
			newMessages = JSON.parse(data);

			for(var i = 0 ; i < newMessages.length ; i++){

				$("#feed").append(
						"<div class=\"row message-box\">"+
						"<span class=\"user-img\"> </span>"+
						"<span class=\"user-type\">"+newMessages[i].type.value+"</span>"+
						"<span class=\"user-id\"> "+newMessages[i].ID+"</span>:"+
						"<span class=\"message-content\">"+newMessages[i].message+"</span>"+
						"</div>"	
				);
			}
		}
	});
}

class JSMessage{

	constructor(id,type,message){
		this.id = id;
		this.type = type;
		this.message = message;
	}
}