package com.just4roomies.j4r.Modelos;

import java.util.ArrayList;

/**
 * Created by rudielavilaperaza on 28/09/16.
 */
public class Model_Chat_Response {

    private String message;
    private int code;
    private ArrayList<Model_Chat> list;
    private ArrayList<Model_Chat_Solicitudes> listRequest;


    public ArrayList<Model_Chat> getList() {
        return list;
    }

    public void setList(ArrayList<Model_Chat> list) {
        this.list = list;
    }

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

    public ArrayList<Model_Chat_Solicitudes> getListRequest() {
        return listRequest;
    }

    public void setListRequest(ArrayList<Model_Chat_Solicitudes> listRequest) {
        this.listRequest = listRequest;
    }
}
