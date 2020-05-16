package com.example.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.inventory.event.ItemChangePublisher;
import com.example.inventory.model.ItemDO;

@Service("inventoryService")
public class InventoryService {
	private static AtomicLong itemIdGenerator = new AtomicLong(0);
	
	private static Map<Long, ItemDO> itemMap = new ConcurrentHashMap<>();
	
	@Autowired
	private ItemChangePublisher itemChangePublisher;
	
	static {
		itemMap.put(itemIdGenerator.incrementAndGet(), new ItemDO(itemIdGenerator.get(), "Cereals", "Corn Flakes", 1000));
		itemMap.put(itemIdGenerator.incrementAndGet(), new ItemDO(itemIdGenerator.get(), "Toiletries", "Lux Soap", 2000));
		itemMap.put(itemIdGenerator.incrementAndGet(), new ItemDO(itemIdGenerator.get(), "Toiletries", "Dettol Handwash", 3000));
	}
	
	public List<ItemDO> getItems() {
		List<ItemDO> itemDOs = new ArrayList<>();

		itemMap.forEach((Long itemId, ItemDO itemDO) -> itemDOs.add(itemDO));
		
		return itemDOs;
	}
	
	public ItemDO getItem(Long itemId) {	
		ItemDO itemDO = itemMap.get(itemId);
		if (itemDO == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with id " + itemId + " is missing");
		}
		
		return itemDO;
	}

	public ItemDO addItem(ItemDO newItemDO) {
		// Error if the item already exists
		itemMap.forEach((Long itemId, ItemDO itemDO) -> {
			if (itemDO.getType().equalsIgnoreCase(newItemDO.getType()) 
					&& itemDO.getName().equalsIgnoreCase(newItemDO.getName())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Item with type '" + newItemDO.getType() + "' and name '" + newItemDO.getName() + "' is already present");
			}
		});
		
		// Add the item to the store
		Long newItemId = itemIdGenerator.incrementAndGet();
		newItemDO.setId(newItemId);
		
		itemMap.put(newItemId, newItemDO);
		
		// Notify the subscribers about the new item added to the store
		itemChangePublisher.publishItemChange("CREATE", newItemDO);
		
		return newItemDO;
	}
}
