package com.example.event.eventapp.modle;

import java.io.Serializable;

public class Item implements Serializable

{   public String tag2;
    public String tag1;
    public int image;
    public String time;
    public String title;
    public String venue;



    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }



    public String getTag2() {
        return tag2;
    }

    public String getTag1() {
        return tag1;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
