package org.magcruise.citywalk.jsonrpc.client;

import java.net.URL;

import org.magcruise.citywalk.jsonrpc.api.UserServiceInterface;

import jp.go.nict.langrid.client.jsonrpc.JsonRpcClientFactory;

public class UserServiceClient {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args) {
		try {
			URL url = new URL(
					"http://localhost:8080/magcruise-citywalk-server/UserService");

			UserServiceInterface client = create(url);
			client.login("ayaki", "houchimin");
			client.login("ayaki", "houchimin");
			client.login("ayaki", "houchimin");
			client.login("ayaki", "houchimin");
			client.login("ayaki", "houchimin");
			client.login("ayaki", "houchimin");
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public static UserServiceInterface create(URL url) {
		return new JsonRpcClientFactory().create(UserServiceInterface.class,
				url);
	}
}
