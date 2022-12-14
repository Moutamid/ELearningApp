package com.moutamid.elearningapp.models;

public class Model_Message {
    String message;
    int sender_img;
    String currenttime;

    public Model_Message() {
    }

    public Model_Message(String message, int sender_img, String currenttime) {
        this.message = message;
        this.sender_img = sender_img;
        this.currenttime = currenttime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSender_img() {
        return sender_img;
    }

    public void setSender_img(int sender_img) {
        this.sender_img = sender_img;
    }

    public String getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }
}
