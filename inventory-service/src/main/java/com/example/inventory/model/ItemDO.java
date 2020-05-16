package com.example.inventory.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class ItemDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "id")
	private Long id;

	@XmlElement(name = "type")
	private String type;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "quantity")
	private int quantity;

	public ItemDO() {
	}

	public ItemDO(Long id, String type, String name, int quantity) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
