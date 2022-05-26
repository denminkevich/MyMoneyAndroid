package com.example.mymoney;

import android.widget.ImageButton;

import java.util.Date;

public class OperatesGroup {
    private Date operDate;
    private int ImageResource;
    private double expCount;
    private String operName;

    public OperatesGroup(String name, Date date, int resource, double count) {
        this.operDate = date;
        this.ImageResource = resource;
        this.expCount = count;
        this.operName = name;
    }

    public String getOperName() {return operName;}

    public void setOperName(String name) {
        this.operName = name;
    }

    public int getImg() {return ImageResource;}

    public void setImg(int img) { this.ImageResource = img; }

    public Date getDate() {return operDate;}

    public void setDate(Date date) {
        this.operDate = date;
    }

    public double getCount() {return expCount;}

    public void setCount(double count) {
        this.expCount = count;
    }
}
