package com.wook.javatest.servlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServerLauncher {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationServerLauncher.class);

    public static void main(String[] args) throws LifecycleException {
        // Embed Tomcat
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with baseDir : {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }

}
