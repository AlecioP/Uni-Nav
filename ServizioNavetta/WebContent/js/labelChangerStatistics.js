/**
 * 
 */

$(function(){ /* DOM ready */

	var sel = $("#select-stat")[0];

	$(sel).change(function(){
		var opt = this.options[this.selectedIndex].value;
		console.log(opt);
		switch (opt) {
		case "bus-good-drivers":{
			changeLabel("Id Navetta");
			break;
		}
		case "last-month-books":{
			changeLabel("Id Studente");
			break;
		}
		default:{
			break;
		}
		}
	});
});

function changeLabel(content){
	var el = $("#stat-label")[0];
	el.innerHTML = content;
}