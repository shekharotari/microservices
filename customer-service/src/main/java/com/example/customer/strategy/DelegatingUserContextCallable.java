package com.example.customer.strategy;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<T> implements Callable<T> {
	private Callable<T> callable;
	private UserContext userContext;
	
	public DelegatingUserContextCallable(Callable<T> callable, UserContext userContext) {
		super();
		this.callable = callable;
		this.userContext = userContext;
	}

	/**
	 * This function is invoked before the method protected by @HystrixCommand annotation.
	 */
	@Override
	public T call() throws Exception {
		// Associate the ThreadLocal variable that stores the UserContext
		// with the thread running the Hystrix protected method
		UserContextHolder.setUserContext(userContext);
		
		try {
			// Call Hystrix protected method e.g. CustomerService#getItems()
			return callable.call();
		} finally {
			userContext = null;
		}
	}

}
