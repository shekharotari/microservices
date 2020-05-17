package com.example.gateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class UserResponseTrackingFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();

		requestContext.getResponse().addHeader("custom-correlation-id", getCustomCorrelationId());
		
		System.out.println("Completed processing for Request URL: " + requestContext.getRequest().getRequestURL());
		System.out.println("Completed processing for Custom Correlation Id: " + getCustomCorrelationId());
		
		return null;
	}

	private String getCustomCorrelationId() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		
		if (requestContext.getRequest().getHeader("custom-correlation-id") != null) {
			return requestContext.getRequest().getHeader("custom-correlation-id");
		} else if (requestContext.getZuulRequestHeaders().get("custom-correlation-id") != null) {
			return requestContext.getZuulRequestHeaders().get("custom-correlation-id");
		}
		
		return null;
	}
}
