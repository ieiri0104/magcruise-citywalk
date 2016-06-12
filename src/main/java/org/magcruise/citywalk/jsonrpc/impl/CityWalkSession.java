package org.magcruise.citywalk.jsonrpc.impl;

import javax.servlet.http.HttpSession;

public class CityWalkSession {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private HttpSession session;

	public CityWalkSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}

	public boolean isLogined() {
		if (getAttribute("userId") == null) {
			return false;
		} else {
			log.debug(session.getId());
			return true;
		}
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

}
