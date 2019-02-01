/**
* 
*Author : Alessio Portaro
*Date : Mer 30 Gen 2019 00:55:51 CET
* 
*/

var myToken = 'pk.eyJ1IjoibWltbW9mbG93IiwiYSI6ImNqcDZ4eGF4ZTFlazQzdmxrb3UwYXV2MnAifQ.60aM8oR1UjGXezjzNZkacw';
var mapAttribution = 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>';
var LAT = 0.0;
var LNG = 0.0;
var customIcon;
var mymap;
var tileLayer;
var nearStart = [];
var nearStop = [];
var fermataPartenza = undefined;/* new Fermata();*/
var fermataArrivo = undefined;/* new Fermata();*/
var lastElClicked = "start";

/*DEBUG*/
var clickPoint;
var someMarker;
/**/

$(function(){ /* DOM ready */
    
    initCustomMarker();
	navigator.geolocation.getCurrentPosition(function (position) {	
    	LAT = position.coords.latitude;
    	LNG = position.coords.longitude;
    	createMap();
        fermataPartenza = new Fermata();
        fermataPartenza.nome ="Actual Position";
        fermataPartenza.latitudine = LAT;
        fermataPartenza.longitudine = LNG;
        fermateNear(LAT,LNG,"start");
	});
	
	
	$("#start-geoloc").click(function(){
        this.checked = true;
        emptyArray(nearStart);
		updatePosition();
        fermateNear(LAT,LNG,"start");
        checkDisabledButton();
        lastElClicked="start";
        markStop(fermataArrivo);
        markStop(fermataPartenza);
	});/*ACTION LISTENER*/
    
	$("#start-map").click(function(){
        this.checked = true;
        emptyArray(nearStart);
        createMap();
        checkDisabledButton();
        lastElClicked="start";
        markStop(fermataArrivo);
        markStop(fermataPartenza);
	});/*ACTION LISTENER*/
    
	$("#stop-map").click(function(){
        this.checked = true;
		emptyArray(nearStop);
        createMap();
        checkDisabledButton();
        lastElClicked="stop";
        markStop(fermataArrivo);
        markStop(fermataPartenza);
	});/*ACTION LISTENER*/
    
	$("#stop-geoloc").click(function(){
        this.checked = true;
		emptyArray(nearStop);
		updatePosition();
        fermateNear(LAT,LNG,"stop");
        checkDisabledButton();
        lastElClicked="stop";
        markStop(fermataArrivo);
        markStop(fermataPartenza);
	});/*ACTION LISTENER*/
    
    
    $("#compute-routes").click(function(){
        var disabled = this.hasAttribute("disabled");
		if(disabled==false){
            $.ajax({type: "GET", url: "creaPrenotazione", 
                data : {
                    "state" : "computeLine", 
                    "start-point" : JSON.stringify(fermataPartenza), 
                    "stop-point" : JSON.stringify(fermataArrivo)
                },
                success: function(data){
                    /*add routes in map*/
                }	
            });/*AJAX CALL*/
        }
	});/*ACTION LISTENER*/
});/* DOM ready */

function updatePosition(){
    navigator.geolocation.getCurrentPosition(function(position){
            LAT = position.coords.latitude;
            LNG = position.coords.longitude;
            createMap();
            markCurrentPosition();
        });
}

