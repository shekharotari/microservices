package com.example.customer.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.customer.model.ItemDO;

@FeignClient(value = "inventory-service", path = "/inventoryService/inventory")
public interface InventoryFeignClient {

	@GetMapping(value = "/items", produces = {"application/json"})
	List<ItemDO> getItems();
	
	@GetMapping(value = "/items/{id}", produces = {"application/json"})
	ItemDO getItem(@PathVariable("id") Long itemId);
}
