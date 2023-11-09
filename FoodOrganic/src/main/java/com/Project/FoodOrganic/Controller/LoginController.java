package com.Project.FoodOrganic.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Project.FoodOrganic.Entity.Role;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Repository.RoleRepo;
import com.Project.FoodOrganic.Service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService service;
	@Autowired
	private RoleRepo rolerepo;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup(ModelMap model) {
		User user = new User();
		model.addAttribute("User", user);
		return "signup";
	}

	@PostMapping("/dangky")
	public String dangky(@ModelAttribute User user,Model model) {
		if (service.findByUsername(user.getUsername()) == null) {
			List<User> u = service.findByEmail(user.getEmail()) ;	
			if(u.size() == 0) {
				BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
				user.setPassword(passEncode.encode(user.getPassword()));

				Role userRole = rolerepo.findByName("USER");
				if (userRole == null) {
					userRole = new Role();
					userRole.setName("USER");
				}
				user.setRole(userRole);
				service.save(user);
				return "redirect:/signup";
			}else {
				User User = new User();
				model.addAttribute("User" ,User) ;
				model.addAttribute("msg","Email đã tồn tại") ;
				return "signup";
			}

		}else {
			User User = new User();
			model.addAttribute("User" ,User) ;
			model.addAttribute("msg","Username đã tồn tại") ;
			return "signup";
		}
		
		
	}
	

}
