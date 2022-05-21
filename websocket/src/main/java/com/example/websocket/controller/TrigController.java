package com.example.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TrigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TrigController.class);

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private TrigChannelInterceptor listener;
	
	@Autowired
	private TrigService trigService;

	@MessageMapping("/trig/next-values-user")
	@SendToUser(value = "/topic/trig/next-values-user", broadcast = false)
	public TrigResponse nextValuesUser(@Payload TrigRequest request) {
		LOGGER.trace("nextValues: request: {}", request);
		return trigService.getNextValues(request);
	}

	@Scheduled(fixedRate = 1000)
	public void pushValues() {
		if (listener.getRequest() != null) {
			LOGGER.trace("pushValues: request: {}", listener.getRequest());
			TrigResponse response = trigService.getNextValues(listener.getRequest());
			simpMessagingTemplate.convertAndSend("/topic/trig/next-values", response);
		}
	}
}
