package com.wook.javatest.grade;

import java.util.List;

public class Courses {

    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    // 학점 * 평점 -> 총합
    public double multiplyCreditAndCourseGrade() {
        return courses.stream()
                .mapToDouble(Course::multipliedCreditAndCourseGrade)
                .sum();
//        double multipliedCreditAndCourseGrade = 0;
//        for (Course course : courses) {
//
//            multipliedCreditAndCourseGrade += course.multipliedCreditAndCourseGrade();
//        }
//        return multipliedCreditAndCourseGrade;
    }

    // 학점 수 총합
    public int calculateTotalCredit() {
        return courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
    }
}
