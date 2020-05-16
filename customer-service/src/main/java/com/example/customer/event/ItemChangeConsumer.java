package com.example.customer.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.example.customer.channel.CustomChannels;
import com.example.customer.event.model.ItemChangeEventDO;

@EnableBinding(CustomChannels.class)
public class ItemChangeConsumer {
	
	@StreamListener(value = "inboundItemChanges")
	public void consumeItemChange(ItemChangeEventDO itemChangeEventDO) {
		System.out.println("We have received Item Change notification.");
		System.out.println("Action Type: " + itemChangeEventDO.getActionType());
		System.out.println("Item Details: " + itemChangeEventDO.getItemDO());
	}
}
