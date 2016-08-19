/* Consntants */
var KEY_CITY_WALK_DATA = "city_walk_data";
var KEY_USER_ID        = "user_id";
/**************/

$(function() {
	setNavTitle();
});

function setNavTitle() {
	$("#nav-title").text(document.title);
}

function getParamDic() {
	var paramDic = {};
	var url = location.href;
	params  = url.split("?");
	if (params.length < 1) {
		return paramDic;
	}
	paramms = params[1].split("&");
	for (var i = 0; i < paramms.length; i++) {
		var tmp = paramms[i].split("=");
		paramDic[tmp[0]] = tmp[1];
	}
	return paramDic;
}

function getBaseUrl() {
	var u = parseUri(document.URL);
	var urlPrefix = u.protocol + "://" + u.authority + "/"
			+ u.directory.split("/")[1] + "/";
	return urlPrefix + "CityWalkService";
}

var CheckinType = {
	Photo : "PhotoTask",
	QR    : "QRCodeTask",
};

function getCheckinURL(checkpoint) {
	var suffix = "";
	switch (checkpoint.checkin.checkinType) {
	case CheckinType.Photo:
		suffix = "photo";
		break;
	case CheckinType.QR:
		suffix = "qr";
		break;
	default:
		break;
	}
	return "checkin-" + suffix + ".html?id=" + checkpoint.id;
}

var TaskType = {
	Selection   : "SelectionTask",
	Description : "DescriptionTask",
};

function getTaskURL(checkpoint) {
	var suffix = "";
	switch (checkpoint.task.taskType) {
	case TaskType.Selection:
		suffix = "selection";
		break;
	case TaskType.Description:
		suffix = "description";
		break;
	default:
		break;
	}
	return "task-" + suffix + ".html?id=" + checkpoint.id;
}

/* City Walk Data */
function fetchCityWalkData() {
	$.ajax({
		type: 'GET',
		url: '../json/initial_data.json',
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
	setItem(KEY_CITY_WALK_DATA, JSON.stringify(data));
}

function loadCityWalkData() {
	return JSON.parse(getItem(KEY_CITY_WALK_DATA));
}

function getCheckpoints() {
	return loadCityWalkData()["checkpoints"];
}

function getCheckpoint(id) {
	var array = getCheckpoints().filter(function(c) {
		return c.id == id;
	});
	if (array.length == 1) {
		return array[0];
	}
	return null;
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

/* Session Storage */
function setUserId(val) {
	window.sessionStorage.setItem(KEY_USER_ID, val);
}
function getUserId() {
	return window.sessionStorage.getItem(KEY_USER_ID);
}

/* Geo */
function floatFormat(number, n) {
	var _pow = Math.pow(10 , n);
	return Math.round(number * _pow) / _pow;
}