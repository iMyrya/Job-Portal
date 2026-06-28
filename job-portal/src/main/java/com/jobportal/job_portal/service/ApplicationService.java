package com.jobportal.job_portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.model.JobApplication;
import com.jobportal.job_portal.model.User;
import com.jobportal.job_portal.repository.JobApplicationRepository;
import com.jobportal.job_portal.repository.JobRepository;
@Service
public class ApplicationService {
	 @Autowired
	    private JobApplicationRepository applicationRepository;

	    @Autowired
	    private JobRepository jobRepository;

	    public String applyForJob(Long jobId, User applicant) {
	        Job job = jobRepository.findById(jobId)
	                .orElseThrow(() -> new RuntimeException("Job not found"));

	        if (applicationRepository.existsByJobAndApplicant(job, applicant)) {
	            return "already_applied";
	        }

	        JobApplication application = new JobApplication();
	        application.setJob(job);
	        application.setApplicant(applicant);
	        application.setStatus(JobApplication.Status.APPLIED);
	        applicationRepository.save(application);
	        return "success";
	    }

	    public List<JobApplication> getMyApplications(User applicant) {
	        return applicationRepository.findByApplicant(applicant);
	    }

	    public List<JobApplication> getApplicationsForJob(Long jobId) {
	        Job job = jobRepository.findById(jobId)
	                .orElseThrow(() -> new RuntimeException("Job not found"));
	        return applicationRepository.findByJob(job);
	    }

	    public void updateStatus(Long applicationId, JobApplication.Status status) {
	        JobApplication app = applicationRepository.findById(applicationId)
	                .orElseThrow(() -> new RuntimeException("Application not found"));
	        app.setStatus(status);
	        applicationRepository.save(app);
	    }
}
