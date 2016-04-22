package org.magcruise.citywalk;

import java.util.List;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface CityWalkServiceInterface {

	public void insertActivity(@Parameter(sample = "ayaki") String userId,
			@Parameter(sample = "activity_0") String activityId,
			@Parameter(sample = "100") int score);

	public void addActivity(
			@Parameter(sample = "{\"userId\": \"ayaki\", \"taskId\": \"task2\", \"score\": 9}") Activity activity);

	/**
	 * JavaScriptと通信する場合，返り値の実際の型が，ここで指定した型のサブタイプだったとしても，ここで指定した返り値の型(
	 * をMapに読み替えたもの)で返る．
	 *
	 * @param userId
	 * @return
	 */
	public List<Activity> getActivities(
			@Parameter(sample = "ayaki") String userId);

	void testException();

}
