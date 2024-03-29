package com.wook.javatest;

import com.wook.javatest.webCalculator.step3.CustomWebThreadPoolServer;

import java.io.IOException;

// GET /calcauldate?operand1=7&operator=*&operand2=5 return 35

/**
 * 사용자 요청을 메인쓰레드가 처리하도록 구현
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // new CustomWebApplicationServer(8080).start();
        // new CustomWebNewThreadServer(8080).start();
        new CustomWebThreadPoolServer(8080).start();
    }
}
