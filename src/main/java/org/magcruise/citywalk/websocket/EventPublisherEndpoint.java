package org.magcruise.citywalk.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/activity/{checkpointGroupId}/{checkpointId}/{userId}")
public class EventPublisherEndpoint {

	private static EventPublisher publisher = new EventPublisher();

	@OnOpen
	public synchronized void onOpen(@PathParam("userId") String userId,
			@PathParam("checkpointGroupId") String checkpointGroupId,
			@PathParam("checkpointId") String checkpointId, Session session) {
		publisher.onOpen(userId, checkpointGroupId, checkpointId, session);
	}

	@OnClose
	public synchronized void onClose(@PathParam("userId") String userId,
			Session session) {
		publisher.onClose(userId, session);
	}

	@OnError
	public void onError(Session session, Throwable cause) {
		publisher.onError(session, cause);
	}

}