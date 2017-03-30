package com.just4roomies.j4r.Modelos;

import java.util.List;

/**
 * Created by rudielavilaperaza on 12/09/16.
 */
public class Model_Perfiles {

    private String message;
    private int code;
    private List<Model_Profiles> profiles;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Model_Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Model_Profiles> profiles) {
        this.profiles = profiles;
    }
}
