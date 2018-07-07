package com.example.acer.teacher.Home;

public class ClassOutdata {
    private  String name;
    private  String subject;

    public ClassOutdata(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
