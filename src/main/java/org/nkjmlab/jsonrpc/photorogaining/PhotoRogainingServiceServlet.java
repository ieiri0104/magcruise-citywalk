package org.nkjmlab.jsonrpc.photorogaining;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import jp.go.nict.langrid.servicecontainer.handler.annotation.Service;
import jp.go.nict.langrid.servicecontainer.handler.annotation.Services;

@WebServlet(urlPatterns = "/json/*", initParams = {
		@WebInitParam(name = "dumpRequests", value = "true"),
		@WebInitParam(name = "additionalResponseHeaders", value = "Access-Control-Allow-Origin: *") })
@Services({
		@Service(name = "PhotoRogainingService", impl = PhotoRogainingService.class) })
public class PhotoRogainingServiceServlet extends
		jp.go.nict.langrid.servicecontainer.handler.jsonrpc.servlet.JsonRpcServlet {
}
