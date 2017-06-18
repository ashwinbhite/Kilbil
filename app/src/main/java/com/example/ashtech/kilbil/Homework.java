package com.example.ashtech.kilbil;

/**
 * Created by Ashtech on 6/12/2017.
 */

public class Homework {
    private String className;
    private String hwDesc;
    private String date;



    public Homework(String className, String hwDesc, String date) {
        this.className = className;
        this.hwDesc = hwDesc;
        this.date = date;
    }

    public Homework() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHwDesc() {
        return hwDesc;
    }

    public void setHwDesc(String hwDesc) {
        this.hwDesc = hwDesc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}