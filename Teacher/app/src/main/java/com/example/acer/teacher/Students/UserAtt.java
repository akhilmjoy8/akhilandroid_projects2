package com.example.acer.teacher.Students;

public class UserAtt {
    private  String subject;
    private String Status;

    public UserAtt(String subject, String status) {
        this.subject = subject;
        Status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
