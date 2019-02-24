var ticketCode;
var codeOk;
let scanner = new Instascan.Scanner({ video: document.getElementById('video') });
scanner.addListener('scan', function (content) {
		console.log(content);
		 ticketCode = {code:content}; 
	        $.post({
	    		type: "POST",
	    		url: "ObliteraBigliettoQR",
	    		datatype: "json",
	    		data: { "codice":JSON.stringify(ticketCode) },
	    		success: function (data){
	    			codeOk = JSON.parse(data);
	    			console.log(codeOk);
	    			switch (codeOk.risposta[0]) {
					case "no":{
						change("red");
						break;
					}
					case "si":{
						change("green");
						break;
					}
					}
	    		}
	    	});
      
});
Instascan.Camera.getCameras().then(function (cameras) {
        if (cameras.length > 0) {
          scanner.start(cameras[0]); //camera[1] quella posteriore?
        } else {
          console.error('No cameras found.');
        }
}).catch(function (e) {
        console.error(e);
   });

function change(color){
	var el = $("#scanner")[0];
	
	$(el).css("background-color",color);
	$(el).css("border-color",color);
	
	setTimeout(restore,500);
}

function restore(){
	var el = $("#scanner")[0];
	
	$(el).css("background-color","grey");
	$(el).css("border-color","grey");
}