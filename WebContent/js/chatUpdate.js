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
var user;
var time;
var time_box;

function updateChat(){
//	console.log("Updated chat");
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
		time_box = $(el).find(".time")[0];
		time = time_box.innerText;
		
	}
	message = new JSMessage(id,type,txt,time);
	$.ajax({type: "GET", url: "Updater", 
		data : {
			"last-message" : JSON.stringify(message),
			"no-messages" : no_messages
		},
		success: function(data){
			newMessages = JSON.parse(data);
			if(newMessages[0]!=undefined){
				console.log(newMessages[0].message);
				console.log(data);
				user = $("#myid")[0];
				console.log();
			}
			
			var additionClass=" ";
			
			for(var i = 0 ; i < newMessages.length ; i++){
				if(newMessages[i].ID == user.innerText)
					additionClass = "mine";
				else
					additionClass = " ";
				
				var icon = " ";
				switch (newMessages[i].type.value) {
				case 'DRIVER':{
					icon = "<span class=\"user-img\"><img class=\"img-fluid img-thumbnail img-circle\" src=\"https://image.flaticon.com/icons/svg/206/206883.svg\"></span>";
					break;
				}
				case 'ADMIN':{
					icon = "<span class=\"user-img\"><img class=\"img-fluid img-thumbnail img-circle\" src=\"https://upload.wikimedia.org/wikipedia/commons/a/a6/User-admin.svg\"></span>";
					break;
				}
				case 'STUDENT':{
					icon = "<span class=\"user-img\"><img class=\"img-fluid img-thumbnail img-circle\" src=\"https://image.flaticon.com/icons/svg/194/194931.svg\"></span>";
					break;
				}
				}
				$("#feed").append(
						
						"<div class=\"row message-box "+additionClass+"\">"+
						icon+
						"<span class=\"user-type\">"+newMessages[i].type.value+"</span>"+
						"<span class=\"user-id\"> "+newMessages[i].ID+"</span>:<br>"+
						"<span class=\"message-content\">"+newMessages[i].message+"</span>"+
						"<span style=\" display : none; \" class=\"time\">"+newMessages[i].time+"</span>"+
						"</div>"	
				);
			}
		}
	});
}

class JSMessage{

	constructor(id,type,message,time){
		this.id = id;
		this.type = type;
		this.message = message;
		this.time = time;
	}
}