package org.magcruise.citywalk.jsonrpc.impl;

import org.apache.logging.log4j.Logger;
import org.nkjmlab.util.log4j.ServletLogManager;

import jp.go.nict.langrid.commons.ws.ServletServiceContext;
import jp.go.nict.langrid.servicecontainer.service.AbstractService;

public class AbstractCityWalkService extends AbstractService {

	protected static Logger log = ServletLogManager.getLogger();

	protected CityWalkSession getSession() {
		return new CityWalkSession(
				((ServletServiceContext) getServiceContext()).getRequest());
	}

}
