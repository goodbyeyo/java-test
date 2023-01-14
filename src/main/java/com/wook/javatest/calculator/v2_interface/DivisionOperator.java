package com.wook.javatest.calculator.v2_interface;

public class DivisionOperator implements NewArithmeticOperator {

    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operator1, PositiveNumber operator2) {
//        if (operator2.toInt() == 0) {
//            throw new IllegalArgumentException("0으로 나눌수 없습니다");
//        }
        return operator1.toInt() / operator2.toInt();
    }

}
