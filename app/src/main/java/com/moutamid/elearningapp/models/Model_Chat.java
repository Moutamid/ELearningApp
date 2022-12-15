package com.moutamid.elearningapp.models;

public class Model_Chat {
    String tutor, title;
    String image;
    String message;
    String tutorUid;
    String studentUid;

    public Model_Chat() { }

    public Model_Chat(String tutor, String title, String image, String message, String tutorUid, String studentUid) {
        this.tutor = tutor;
        this.title = title;
        this.image = image;
        this.message = message;
        this.tutorUid = tutorUid;
        this.studentUid = studentUid;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTutorUid() {
        return tutorUid;
    }

    public void setTutorUid(String tutorUid) {
        this.tutorUid = tutorUid;
    }

    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }
}
