package com.example.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service("customerService")
public class CustomerServiceBean {
	@Autowired
	private InventoryFeignClient inventoryFeignClient;
	
	private static AtomicLong customerIdGenerator = new AtomicLong(0);
	
	private static Map<Long, CustomerDO> customerMap = new ConcurrentHashMap<>();
	
	static {
		customerMap.put(customerIdGenerator.incrementAndGet(), new CustomerDO(customerIdGenerator.get(), "Reshama", "Otari", 35));
		customerMap.put(customerIdGenerator.incrementAndGet(), new CustomerDO(customerIdGenerator.get(), "Chandrashekhar", "Otari", 39));
	}
	
	public List<CustomerDO> getCustomers() {
		List<CustomerDO> customerDOs = new ArrayList<>();
		
		customerMap.forEach((Long customerId, CustomerDO customerDO) -> customerDOs.add(customerDO));
		
		return customerDOs;
	}
	
	public CustomerDO getCustomer(Long customerId) {
		CustomerDO customerDO = customerMap.get(customerId);
		if (customerDO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + customerId + " is missing");
		}
		
		return customerDO;
	}
	
	@HystrixCommand(
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
			},
			fallbackMethod = "getItemsFallback",
			threadPoolKey = "inventoryPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "5"),
			}
			)
	public List<ItemDO> getItems() {
		return inventoryFeignClient.getItems();
	}
	
	public ItemDO getItem(Long itemId) {
		return inventoryFeignClient.getItem(itemId);
	}
	
	public List<ItemDO> getItemsFallback() {
		System.out.println("Fallback getItems method is called");
		return new ArrayList<ItemDO>(0);
	}
}