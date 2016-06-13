package org.magcruise.citywalk.jsonrpc.impl;

import java.io.File;

import org.nkjmlab.util.db.DbClient;
import org.nkjmlab.util.db.DbClientFactory;
import org.nkjmlab.util.db.H2ConfigFactory;
import org.nkjmlab.util.db.H2Server;

import jp.go.nict.langrid.commons.ws.ServletServiceContext;
import jp.go.nict.langrid.servicecontainer.service.AbstractService;

public class AbstractCityWalkService extends AbstractService {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected static DbClient client;

	static {
		H2Server.startFromServlet();
		File dbFile = new File(System.getProperty("java.io.tmpdir"),
				"citywalk");
		log.debug(dbFile);
		client = DbClientFactory.createH2Client(H2ConfigFactory.create(dbFile));
	}

	protected CityWalkSession getSession() {
		return new CityWalkSession(((ServletServiceContext) getServiceContext())
				.getRequest().getSession());
	}

}
