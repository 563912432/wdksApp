package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/10/31 0031.
 * 我的答疑的数据适配
 */

public class MyAsk {
    private String askTitle;
    private String askTime;
    private String answerTitle;
    private String answerTime;

    public MyAsk(String askTitle, String askTime, String answerTitle, String answerTime) {
        this.askTitle = askTitle;
        this.askTime = askTime;
        this.answerTitle = answerTitle;
        this.answerTime = answerTime;
    }

    public String getAskTitle() {
        return askTitle;
    }

    public void setAskTitle(String askTitle) {
        this.askTitle = askTitle;
    }

    public String getAskTime() {
        return askTime;
    }

    public void setAskTime(String askTime) {
        this.askTime = askTime;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }
}
