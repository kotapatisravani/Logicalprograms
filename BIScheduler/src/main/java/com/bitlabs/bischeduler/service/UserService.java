package com.bitlabs.bischeduler.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;

import com.bitlabs.bischeduler.entity.Password;
import com.bitlabs.bischeduler.entity.Users;

public interface UserService {

	boolean saveUsersData(@Valid Users users);

	boolean login(Users user);

	

	List<Users> getUsersByUsername(String username);

	List<Users> getAllUsers();

	String updateUserById(int id, Users user);


	String updatePaswword(Password pwd);

	Users getUserDataById(int id);

	boolean deleteUserById(Users user);



	



}
