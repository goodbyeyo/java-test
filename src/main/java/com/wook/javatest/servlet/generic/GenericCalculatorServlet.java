package com.wook.javatest.servlet.generic;

import com.wook.javatest.calculator.Calculator;
import com.wook.javatest.calculator.v2_interface.PositiveNumber;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class GenericCalculatorServlet extends GenericServlet {

    private static final Logger log = LoggerFactory.getLogger(GenericCalculatorServlet.class);


    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

}
