package com.gloobe.just4roomies.Modelos;

/**
 * Created by rudielavilaperaza on 28/09/16.
 */
public class Model_Chat {

    private int id;
    private int user_id_send;
    private int user_id_receiver;
    private int chat_id;
    private String status;
    private String date;
    private String data_user_send;
    private Model_User_Data data_user;
    private String request;


    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id_send() {
        return user_id_send;
    }

    public void setUser_id_send(int user_id_send) {
        this.user_id_send = user_id_send;
    }

    public int getUser_id_receiver() {
        return user_id_receiver;
    }

    public void setUser_id_receiver(int user_id_receiver) {
        this.user_id_receiver = user_id_receiver;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public Model_User_Data getData_user() {
        return data_user;
    }

    public void setData_user(Model_User_Data data_user) {
        this.data_user = data_user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData_user_send() {
        return data_user_send;
    }

    public void setData_user_send(String data_user_send) {
        this.data_user_send = data_user_send;
    }
}
