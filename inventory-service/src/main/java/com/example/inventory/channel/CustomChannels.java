package com.example.inventory.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomChannels {

	@Output("outboundItemChanges")
	MessageChannel outboundItemChange();
}
