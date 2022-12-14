package com.moutamid.elearningapp.models;

public class Model_Courses {
    String title , tutor , member , efficient , price , status , time , des ;
    String image;

    public Model_Courses() {
    }

    public Model_Courses(String title, String tutor, String member, String efficient, String price, String status, String time, String des, String image) {
        this.title = title;
        this.tutor = tutor;
        this.member = member;
        this.efficient = efficient;
        this.price = price;
        this.status = status;
        this.time = time;
        this.des = des;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getEfficient() {
        return efficient;
    }

    public void setEfficient(String efficient) {
        this.efficient = efficient;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
