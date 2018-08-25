package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 个人中心页面的我的视频观看记录
 */

public class MyVideo {
    private String imageUrl;
    private String name;
    private String watched;
    private String content;
    private String time;
    private String state;

    public MyVideo(String name) {
        this.name = name;
    }

    public MyVideo(String imageUrl, String name, String watched, String content, String time, String state) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.watched = watched;
        this.content = content;
        this.time = time;
        this.state = state;
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

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
