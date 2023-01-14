package com.wook.javatest.calculator.v2_interface;

public interface NewArithmeticOperator {
    boolean supports(String operator);

    int calculate(PositiveNumber operator1, PositiveNumber operator2);
}
