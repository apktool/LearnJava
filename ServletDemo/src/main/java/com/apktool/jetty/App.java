package com.apktool.jetty;

import com.apktool.jetty.spring.configuration.MvcConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author apktool
 * @package com.apktool.jetty
 * @class App
 * @description TODO
 * @date 2020-08-28 00:21
 */
public class App {
    public void run() throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SECURITY | ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        context = getJerseyHandler(context);
        context = getSpringHandler(context);

        server.setHandler(context);
        server.start();
        server.join();
    }


    /**
     * curl -X GET http://localhost:8080/jersey/hello/ping
     *
     * @param context
     * @return
     */
    private ServletContextHandler getJerseyHandler(ServletContextHandler context) {
        ServletHolder holder = context.addServlet(ServletContainer.class, "/jersey/*");
        holder.setInitOrder(1);
        holder.setInitParameter("jersey.config.server.provider.packages", "com.apktool.jetty.jersey.controller");

        return context;
    }


    /**
     * curl -X GET http://localhost:8080/spring/hello/ping
     *
     * @param context
     * @return
     */
    private ServletContextHandler getSpringHandler(ServletContextHandler context) {
        AnnotationConfigWebApplicationContext apiDispatcherContext = new AnnotationConfigWebApplicationContext();
        DispatcherServlet apiDispatcherServlet = new DispatcherServlet(apiDispatcherContext);
        apiDispatcherContext.register(MvcConfiguration.class);

        ServletHolder holder = new ServletHolder(apiDispatcherServlet);
        holder.setInitOrder(3);
        context.addServlet(holder, "/spring/*");

        return context;
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }
}
