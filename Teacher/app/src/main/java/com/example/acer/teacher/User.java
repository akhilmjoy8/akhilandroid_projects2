package com.example.acer.teacher;

public class User {
    private String uid;
    private  String id;
    private  String name;
    private  String subject;
    private String count;

    public User(String uid,String id, String name, String count) {
        this.uid =uid;
        this.id = id;
        this.name = name;
        this.count = count;

    }

    public User(String subject) {
        this.subject = subject;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public User(){

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
