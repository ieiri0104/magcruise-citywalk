var id  = getParamDic()["id"];
var lat = getParamDic()["lat"];
var lon = getParamDic()["lon"];
var checkpoint = getCheckpoint(id);

$(function() {
	var task = checkpoint.task;
	$("#label").text(task.label);
	
	var selectionType = (task.answerIndexes.length == 1) ? "radio" : "checkbox"
	task.selections.forEach(function(selection, i) {
		var selectionElem =
			'<div class="selection">' + 
				'<label><input type="' + selectionType + '" name="selection" class="selection" value=' + i + '>' + selection + '</label>'
			'</div>';
		$(".form-group").append(selectionElem);
	});
	
	$(".selection").click(function() {
		var enableBtnNext = false;
		// 一つでもチェックがあれば、回答するボタンを押せるように
		// [name=selection]
		$(".selection").each(function() {
			enableBtnNext = (enableBtnNext || $(this).prop('checked'));
		});
		$("#btn-next").prop("disabled", !enableBtnNext);
	});
	
	$("#btn-next").click(function() {
		// 回答を取得
		var indexes = $('.selection:checked').map(function() {
			return parseInt($(this).val());
		}).get();
		addActivity(task, indexes);
	});
});

function addActivity(task, indexes) {
	var isCorrect = isSameElements(task.answerIndexes, indexes);
	var arg = {
		lat		: lat,
		lon		: lon,
		userId	: getUserId(),
		taskId	: task.id,
		score	: (isCorrect) ? task.score : 0,
		inputs	: {
			instanceClass	: task.instanceClass,
			value			: indexes.sort().toString()
		}
	};
	new JsonRpcClient(new JsonRpcRequest(getBaseUrl(), "addActivity",
		[ arg ], function() {
			location.href = "./checkpoints.html";
		})).rpc();
}

function isSameElements(array1, array2) {
	return array1.sort().toString() === array2.sort().toString();
}
