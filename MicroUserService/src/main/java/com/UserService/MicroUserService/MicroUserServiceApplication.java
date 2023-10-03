package com.UserService.MicroUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MicroUserServiceApplication {





	public static void main(String[] args) {


		SpringApplication.run(MicroUserServiceApplication.class, args);
		System.out.println("the user micro service has been started ");
	}

}
