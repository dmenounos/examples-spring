package com.example.websocket.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class TrigSessionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrigSessionListener.class);

	private AtomicInteger sessionCount = new AtomicInteger();

	@EventListener
	public void onSessionConnect(SessionConnectEvent event) {
		LOGGER.debug("onSessionConnect: {}", sessionCount.incrementAndGet());
	}

	@EventListener
	public void onSessionDisconnect(SessionDisconnectEvent event) {
		LOGGER.debug("onSessionDisconnect: {}", sessionCount.decrementAndGet());
	}
}
