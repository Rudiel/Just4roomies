package com.gloobe.just4roomies.Modelos;

/**
 * Created by rudielavilaperaza on 29/09/16.
 */
public class Model_Chat_Mensaje {

    private int user_id_send;
    private String message;
    private int chat_id;

    public int getUser_id_send() {
        return user_id_send;
    }

    public void setUser_id_send(int user_id_send) {
        this.user_id_send = user_id_send;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }
}
