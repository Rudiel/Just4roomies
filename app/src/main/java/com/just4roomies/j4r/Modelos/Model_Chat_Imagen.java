package com.just4roomies.j4r.Modelos;

/**
 * Created by rudielavilaperaza on 19/10/16.
 */

public class Model_Chat_Imagen {

    private int user_id_send;
    private String image;
    private int chat_id;


    public int getUser_id_send() {
        return user_id_send;
    }

    public void setUser_id_send(int user_id_send) {
        this.user_id_send = user_id_send;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }
}
