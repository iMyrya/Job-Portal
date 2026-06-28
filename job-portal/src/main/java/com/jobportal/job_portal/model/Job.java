package com.jobportal.job_portal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String company;
	    private String location;
	    private String description;
	    private String skillsRequired;
	    private LocalDate postedDate;

	    @ManyToOne
	    @JoinColumn(name = "recruiter_id")
	    private User recruiter;

}
