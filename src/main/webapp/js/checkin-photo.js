var id = getParamDic()["id"];
var checkpoint = getCheckpoint(id);

$(function() {
	$("#btn-next").click(function() {
		location.href = getTaskURL(checkpoint); // util.js
	});
});

function handleFiles(files) {
	if (files == null || files.length == 0 || files[0] == null) {
		alert("画像を取得できませんでした。");
		return;
	}
	var file = files[0];
	var fileReader = new FileReader();
	fileReader.onload = function(event) {
		$("#img-preview").attr('src', event.target.result);
	};
	fileReader.readAsDataURL(file);
	
	$("#btn-next").prop("disabled", false);
}