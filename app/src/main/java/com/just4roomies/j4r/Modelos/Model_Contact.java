package com.just4roomies.j4r.Modelos;

/**
 * Created by rudielavilaperaza on 05/09/16.
 */
public class Model_Contact {

    private String bodyMessage;
    private String fullname;
    private String subject;
    private int user_id;

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
