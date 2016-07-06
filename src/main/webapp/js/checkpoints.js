var map;
var markers = [];

$(function() {
	
});

function initMap() {
	var maxLat = -90.0, minLat = 90.0;
	var maxLon = -180.0, minLon = 180.0;
	var checkpoints = getCheckpoints();
	
	map = new google.maps.Map(document.getElementById('map'), {
//		center: {lat: 38.4400, lng: 139.11090},
//		zoom: 8
	});
	
	$.each(checkpoints, function(i, checkpoint) {
		// マーカーの追加
		var marker = new google.maps.Marker({
		    position: {lat: checkpoint.lat, lng: checkpoint.lon},
		    map: map,
		});
		markers.push(marker);
		if (maxLat < checkpoint.lat) maxLat = checkpoint.lat;
		if (minLat > checkpoint.lat) minLat = checkpoint.lat;
		if (maxLon < checkpoint.lon) maxLon = checkpoint.lon;
		if (minLon > checkpoint.lon) minLon = checkpoint.lon;
	});
	
	// 全てのマーカーが入るように縮尺を調整
	var sw = {lat: minLat, lng: minLon};
	var ne = {lat: maxLat, lng: maxLon};
	var latlngBounds = new google.maps.LatLngBounds(sw, ne);
	map.fitBounds(latlngBounds);
}
