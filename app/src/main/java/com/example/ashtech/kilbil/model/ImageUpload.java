package com.example.ashtech.kilbil.model;

/**
 * Created by Ashtech on 6/17/2017.
 */

public class ImageUpload {

    public String url;
    public String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public ImageUpload( String url,String des) {

        this.url = url;
        this.desc=des;
    }

    public ImageUpload(){}
}
