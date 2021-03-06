package org.magcruise.citywalk.jsonrpc.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.magcruise.citywalk.model.row.User;

public class CityWalkSession {
	private static final String USER_ID = "userId";

	private static final String GROUP_ID = "groupId";

	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private HttpSession session;

	public CityWalkSession(HttpServletRequest request) {
		this.session = request.getSession(true);
	}

	public HttpSession getSession() {
		return session;
	}

	public boolean isLogined() {
		if (getUserId().length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public User getUser() {
		return new User(getUserId(), getGroupId());
	}

	public String getGroupId() {
		return getAttribute(GROUP_ID) == null ? ""
				: getAttribute(GROUP_ID).toString();
	}

	public String getUserId() {
		return getAttribute(USER_ID) == null ? ""
				: getAttribute(USER_ID).toString();
	}

	public String getId() {
		return session.getId();
	}

	public Object getAttribute(String key) {
		return session.getAttribute(key);
	}

	public void invalidate() {
		session.invalidate();
	}

	public void setAttribute(String key, Object value) {
		session.setAttribute(key, value);
	}

	public void setMaxInactiveInterval(int maxInterval) {
		session.setMaxInactiveInterval(maxInterval);
	}

	public void setUserId(String userId) {
		session.setAttribute(USER_ID, userId);
	}

	public void setGroupId(String groupId) {
		session.setAttribute(GROUP_ID, groupId);
	}

}
