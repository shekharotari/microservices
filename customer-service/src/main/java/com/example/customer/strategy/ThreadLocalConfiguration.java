package com.example.customer.strategy;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;

@Configuration
public class ThreadLocalConfiguration {
	@Autowired
	private HystrixConcurrencyStrategy concurrencyStrategy;
	
	@PostConstruct
	public void init() {
		HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
		HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
		HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
		HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
		
		HystrixPlugins.reset();

		HystrixPlugins.getInstance().registerConcurrencyStrategy(new ThreadLocalAwareConcurrencyStrategy(concurrencyStrategy));
		HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
		HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
		HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
		HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
	}
}
