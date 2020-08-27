package com.example.question1.model;

import java.io.Serializable;

public class Fruit implements Serializable {
    private String tfvname;
    private String botname;
    private String othname;
    private String imageurl;
    private String description;

    public Fruit(String tfvname, String botname, String othname, String imageurl, String description) {
        this.setTfvname(tfvname);
        this.setBotname(botname);
        this.setOthname(othname);
        this.setImageurl(imageurl);
        this.setDescription(description);
    }

    @Override
    public String toString() {
        return "{" +
                "tfvname=" + getTfvname() +
                ", botname=" + getBotname() +
                ", othname=" + getOthname() +
                ", imageurl=" + getImageurl() +
                ", description=" + getDescription() +
                "}";
    }

    public String getTfvname() {
        return tfvname;
    }

    public void setTfvname(String tfvname) {
        this.tfvname = tfvname;
    }

    public String getBotname() {
        return botname;
    }

    public void setBotname(String botname) {
        this.botname = botname;
    }

    public String getOthname() {
        return othname;
    }

    public void setOthname(String othname) {
        this.othname = othname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}