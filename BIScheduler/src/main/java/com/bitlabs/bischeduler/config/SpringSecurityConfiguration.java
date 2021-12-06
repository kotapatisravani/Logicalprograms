package com.bitlabs.bischeduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bitlabs.bischeduler.filters.JwtRequestFilter;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(myUserDetailsService);
	}

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	return NoOpPasswordEncoder.getInstance();
//	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.csrf().disable()

	.authorizeRequests()
	.antMatchers("/deleteuserbyid","/getusersbyusername","/getallusers","/deletejob","/getalljob").hasRole("ADMIN")
	.antMatchers("/updatebyid","/updatepassword","/savejobs","/updatejob","/getJobDeatailByName","/getjobs").hasAnyRole("USER","ADMIN")
	.antMatchers("/authenticate","/userregistration").permitAll()

	.anyRequest().authenticated().and().
	exceptionHandling().and().sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
}
