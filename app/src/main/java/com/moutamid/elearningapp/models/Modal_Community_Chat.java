package com.moutamid.elearningapp.models;

public class Modal_Community_Chat {
    String message;
    String time;
    String userID;
    String name;
    String image;
    boolean isInstructor;

    public Modal_Community_Chat() {
    }

    public Modal_Community_Chat(String message, String time, String userID, String name, String image, boolean isInstructor) {
        this.message = message;
        this.time = time;
        this.userID = userID;
        this.name = name;
        this.image = image;
        this.isInstructor = isInstructor;
    }

    public boolean isInstructor() {
        return isInstructor;
    }

    public void setInstructor(boolean instructor) {
        isInstructor = instructor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
