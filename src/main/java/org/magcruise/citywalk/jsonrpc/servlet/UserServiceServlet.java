package org.magcruise.citywalk.jsonrpc.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.magcruise.citywalk.jsonrpc.impl.UserService;

import jp.go.nict.langrid.servicecontainer.handler.annotation.Service;
import jp.go.nict.langrid.servicecontainer.handler.annotation.Services;

@WebServlet(urlPatterns = "/UserService/*", initParams = {
		@WebInitParam(name = "dumpRequests", value = "false"),
		@WebInitParam(name = "additionalResponseHeaders", value = "Access-Control-Allow-Origin: *") })
@Services({ @Service(name = "UserService", impl = UserService.class) })
public class UserServiceServlet extends
		jp.go.nict.langrid.servicecontainer.handler.jsonrpc.servlet.JsonRpcServlet {

}
