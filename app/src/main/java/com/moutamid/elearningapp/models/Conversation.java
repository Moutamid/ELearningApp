package com.moutamid.elearningapp.models;

public class Conversation {
    String message;
    String time;
    String senderID;
    String image;
    long timestamps;
    String name;

    public Conversation() {}

    public Conversation(String message, String time, String senderID, String image, long timestamps, String name) {
        this.message = message;
        this.time = time;
        this.senderID = senderID;
        this.image = image;
        this.timestamps = timestamps;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(long timestamps) {
        this.timestamps = timestamps;
    }
}