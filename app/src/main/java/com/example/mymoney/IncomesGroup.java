package com.example.mymoney;

public class IncomesGroup {
    private String incName;
    private double incCount;
    private int ImageResource;

    public IncomesGroup(String name, double count, int resource) {
        this.incName = name;
        this.incCount = count;
        this.ImageResource = resource;
    }

    public String getName() {return incName;}

    public void setName(String name) {
        this.incName = name;
    }

    public Double getCount() {return incCount;}

    public void setExpCount(Double count) {
        this.incCount = count;
    }

    public int getImg() {return ImageResource;}

    public void setName(int imageResource) {
        this.ImageResource = imageResource;
    }
}
