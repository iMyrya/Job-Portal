package com.jobportal.job_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.model.User;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByRecruiter(User recruiter);
	 List<Job> findByTitleContainingIgnoreCase(String keyword);

}
