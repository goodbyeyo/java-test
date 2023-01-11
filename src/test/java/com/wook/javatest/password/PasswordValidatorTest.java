package com.wook.javatest.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 비밀번호는 최소 8자 이상 12자 이하 여야 한다
 * 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외 발생
 * 경계조건에 대한 테스트 코드 작성
 */
public class PasswordValidatorTest {

    @DisplayName("비밀번호가 최소 8자 이상, 12자 자리 이하면 예외가 발생하지 않는다")
    @Test
    void validatePasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("password"))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만 또는 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"aaabbbc","aaabbbcccddd"})
    @Test
    void validatePasswordTestAgain() {
        assertThatCode(() -> PasswordValidator.validate("shortPw"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 8자리 이상 12자리 이하여야 합니다");
    }


}
