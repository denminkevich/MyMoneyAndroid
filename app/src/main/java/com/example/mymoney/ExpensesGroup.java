package com.example.mymoney;

public class ExpensesGroup {
    private String expName;
    private double expCount;
    private int ImageResource;

    public ExpensesGroup(String name, double count, int resource) {
        this.expName = name;
        this.expCount = count;
        this.ImageResource = resource;
    }

    public String getName() {return expName;}

    public void setName(String name) {
        this.expName = name;
    }

    public Double getCount() {return expCount;}

    public void setExpCount(Double count) {
        this.expCount = count;
    }

    public int getImg() {return ImageResource;}

    public void setName(int imageResource) {
        this.ImageResource = imageResource;
    }
}
