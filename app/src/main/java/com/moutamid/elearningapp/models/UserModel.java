package com.moutamid.elearningapp.models;

public class UserModel {
    String name, email, password, coursename, coursedes, image;
    boolean isInstructor;

    public UserModel() {}

    public UserModel(String name, String email, String password, String coursename, String coursedes, String image, boolean isInstructor) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coursename = coursename;
        this.coursedes = coursedes;
        this.image = image;
        this.isInstructor = isInstructor;
    }

    public UserModel(String name, String email, String password, String image, boolean isInstructor) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
        this.isInstructor = isInstructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedes() {
        return coursedes;
    }

    public void setCoursedes(String coursedes) {
        this.coursedes = coursedes;
    }

    public boolean isInstructor() {
        return isInstructor;
    }

    public void setInstructor(boolean instructor) {
        isInstructor = instructor;
    }
}
