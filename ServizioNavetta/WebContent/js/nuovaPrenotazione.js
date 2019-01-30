/**
 * 
 * 	Author : Alessio Portaro
 *  Date : Mer 30 Gen 2019 00:55:51 CET
 * 
 */

var myToken = 'pk.eyJ1IjoibWltbW9mbG93IiwiYSI6ImNqcDZ4eGF4ZTFlazQzdmxrb3UwYXV2MnAifQ.60aM8oR1UjGXezjzNZkacw';
var mapAttribution = 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>';
var LAT = 0.0;
var LNG = 0.0;
/*
 * HINT : 
 * 
 *	var routingControl = L.Routing.control({
 *  routeLine: function(route) {
 *      var line = L.Routing.line(route);
 *      line.on('click', function(e) { console.log(e); });
 *      return line;
 *  }
 *  [...]
 *	});
 * 
 */

$(function(){ /* DOM ready */
	navigator.geolocation.getCurrentPosition(function (position) {
    	LAT = position.coords.latitude;
    	LNG = position.coords.longitude;
    	
    	/**/
    	//Probably in some if
    	var mymap = L.map('map').setView([LAT,LNG],15);
    	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', 
    			{
    		attribution: mapAttribution,
    		maxZoom: 18,
    		id: 'mapbox.streets',
    		/*acquisito su mapbox.com -> Token pubblico di default*/
    		accessToken: myToken
    			}
    	).addTo(mymap);
    	/**/
	});
});