package com.example.gateway.filter;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class UserRequestTrackingFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		
		// Check if custom correlation id is present. If not, then generate it.
		String customCorrelationId = getCustomCorrelationId();
		if (customCorrelationId == null) {
			customCorrelationId = UUID.randomUUID().toString();
			requestContext.addZuulRequestHeader("custom-correlation-id", customCorrelationId);
		}
		
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
