package com.gloobe.just4roomies.Modelos;

/**
 * Created by rudielavilaperaza on 26/08/16.
 */
public class RespuestaLoginFB {

    Profile profile;
    Personalidad_String personality;
    String code;
    String message;
    Model_Room room;


    public Model_Room getRoom() {
        return room;
    }

    public void setRoom(Model_Room room) {
        this.room = room;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Personalidad_String getPersonality() {
        return personality;
    }

    public void setPersonality(Personalidad_String personality) {
        this.personality = personality;
    }
}
