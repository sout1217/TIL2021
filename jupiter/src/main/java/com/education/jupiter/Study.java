package com.education.jupiter;

public class Study {

    private StudyStatus status;

    private int limit;

    public Study(int limit) {
        this.limit  = limit;

        throw new IllegalArgumentException("0보다 커야 된다");
    }

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
