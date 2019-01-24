function loadStudent() {
	alert("cio");
	// var stud = {
	// matricola : studente.matricola,
	// nome: studente.nome,
	// cognome: studente.cognome
	// };
	// $.ajax({
	// type: "POST",
	// url: "dettagliScuola",
	// datatype: "json",
	// data: JSON.stringify(stud),
	// success: function (data){
	// var scuola = JSON.parse(data);
	// $("#dettagliScuola").text(scuola.nome);
	// }
	// });
}

function validateForm() {
	var x = document.forms["myForm"]["matricola"].value;
	if (x == "") {
		alert("Name must be filled out");
		return false;
	}
}