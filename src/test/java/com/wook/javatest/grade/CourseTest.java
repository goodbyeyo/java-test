package com.wook.javatest.grade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CourseTest {

    @Test
    @DisplayName("과목을 생성한다")
    void createTest() {
        assertThatCode(() -> new Course("운영체제", 3, "A"))
                .doesNotThrowAnyException();
    }


}
