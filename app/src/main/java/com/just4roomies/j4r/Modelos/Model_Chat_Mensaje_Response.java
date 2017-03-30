package com.just4roomies.j4r.Modelos;

/**
 * Created by rudielavilaperaza on 29/09/16.
 */
public class Model_Chat_Mensaje_Response {

    private String message;
    private int code;
    private int chat_id;


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

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }
}
