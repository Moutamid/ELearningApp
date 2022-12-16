package com.moutamid.elearningapp.models;

public class CourseIDs {
    String ID;
    boolean enroll;
    String sellerID, userID;

    public CourseIDs() {
    }

    public CourseIDs(String ID, boolean enroll, String sellerID, String userID) {
        this.ID = ID;
        this.enroll = enroll;
        this.sellerID = sellerID;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isEnroll() {
        return enroll;
    }

    public void setEnroll(boolean enroll) {
        this.enroll = enroll;
    }
}
