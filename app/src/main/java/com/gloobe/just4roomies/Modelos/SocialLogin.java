package com.gloobe.just4roomies.Modelos;

/**
 * Created by rudielavilaperaza on 26/08/16.
 */
public class SocialLogin {

    private String social_id;
    private String token;
    private String device;

    public String getSocial_id() {
        return social_id;
    }

    public void setSocial_id(String social_id) {
        this.social_id = social_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
