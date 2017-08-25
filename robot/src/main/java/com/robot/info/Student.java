package com.robot.info;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class Student {
    private int studendtd;
    private String name;
    private String className;

    public Student() {
    }

    public Student(int studentId, String name) {
        this.name = name;
        this.studendtd = studentId;
    }

    public int getStudentId() {
        return this.studendtd;
    }

    public void setStudendId(int studentId) {
        this.studendtd = studendtd;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String name) {
        this.className = name;
    }
}