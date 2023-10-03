package com.HotelService.MicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicroHotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroHotelServiceApplication.class, args);

		System.out.println("started the hotel service");
	}

}
