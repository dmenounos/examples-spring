package com.example.websocket.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class BroadcastHandler extends TextWebSocketHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(BroadcastHandler.class);

	private List<WebSocketSession> sessionList = Collections.synchronizedList(new ArrayList<>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.trace("afterConnectionEstablished: session: {}", session);
		sessionList.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.trace("afterConnectionClosed: session: {} status: {}", session, status);
		sessionList.remove(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.trace("handleTextMessage: session: {} message: {}", session, message);
		synchronized (sessionList) {
			for (WebSocketSession s : sessionList) {
				s.sendMessage(message);
			}
		}
	}
}
