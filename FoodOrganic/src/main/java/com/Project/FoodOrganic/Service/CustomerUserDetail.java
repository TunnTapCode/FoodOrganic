package com.Project.FoodOrganic.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Project.FoodOrganic.Entity.User;

@Service
public class CustomerUserDetail implements UserDetailsService{
	@Autowired  
	UserService userService ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userEntity = userService.findByUsername(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException(" Not found !");
		}else {
			return 	org.springframework.security.core.userdetails.User.withUsername(username).password(userEntity.getPassword()).authorities(userEntity.getRole().getName()).build() ;
		}
		
		
	}
	
	

}
