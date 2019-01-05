package com.windhunter.hunterhome.entity;

import java.io.Serializable;

public class Submission implements Serializable {
    /*CREATE TABLE table_submission(
    worker_id VARCHAR(32) NOT NULL,
    task_id VARCHAR(32) NOT NULL,
    task_achievement VARCHAR(32) NOT NULL,
    FOREIGN KEY (worker_id) REFERENCES table_user(user_id),
    FOREIGN KEY (task_id) REFERENCES table_post(post_id),
    PRIMARY KEY(worker_id,task_id)
    )ENGINE=INNODB DEFAULT CHARSET=UTF8;*/
    private String worker_id;
    private String task_id;
    private String task_achievement;

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
    @Override
    public String toString() {
        return "Submission{" +
                "worker_id='" + worker_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", task_achievement='" + task_achievement + '\'' +
                '}';
    }
}
