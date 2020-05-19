package com.example.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

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
			private static final String AUTHORIZATION_HEADER = "Authorization";
			private static final String BEARER_TOKEN_TYPE = "Bearer";
			
			@Override
			public void apply(RequestTemplate template) {
				if (!template.headers().containsKey(AUTHORIZATION_HEADER)) {
			        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        if (auth != null && auth.getDetails() instanceof OAuth2AuthenticationDetails) {
			            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
			            template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
			        }
			    }
			}
		};
	}
}
