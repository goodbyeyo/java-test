package com.wook.javatest.webCalculator.step3;

import com.wook.javatest.webCalculator.step2.ClientRequestHandler;
import com.wook.javatest.webCalculator.step2.CustomWebNewThreadServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebThreadPoolServer {

    private final Logger logger = LoggerFactory.getLogger(CustomWebNewThreadServer.class);
    private final int port;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public CustomWebThreadPoolServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebThreadPoolServer] started {}, port", port);

            Socket clientSocket;
            logger.info("[CustomWebThreadPoolServer] waiting for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebThreadPoolServer] client connected");
                /*
                 * Thread Pool 을 적용해 안정적인 서비스가 가능
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }

    }
}
