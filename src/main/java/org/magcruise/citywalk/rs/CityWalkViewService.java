package org.magcruise.citywalk.rs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.mvc.Viewable;

@Path("/")
public class CityWalkViewService {

	private static Logger log = LogManager.getLogger();

	@Context
	private HttpHeaders header;

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@Context
	private ServletContext servletContext;

	public static void main(String[] args) {
		final Response response = ClientBuilder.newClient() //
				.target("http://localhost:8080/magcruise-citywalk-server/")
				.path("/hello/text") // リクエストを投げるURLのパス部分
				.request(MediaType.TEXT_PLAIN_TYPE) // 受け入れ可能なレスポンス形式(HTTPヘッダでいうAcceptに相当)
				// application/x-www-form-urlencoded形式でデータを作成(HTTPヘッダでいうContent-Typeに相当)し、リクエストを送信.
				.post(Entity.entity("text=hoge",
						MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		System.out.println(response.getStatusInfo());

	}

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
			return null;
		}
	}

	@GET
	@Path("/citywalk/{city}/")
	@Produces(MediaType.TEXT_HTML)
	public Viewable cityWalk(@PathParam("city") String city) {
		try {
			return new Viewable("/views/citywalk.html");
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

}