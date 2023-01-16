package com.wook.javatest.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 음식점에서 음식 주문하는 과정 구현
 * 요구사항
 * 1. 도메인을 구성하는 객체에는 어떤것들이 있는지? - 손님, 메뉴판, 음식종류, 요리사, 요리
 * 2. 객체들간의 관계 고민 - 손님(메뉴판), 손님(요리사), 요리사(요리)
 * 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링하기 - 손님(손님타입), 음심들(요리타입), 메뉴판(메뉴판타입), 메뉴(메뉴타입)
 * 4. 협력 설계
 * 5. 객체를 포괄하는 타입에 적절한 책임을 할당
 * 6. 구현
 */
public class CustomerTest {

    @DisplayName("메뉴이름에 해당하는 요리를 주문")
    @Test
    void orderTest() {
        Customser customer = new Customser();
        Menu menu = new Menu(List.of(new MenuItem("초밥", 15000), new MenuItem("국밥", 10000)));
        Cooking cooking = new Cooking();

        assertThatCode(() -> customer.order("초밥", menu, cooking))
                .doesNotThrowAnyException();
    }
}
