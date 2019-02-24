/**
 * 
 */
$(document).ready(function() { 
	/* code here */ 
	indietro();
});

function indietro() {
	$("#pallino").hide();
	$("#prenotazioni").show();
}
function updateQRCode(text) {
	//alert("erf");
	var element = document.getElementById("qrcode");

	var bodyElement = document.body;
	if (element.lastChild) {
		element.replaceChild(showQRCode(text), element.lastChild);
		$("#prenotazioni").hide();
		$("#pallino").show();
		//alert("ee");
	} else {
		element.appendChild(showQRCode(text));
		$("#prenotazioni").hide();
		$("#pallino").show();
	}

}