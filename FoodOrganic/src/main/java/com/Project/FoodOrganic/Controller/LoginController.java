package com.Project.FoodOrganic.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.FoodOrganic.Entity.Image_Avt;
import com.Project.FoodOrganic.Entity.Role;
import com.Project.FoodOrganic.Entity.User;
import com.Project.FoodOrganic.Repository.RoleRepo;
import com.Project.FoodOrganic.Service.ImageService;
import com.Project.FoodOrganic.Service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService service;
	@Autowired
	private RoleRepo rolerepo;
	@Autowired
	ImageService imageService;
	@Autowired
	PasswordEncoder passwordEncoder ;

	@GetMapping("/login")
	public String login() {
		return "Login/login";
	}

	@GetMapping("/signup")
	public String signup(ModelMap model) {
		User user = new User();
		model.addAttribute("User", user);
		return "Login/signup";
	}

	@PostMapping("/dangky")
	public String dangky(@ModelAttribute User user, Model model) {
		if (service.findByUsername(user.getUsername()) == null) {
			List<User> u = service.findByEmail(user.getEmail());
			if (u.size() == 0) {
				BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
				user.setPassword(passEncode.encode(user.getPassword()));

				Role userRole = rolerepo.findByName("USER");
				if (userRole == null) {
					userRole = new Role();
					userRole.setName("USER");
				}
				user.setRole(userRole);
				List<Image_Avt> listI = imageService.findAll();
				Random r = new Random();
				Long random = r.nextLong(listI.size());
				Optional<Image_Avt> image = imageService.findById(random);
				user.setImage_Avt(image.get());
				service.save(user);
				User User = new User();
				model.addAttribute("User", User);
				model.addAttribute("msg1", "Đăng kí thành công");
				return "Login/signup";
			} else {
				User User = new User();
				model.addAttribute("User", User);
				model.addAttribute("msg", "Email đã tồn tại");
				return "Login/signup";
			}

		} else {
			User User = new User();
			model.addAttribute("User", User);
			model.addAttribute("msg", "Username đã tồn tại");
			return "Login/signup";
		}

	}

	@PostMapping("/changepass")
	public String changepass(@RequestParam(name = "oldpass") String oldpass,
			                 @RequestParam(name = "newpass") String newpass, 
			                 @RequestParam(name = "renewpass") String repass,
			                 Authentication auth,Model model) {
		
		
		User u = service.findByUsername(auth.getName());
		if(u == null) {
			return "/login" ;
		}else {
			if(newpass.equals(repass)) {
				if(passwordEncoder.matches(oldpass, u.getPassword())) {
					u.setPassword(passwordEncoder.encode(newpass));
					service.save(u);
					List<Image_Avt> listI = imageService.findAll();
					model.addAttribute("user", u);
					model.addAttribute("listI", listI);
					model.addAttribute("msg1", "Thay đổi mật khẩu thành công.");
					return "/profile";
				}else {
					List<Image_Avt> listI = imageService.findAll();
					model.addAttribute("user", u);
					model.addAttribute("listI", listI);
					model.addAttribute("msg", "Thay đổi mật khẩu thất bại.") ;
					return "/profile";
				}
			}else {
				List<Image_Avt> listI = imageService.findAll();
				model.addAttribute("user", u);
				model.addAttribute("listI", listI);
				model.addAttribute("msg", "Mật khẩu không khớp.") ;
				return "/profile";
			}
		}
		
		
	}

}
