package com.example.wdks.newwdksapp.data;


/**
 * Created by Administrator on 2016/9/3 0003.
 * 资讯页的数据
 */
public class NewsData {
    private String title;
    private String author;
    private String time;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;

    public NewsData(String title, String author, String time) {
        this.title = title;
        this.author = author;
        this.time = time;
    }

    public NewsData(String title, String author, String time, String imageUrl1) {
        this.title = title;
        this.author = author;
        this.time = time;
        this.imageUrl1 = imageUrl1;
    }

    public NewsData(String title, String author, String time, String imageUrl1, String imageUrl2) {
        this.title = title;
        this.author = author;
        this.time = time;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
    }

    public NewsData(String title, String author, String time, String imageUrl1, String imageUrl2, String imageUrl3) {
        this.title = title;
        this.author = author;
        this.time = time;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }
}
