package com.production.sdkskin.Model;

public class imageinfo {
        public int width;
        public int height;
        public String url;

    public imageinfo(int width, int height, String url) {
        this.width = width;
        this.height = height;
        this.url = url;
    }

    public imageinfo() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
