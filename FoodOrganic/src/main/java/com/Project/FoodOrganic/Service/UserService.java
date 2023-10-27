package com.Project.FoodOrganic.Service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.Project.FoodOrganic.Entity.User;

import com.Project.FoodOrganic.Repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userrepo ;

	
//	@Autowired
//	PasswordEncoder passwordEncoder ;
	
	public User findByUsername(String Username) {
		return userrepo.findByUsername(Username);
	}
	
	public void save (User user) {
		userrepo.save(user) ;
		
	}

}
