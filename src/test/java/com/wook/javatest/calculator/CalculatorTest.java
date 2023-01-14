package com.wook.javatest.calculator;

import com.wook.javatest.calculator.v2_interface.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 사직연산 테스트
 * 1) 양수로만 계산 가능
 * 2) 나웃셈에서 0을 나누는 경우 IllegalArgument 예외 발생
 * 3) MVC 패턴 기반으로 구현
 */
public class CalculatorTest {

    @DisplayName("덧셈 연산을 수행")
    @Test
    void additionalTest() {
        int calculateResult = Calculator.calculate(new PositiveNumber(1), "+", new PositiveNumber(2));
        assertThat(calculateResult).isEqualTo(3);
    }

    @DisplayName("뺄셈 연산을 수행")
    @Test
    void subtractionTest() {
        int calculateResult = Calculator.calculate(new PositiveNumber(3), "-", new PositiveNumber(2));
        assertThat(calculateResult).isEqualTo(1);
    }

    @DisplayName("전체 연산을 수행한다")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate(
                new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        assertThat(result).isEqualTo(calculateResult);
    }

    private Stream<Arguments> formulaAndResult() {
        return Stream.of(
                Arguments.arguments(1, "+", 2, 3),
                Arguments.arguments(3, "-", 2, 1),
                Arguments.arguments(3, "*", 4, 12),
                Arguments.arguments(6, "/", 2, 3)
        );
    }
}
