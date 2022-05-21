package com.example.websocket.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class TrigChannelInterceptor implements ChannelInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrigSessionListener.class);

	private static final String BROADCAST_DESTINATION = "/topic/trig/next-values";

	private AtomicInteger subscriptionCount = new AtomicInteger();
	private TrigRequest request;

	@Override
	public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//		LOGGER.trace("afterSendCompletion: command: {} destination: {}", accessor.getCommand(), accessor.getDestination());

		if (StompCommand.SUBSCRIBE.equals(accessor.getCommand()) && BROADCAST_DESTINATION.equals(accessor.getDestination())) {
			LOGGER.debug("afterSendCompletion: subscriptionCount: {}", subscriptionCount.incrementAndGet());

			if (subscriptionCount.intValue() == 1) {
				request = new TrigRequest(0, 10);
			}
		}

		if (StompCommand.UNSUBSCRIBE.equals(accessor.getCommand()) && BROADCAST_DESTINATION.equals(accessor.getDestination())) {
			LOGGER.debug("afterSendCompletion: subscriptionCount: {}", subscriptionCount.decrementAndGet());

			if (subscriptionCount.intValue() == 0) {
				request = null;
			}
		}
	}

	public TrigRequest getRequest() {
		return request;
	}
}
