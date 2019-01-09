package com.windhunter.hunterhome.repository;

import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.Submission;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface SubmissionRepository {

    @Insert("INSERT INTO table_submission VALUES(#{submission.worker_id},#{submission.task_id},#{submission.task_achievement},#{submission.submission_time},#{submission.submission_level})")
    void addSubmission(Submission submission);

    @Update("UPDATE table_submission SET task_achievement = #{submission.task_achievement},submission_time = #{submission.submission_time},submission_level = #{submission.submission_level} WHERE BINARY worker_id = #{submission.worker_id} AND BINARY task_id = #{submission.task_id}")
    void setSubmission(Submission submission);

    @Update("UPDATE table_submission SET submission_level = #{submission.submission_level} WHERE BINARY worker_id = #{submission.worker_id} AND BINARY task_id = #{submission.task_id}")
    void updateSubmissionGrade(String worker_id, String task_id, char level);

    @Select("SELECT * FROM table_submission WHERE BINARY worker_id = #{submission.worker_id} AND BINARY task_id = #{submission.task_id}")
    Submission getSubmissionById(String worker_id, String task_i);

    @Delete("DELETE FROM table_submission WHERE BINARY worker_id = #{submission.worker_id} AND BINARY task_id = #{submission.task_id}")
    void deleteSubmissionById(String worker_id, String task_id);

    List<Submission> getSubmissions(Page page);

    Integer getSubmissionCount(Submission submission);
}
