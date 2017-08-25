package com.robot.info;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class ClassInfo {

    private String name;
    private int id;

    public ClassInfo() {
    }

    public ClassInfo(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
