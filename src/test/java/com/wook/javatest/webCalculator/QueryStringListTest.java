package com.wook.javatest.webCalculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringListTest {

    @Test
    void createTest() {
        // List<QueryString>
        QueryStrings queryStringList = new QueryStrings("operand1=7&operator=*&operand2=5");
        assertThat(queryStringList).isNotNull();

    }



}
