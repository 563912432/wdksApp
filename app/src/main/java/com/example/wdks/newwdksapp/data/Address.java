package com.example.wdks.newwdksapp.data;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 收货地址的list数据
 */

public class Address {
    private String name;
    private String tel;
    private String district;
    private String address;

    public Address(String name, String tel, String district, String address) {
        this.name = name;
        this.tel = tel;
        this.district = district;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
