package com.example.customer.strategy;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserContextFilter implements Filter {
	private static final Logger LOG = LogManager.getLogger(UserContextFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		// Copy all the request headers
		Enumeration<String> headers = httpServletRequest.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			UserContextHolder.getUserContext().addHeader(header, httpServletRequest.getHeader(header));
			LOG.debug("Name: {}, Value: {}", header, httpServletRequest.getHeader(header));
		}
		
		chain.doFilter(httpServletRequest, response);
	}

}
