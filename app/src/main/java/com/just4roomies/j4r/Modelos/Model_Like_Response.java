package com.just4roomies.j4r.Modelos;

/**
 * Created by rudielavilaperaza on 26/09/16.
 */
public class Model_Like_Response {

    private String messagesuccess;
    private int code;
    private int chat_id;

    public String getMessagesuccess() {
        return messagesuccess;
    }

    public void setMessagesuccess(String messagesuccess) {
        this.messagesuccess = messagesuccess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }
}
