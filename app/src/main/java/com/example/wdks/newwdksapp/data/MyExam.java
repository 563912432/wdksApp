package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 个人中心页面的我的题库
 */

public class MyExam {
    private String imageUrl;
    private String name;
    private String content;
    private String time;

    public MyExam(String name) {
        this.name = name;
    }

    public MyExam(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public MyExam(String imageUrl, String name, String content, String time) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.content = content;
        this.time = time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
