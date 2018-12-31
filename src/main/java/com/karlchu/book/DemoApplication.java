package com.karlchu.book;

import com.karlchu.book.common.WebSiteMeshFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean siteMeshFilter(){
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new WebSiteMeshFilter());
		filter.addUrlPatterns("/*");
		return filter;
	}
}