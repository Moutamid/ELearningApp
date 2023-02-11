package com.moutamid.elearningapp.models;

public class InstructorModel {
    String name, email, password, courseName, courseDes, image, courseThumb;

    public InstructorModel() {
    }

    public InstructorModel(String name, String email, String password, String courseName, String courseDes, String image, String courseThumb) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.image = image;
        this.courseThumb = courseThumb;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCourseThumb() {
        return courseThumb;
    }

    public void setCourseThumb(String courseThumb) {
        this.courseThumb = courseThumb;
    }
}
