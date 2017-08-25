package com.robot.info;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class Teacher {

    private int teacherId;
    private String name;
    private String className;

    public Teacher() {
    }

    public Teacher(int teacherId, String name) {
        this.name = name;
        this.teacherId = teacherId;
    }

    public int getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
