package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/11/1 0001.
 * 订单记录的listView数据
 */

public class Recharge {
    private String time;
    private String money;
    private String number;

    public Recharge(String time, String money, String number) {
        this.time = time;
        this.money = money;
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
