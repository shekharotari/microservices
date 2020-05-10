package com.example.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerServiceController {
	
	@Autowired
	private DatabaseProperties databaseProperties;
	
	@Autowired
	@Qualifier("customerService")
	private CustomerServiceBean customerServiceBean;
	
	
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<CustomerDO>> getCustomers() {
		ResponseEntity<List<CustomerDO>> responseEntity = new ResponseEntity<>(customerServiceBean.getCustomers(), HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json"})
	public ResponseEntity<CustomerDO> getCustomer(@PathVariable("id") Long customerId) {		
		ResponseEntity<CustomerDO> responseEntity = new ResponseEntity<>(customerServiceBean.getCustomer(customerId), HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/database-properties", produces = {"application/json"})
	public ResponseEntity<DatabaseProperties> getDatabaseProperties() {
		return new ResponseEntity<>(databaseProperties, HttpStatus.OK);
	}
	
	@GetMapping(value = "/items", produces = {"application/json"})
	public List<ItemDO> getItems() {
		return customerServiceBean.getItems();
	}
	
	@GetMapping(value = "/items/{id}", produces = {"application/json"})
	public ItemDO getItem(@PathVariable("id") Long itemId) {
		return customerServiceBean.getItem(itemId);
	}
}
