var id = getParamDic()["id"];
var checkpoint = getCheckpoint(id);

$(function() {
	var task = checkpoint.task;
	$("#label").text(task.label);

	var answerSel = "#answer-text";
	var buttonSel = "#btn-next";
	// 回答の変更を監視
	function checkChange(e){
	    var old = v = $(e).find(answerSel).val();
	    return function(){
	        v = $(e).find(answerSel).val();
	        if(old != v){
	            old = v;
	            $(buttonSel).prop("disabled", (v.length == 0));
	        }
	    }
	}
	$(answerSel).keyup(checkChange(this));
	
	$(buttonSel).click(function() {
		var value = $(answerSel).val();
		if (task.answer_texts.indexOf(value) >= 0) {
			alert("正解です。タスク完了！");
		} else {
			alert("不正解です。もう一度調査しなおして下さい。");
		}
	});
});
