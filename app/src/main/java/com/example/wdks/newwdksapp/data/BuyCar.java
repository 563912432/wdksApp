package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class BuyCar {

    int id;
    String content;
    int carNum;
    float price;
    String imageUrl;

    public BuyCar(int id, String content, int carNum, float price, String imageUrl) {
        this.id = id;
        this.content = content;
        this.carNum = carNum;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String CarNum() {
        this.carNum = carNum;
        return carNum + "";
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
