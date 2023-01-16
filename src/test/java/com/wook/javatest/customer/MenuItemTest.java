package com.wook.javatest.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuItemTest {

    @DisplayName("메뉴항목 생성")
    @Test
    void createTest() {
        assertThatCode(() -> new MenuItem("초밥", 15000))
                .doesNotThrowAnyException();
    }
}
