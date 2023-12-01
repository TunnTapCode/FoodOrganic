package com.Project.FoodOrganic.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;

import com.Project.FoodOrganic.Service.CustomerUserDetail;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	CustomerUserDetail customerUserDetail;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/signup", "/dangky", "/static/**","/home","/detail/**","/review/**","/user/blogs/**","/user/blogs/blogs-detail/**","/product","/","").permitAll()
						.requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated())
						
				.formLogin(
						(login) -> login.loginPage("/login").usernameParameter("username").passwordParameter("password")
								.loginProcessingUrl("/check")
								.successHandler((request, response, authentication) -> {
									
									if (authentication.getAuthorities().stream()
									        .anyMatch(authority -> authority.getAuthority().equals("ADMIN"))) {
										response.sendRedirect("/admin/dashboard");
									}else {
										response.sendRedirect("/home");
									}
									
									
								}).failureUrl("/login?error=true").permitAll())

				.logout((logout) -> logout.logoutUrl("/logout"))

				.rememberMe((remember) -> remember.rememberMeServices(rememberMeServices(customerUserDetail))
						.key("JSESSIONID").tokenValiditySeconds(60));

		return http.build();
	}

	@Bean
	WebSecurityCustomizer webSecurity() {
		return (web) -> web.ignoring().requestMatchers("/css/**", "/images/**", "/fonts/**", "/img/**", "/js/**",
				"/lib/**",
				"/scss/**", "/vendeor/**", "/static/**");
	}

	@Bean
	RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
		RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
		TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("JSESSIONID", userDetailsService,
				encodingAlgorithm);
		rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
		return rememberMe;
	}

}
