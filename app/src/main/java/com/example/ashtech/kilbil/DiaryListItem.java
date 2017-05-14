package com.example.ashtech.kilbil;

/**
 * Created by Ashtech on 5/7/2017.
 */

public class DiaryListItem {
    private int resId;
    private String itemName;

    public DiaryListItem(int resId, String itemName) {
        this.resId = resId;
        this.itemName = itemName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
