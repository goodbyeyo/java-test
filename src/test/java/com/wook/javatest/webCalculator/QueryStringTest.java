package com.wook.javatest.webCalculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest {
    @Test
    void createTEst() {
        QueryString queryString = new QueryString("operand1", "10");
        assertThat(queryString).isNotNull();
    }
}
