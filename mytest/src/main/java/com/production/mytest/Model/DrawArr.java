package com.production.mytest.Model;

public class DrawArr{
    public DrawArr() {
    }
    public float height;
    public float left;
    public float top;
    public float width;

    public DrawArr(float height, float left, float top, float width) {
        this.height = height;
        this.left = left;
        this.top = top;
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
