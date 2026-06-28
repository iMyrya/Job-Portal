package com.jobportal.job_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.model.JobApplication;
import com.jobportal.job_portal.model.User;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByApplicant(User applicant);
    List<JobApplication> findByJob(Job job);
    boolean existsByJobAndApplicant(Job job, User applicant);
}
