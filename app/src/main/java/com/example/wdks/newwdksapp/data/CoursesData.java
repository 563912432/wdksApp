package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/8/31 0031.
 * 商城页面图书和题库的数据
 */
public class CoursesData {
    private String imageUrl;
    private String title;
    private String name;
    private String content;
    private String price1;
    private String price2;

    public CoursesData(String name) {
        this.name = name;
    }

    public CoursesData(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public CoursesData(String imageUrl, String title, String price1, String price2) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.price1 = price1;
        this.price2 = price2;
    }

    public CoursesData(String imageUrl, String title, String content, String price1, String price2) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.price1 = price1;
        this.price2 = price2;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }
}
