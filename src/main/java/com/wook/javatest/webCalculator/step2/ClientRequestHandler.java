package com.wook.javatest.webCalculator.step2;

import com.wook.javatest.calculator.Calculator;
import com.wook.javatest.calculator.v2_interface.PositiveNumber;
import com.wook.javatest.webCalculator.step1.HttpRequest;
import com.wook.javatest.webCalculator.step1.HttpResponse;
import com.wook.javatest.webCalculator.step1.QueryStrings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

// Runnable 을 구현한 구현체 -> Thread 생성
public class ClientRequestHandler implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Step 2 : 사용자 요청이 들어올때마다 Thread 를 새로 생성해서 사용자 요청을 처리
     */
    @Override
    public void run() {
        logger.info("[ClientRequestHandler] new Client {} started", Thread.currentThread().getName());

        try (InputStream in = clientSocket.getInputStream();
             OutputStream out = clientSocket.getOutputStream()){

            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            DataOutputStream dos = new DataOutputStream(out);

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
            logger.error("[ClientRequestHandler] Error reading");
        }
    }
}
