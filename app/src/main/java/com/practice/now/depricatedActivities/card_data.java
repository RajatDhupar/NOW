package com.practice.now.depricatedActivities;

/**
 * Created by user on 03-03-2017.
 */

public class card_data {

    public String title;
    public String des;
    public String image;
    public String url;

    public card_data(String title, String des , String image,String url){
        this.title = title;
        this.des = des;
        this.image = image;
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
