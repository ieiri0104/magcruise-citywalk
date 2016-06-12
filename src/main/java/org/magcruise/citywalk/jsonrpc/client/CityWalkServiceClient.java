package org.magcruise.citywalk.jsonrpc.client;

import java.net.URL;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;

import jp.go.nict.langrid.client.jsonrpc.JsonRpcClientFactory;

public class CityWalkServiceClient {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args) {
		try {
			URL url = new URL(
					"http://localhost:8080/magcruise-citywalk-server/json/magcruise-citywalk-server");

			CityWalkServiceInterface client = create(url);
			log.debug(client.getActivities("ayaki"));
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public static CityWalkServiceInterface create(URL url) {
		return new JsonRpcClientFactory().create(CityWalkServiceInterface.class,
				url);
	}
}
