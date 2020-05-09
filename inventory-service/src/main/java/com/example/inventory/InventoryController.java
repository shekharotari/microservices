package com.example.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {
private static AtomicLong itemIdGenerator = new AtomicLong(0);
	
	private static Map<Long, ItemDO> itemMap = new ConcurrentHashMap<>();
	
	static {
		itemMap.put(itemIdGenerator.incrementAndGet(), new ItemDO(itemIdGenerator.get(), "Corn Flakes", 1000));
		itemMap.put(itemIdGenerator.incrementAndGet(), new ItemDO(itemIdGenerator.get(), "Lux Soap", 2000));
		itemMap.put(itemIdGenerator.incrementAndGet(), new ItemDO(itemIdGenerator.get(), "Dettol Handwash", 3000));
	}
	
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<ItemDO>> getItems() {
		List<ItemDO> itemDOs = new ArrayList<>();
		
		itemMap.forEach((Long itemId, ItemDO itemDO) -> itemDOs.add(itemDO));
		
		ResponseEntity<List<ItemDO>> responseEntity = new ResponseEntity<>(itemDOs, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json"})
	public ResponseEntity<ItemDO> getCustomer(@PathVariable("id") Long itemId) {		
		ItemDO itemDO = itemMap.get(itemId);
		if (itemDO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with id " + itemId + " is missing");
		}
		
		ResponseEntity<ItemDO> responseEntity = new ResponseEntity<>(itemDO, HttpStatus.OK);
		return responseEntity;
	}
}
