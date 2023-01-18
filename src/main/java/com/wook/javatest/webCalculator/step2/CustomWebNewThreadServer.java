package com.wook.javatest.webCalculator.step2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomWebNewThreadServer {

    private final int port;

    private final Logger logger = LoggerFactory.getLogger(CustomWebNewThreadServer.class);

    public CustomWebNewThreadServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {}, port", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                /*
                 * Step 2 : 사용자 요청이 들어올때마다 Thread 를 새로 생성해서 사용자 요청을 처리
                 * - 문제점1)
                 *   - Thread 는 생성될때마다 독림적인 static memory 를 할당받게 되므로
                 *   - 메모리 할당작업은 비싼 작업이므로 사용자의 요청이 있을때마다
                 *   - Thread 를 생성하게 되면 성능 이슈가 생길 수 있다
                 * - 문제점2)
                 *   - 동시접속자 수가 많으면 Thread 수도 많아지게 되는데
                 *   - 쓰레드 수가 많으면 CPU Context 스위칭 횟수, CPU, Memory 사용량이 증가하게 됨
                 *   - 최악의 경우에는 서버의 리소스 한계로 인해서 서버가 다운될수도 있다
                 */
                logger.info("[CustomWebApplicationServer] client connected");
                new Thread(new ClientRequestHandler(clientSocket)).start();
            }
        }

    }
}
