package org.magcruise.citywalk.rs.server;

import org.glassfish.jersey.filter.LoggingFilter;
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
		register(LoggingFilter.class);
	}

}