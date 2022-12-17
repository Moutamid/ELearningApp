package com.moutamid.elearningapp.models;

public class ChatList {
    String ID, image, name, message;

    public ChatList() {
    }

    public ChatList(String ID, String image, String name, String message) {
        this.ID = ID;
        this.image = image;
        this.name = name;
        this.message = message;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
}
