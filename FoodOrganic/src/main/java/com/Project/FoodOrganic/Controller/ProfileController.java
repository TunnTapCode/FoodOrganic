package com.Project.FoodOrganic.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Project.FoodOrganic.Entity.Image_Avt;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Service.ImageService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
public class ProfileController {

	@Autowired
	UserService userService;
	@Autowired
	ImageService imageService ;
	
	@GetMapping("/profile")
	public String profile(Model model, Authentication auth) {
		List<Image_Avt> listI = imageService.findAll();
		User user = userService.findByUsername(auth.getName());
		model.addAttribute("user", user);
		model.addAttribute("listI", listI);
		return "Home/profile" ;
	}
	
	@PostMapping("/update-profile")
	public String update_profile(@ModelAttribute User user){
		User u = userService.findByUsername(user.getUsername());
		u.setAddress(user.getAddress());
		u.setFullname(user.getFullname());
		u.setPhone(user.getPhone());
		userService.save(u);
		return "redirect:/profile";
	}
	
}
