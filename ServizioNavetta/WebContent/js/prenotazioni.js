function obliteraEntrataUscita(ID) {
	var prenF = {
		id : ID,
	};
	$.ajax({
		type : "POST",
		url : "obliteraEntrataManualmente",
		datatype : "json",
		data : JSON.stringify(prenF),
		success : function(data) {
			var x = JSON.parse(data);
			if (x.x == 0)
				document.getElementById("entrata").disabled = true;
			else
				document.getElementById("uscita").disabled = true;
		}
	});
}
