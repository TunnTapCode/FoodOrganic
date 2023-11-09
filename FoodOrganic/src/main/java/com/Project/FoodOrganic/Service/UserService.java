package com.Project.FoodOrganic.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.Project.FoodOrganic.Entity.User;

import com.Project.FoodOrganic.Repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userrepo ;


	
	public User findByUsername(String Username) {
		return userrepo.findByUsername(Username);
	}
	public List<User> findByEmail(String enail) {
		return userrepo.findByEmail(enail);
	}
	
	
	public void save (User user) {
		userrepo.save(user) ;
		
	}
	public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }

}
