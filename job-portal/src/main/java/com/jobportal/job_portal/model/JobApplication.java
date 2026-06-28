package com.jobportal.job_portal.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "job_id")
	    private Job job;

	    @ManyToOne
	    @JoinColumn(name = "applicant_id")
	    private User applicant;

	    @Enumerated(EnumType.STRING)
	    private Status status;

	    public enum Status {
	        APPLIED, REVIEWED, SELECTED, REJECTED
	    }

}