/*This function makes an asynch call to the server and retrieves the N nearest stops to the point given as argument*/
function fermateNear(lat,lng,whereAdd){
    var state;
    if(whereAdd==="start"){
        state = "partenza";
        emptyArray(nearStart);
    }else if(whereAdd==="stop"){
        state = "arrivo";
        emptyArray(nearStop);
    }
    $.ajax({type: "GET", url: "creaPrenotazione", data : {"state" : state, "actual-lat" : lat, "actual-lng" : lng },
    		success: function(data){
    			var fermateVicine = JSON.parse(data);
                if(mymap!=undefined){
                	for(var i=0;i<fermateVicine.length;i++){
                        var tmp = new Fermata();
                        console.log("From server : title "+fermateVicine[i].nome)
                        tmp.nome = fermateVicine[i].nome;
                		tmp.latitudine=fermateVicine[i].latitudine;
                    	tmp.longitudine=fermateVicine[i].longitudine;
                    	var marker = new L.Marker([tmp.latitudine, tmp.longitudine]).addTo(mymap);
                    	marker.options.title = tmp.nome;
                    	console.log(marker.options.title);
                    	someMarker = marker;
                        if(whereAdd==="start"){
                            nearStart.push(tmp);
                            //$(marker).click(function(){
                            marker.on('click',function(){
                                var tmp1 = new Fermata();
                                tmp1.nome = this.options.title;
                                tmp1.latitudine = this.getLatLng().lat;
                                tmp1.longitudine = this.getLatLng().lng;
                                console.log(this.title);
                                console.log(this.getLatLng());
                                fermataPartenza = tmp1;
                            });
                        }else if(whereAdd==="stop"){
                            nearStop.push(tmp);
                            marker.on('click',function(){
                                var tmp1 = new Fermata();
                                tmp1.nome = this.title;
                                tmp1.latitudine = this.getLatLng().lat;
                                tmp1.longitudine = this.getLatLng().lng;
                                fermataArrivo = tmp1;
                            });
                        }
                	}
                }
    		}	
    });
}

/*This function removes every */
function emptyArray(array){
    while(array.length>0)
        array.pop();
}

function createMap(){
    deleteMap();
    mymap = new L.Map('map').setView([LAT,LNG],15);
    tileLayer = new L.TileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', 
    {
        attribution: mapAttribution,
        maxZoom: 18,
        id: 'mapbox.streets',
        /*acquisito su mapbox.com -> Token pubblico di default*/
        accessToken: myToken
    }).addTo(mymap);
   
    mymap.on('click',function(ev){
        var objLatLng = mymap.mouseEventToLatLng(ev.originalEvent);
//        alert("CLICK ON : LAT "+objLatLng.lat+" LNG "+objLatLng.lng);
        clickPoint = objLatLng;
        handleMapClick(objLatLng);
    }); 
}

function initCustomMarker(){
    customIcon = L.icon({
        iconUrl: 'img/bus.png',
        shadowUrl: 'img/marker.png',

        iconSize:     [45, 20], // size of the icon
        shadowSize:   [8, 8], // size of the shadow
        iconAnchor:   [20, 20], // point of the icon which will correspond to marker's location
        shadowAnchor: [1, 1],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });
}

function markCurrentPosition(){
    if(mymap!=undefined)
        L.marker([LAT, LNG], {icon : customIcon}).addTo(mymap);
}

function areStopNStartSet(){
    if(fermataArrivo!=undefined && fermataPartenza!=undefined)
        return true;
    return false;
}

function checkDisabledButton(){
    if(areStopNStartSet()===true)
            $("#compute-routes").removeAttr("disabled");
}

function deleteMap(){
    //L.map('map').remOve();
    var mapEl = $("#map-container")[0];
    mapEl.innerHTML = "<div id=\"map\"></div>";
}

function handleMapClick(objLatLng){
    if(lastElClicked==="start"){
        var elCH = $("#start-map")[0];
        var condition = elCH.checked;
        if(condition===true){
            var tmp = new Fermata();
            tmp.nome="Custom start";
            tmp.latitudine = objLatLng.lat;
            tmp.longitudine = objLatLng.lng;
            fermataPartenza = tmp;
            markStop(tmp);
        }
    }else if(lastElClicked=="stop"){
        var elCH = $("#stop-map")[0];
        var condition = elCH.checked;
        if(condition===true){
            var tmp = new Fermata();
            tmp.nome="Custom stop";
            tmp.latitudine = objLatLng.lat;
            tmp.longitudine = objLatLng.lng;
            fermataArrivo = tmp;
            markStop(tmp);
        }
    }
}

function markStop(fermata){
    if(mymap!=undefined && fermata!=undefined){
        var m = L.marker([fermata.latitudine, fermata.longitudine],{icon : customIcon});
        m.addTo(mymap);
        m.options.title = fermata.nome;
    }
}

class Fermata{
    constructor(){
        this.nome = "";
        this.latitudine=0.0;
        this.longitudine=0.0;
    }
}