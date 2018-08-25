package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/10/17 0017.
 * 确认订单页面的数据
 */

public class Order {
    private String imageUrl;
    private String name;
    private String number;
    private String price;

    public Order(String imageUrl, String name, String number, String price) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.number = number;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
