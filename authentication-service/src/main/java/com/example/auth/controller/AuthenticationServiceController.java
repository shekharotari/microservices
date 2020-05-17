package com.example.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationServiceController {
	
	@GetMapping(value = "/user", produces = {"application/json"})
	public Map<String, Object> getUserInfo(OAuth2Authentication authentication) {
		Map<String, Object> userInfoMap = new HashMap<String, Object>();
		
		userInfoMap.put("user", authentication.getUserAuthentication().getPrincipal());
		userInfoMap.put("authorities", authentication.getUserAuthentication().getAuthorities());
		
		return userInfoMap;
	}
}
