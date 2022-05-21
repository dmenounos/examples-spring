package com.example.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class PrivateHandler extends TextWebSocketHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateHandler.class);

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.trace("afterConnectionEstablished: session: {}", session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.trace("afterConnectionClosed: session: {} status: {}", session, status);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.trace("handleTextMessage: session: {} message: {}", session, message);
		session.sendMessage(message);
	}
}
