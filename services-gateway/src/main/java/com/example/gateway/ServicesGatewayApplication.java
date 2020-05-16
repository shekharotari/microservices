package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ServicesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesGatewayApplication.class, args);
	}

}
