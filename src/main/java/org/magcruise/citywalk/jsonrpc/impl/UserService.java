package org.magcruise.citywalk.jsonrpc.impl;

import org.magcruise.citywalk.jsonrpc.api.UserServiceInterface;

public class UserService extends AbstractCityWalkService
		implements UserServiceInterface {

	@Override
	public void login(String userId, String groupId) {
		CityWalkSession session = getSession();
		if (session.isLogined()) {
			log.debug(session.getId());
			log.debug("already logined as {}", session.getAttribute("userId"));
			session.invalidate();
			log.debug("session will be invalidate.");
		} else {
			log.debug("create new session for {}", userId);
			session.setMaxInactiveInterval(10 * 60 * 60);
			session.setAttribute("userId", userId);
		}
	}
}
