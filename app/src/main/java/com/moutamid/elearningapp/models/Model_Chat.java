package com.moutamid.elearningapp.models;

public class Model_Chat {
    String tutor , title ;
    int image ;

    public Model_Chat() {
    }

    public Model_Chat(String tutor, String title, int image) {
        this.tutor = tutor;
        this.title = title;
        this.image = image;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
