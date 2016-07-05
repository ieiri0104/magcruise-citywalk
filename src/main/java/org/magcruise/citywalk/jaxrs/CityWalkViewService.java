package org.magcruise.citywalk.jaxrs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;
import org.magcruise.citywalk.jsonrpc.impl.CityWalkSession;
import org.magcruise.citywalk.model.row.User;

@Path("/")
public class CityWalkViewService {

	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();
	@Context
	private HttpHeaders header;

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@Context
	private ServletContext servletContext;

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public Viewable index() {
		try {
			return new Viewable("/views/index.html");
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	/**
	 * Get a HTML file.
	 *
	 * @param pageName
	 *            the page name for get.
	 * @return the viewable object including the html file.
	 */
	@GET
	@Path("/{pageName}.html")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getHtml(@PathParam("pageName") String pageName) {
		try {
			return new Viewable("/views/" + pageName + ".html");
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}
	}

	@GET
	@Path("/checkpoints.html")
	@Produces(MediaType.TEXT_HTML)
	public Viewable getCheckpoints() {
		try {
			CityWalkSession s = getSession(request);
			if (s.isLogined()) {
				return new Viewable("/views/checkpoints.html", s.getUser());
			}

			return new Viewable("/views/checkpoints.html",
					new User("ieiri", "waseda"));
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}
	}

	private CityWalkSession getSession(HttpServletRequest request2) {
		return new CityWalkSession(request2);
	}

}