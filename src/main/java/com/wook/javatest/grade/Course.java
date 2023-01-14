package com.wook.javatest.grade;

public class Course {

    private final String subject;   // 과목명
    private final int credit; //    // 학점
    private final String grade;    // 성적

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public double getGradeToNumber() {
        double grade = 0;
        switch (this.grade) {
            case "A+" -> grade = 4.5;
            case "A" -> grade = 4.0;
            case "B+" -> grade = 3.5;
            case "B" -> grade = 3.0;
            case "C+" -> grade = 2.5;
            case "C" -> grade = 2.0;
            case "D+" -> grade = 1.5;
            case "D" -> grade =  1.0;
            default -> grade = 0;
        }
        return grade;
    }

    public int getCredit() {
        return credit;
    }

    public double multipliedCreditAndCourseGrade() {
        return credit * getGradeToNumber();
    }
}
