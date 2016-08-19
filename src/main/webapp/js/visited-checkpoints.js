$(function() {
	new JsonRpcClient(new JsonRpcRequest(getBaseUrl(), "getActivities", [ getUserId() ], function(data) {
		showCheckpoints(data.result)
	})).rpc();
});

function showCheckpoints(activities) {
	activities.forEach(function(activity) {
		// TODO
		console.log(activity.taskId);
	});
}