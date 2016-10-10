package com.gloobe.just4roomies.Modelos;

import java.io.FileInputStream;

/**
 * Created by rudielavilaperaza on 31/08/16.
 */
public class Model_UserUpdate {

    private int user_id;
    private String comments;
    private String price;
    private Boolean smoke;
    private Boolean active;
    private Boolean pet;
    private Boolean student;
    private Boolean cook;
    private Boolean party;
    private String image1;
    private String image2;
    private String image3;
    private String longitud;
    private String latitud;
    private String place;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getSmoke() {
        return smoke;
    }

    public void setSmoke(Boolean smoke) {
        this.smoke = smoke;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getPet() {
        return pet;
    }

    public void setPet(Boolean pet) {
        this.pet = pet;
    }

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    public Boolean getCook() {
        return cook;
    }

    public void setCook(Boolean cook) {
        this.cook = cook;
    }

    public Boolean getParty() {
        return party;
    }

    public void setParty(Boolean party) {
        this.party = party;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
