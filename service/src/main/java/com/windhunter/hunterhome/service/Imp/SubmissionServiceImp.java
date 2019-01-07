package com.windhunter.hunterhome.service.Imp;

import com.windhunter.hunterhome.entity.Page;
import com.windhunter.hunterhome.entity.ResultBean;
import com.windhunter.hunterhome.entity.Submission;
import com.windhunter.hunterhome.repository.SubmissionRepository;
import com.windhunter.hunterhome.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class SubmissionServiceImp implements SubmissionService {
    @Autowired
    SubmissionRepository submissionRepository;
    @Override
    public ResultBean addSubmission(Submission submission) {
        submission.setSubmission_time(new Timestamp(new Date().getTime()));
        submission.setTask_id(UUID.randomUUID().toString().replace("-",""));
        submission.setWorker_id(UUID.randomUUID().toString().replace("-",""));
        submissionRepository.addSubmission(submission);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean setSubmission(Submission submission) {
        submissionRepository.setSubmission(submission);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean updateSubmissionGrade(String worker_id, String task_id, char level) {
        submissionRepository.updateSubmissionGrade(worker_id, task_id, level);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean getSubmissionById(String worker_id, String task_i) {
        return new ResultBean(666,"SUCCESS",submissionRepository.getSubmissionById(worker_id, task_i));
    }

    @Override
    public ResultBean deleteSubmissionById(String worker_id, String task_id) {
        submissionRepository.deleteSubmissionById(worker_id, task_id);
        return new ResultBean(666,"SUCCESS",null);
    }

    @Override
    public ResultBean getSubmissions(Submission submission) {
        Page page = new Page();
        return new ResultBean(666,"SUCCESS",submissionRepository.getSubmissions(page));
    }

    @Override
    public ResultBean getEnhanceSubmissionById(Submission submission) {
        return null;
    }

    @Override
    public ResultBean getEnhanceSubmissions(Submission submission) {
        return null;
    }

    @Override
    public ResultBean getSubmissionCount(Submission submission) {
        return null;
    }
}
