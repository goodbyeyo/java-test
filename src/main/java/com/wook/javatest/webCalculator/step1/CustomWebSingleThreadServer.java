package com.wook.javatest.webCalculator.step1;

import com.wook.javatest.calculator.Calculator;
import com.wook.javatest.calculator.v2_interface.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebSingleThreadServer {

    private final int port;

    private final Logger logger = LoggerFactory.getLogger(CustomWebSingleThreadServer.class);

    public CustomWebSingleThreadServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {}, port", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected");

                /**
                 * Step 1 : 사용자 요청을 메인 Thread 가 처리
                 */
                try {
                    InputStream in = clientSocket.getInputStream();
                    OutputStream out = clientSocket.getOutputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        System.out.println(line);
//                    }
                    HttpRequest request = new HttpRequest(br);
                    if (request.isGetRequest() && request.matchPath("/calculate")) {
                        QueryStrings queryStrings = request.getQueryString();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate(
                                new PositiveNumber(operand1),
                                operator,
                                new PositiveNumber(operand2));
                        byte[] body = String.valueOf(result).getBytes();
                        HttpResponse response = new HttpResponse(dos);
                        response.response200Header("application/json", body.length);
                        response.responseBody(body);
                    }
                } catch (IOException e) {
                    logger.error("[CustomWebApplicationServer] Error reading");
                }
            }
        }

    }
}
