package org.nkjmlab.jsonrpc.photorogaining;

import java.io.File;
import java.util.List;

import org.nkjmlab.util.rdb.RDBConfig;
import org.nkjmlab.util.rdb.RDBUtil;
import org.nkjmlab.util.rdb.RDBUtilWithConnectionPool;

public class PhotoRogainingService implements PhotoRogainingServiceInterface {
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private static RDBUtil util;

	static {
		String dbFile = new File(System.getProperty("java.io.tmpdir"),
				"kyoto-photorogaining").toString();
		log.debug(dbFile);
		util = new RDBUtilWithConnectionPool(
				new RDBConfig("jdbc:h2:tcp://localhost/" + dbFile, "sa", ""));
		util.dropIfExists(Activity.TABLE_NAME);
		util.createTableIfNotExists(Activity.TABLE_NAME
				+ "(id int primary key auto_increment, user_id varchar, task_id varchar, score int)");
	}

	@Override
	public void insertActivity(String userId, String taskId, int score) {
		log.debug("{},{},{}", userId, taskId, score);
		util.insert(new Activity(userId, taskId, score));
	}

	@Override
	public void addActivity(Activity activity) {
		util.insert(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return util.readList(Activity.class,
				"SELECT * FROM " + Activity.TABLE_NAME + " WHERE user_id=?",
				userId);
	}

	@Override
	public void testException() {
		throw new RuntimeException("ERROR");
	}

	public List<Activity> getNewActivitiesOrderById(String userId,
			int latestActivityId) {
		return util.readList(Activity.class,
				"SELECT * FROM " + Activity.TABLE_NAME
						+ " WHERE user_id=? AND id>? ORDER BY id",
				userId, latestActivityId);
	}

}
