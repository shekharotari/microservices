package com.example.customer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.DatabaseProperties;
import com.example.customer.model.CustomerDO;
import com.example.customer.model.ItemDO;
import com.example.customer.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerServiceController {
	
	@Autowired
	private DatabaseProperties databaseProperties;
	
	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;
	
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<CustomerDO>> getCustomers(@Autowired HttpServletRequest httpServletRequest) {
		System.out.println("Processing for custom correlation id " + httpServletRequest.getHeader("custom-correlation-id"));
		
		ResponseEntity<List<CustomerDO>> responseEntity = new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json"})
	public ResponseEntity<CustomerDO> getCustomer(@PathVariable("id") Long customerId) {		
		ResponseEntity<CustomerDO> responseEntity = new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/database-properties", produces = {"application/json"})
	public ResponseEntity<DatabaseProperties> getDatabaseProperties() {
		return new ResponseEntity<>(databaseProperties, HttpStatus.OK);
	}
	
	@GetMapping(value = "/items", produces = {"application/json"})
	public List<ItemDO> getItems() {
		return customerService.getItems();
	}
	
	@GetMapping(value = "/items/{id}", produces = {"application/json"})
	public ItemDO getItem(@PathVariable("id") Long itemId) {
		return customerService.getItem(itemId);
	}
}
