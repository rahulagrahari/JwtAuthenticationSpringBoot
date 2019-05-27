package com.user.management.UserManagement;

import com.user.management.UserManagement.Security.ZuulHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@EnableZuulProxy
@EnableEurekaServer
@SpringBootApplication
public class UserManagementApplication {


	@Bean
	public ZuulHeaderFilter getZuulHeaderFilter(){
		return new ZuulHeaderFilter();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
