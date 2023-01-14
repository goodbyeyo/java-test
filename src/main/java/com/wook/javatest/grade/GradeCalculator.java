package com.wook.javatest.grade;

import java.util.List;

public class GradeCalculator {

    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public GradeCalculator(Courses courses) {
        this.courses = courses;
    }
    //        private final List<Course> courses;

//    public GradeCalculator(List<Course> courses) {
//        this.courses = courses;
//    }

    public double calculateGrade() {

        // 1급 Collection 사용
        double multipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
        int totalCredit = courses.calculateTotalCredit();

        // 이수 학점 * 이수 과목 평점 합계
//        double multipliedCreditAndCourseGrade = 0;
//        for (Course course : courses) {
//            // 이수 학점 이수 과목 총합을 GradeCalculator 에서 계산하는것은 응집도가 낮다
//            // 즉 이부분을 다른 모듈에서도 사용 할 경우 로직에 수정이 발생하는경우 다같이 수정해야함
//            // 따라서 계산 부분의 역할을 Course 에게 위임한다
//            // multipliedCreditAndCourseGrade += course.getCredit() * course.getGradeToNumber();
//            multipliedCreditAndCourseGrade += course.multipliedCreditAndCourseGrade();
//        }

        // 이수 학점 총합
//        int totalCredit = courses.stream()
//                .mapToInt(Course::getCredit)
//                .sum();

        return multipliedCreditAndCourseGrade / totalCredit;
    }
}
