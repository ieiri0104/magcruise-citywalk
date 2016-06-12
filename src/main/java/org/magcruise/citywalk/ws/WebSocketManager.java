package org.magcruise.citywalk.ws;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.magcruise.citywalk.jsonrpc.impl.CityWalkService;
import org.magcruise.citywalk.model.Activity;

import jp.go.nict.langrid.repackaged.net.arnx.jsonic.JSON;

@ServerEndpoint("/websocket/newEvents/{userId}")
public class WebSocketManager {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();
	private static Map<String, ScheduledFuture<?>> workers = new ConcurrentHashMap<>();
	private static ScheduledExecutorService pool = Executors
			.newScheduledThreadPool(20);

	private static Map<String, Integer> latestActivityId = new ConcurrentHashMap<>();

	@OnOpen
	public synchronized void onOpen(@PathParam("userId") String userId,
			Session session) {

		if (workers.get(session.getId()) != null) {
			finlizeSession(session);
			return;
		}

		Basic b = session.getBasicRemote();

		ScheduledFuture<?> f = pool.scheduleWithFixedDelay(() -> {
			try {
				if (Thread.interrupted()) {
					return;
				}
				List<Activity> acts = new CityWalkService()
						.getNewActivitiesOrderById(userId,
								getLatestActivityId(userId));
				if (acts.size() > 0) {
					registerLatestActivityId(userId, acts);
					b.sendText(JSON.encode(acts));
				}
			} catch (IllegalStateException e) {
				log.error(e, e);
			} catch (Exception e) {
				log.error(e, e);
			}
		}, 0, 1, TimeUnit.SECONDS);

		workers.put(session.getId(), f);
	}

	private void registerLatestActivityId(String userId, List<Activity> acts) {
		latestActivityId.put(userId, acts.get(acts.size() - 1).getId());
	}

	private int getLatestActivityId(String userId) {
		latestActivityId.putIfAbsent(userId, -1);
		return latestActivityId.get(userId);
	}

	@OnClose
	public synchronized void onClose(@PathParam("userId") String userId,
			Session session) {

		finlizeSession(session);

	}

	private void finlizeSession(Session session) {
		try {
			session.close();
		} catch (IOException e) {
			log.error(e, e);
		}

		ScheduledFuture<?> f = workers.remove(session.getId());
		if (f != null) {
			f.cancel(true);
		}

	}

	@OnError
	public void onError(Session session, Throwable cause) {
		finlizeSession(session);
		log.error(cause, cause);
	}

}
