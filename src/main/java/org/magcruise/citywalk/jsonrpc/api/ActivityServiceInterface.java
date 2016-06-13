package org.magcruise.citywalk.jsonrpc.api;

import java.util.List;

import org.magcruise.citywalk.model.Activity;
import org.magcruise.citywalk.model.Input;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface ActivityServiceInterface {

	void addActivity(@Parameter(sample = "ayaki") String userId,
			@Parameter(sample = "activity_0") String activityId,
			@Parameter(sample = "100") int score,
			@Parameter(sample = "{\"instanceClass\": \"selecgt\"}") Input inputs);

	void addActivity(
			@Parameter(sample = "{\"userId\": \"ayaki\", \"taskId\": \"task2\", \"score\": 9}") Activity activity);

	/**
	 * JavaScriptと通信する場合，返り値の実際の型が，ここで指定した型のサブタイプだったとしても，ここで指定した返り値の型(
	 * をMapに読み替えたもの)で返る．
	 *
	 * @param userId
	 * @return
	 */
	List<Activity> getActivities(@Parameter(sample = "ayaki") String userId);

	List<Activity> getNewActivitiesOrderById(String userId,
			int latestActivityId);

}
