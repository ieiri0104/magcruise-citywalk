package org.magcruise.citywalk.jaxrs.server;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;

/**
 * Application config crass. add {@code @ApplicationPath} Annotation.
 *
 * @author nkjm
 *
 */
public class ApplicationConfig extends ResourceConfig {
	public ApplicationConfig() {
		packages(this.getClass().getPackage().getName());
		register(ThymeleafTemplateProcessor.class);
		register(MvcFeature.class);
	}

}