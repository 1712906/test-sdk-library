package com.production.mytest.Item;

public class Item {
    int imgUrl;
    String subject;
    String detail;

    public Item(int imgUrl, String subject, String detail) {
        this.imgUrl = imgUrl;
        this.subject = subject;
        this.detail = detail;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
