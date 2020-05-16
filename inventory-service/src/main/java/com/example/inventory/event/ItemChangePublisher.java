package com.example.inventory.event;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import com.example.inventory.channel.CustomChannels;
import com.example.inventory.event.model.ItemChangeEventDO;
import com.example.inventory.model.ItemDO;

@EnableBinding(CustomChannels.class)
public class ItemChangePublisher {
	@Autowired
	private CustomChannels customChannels;

	public void publishItemChange(String actionType, ItemDO itemDO) {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("type", itemDO.getType());
		
		MessageHeaders messageHeaders = new MessageHeaders(headers);
		
		ItemChangeEventDO itemChangeEventDO = new ItemChangeEventDO(actionType, itemDO);
		
		Message<ItemChangeEventDO> message = MessageBuilder.createMessage(itemChangeEventDO, messageHeaders);
		
		MessageChannel channel = customChannels.outboundItemChange();
		channel.send(message, 5 * 1000);
	}
}
