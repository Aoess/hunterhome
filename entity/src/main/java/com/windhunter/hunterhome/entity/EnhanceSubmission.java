package com.windhunter.hunterhome.entity;

public class EnhanceSubmission extends Submission{
    private String submission_photo;
    private String submission_nikename;
    private String worker_title;

    @Override
    public String toString() {
        return "EnhanceSubmission{" +
                "submission_photo='" + submission_photo + '\'' +
                ", submission_nikename='" + submission_nikename + '\'' +
                ", worker_title='" + worker_title + '\'' +
                '}';
    }

    public String getWorker_title() {
        return worker_title;
    }

    public void setWorker_title(String worker_title) {
        this.worker_title = worker_title;
    }

    public String getSubmission_nikename() {

        return submission_nikename;
    }

    public void setSubmission_nikename(String submission_nikename) {
        this.submission_nikename = submission_nikename;
    }

    public String getSubmission_photo() {

        return submission_photo;
    }

    public void setSubmission_photo(String submission_photo) {
        this.submission_photo = submission_photo;
    }
}
