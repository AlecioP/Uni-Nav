/**
 * 
 */
var anime;
var active = false;
document.addEventListener('DOMContentLoaded', function () {
//	if(active){//ACTIVATE AFTER APP RELEASE (NOW DEACTIVATED TO AVOID IP BLOCK)
		$.ajax({
			type: "GET",		
			url: "https://api.jikan.moe/v3/anime/27/recommendations",		
			success: function(data){
				anime = data;
				var randomIndex = Math.floor(Math.random() * anime.recommendations.length); 
				var image = anime.recommendations[randomIndex].image_url;
				var title = anime.recommendations[randomIndex].title;
				el = $("#anime")[0];
				el.innerHTML = "<h3>"+title+"</h3>";
				el.innerHTML = el.innerHTML+"<img src=\""+image+"\" />";
			}	
		});
//	}
});