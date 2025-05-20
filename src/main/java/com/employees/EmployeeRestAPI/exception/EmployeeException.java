package com.employees.EmployeeRestAPI.exception;

public class EmployeeException {
    private int status;
    private String message;
    private long timestamps;

    public EmployeeException() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int id) {
        this.status= status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(long timestamps) {
        this.timestamps = timestamps;
    }

    public EmployeeException(int status, String message, long timestamps) {
        this.status = status;
        this.message = message;
        this.timestamps = timestamps;
    }
}
