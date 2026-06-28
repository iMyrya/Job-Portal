package com.jobportal.job_portal.config;
//import com.jobportal.job_portal.repository.UserRepository;
import com.jobportal.job_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
	    private UserRepository userRepository;   // ✅ injected as a bean

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/register", "/login", "/jobs",
	                                 "/jobs/search", "/h2-console/**").permitAll()
	                .requestMatchers("/recruiter/**").hasAuthority("RECRUITER")
	                .requestMatchers("/jobseeker/**").hasAuthority("JOBSEEKER")
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	                .loginPage("/login")
	                .defaultSuccessUrl("/dashboard", true)
	                .permitAll()
	            )
	            .logout(logout -> logout
	                .logoutSuccessUrl("/login?logout")
	                .permitAll()
	            )
	            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
	            .headers(headers -> headers
	                .frameOptions(frame -> frame.disable()));

	        return http.build();
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        return email -> {                          // ✅ 'email' is the lambda parameter
	            var user = userRepository              // ✅ using the injected field, not the class name
	                    .findByEmail(email)
	                    .orElseThrow(() ->
	                        new UsernameNotFoundException("User not found: " + email));

	            return org.springframework.security.core.userdetails.User
	                    .withUsername(user.getEmail())
	                    .password(user.getPassword())
	                    .authorities(user.getRole().name())
	                    .build();
	        };
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
