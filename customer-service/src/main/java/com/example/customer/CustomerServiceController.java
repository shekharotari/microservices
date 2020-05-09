package com.example.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/customer")
public class CustomerServiceController {
	
	@Autowired
	private DatabaseProperties databaseProperties;
	
	private static AtomicLong customerIdGenerator = new AtomicLong(0);
	
	private static Map<Long, CustomerDO> customerMap = new ConcurrentHashMap<>();
	
	static {
		customerMap.put(customerIdGenerator.incrementAndGet(), new CustomerDO(customerIdGenerator.get(), "Reshama", "Otari", 35));
		customerMap.put(customerIdGenerator.incrementAndGet(), new CustomerDO(customerIdGenerator.get(), "Chandrashekhar", "Otari", 39));
	}
	
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<CustomerDO>> getCustomers() {
		List<CustomerDO> customerDOs = new ArrayList<>();
		
		customerMap.forEach((Long customerId, CustomerDO customerDO) -> customerDOs.add(customerDO));
		
		ResponseEntity<List<CustomerDO>> responseEntity = new ResponseEntity<>(customerDOs, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json"})
	public ResponseEntity<CustomerDO> getCustomer(@PathVariable("id") Long customerId) {		
		CustomerDO customerDO = customerMap.get(customerId);
		if (customerDO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + customerId + " is missing");
		}
		
		ResponseEntity<CustomerDO> responseEntity = new ResponseEntity<>(customerDO, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/database-properties", produces = {"application/json"})
	public ResponseEntity<DatabaseProperties> getDatabaseProperties() {
		return new ResponseEntity<>(databaseProperties, HttpStatus.OK);
	}
}
