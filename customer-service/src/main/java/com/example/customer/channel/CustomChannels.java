package com.example.customer.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
	@Input("inboundItemChanges")
	SubscribableChannel inBoundItemChange();

}
