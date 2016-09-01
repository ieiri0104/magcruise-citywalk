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

import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.mvc.Viewable;
import org.magcruise.citywalk.jsonrpc.CityWalkSession;
import org.nkjmlab.util.log4j.ServletLogManager;

@Path("/")
public class CityWalkViewService {

	protected static Logger log = ServletLogManager.getLogger();

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
		return getPage(pageName);
	}

	private Viewable getPage(String pageName) {
		try {
			switch (pageName) {
			case "login":
			case "index":
				return new Viewable("/views/" + pageName + ".html");
			default:
				CityWalkSession s = CityWalkSession.create(request);
				if (s.isLogined()) {
					return new Viewable("/views/" + pageName + ".html");
				}
				return new Viewable("/views/index.html");
			}

		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}

	}

}