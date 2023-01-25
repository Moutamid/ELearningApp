package com.moutamid.elearningapp.models;

public class Model_Content {
    String course_id;
    String content_id;
    String title;
    String desc;
    String Status, efficient;
    long member;
    String tutor;
    String video_length;
    boolean is_bestSeller;
    String video_link, image;
    String category;
    long price;
    String sellerID;

    public Model_Content() {}

    public Model_Content(String course_id, String title, String desc, String tutor, String video_link, String image, String category) {
        this.course_id = course_id;
        this.title = title;
        this.desc = desc;
        this.tutor = tutor;
        this.video_link = video_link;
        this.image = image;
        this.category = category;
    }

    public Model_Content(String course_id, String title, String desc, String status, String efficient, long member, String tutor, String video_length, boolean is_bestSeller, String video_link, String image, String category, long price, String sellerID) {
        this.course_id = course_id;
        this.title = title;
        this.desc = desc;
        this.Status = status;
        this.efficient = efficient;
        this.member = member;
        this.tutor = tutor;
        this.video_length = video_length;
        this.is_bestSeller = is_bestSeller;
        this.video_link = video_link;
        this.image = image;
        this.category = category;
        this.price = price;
        this.sellerID = sellerID;
    }

    public Model_Content(String course_id, String content_id, String title, String desc, String status, String efficient, long member, String tutor, String video_length, boolean is_bestSeller, String video_link, String image, String category, long price, String sellerID) {
        this.course_id = course_id;
        this.content_id = content_id;
        this.title = title;
        this.desc = desc;
        this.Status = status;
        this.efficient = efficient;
        this.member = member;
        this.tutor = tutor;
        this.video_length = video_length;
        this.is_bestSeller = is_bestSeller;
        this.video_link = video_link;
        this.image = image;
        this.category = category;
        this.price = price;
        this.sellerID = sellerID;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getEfficient() {
        return efficient;
    }

    public void setEfficient(String efficient) {
        this.efficient = efficient;
    }

    public long getMember() {
        return member;
    }

    public void setMember(long member) {
        this.member = member;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getVideo_length() {
        return video_length;
    }

    public void setVideo_length(String video_length) {
        this.video_length = video_length;
    }

    public boolean isIs_bestSeller() {
        return is_bestSeller;
    }

    public void setIs_bestSeller(boolean is_bestSeller) {
        this.is_bestSeller = is_bestSeller;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

}
