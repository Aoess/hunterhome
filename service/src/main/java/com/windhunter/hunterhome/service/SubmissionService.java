package com.windhunter.hunterhome.service;

import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.Submission;

public interface SubmissionService {

    ResultBean addSubmission(Submission submission);

    ResultBean setSubmission(Submission submission);

    ResultBean updateSubmissionGrade(String worker_id, String task_id, char level);

    ResultBean getSubmissionById(String worker_id, String task_i);

    ResultBean deleteSubmissionById(String worker_id, String task_id);

    ResultBean getSubmissions(Submission submission);

    ResultBean getEnhanceSubmissionById(Submission submission);

    ResultBean getEnhanceSubmissions(Submission submission);

    ResultBean getSubmissionCount(Submission submission);
}
