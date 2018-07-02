package com.apollo.socket;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class ChatWebSocketHandler extends TextWebSocketHandler{
	
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	
	@Override
	public void afterConnectionEstablished( //function onOpen(evt)
			WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		System.out.println(session.getAttributes().size());
		System.out.println(session.getAttributes().toString());
		String userid = (String)session.getAttributes().get("nickname");
		log(userid + " 연결 됨");
		users.put(userid, session);
	}

	@Override
	public void afterConnectionClosed( //function onClose(evt) 
			WebSocketSession session, CloseStatus status) throws Exception {
		log(session.getId() + " 연결 종료됨");
		users.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(  //function onMessage(evt) 
			WebSocketSession session, TextMessage message) throws Exception {
		log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
		for (WebSocketSession s : users.values()) {
			s.sendMessage(new TextMessage(session.getAttributes().get("nickname")+": "+message.getPayload()));
			log(s.getId() + "에 메시지 발송: " + message.getPayload());
		}
	} //클라이언트가 전송한 메세지값을 websocketsession에 담는다
	
	
	//에러처리 부분
	@Override
	public void handleTransportError(
			WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + " 익셉션 발생: ");
		exception.printStackTrace();
	}

	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}

	
	

}
