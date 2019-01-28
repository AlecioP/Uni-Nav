/**
 *  Author : Alessio Portaro
 *  Date : Dom 27 Gen 2019 16:16:39 CET
 *
 */


var myToken = 'pk.eyJ1IjoibWltbW9mbG93IiwiYSI6ImNqcDZ4eGF4ZTFlazQzdmxrb3UwYXV2MnAifQ.60aM8oR1UjGXezjzNZkacw';
var mapAttribution = 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>';
var LAT = 0.0;
var LNG = 0.0;
function Fermata(){}
var prossimaFermata = new Fermata();//JSON of Fermata Object
prossimaFermata.lng = 16.226335;
prossimaFermata.lat = 39.363219;
prossimaFermata.nome = "Cubo 30b";
var polyTratto;//Leaflet JS Object
var autistaID;// Integer representing the Id of Driver
/*This method is invoked every time the position of the device changes*/
$(function(){ /* DOM ready */
	navigator.geolocation.getCurrentPosition(function (position) {
    	LAT = position.coords.latitude;
    	LNG = position.coords.longitude;
    
    	if(amIinNewPolyLine()) {
        	/**/
    		$.ajax({type: "POST", url: "driverRegisterMediator", data : {lat : LAT, lng : LNG},
        			success: function(data){
						/*CALLBACK POLY LINE CHANGED*/
                    	/**/
					}	
        	});
        	/**/
    	}else{
    		adjustMap();
    	}
	});
});

function amIinNewPolyLine() {//boolean
	
	/*
    if(polyTratto!=undefined){
    	var point1 = polyTratto.getWaypoints()[0].latLng;
    	var point2 = polyTratto.getWaypoints()[1].latLng;
    	var tollerance = 1;
    	return ! belongsSegment(L.latLng(LAT,LNG),point1,point2,tollerance);
    }
    */
    return false;
}

function adjustMap() {
    /*TO-DO */
    
    L.map('mapid').remove();
    
    var mymap = L.map('mapid').setView([LAT,LNG],15);
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', 
			{
		attribution: mapAttribution,
		maxZoom: 18,
		id: 'mapbox.streets',
		/*acquisito su mapbox.com -> Token pubblico di default*/
		accessToken: myToken
			}
	).addTo(mymap);
    var customIcon = L.icon({
        iconUrl: 'img/bus.png',
        shadowUrl: 'img/marker.png',

        iconSize:     [45, 20], // size of the icon
        shadowSize:   [8, 8], // size of the shadow
        iconAnchor:   [20, 20], // point of the icon which will correspond to marker's location
        shadowAnchor: [1, 1],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });
    /*Current position marker*/
    L.marker([LAT, LNG], {icon : customIcon}).addTo(mymap);
    
    /*Next stop position marker*/
    L.marker([prossimaFermata.lat,prossimaFermata.lng]).addTo(mymap);
    
    /*Connect points using Leaflet Routing Machine*/
    polyTratto = L.Routing.control({
        waypoints: [
            L.latLng(LAT, LNG),
            L.latLng(prossimaFermata.lat, prossimaFermata.lng)
        ],
        routeWhileDragging: true,
        router : L.Routing.mapbox(myToken)
    }).addTo(mymap);
    
    var array = $("#next-stop-value");
    array[0].innerHTML = prossimaFermata.nome;
    
}