package com.game.behnam.myapplication.utils;

public class InnerListItem {
    private String name;
    private String img;
    private String content;
    private int color=0;
public InnerListItem(String name, String img, String content){
    this.name=name;
    this.img=img;
    this.content=content;
}
    public InnerListItem(String name, String img, String content, int color){
        this.name=name;
        this.img=img;
        this.content=content;
        this.setColor(color);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
