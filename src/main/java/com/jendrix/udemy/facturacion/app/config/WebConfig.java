package com.jendrix.udemy.facturacion.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		//TODO: tomar el upload folder desde una properties
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/Users/fravegamac/Personal/uploads/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/403").setViewName("errors/403");
	}
}
