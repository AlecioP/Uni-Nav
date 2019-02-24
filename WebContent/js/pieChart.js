/**
 * 
 */
var pie;
var data = [];
function createPie(){
//	alert("create pie");
	
	for(var i = 0 ; i < array.length ; i++){
		console.log(array[i].left+" "+array[i].right);
		var current = [];
		current.push("Autista "+array[i].left);
		current.push(array[i].right);
		data.push(current);
	}
	
	pie = Highcharts.chart('mychart', {
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
        title: {
            text: 'Prenotazioni per Autista'
        },
        plotOptions: {
            pie: {
                innerSize: 90,
                depth: 50
            }
        },
        series: [{
            name: 'Numero di prenotazioni',
            data: data
        }]
    });
}