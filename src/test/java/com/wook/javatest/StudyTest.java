package com.wook.javatest;

import com.wook.javatest.study.Study;
import com.wook.javatest.study.StudyStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.Duration;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // under bar -> 공백으로 치환
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트가 하나의 인스턴스를 공유하도록 설정
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   // 테스트 순서 지정
//@ExtendWith(FindSlowTestExtension.class)
class StudyTest {

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Order(3)
    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")    // 한글 과 이모지 사용 가능
    @FastTest// @Tag("fast")
    void create_new_study() {     // public 사용할 필요없다
        System.out.println(this);   // 테스트 메서드마다 각각의 인스턴스 생성 -> 테스트간의 의존성 제거 목적
        Study study = new Study(10);

        assertNotNull(study);
        // assertEquals( 기대값, 실제파라미터, 실패메세지)
        // 테스트 실패, 성공과 관계없이 항상 문자열 연산 실행
        // assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면" + StudyStatus.DRAFT + "상태다");

        // 림다식으로 문자열을 생성하면 테스트가 실패했을때만 문자 연산을 실행한다
        assertAll(
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 처음 만들면\" + StudyStatus.DRAFT + \"상태다"),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다"),
                () -> assertNotNull(study)
        );
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
//            @Override
//            public String get() {
//                return "스터디를 처음 만들면 상태값이 DRAFT 상태다";
//            }
//        });
        System.out.println("create");
    }

    // @Test
    // @Tag("fast")
    @Test
    @Order(2)
    @Disabled
    void limit_test() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println(this);
        Study study = new Study(10);
        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다");
    }

    @Order(1)
    @Test
    @FastTest
    @DisplayName("Throw Test 😍")
    void throw_test(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals("Limit must be greater than zero", message);
    }

    @Test
    void timeout_test(){
        // 테스트 실행 기다리는 로직
        assertTimeout(Duration.ofSeconds(100), () -> {
            new Study(10);
            Thread.sleep(300);
        } );

        // timeout 시간 경과되면 즉각 실행 종료
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        // TODO ThreadLocal 사용하는 블럭이 있으면 예상하지 못한 결과 발생 할 수 있다
        // 다른 쓰레드와 쓰레드가 공유되지 않음, Spring 의 transactions -> rollback 설정
        // 즉 트랜잭션을 가지는 쓰레드와 별도의 쓰레드로 실행되기때문에 결과가 반영될수 있다
    }

    @Test
    void assertThat_test() {
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    void assume_test() {
        System.out.println(System.getenv("TEST_ENV"));

        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));

        assumingThat("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
            System.out.println("LOCAL");
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);

        });
        assumingThat("PROD".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
            System.out.println("PROD");
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void enabled_test() {
        System.out.println("Enabled Test :MAC, LINUX");
    }

    @Test
    @DisabledOnOs(OS.MAC)
    void disabled_test() {
        System.out.println("Disabled Test : MAC");
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11, JRE.JAVA_17})
    void enabledOnJre_test() {
        System.out.println("EnabledOnJre Test");
    }

    @Test
    @DisabledOnJre(JRE.OTHER)
    void disabledOnJre_test() {
        System.out.println("DisabledOnJre Test");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void enabledIfEnvironmentVariable_test() {
        System.out.println("EnabledIfEnvironmentVariable Test");
    }

    @DisplayName("반복테스트")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}") // 생략가능
    void repeat_test(RepetitionInfo repetitionInfo) {
        System.out.println("Repeat Test " + repetitionInfo.getCurrentRepetition() + "/"
                + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("파라미터 반복 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"12월", "22일은", "저의", "생일입니다"})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("파라미터 반복 테스트")
    @ParameterizedTest(name = "{index} {displayName} limit={0}")
    @ValueSource(ints = {10, 20, 30, 40})
    void value_source_parameterized_test(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            assertEquals(Study.class, aClass, "Can only convert to Study");
            return new Study(Integer.parseInt(o.toString()));
        }
    }

    @DisplayName("Csv 여러개의 인자값 전달 테스트")
    @ParameterizedTest(name = "{index} {displayName} limit={0}")
    @CsvSource({"10, Junit5 테스트", "20, CsvSource 테스트"})
    void csvSource_parameterized_test(Integer limit, String name) {
        System.out.println(new Study(limit, name));
    }

    @DisplayName("Csv ArgumentAccessor 이용한 방법 테스트")
    @ParameterizedTest(name = "{index} {displayName} limit={0}")
    @CsvSource({"10, Junit5 테스트", "20, CsvSource 테스트"})
    void csvSource_with_argumentsAccessor_parameterized_test(ArgumentsAccessor argumentsAccessor) {
        System.out.println(new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1)));
    }

    @DisplayName("Csv CustomAccessor 이용한 객체 바인딩 테스트")
    @ParameterizedTest(name = "{index} {displayName} limit={0}")
    @CsvSource({"10, Junit5 테스트", "20, CsvSource 테스트"})
    void csvSource_with_customAccessor_parameterized_test(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext)
                throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }


    @Test
    @Disabled
    void create_new_study_again() {
        System.out.println("create1");
    }

    @BeforeAll
    // static void beforeAll() {
    void beforeAll(){   // static 이지 않아도 된다 @TestInstance(Lifecycle.PER_CLASS)
        System.out.println("beforeAll");
    }

    @AfterAll
    // static void afterAll() {
    void afterAll(){ // static 이지 않아도 된다 @TestInstance(Lifecycle.PER_CLASS)
        System.out.println("afterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

}