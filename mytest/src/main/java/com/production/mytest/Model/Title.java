package com.production.mytest.Model;

public class Title{
    public String en;
    public String vi;

    public Title(String en, String vi) {
        this.en = en;
        this.vi = vi;
    }

    public Title() {
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }
}