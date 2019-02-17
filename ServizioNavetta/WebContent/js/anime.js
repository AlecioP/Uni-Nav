/**
 * 
 */
var anime;
document.addEventListener('DOMContentLoaded', function () {
	$.ajax({
		type: "GET",		
		url: "https://api.jikan.moe/v3/anime/27/recommendations",		
		success: function(data){
			anime = data;
			var randomIndex = Math.floor(Math.random() * anime.recommendations.length); 
			var image = anime.recommendations[randomIndex].image_url;
			var title = anime.recommendations[randomIndex].title;
			el = $("#anime")[0];
			el.innerHTML = "<span>"+title+"</span><br>";
			el.innerHTML = el.innerHTML+"<img src=\""+image+"\" />";
		}	
	});
});