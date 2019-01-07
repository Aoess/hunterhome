package com.windhunter.hunterhome.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Submission implements Serializable {
    /*CREATE TABLE table_submission(
    worker_id VARCHAR(32) NOT NULL,
    task_id VARCHAR(32) NOT NULL,
    task_achievement VARCHAR(32) NOT NULL,
    submission_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    submission_level VARCHAR(5) NOT NULL,
    FOREIGN KEY (worker_id) REFERENCES table_user(user_id),
    FOREIGN KEY (task_id) REFERENCES table_post(post_id),
    PRIMARY KEY(worker_id,task_id)
    )ENGINE=INNODB DEFAULT CHARSET=UTF8;*/
    private String worker_id;
    private String task_id;
    private String task_achievement;
    private Timestamp submission_time;
    private Character submission_level;

    public Submission(){
    }
    public String getTask_achievement() {
        return task_achievement;
    }

    public void setTask_achievement(String task_achievement) {
        this.task_achievement = task_achievement;
    }

    public String getTask_id() {

        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getWorker_id() {

        return worker_id;
    }

    public void setWorker_id(String worker_id) {
        this.worker_id = worker_id;
    }

    public Timestamp getSubmission_time() {
        return submission_time;
    }

    public void setSubmission_time(Timestamp submission_time) {
        this.submission_time = submission_time;
    }

    public Character getSubmission_level() {
        return submission_level;
    }

    public void setSubmission_level(Character submission_level) {
        this.submission_level = submission_level;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "worker_id='" + worker_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", task_achievement='" + task_achievement + '\'' +
                ", submission_time=" + submission_time +
                ", submission_level='" + submission_level + '\'' +
                '}';
    }
}
