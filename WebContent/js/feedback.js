/**
 * 
 */

$(window).on('load', function() {
	$("#comment").hide();
	$("#feedback").show();
});
function updateFeedback(text) {
	var element = document.getElementById("qrcode");
	document.getElementById("codicePren").value = text;



	$("#feedback").hide();
	$("#comment").show();
}
function indietro() {
	document.getElementById("codicePren").value = null;

	$("#comment").hide();
	$("#feedback").show();
}