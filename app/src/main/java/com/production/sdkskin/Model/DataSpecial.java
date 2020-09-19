package com.production.sdkskin.Model;

import java.util.List;

public class DataSpecial {


    public class Datum{
        public Datum() {
        }

        public String valueEN;
        public String valueVI;
        public List<DrawArr> drawArr;
        public String key;
        public String value;
    }
    public Title title;
    public Descript descript;
    public List<Datum> data;

    public DataSpecial(Title title, Descript descript, List<Datum> data) {
        this.title = title;
        this.descript = descript;
        this.data = data;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Descript getDescript() {
        return descript;
    }

    public void setDescript(Descript descript) {
        this.descript = descript;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public DataSpecial() {
    }
}
