package com.example.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.example.customer.strategy.UserContext;
import com.example.customer.strategy.UserContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer
public class CustomerServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public RequestInterceptor feignRequestInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
				template.header(UserContext.AUTHORIZATION_HEADER, UserContextHolder.getUserContext().getHeader(UserContext.AUTHORIZATION_HEADER));
			}
		};
	}
}
