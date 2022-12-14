package com.moutamid.elearningapp.models;

public class Model_Enrolled {
    String title , tutor ;
    int image ;

    public Model_Enrolled() {
    }

    public Model_Enrolled(String title, String tutor, int image) {
        this.title = title;
        this.tutor = tutor;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
