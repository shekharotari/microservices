package com.example.customer.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
	public static final String AUTHORIZATION_HEADER = "authorization";
	
	private Map<String, String> headers = new HashMap<String, String>();
	
	public void addHeader(String name, String value) {
		headers.put(name, value);
	}
	
	public String getHeader(String name) {
		return headers.get(name);
	}
	
}
