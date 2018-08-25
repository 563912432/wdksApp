package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/10/31 0031.
 * 订单记录的数据
 */

public class OrderRecord {
    private String orderNum, orderState, imageUrl, title, price, numbers, content, addAll, freight, pay,
            delete, contact;

    public OrderRecord(String orderNum, String orderState, String imageUrl, String title, String price, String numbers,
                       String content, String addAll, String freight, String pay, String delete, String contact) {
        this.orderNum = orderNum;
        this.orderState = orderState;
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
        this.numbers = numbers;
        this.content = content;
        this.addAll = addAll;
        this.freight = freight;
        this.pay = pay;
        this.delete = delete;
        this.contact = contact;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddAll() {
        return addAll;
    }

    public void setAddAll(String addAll) {
        this.addAll = addAll;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
