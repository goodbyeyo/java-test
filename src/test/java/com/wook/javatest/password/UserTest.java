package com.wook.javatest.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @DisplayName("패스워드 초기화한다")
    @Test
    void initPasswordTest() {
        // given
        User user = new User();

        // when
        // user.initPassword(new CorrectFixedPasswordGenerator());
        user.initPassword(() -> "connectPw"); // ramda 를 이용해서 구현체 생략

        // then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합하지 않아 초기화가 되지 않는다")
    @Test
    void initPasswordFailTest() {
        // given
        User user = new User();

        // when
        // user.initPassword(new WrongFixedPasswordGenerator());
        user.initPassword(() -> "pw");  // ramda 를 이용해서 구현체 생략

        // then
        assertThat(user.getPassword()).isNull();
    }
}