package com.jobportal.job_portal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.model.User;
import com.jobportal.job_portal.repository.JobRepository;

@Service
public class JobService {
	 @Autowired
	    private JobRepository jobRepository;

	    public void postJob(Job job, User recruiter) {
	        job.setRecruiter(recruiter);
	        job.setPostedDate(LocalDate.now());
	        jobRepository.save(job);
	    }

	    public List<Job> getAllJobs() {
	        return jobRepository.findAll();
	    }

	    public List<Job> searchJobs(String keyword) {
	        return jobRepository.findByTitleContainingIgnoreCase(keyword);
	    }

	    public List<Job> getJobsByRecruiter(User recruiter) {
	        return jobRepository.findByRecruiter(recruiter);
	    }

	    public Job getJobById(Long id) {
	        return jobRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Job not found"));
	    }

	    public void deleteJob(Long id) {
	        jobRepository.deleteById(id);
	    }

}
