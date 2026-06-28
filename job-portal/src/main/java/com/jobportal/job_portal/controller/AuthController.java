package com.jobportal.job_portal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jobportal.job_portal.model.User;
import com.jobportal.job_portal.service.UserService;
@Controller
public class AuthController {
	 @Autowired
	    private UserService userService;

	    @GetMapping("/register")
	    public String showRegisterPage(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute User user) {
	        userService.registerUser(user);
	        return "redirect:/login";
	    }

	    @GetMapping("/login")
	    public String showLoginPage() {
	        return "login";
	    }

}
