package com.akash.myFirstWebApp.ToDoListMarker.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	// LDAP or Database
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("akash", "dummy1");
		UserDetails userDetails2 = createNewUser("palak", "dummy2");
		UserDetails userDetails3 = createNewUser("sagar", "dummy3");

		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
	}


	private UserDetails createNewUser( String username, String password) {
		
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder)
		.username(username)
		.password(password)
		.roles("USER", "ADMIN")
		.build();
		return userDetails;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// all urls are protected 
	// login form is shown for unauthorised urls
	// disable CSRF 
	// disable frames

	// build your own http request filter chain , with disbaled CSRF and frameOptions
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// authorize all URLs
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		// show login form 
		http.formLogin(withDefaults());
		
		// disable http 
		http.csrf().disable();
		http.headers().frameOptions().disable();		
		return http.build();
	}
	
	
}
