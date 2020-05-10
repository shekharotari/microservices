package com.example.customer;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "inventory-service", path = "/inventoryService/inventory")
public interface InventoryFeignClient {

	@GetMapping(produces = {"application/json"})
	List<ItemDO> getItems();
	
	@GetMapping(value = "/{id}", produces = {"application/json"})
	ItemDO getItem(@PathVariable("id") Long itemId);
}
