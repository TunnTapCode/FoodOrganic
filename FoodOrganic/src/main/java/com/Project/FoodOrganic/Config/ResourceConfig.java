package com.Project.FoodOrganic.Config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
@EnableWebMvc
public class ResourceConfig implements WebMvcConfigurer  {
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/static/**").addResourceLocations("classpath:/static/");
}
}
