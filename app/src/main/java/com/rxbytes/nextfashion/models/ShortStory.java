package com.rxbytes.nextfashion.models;

/**
 * Created by pnagarjuna on 26/05/16.
 */
public class ShortStory {

    private String db;
    private String title;
    private String desc;
    private String image;
    private String author;
    private String payload;
    private boolean authorProfile;

    public ShortStory() {}

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

    public boolean isAuthorProfile() {
        return authorProfile;
    }

    public void setAuthorProfile(boolean authorProfile) {
        this.authorProfile = authorProfile;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
