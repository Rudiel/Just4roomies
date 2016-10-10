package com.gloobe.just4roomies.Modelos;

/**
 * Created by rudielavilaperaza on 29/09/16.
 */
public class Model_Chat_Conversacion_Mensaje {

    private int id;
    private String user_id_send;
    private String user_id_receiver;
    private String chat_id;
    private String message;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id_send() {
        return user_id_send;
    }

    public void setUser_id_send(String user_id_send) {
        this.user_id_send = user_id_send;
    }

    public String getUser_id_receiver() {
        return user_id_receiver;
    }

    public void setUser_id_receiver(String user_id_receiver) {
        this.user_id_receiver = user_id_receiver;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
