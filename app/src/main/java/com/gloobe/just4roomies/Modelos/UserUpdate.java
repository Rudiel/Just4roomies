package com.gloobe.just4roomies.Modelos;

/**
 * Created by rudielavilaperaza on 29/08/16.
 */
public class UserUpdate {
    private String message;
    private int code;
    private Profile profile;
    private Personalidad personality;


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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Personalidad getPersonality() {
        return personality;
    }

    public void setPersonality(Personalidad personality) {
        this.personality = personality;
    }
}
