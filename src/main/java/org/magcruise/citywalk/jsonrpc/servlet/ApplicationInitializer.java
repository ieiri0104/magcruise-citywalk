package org.magcruise.citywalk.jsonrpc.servlet;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.magcruise.citywalk.model.table.Activities;
import org.magcruise.citywalk.model.table.Checkpoints;
import org.magcruise.citywalk.model.table.Tasks;
import org.magcruise.citywalk.model.table.Users;
import org.nkjmlab.util.db.DbClient;
import org.nkjmlab.util.db.DbClientFactory;
import org.nkjmlab.util.db.H2ClientWithConnectionPool;
import org.nkjmlab.util.db.H2ConfigFactory;
import org.nkjmlab.util.db.H2Server;

@WebListener
public class ApplicationInitializer implements ServletContextListener {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected static H2ClientWithConnectionPool client;

	static {
		H2Server.start();
		if (client == null) {
			File dbFile = new File(System.getProperty("java.io.tmpdir"),
					"citywalk");
			log.debug(dbFile);
			client = DbClientFactory.createH2ClientWithConnectionPool(
					H2ConfigFactory.create(dbFile));
		}

	}

	public static DbClient getDbClient() {
		return client;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		new Activities().createTableIfNotExists();
		new Tasks().createTableIfNotExists();
		new Users().createTableIfNotExists();
		new Checkpoints().createTableIfNotExists();
		log.info("initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		client.dispose();
		log.info("destroyed");
	}

}
