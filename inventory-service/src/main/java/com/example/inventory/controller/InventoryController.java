package com.example.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory.model.ItemDO;
import com.example.inventory.service.InventoryService;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping(value = "/items", produces = {"application/json"})
	public ResponseEntity<List<ItemDO>> getItems() {
		ResponseEntity<List<ItemDO>> responseEntity = new ResponseEntity<>(inventoryService.getItems(), HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "/items/{id}", produces = {"application/json"})
	public ResponseEntity<ItemDO> getItem(@PathVariable("id") Long itemId) {	
		ResponseEntity<ItemDO> responseEntity = new ResponseEntity<>(inventoryService.getItem(itemId), HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping(value = "/items", produces = {"application/json"})
	public ResponseEntity<ItemDO> addItem(@RequestBody ItemDO itemDO) {
		ResponseEntity<ItemDO> responseEntity = new ResponseEntity<>(inventoryService.addItem(itemDO), HttpStatus.CREATED);
		return responseEntity;
	}
}
