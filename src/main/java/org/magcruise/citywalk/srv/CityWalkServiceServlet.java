package org.magcruise.citywalk.srv;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import jp.go.nict.langrid.servicecontainer.handler.annotation.Service;
import jp.go.nict.langrid.servicecontainer.handler.annotation.Services;

@WebServlet(urlPatterns = "/json/*", initParams = {
		@WebInitParam(name = "dumpRequests", value = "false"),
		@WebInitParam(name = "additionalResponseHeaders", value = "Access-Control-Allow-Origin: *") })
@Services({
		@Service(name = "CityWalkService", impl = CityWalkService.class) })
public class CityWalkServiceServlet extends
		jp.go.nict.langrid.servicecontainer.handler.jsonrpc.servlet.JsonRpcServlet {

}