package com.gloobe.just4roomies.Modelos;

import java.io.File;

/**
 * Created by rudielavilaperaza on 29/08/16.
 */
public class AddRoom {
    private String price;
    private Boolean furnished;
    private String comments;
    private String date;
    private String image1;
    private String image2;
    private String image3;
    private int user_id;
    private int id;

    public int getRoom_id() {
        return id;
    }

    public void setRoom_id(int room_id) {
        this.id = room_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
