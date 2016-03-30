package com.fisherevans.utilapi;

import com.fisherevans.utilapi.rest.Hello;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);


    private static final int DEFAULT_PORT = 8080;

    private int serverPort;

    public App(int serverPort) throws Exception {
        logger.info("Starting server...");
        this.serverPort = serverPort;
        Server server = configureServer();
        server.start();
        logger.info("Server started");
        server.join();
    }

    private Server configureServer() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(Hello.class.getPackage().getName());
        resourceConfig.register(JacksonFeature.class);
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder sh = new ServletHolder(servletContainer);
        Server server = new Server(serverPort);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(sh, "/*");
        server.setHandler(context);
        return server;
    }

    public static void main(String[] args) throws Exception {
        int serverPort = DEFAULT_PORT;
        if(args.length >= 1) {
            try {
                serverPort = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.error("Failed to start", e);
            }
        }
        new App(serverPort);
    }
}
