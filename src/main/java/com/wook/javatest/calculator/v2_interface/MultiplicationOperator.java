package com.wook.javatest.calculator.v2_interface;

public class MultiplicationOperator implements NewArithmeticOperator {

    @Override
    public boolean supports(String operator) {
        return "*".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operator1, PositiveNumber operator2) {
        return operator1.toInt() * operator2.toInt();
    }
}
