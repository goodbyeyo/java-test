package com.wook.javatest.study;

import com.wook.javatest.study.StudyStatus;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT; // 기본값 설정
    private int limit;
    private String name;

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Limit must be greater than zero");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }
}
