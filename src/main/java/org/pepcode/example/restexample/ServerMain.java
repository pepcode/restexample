package org.pepcode.example.restexample;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class ServerMain {

	static final String APP_PATH = "/restapi";

	static final String CONTEXT_ROOT = "/";

	public ServerMain() {
	}

	public static void main(String args[]) throws Exception {
		try {
			final int port = 5001;
			final Server server = new Server(port);

			final ServletContextHandler context = new ServletContextHandler(server, CONTEXT_ROOT);

			// Setup RESTEasy's HttpServletDispatcher at "/api/*".
			final ServletHolder restEasyServlet = new ServletHolder(new HttpServletDispatcher());
			restEasyServlet.setInitParameter("resteasy.servlet.mapping.prefix", APP_PATH);
			restEasyServlet.setInitParameter("javax.ws.rs.Application", "org.pepcode.example.restexample.App");
			context.addServlet(restEasyServlet, APP_PATH + "/*");

			// Setup the DefaultServlet at "/".
			final ServletHolder defaultServlet = new ServletHolder(new DefaultServlet());
			context.addServlet(defaultServlet, CONTEXT_ROOT);

			server.start();
			server.join();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
