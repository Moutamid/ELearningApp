package com.moutamid.elearningapp.models;

public class CourseIDs {
    String ID;
    boolean enroll;
    String sellerID;

    public CourseIDs() {
    }

    public CourseIDs(String ID, boolean enrolled, String sellerID) {
        this.ID = ID;
        this.enroll = enrolled;
        this.sellerID = sellerID;
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
