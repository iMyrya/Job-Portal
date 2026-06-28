package com.jobportal.job_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.job_portal.model.User;
import com.jobportal.job_portal.repository.UserRepository;
@Service
public class UserService {
	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    public void registerUser(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userRepository.save(user);
	    }

	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	    }
	

}
