package com.Project.FoodOrganic.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import com.Project.FoodOrganic.Service.CustomerUserDetail;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired 
	CustomerUserDetail customerUserDetail ;
	
	 @Bean
	 BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).
		authorizeHttpRequests(auth -> auth.requestMatchers("/static/**","/signup","/dangky").permitAll()
				.anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/check")
						.defaultSuccessUrl("/home",true)
						.permitAll());
		return http.build();
	}
    
    @Bean 
    WebSecurityCustomizer webSecurity () {
    	return (web) -> web.ignoring().requestMatchers("/css/**" 
    			,"/fonts/**","/img/**","/js/**",
    			"/lib/**","/scss/**","/vendeor/**" );
    }
    
}
