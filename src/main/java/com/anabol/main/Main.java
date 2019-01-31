package com.anabol.main;

import com.anabol.servlets.UserServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import com.anabol.servlets.AllRequestsServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        UserServlet userServlet = new UserServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/example");
        context.addServlet(new ServletHolder(userServlet), "/users");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}