/* Consntants */
var LS_CITY_WALK_DATA_KEY = "city_walk_data_key";
/**************/

$(function() {
	setNavTitle();
});

function setNavTitle() {
	$("#nav-title").text(document.title);
}

/* City Walk Data */
function fetchCityWalkData() {
	$.ajax({
		type: 'GET',
		url: '../json/initialize.json',
		dataType: 'json',
		success: function(data) {
			saveCityWalkData(data);
		},
		error: function() {
			alert("初期化データが正しく取得できませんでした。ブラウザーの更新ボタンを押して下さい。")
		}
	});	
}

function saveCityWalkData(data) {
	setItem(LS_CITY_WALK_DATA_KEY, JSON.stringify(data));
}

function loadCityWalkData() {
	return JSON.parse(getItem(LS_CITY_WALK_DATA_KEY));
}

function getCheckpoints() {
	return loadCityWalkData()["checkpoints"];
}

function getTasks() {
	return loadCityWalkData()["tasks"];
}

/* Local Storage */
// localStorageに値を設定
function setItem(key, val) {
	window.localStorage.setItem(key, val);
}
// localStorageから値を取得
function getItem(key) {
	return window.localStorage.getItem(key);
}
// localStorageに保存されている、あるkeyの値を削除する
function removeItem(key) {
	window.localStorage.removeItem(key);
}
// localStorageに保存されているすべての値を削除する
function clear() {
	window.localStorage.clear();
}
