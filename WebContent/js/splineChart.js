/**
 * 
 */
var dates = [];
var values = [];
var test = [];
var datesB = [];
var chart;
var w,h;
function setChart(){
	var workNow = new Date(Date.now());
	var now = new Date(Date.now());
	var oneMonthAgo = new Date(workNow.setMonth(workNow.getMonth()-1));
	var current = oneMonthAgo;
	while(current < now){
		var day = parseInt(current.toDateString().split(" ")[2]);
		var month = current.getMonth();
		var year = current.getFullYear();
		dates.push(Date.UTC(year,month,day));
		//INCR
		current = new Date(current.setDate(current.getDate()+1));
	}
	
	var synchIndex  = 0;
	for(var i = 0 ; i < dates.length ; i++){
		if(synchIndex==array.length){
			values.push(0);
//			console.log("Array empty");
			continue;
		}
		var date = new Date(dates[i]);
		var day1 = parseInt(date.toDateString().split(" ")[2]);
		var formatted = 
			(date.getMonth()+1)+"-"+day1+"-"+date.getFullYear();
		var fromServer = array[synchIndex].left;
		
		var splits = fromServer.split("-");
		
		var serverFormatted = parseInt(splits[0])+"-"+parseInt(splits[1])+"-"+parseInt(splits[2]);
		
//		console.log(formatted);
//		console.log("Compare : "+serverFormatted+" "+formatted);
//		console.log("Compare : "+typeof(serverFormatted)+" "+typeof(formatted));
		
		var bool = serverFormatted.localeCompare(formatted) == 0;
//		console.log("BOOL : "+bool);
		if(bool){
//			console.log("MATCH : "+array[synchIndex].left);
			values.push(array[synchIndex].right);
			synchIndex++;
		}else{
			values.push(0);
		}
	}
	
	for(var j = 0 ; j < values.length ; j++){
		var cd = [];
		cd.push(dates[j]);
		cd.push(values[j]);
		test.push(cd);
		datesB.push(new Date(dates[j]).getDate()+"-"+(new Date(dates[j]).getMonth()+1)+"-"+(new Date(dates[j]).getFullYear()));
	}
	
	chart = Highcharts.chart('mychart', {

	    title: {
	        text: 'Booking'
	    },
	    
	    xAxis :{
	    	title:{
	    		text: 'Days'
	    	},
	    	categories : dates,
	    	labels :{
	    		formatter : function(){
	    			return new Date(this.value).getDate()+"-"+(new Date(this.value).getMonth()+1);
	    		}
	    	}
	    },
	    
	    yAxis: {
	        title: {
	            text: 'Number of bookings'
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    plotOptions: {
	        series: {
	            label: {
	                connectorAllowed: false
	            }
	        }
	    },

	    series: [{
	        name: 'Bookings',
	        data: values
	    }],
	    
	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 100
	            },
	            chartOptions: {
	                legend: {
	                    layout: 'horizontal',
	                    align: 'center',
	                    verticalAlign: 'bottom'
	                }
	            }
	        }]
	    },
	    tooltip : {
	    	formatter : function(){
	    		 
	    		return "<span>Date "+(new Date(this.x).getDate())+"-"+(new Date(this.x).getMonth()+1)+
	    		" <br>"+this.y+" bookings</span>";
	    	}
	    },
	    /*
	    chart : {
	    	events : {
	    		click : function(){
	    			resizeChart(this);
	    		}
	    	}
	    }
	     */
	});
	resizeChart(chart);
}

function resizeChart(chart1){
	w = $("#mychart").width();
	h = $("#mychart").height();
	chart1.setSize(w,h);
}

class ChartData{
	constructor(date,value){
		this.date = date;
		this.value = value;
	}
}