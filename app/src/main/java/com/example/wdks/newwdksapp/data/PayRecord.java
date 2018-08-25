package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/11/1 0001.
 * 缴费记录ListView的数据
 */

public class PayRecord {
    private String title, time, number, money;

    public PayRecord(String title, String time, String number, String money) {
        this.title = title;
        this.time = time;
        this.number = number;
        this.money = money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
