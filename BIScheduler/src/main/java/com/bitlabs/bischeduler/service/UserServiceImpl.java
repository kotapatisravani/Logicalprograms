package com.bitlabs.bischeduler.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitlabs.bischeduler.entity.Password;
import com.bitlabs.bischeduler.entity.Users;
import com.bitlabs.bischeduler.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService { // UserDetailsService pre-define interface
	@Autowired
    private UserRepository userrepository;
		
	
	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Override
	public boolean saveUsersData(Users users) {
         boolean b=false;
	//Users u=userrepository.save(users);
		List<Users> us=userrepository.findAll();
		
		
		
		for(Users s:us) {
			if(s.getUsername().equalsIgnoreCase(users.getUsername())) {
				
				b=true;
				break;
			}
		}
		if(b==false) {
			String pwd=passwordEncoder.encode(users.getPassword());
			users.setPassword(pwd);
			userrepository.save(users);
		}
				
		return b;
	}
	
	@Override
	public boolean login(Users user) {
		boolean b=false;
		
		List<Users> users=userrepository.findAll();
		
		for(Users u:users) {
			
			if(u.getUsername().equalsIgnoreCase(user.getUsername()) && passwordEncoder.matches(user.getPassword(),u.getPassword() ) ){
				
				b=true;
			}
		}
		return b;
	}
	
	@Override
	public String updatePaswword(Password pwd) {
		

		Users u=userrepository.findUserByUsername(pwd.getUsername());
		
		if(u.getPassword().equals(pwd.getOldpwd())) {
			u.setPassword(pwd.getCfmpwd());
			userrepository.save(u);
			return "password update sucessfully";
		}
		else {
			return "old password is incorrect";
		}
		
	}
	
	@Override
	public List<Users> getAllUsers() {
		
		return userrepository.findAll();
	}
	
	@Override
	public boolean deleteUserById(Users user) {
		
		List<Users> b=userrepository.findAll();
		boolean f=false;
		for(Users u:b)
			if(u.getId()==user.getId()) {
				userrepository.deleteById(user.getId());
				f=true;
			}
		return f;
		
	}
	  
	@Override
	public List<Users> getUsersByUsername(String username) {
		
		return userrepository.findUsersByUsername(username);
	}
	
	@Override
	public Users getUserDataById(int id) {
		
		
		return userrepository.findById(id).get();
	}
	
	
	
	
	@Override
	public String updateUserById(int id, Users user) {
		
		Users us=userrepository.findById(id).get();
		user.setCreated_at(us.getCreated_at());
		Users u=userrepository.save(user);
		if(u!=null) {
			return "Update Sucessfull";
		}
		else {
			return "update failed";
		}
	}
	

	

}
