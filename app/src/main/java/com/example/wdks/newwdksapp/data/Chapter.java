package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/10/22 0022.
 * 章节练习页面的数据
 */

public class Chapter {
    private String title, done, all, collect, wrong, isFinish;

    public Chapter(String title, String all) {
        this.title = title;
        this.all = all;
    }

    public Chapter(String title, String all, String isFinish) {
        this.title = title;
        this.all = all;
        this.isFinish = isFinish;
    }

    public Chapter(String title, String done, String all, String collect, String wrong) {
        this.title = title;
        this.done = done;
        this.all = all;
        this.collect = collect;
        this.wrong = wrong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }
}
