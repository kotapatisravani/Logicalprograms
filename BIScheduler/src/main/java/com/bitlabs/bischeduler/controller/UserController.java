package com.bitlabs.bischeduler.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.bischeduler.entity.AuthenticationResponse;
import com.bitlabs.bischeduler.entity.Password;
import com.bitlabs.bischeduler.entity.Users;
import com.bitlabs.bischeduler.filters.JwtRequestFilter;
import com.bitlabs.bischeduler.response.ResponseHandler;
import com.bitlabs.bischeduler.service.JwtUtil;
import com.bitlabs.bischeduler.service.MyUserDetailsService;
import com.bitlabs.bischeduler.service.UserService;

@CrossOrigin 
@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	  	
	@Autowired
	UserService userservice;
	
	@Autowired
	MyUserDetailsService muds;
	
	@PostMapping("/userregistration")
	public ResponseEntity<Object> useregister(@Valid @RequestBody Users users) {
	
		
		//return userservice.saveUsersData(users);
		boolean b=userservice.saveUsersData(users);
		
		if(b==false) 
			  return ResponseHandler.generateResponse("Registered Successfully", HttpStatus.OK,userservice.saveUsersData(users));
			             
		else 
		      return ResponseHandler.generateResponse("already Existed User", HttpStatus.BAD_REQUEST,!b);
					
	    }
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<Object> login(@RequestBody Users user) throws Exception {

		boolean b=userservice.login(user);
		
		if(b==true) {
			
			return createAuthenticationToken(user);
				
		}
	
		else {
			return ResponseHandler.generateResponse("Login Failed", HttpStatus.BAD_REQUEST , b);
		}
	}


public ResponseEntity<Object> createAuthenticationToken(@RequestBody Users user) throws Exception {

	try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()) //UsernamePasswordAuthenticationToken pre define class which will take username and password
		);
	}
	catch (BadCredentialsException e) {
		throw new Exception("Incorrect username or password", e);
	}

	final UserDetails userDetails = muds.loadUserByUsername(user.getUsername());

	final String jwt = jwtTokenUtil.generateToken(userDetails);
	
	return ResponseHandler.generateResponse("Login successfully"+userDetails.getAuthorities(), HttpStatus.OK,new AuthenticationResponse(jwt));


}

@GetMapping("/getallusers")
public List<Users> getAllUsers(){
	List<Users> users=userservice.getAllUsers();
	if(users==null) {
		return (List<Users>) ResponseHandler.generateResponse("Empty Users", HttpStatus.OK,null);
	}
	else {
		return userservice.getAllUsers();
	}
}

@PutMapping("/updatepassword")
public String updatePassword(@RequestBody Password pwd) {
	
	if(pwd.getNewpwd().equals(pwd.getCfmpwd())) {
		
		return userservice.updatePaswword(pwd);
	}
	else {
		return "new password and confirm password is not matching";
	}
}

@DeleteMapping("/deleteuserbyid")
public ResponseEntity<Object> deleteUser(@RequestBody Users user) {
	
	boolean b=userservice.deleteUserById(user);
	
	if(b==true)
		return ResponseHandler.generateResponse("Deleted Successfully", HttpStatus.OK,b);
	else
		return ResponseHandler.generateResponse("entered existed data", HttpStatus.BAD_REQUEST,b);
}

@GetMapping("/getusersbyusername")
public List<Users> getUsersByUsername(@RequestBody Users user){
	
	return userservice.getUsersByUsername(user.getUsername());
}




@PutMapping("/updatebyid")
public String updateUserById(@RequestBody Users user) {
	return userservice.updateUserById(user.getId(),user);
}



@GetMapping("/getbyid")
public Users getUserDataById(@RequestBody Users user) {
	return userservice.getUserDataById(user.getId());
}
}
