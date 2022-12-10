package com.wook.javatest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

// @RunWith(SpringBootTest.class)    // JUnit5 > 불필요
@SpringBootTest     // Junit -> Meta annotation 사용가능
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsAutoConfiguration.class)
@ActiveProfiles("test")
@Disabled  // @Ignore // class ignore -> @Disabled
public class StudyJUnit4Test {

    @BeforeEach// @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void createTest() {
        System.out.println("create test");
    }

    @Test
    public void createTest2() {
        System.out.println("create test2");
    }



}
