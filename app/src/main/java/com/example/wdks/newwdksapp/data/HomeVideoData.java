package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/9/28 0028.
 * 首页视频播放的数据
 */

public class HomeVideoData {
    private String imageUrl;
    private String title;
    private String teacher;
    private String price;

    public HomeVideoData(String imageUrl, String title, String teacher, String price) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.teacher = teacher;
        this.price = price;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "HomeVideoData{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", teacher='" + teacher + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
