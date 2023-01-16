package com.wook.javatest.grade;

/**
 * 요구사항
 * 평균학점 계산 방법 = 학점수 * 교과목 평점 / 수강신청 총학점 수
 * 일급 컬렉션 사용
 */

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 1) 도메인을 구성하는 객체에는 어떤것들이 있는지 고민
 *  - 도메인 구성 객체 종류 :
 *    - 학점 계산기 도메인 : 이수한 과목, 학점 계산기
 * 2) 객체들 간의 관계 고민
 * 3) 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
 *   - 이수한 과목 : 객체지향프로그래밍, 자료구조, 중국어회화 -> 과목(코스)
 * 4) 협력 설계
 * 5) 객체들을 포괄하는 타입에 적절한 책임을 할당
 *   - 이수한 과목을 전달하여 평균학점 계산 요청
 *     -> 학점 계산기
 *       -> 학점수 * 교과목 평점 -> 과목(코스)
 *       -> 수강신청 총 학점 수  -> 과목(코스)
 * 6) 구현
 */

public class GradeCalculatorTest {

    @Test
    void calculateGradeTest() {
        // given
        List<Course> courses = List.of(
                new Course("운영체제", 2, "A+"), // 2 x 4.5 = 9
                new Course("자료구조", 3, "A"),  // 3 x 4.0 = 12.0
                new Course("알고리즘", 3, "B")); // 3 x 3.0 = 9.0

        GradeCalculator gradeCalculator = new GradeCalculator(courses);
//        GradeCalculator gradeCalculator2 = new GradeCalculator(new Courses(courses));

        // when
        double gradeResult = gradeCalculator.calculateGrade();

        // then
        assertThat(gradeResult).isEqualTo(3.75);
    }


}
