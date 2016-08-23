package org.magcruise.citywalk.jsonrpc.servlet;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.magcruise.citywalk.model.conv.CheckpointsAndTasksFactory;
import org.magcruise.citywalk.model.relation.BadgesTable;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.SubmittedActivitiesTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.relation.UserAccountsTable;
import org.magcruise.citywalk.model.relation.VerifiedActivitiesTable;
import org.nkjmlab.util.db.DbClient;
import org.nkjmlab.util.db.DbClientFactory;
import org.nkjmlab.util.db.H2ClientWithConnectionPool;
import org.nkjmlab.util.db.H2ConfigFactory;
import org.nkjmlab.util.db.H2Server;
import org.nkjmlab.util.io.FileUtils;

@WebListener
public class ApplicationInitializer implements ServletContextListener {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected static H2ClientWithConnectionPool client;

	static {
		H2Server.start();
		if (client == null) {
			client = DbClientFactory
					.createH2ClientWithConnectionPool(
							H2ConfigFactory.create(FileUtils.getTempFile("citywalk")));
		}
	}

	public static DbClient getDbClient() {
		return client;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		initializeDatabase(event);
		log.info("initialized");
	}

	/**
	 * Checkpoints table and tasks table are refreshed befoer initialize.
	 * @param event
	 */
	private void initializeDatabase(ServletContextEvent event) {
		new CheckpointsTable().dropTableIfExists();
		new TasksTable().dropTableIfExists();

		new CheckpointsTable().createTableIfNotExists();
		new TasksTable().createTableIfNotExists();
		new UserAccountsTable().createTableIfNotExists();
		new BadgesTable().createTableIfNotExists();
		new VerifiedActivitiesTable().createTableIfNotExists();
		new SubmittedActivitiesTable().createTableIfNotExists();

		Arrays.stream(new File(event.getServletContext().getRealPath("json/checkpoints-and-tasks/"))
				.listFiles((FilenameFilter) (dir, name) -> {
					return name.endsWith(".json");
				})).forEach(f -> CheckpointsAndTasksFactory.refreshAndInsertToDb(f.getPath()));
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		client.dispose();
		log.info("destroyed");
	}

}
