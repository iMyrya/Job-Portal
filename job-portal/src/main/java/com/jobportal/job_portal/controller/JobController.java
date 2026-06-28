package com.jobportal.job_portal.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.model.User;
import com.jobportal.job_portal.service.ApplicationService;
import com.jobportal.job_portal.service.JobService;
import com.jobportal.job_portal.service.UserService;
//import com.jobportal.model.Job;

@Controller
public class JobController {
	@Autowired private JobService jobService;
    @Autowired private UserService userService;
    @Autowired private ApplicationService applicationService;

    @GetMapping("/jobs")
    public String listAllJobs(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("jobs", jobService.searchJobs(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("jobs", jobService.getAllJobs());
        }
        return "jobsList";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        if (user.getRole() == User.Role.RECRUITER) {
            model.addAttribute("myJobs", jobService.getJobsByRecruiter(user));
            return "dashboardRecruiter";
        } else {
            model.addAttribute("myApplications", applicationService.getMyApplications(user));
            return "dashboardJobseeker";
        }
    }

    @GetMapping("/recruiter/post-job")
    public String showPostJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobsPost";
    }

    @PostMapping("/recruiter/post-job")
    public String postJob(@ModelAttribute Job job,
                          @AuthenticationPrincipal UserDetails userDetails) {
        User recruiter = userService.findByEmail(userDetails.getUsername());
        jobService.postJob(job, recruiter);
        return "redirect:/dashboard";
    }

    @PostMapping("/jobseeker/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId,
                              @AuthenticationPrincipal UserDetails userDetails,
                              Model model) {
        User applicant = userService.findByEmail(userDetails.getUsername());
        applicationService.applyForJob(jobId, applicant);
        return "redirect:/jobs";
    }

    @GetMapping("/recruiter/applications/{jobId}")
    public String viewApplications(@PathVariable Long jobId, Model model) {
        model.addAttribute("applications", applicationService.getApplicationsForJob(jobId));
        return "applicationList";
    }

    @PostMapping("/recruiter/applications/status/{appId}")
    public String updateStatus(@PathVariable Long appId,
                               @RequestParam String status) {
        applicationService.updateStatus(appId,
        		com.jobportal.job_portal.model.JobApplication.Status.valueOf(status));
        return "redirect:/dashboard";
    }
    

}