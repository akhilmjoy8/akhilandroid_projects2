package com.example.acer.teacher.Students;

public class Student {
    String Sname;
    String phno;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student(String sname, String phno, String id) {
        Sname = sname;
        this.phno = phno;
        this.id = id;
    }

    public Student(String sname, String phno) {
        Sname = sname;
        this.phno = phno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
