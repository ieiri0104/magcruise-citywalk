$(function() {
	var u = parseUri(document.URL);
	var urlPrefix = u.protocol + "://" + u.authority + "/" + u.directory.split("/")[1] + "/";
	var url = urlPrefix + "CityWalkService";
	$('#login-btn').on('click', function() {
		for (var i = 0; i < $('input').size(); i++) {
			if (!$('input')[i].checkValidity()) {
				$('#submit-for-validation').trigger("click");
				return;
			}
		}
		var userId = $('#user-id').val();
		var groupId = $('#group-id').val();
		new JsonRpcClient(new JsonRpcRequest(url, "login", [
				userId, groupId ], function() {
			location.href = "checkpoints.html";
		})).rpc();
	});
});
