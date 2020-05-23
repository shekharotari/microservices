package com.example.customer.strategy;

public class UserContextHolder {
	private static final ThreadLocal<UserContext> tlUserContext = new ThreadLocal<UserContext>();
	
	public static UserContext getUserContext() {
		if (tlUserContext.get() == null) {
			tlUserContext.set(new UserContext());
		}
		
		return tlUserContext.get();
	}
	
	public static void setUserContext(UserContext userContext) {
		tlUserContext.set(userContext);
	}
}
