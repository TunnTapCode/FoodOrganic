package com.Project.FoodOrganic;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testpass {
	
	public static void main(String [] args) {
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}
}
