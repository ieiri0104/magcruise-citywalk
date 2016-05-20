package org.magcruise.citywalk.srv;

import java.io.File;
import java.util.List;

import org.magcruise.citywalk.model.Activity;
import org.nkjmlab.util.db.DbClient;
import org.nkjmlab.util.db.DbClientFactory;
import org.nkjmlab.util.db.H2ConfigFactory;
import org.nkjmlab.util.db.H2Server;

public class CityWalkService implements CityWalkServiceInterface {
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private static DbClient client;

	static {
		H2Server.startFromServlet();
		File dbFile = new File(System.getProperty("java.io.tmpdir"),
				"kyoto-citywalk");
		log.debug(dbFile);
		client = DbClientFactory.createH2Client(H2ConfigFactory.create(dbFile));
		client.dropTableIfExists(Activity.TABLE_NAME);
		client.createTableIfNotExists(Activity.TABLE_NAME
				+ "(id int primary key auto_increment, user_id varchar, task_id varchar, score int)");
	}

	@Override
	public void insertActivity(String userId, String taskId, int score) {
		log.debug("{},{},{}", userId, taskId, score);
		client.insert(new Activity(userId, taskId, score));
	}

	@Override
	public void addActivity(Activity activity) {
		client.insert(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return client.readList(Activity.class,
				"SELECT * FROM " + Activity.TABLE_NAME + " WHERE user_id=?",
				userId);
	}

	@Override
	public void testException() {
		throw new RuntimeException("ERROR");
	}

	public List<Activity> getNewActivitiesOrderById(String userId,
			int latestActivityId) {
		return client.readList(Activity.class,
				"SELECT * FROM " + Activity.TABLE_NAME
						+ " WHERE user_id=? AND id>? ORDER BY id",
				userId, latestActivityId);
	}

}
