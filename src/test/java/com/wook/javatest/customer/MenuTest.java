package com.wook.javatest.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


public class MenuTest {

    @DisplayName("메뉴판에서 메뉴이름에 해당하는 메뉴를 반환한다")
    @Test
    void menuTest() {
        Menu menu = new Menu(List.of(new MenuItem("초밥", 15000), new MenuItem("국밥", 10000)));
        MenuItem menuItem = menu.choose("초밥");
        assertThat(menuItem).isEqualTo(new MenuItem("초밥", 15000));
    }

    @DisplayName("메뉴판에 없는 메뉴를 선택 할 경우 예외를 반환한다")
    @Test
    void chooseTest() {
        Menu menu = new Menu(List.of(new MenuItem("초밥", 15000), new MenuItem("국밥", 10000)));
        assertThatCode(() -> menu.choose("짜장면"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 메뉴 이름 입니다");
    }
}
