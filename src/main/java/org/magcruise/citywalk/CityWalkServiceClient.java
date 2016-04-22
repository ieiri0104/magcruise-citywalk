package org.magcruise.citywalk;

import java.net.URL;

import jp.go.nict.langrid.client.jsonrpc.JsonRpcClientFactory;

public class CityWalkServiceClient {
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args) {
		try {
			URL url = new URL(
					"http://localhost:8080/MAGCruiseCityWalkServer/json/PhotoRogainingService");

			CityWalkServiceInterface client = create(url);
			System.out.println(client.getActivities("ayaki"));
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public static CityWalkServiceInterface create(URL url) {
		return new JsonRpcClientFactory()
				.create(CityWalkServiceInterface.class, url);
	}
}
