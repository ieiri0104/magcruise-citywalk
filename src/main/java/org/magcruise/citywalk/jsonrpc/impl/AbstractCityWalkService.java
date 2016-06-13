package org.magcruise.citywalk.jsonrpc.impl;

import org.magcruise.citywalk.jsonrpc.servlet.ApplicationInitializer;
import org.nkjmlab.util.db.DbClient;

import jp.go.nict.langrid.commons.ws.ServletServiceContext;
import jp.go.nict.langrid.servicecontainer.service.AbstractService;

public class AbstractCityWalkService extends AbstractService {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected CityWalkSession getSession() {
		return new CityWalkSession(((ServletServiceContext) getServiceContext())
				.getRequest().getSession());
	}

	protected DbClient getClient() {
		return ApplicationInitializer.getDbClient();
	}
}
