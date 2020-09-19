package com.production.sdkskin.Model;

import java.util.List;

public class generalResult {
    Title  title;
    List<DataSpecial> data;

    public generalResult() {

    }

    public generalResult(Title title, List<DataSpecial> data) {
        this.title = title;
        this.data = data;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public List<DataSpecial> getData() {
        return data;
    }

    public void setData(List<DataSpecial> data) {
        this.data = data;
    }
}
