package com.rxbytes.nextfashion.models;

/**
 * Created by pnagarjuna on 26/05/16.
 */
public class ShortStory {

    private String title;
    private String desc;
    private String image;
    private String author;

    public ShortStory(String title, String desc, String image, String author) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
