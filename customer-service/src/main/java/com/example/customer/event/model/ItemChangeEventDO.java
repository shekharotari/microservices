package com.example.customer.event.model;

import java.io.Serializable;

import com.example.customer.model.ItemDO;

public class ItemChangeEventDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String actionType;
	private ItemDO itemDO;

	public ItemChangeEventDO() {
	}

	public ItemChangeEventDO(String actionType, ItemDO itemDO) {
		super();
		this.actionType = actionType;
		this.itemDO = itemDO;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public ItemDO getItemDO() {
		return itemDO;
	}

	public void setItemDO(ItemDO itemDO) {
		this.itemDO = itemDO;
	}

}
