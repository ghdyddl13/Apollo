package socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class Chat extends TextWebSocketHandler {
	
	// 구조 
	// Map<채팅방 이름, HashMap<참여자 sessionId, 참여자 session 객체>>
	private Map<String, HashMap<String,WebSocketSession>> usermap = new HashMap();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		
		// 입장한 채팅방 이름 꺼내와 변수에 저장 
		String chatname = getCurrentChatRoom(session);
		System.out.println("입장한 채팅방 = " + chatname);

		//채팅방이 기존에 존재했던 방인지에 대한 유무 검증
		if (usermap.containsKey(chatname)) { //기존에 존재해 Map에 저장되어 있었다면,
			usermap.get(chatname).put(session.getId(),session); // 클라이언트 session값 저장
			System.out.println("채팅방 존재했음");
			
		} else {
			System.out.println("새로 생성된 채팅방"); // 채팅방이 새로 생성되었다면 
			Map<String,WebSocketSession> list = new HashMap<String , WebSocketSession>(); 
			list.put(session.getId(),session); // 클라이언트의 sessionId와 session 객체를 Map에 저장한 후
			usermap.put(chatname, (HashMap<String, WebSocketSession>) list); // usermap에 Put함으로써 새로운 채팅방 생성
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		
		String chatname = getCurrentChatRoom(session);
		System.out.println("메시지가 입력된 채팅방 :" + chatname);
		
		for(Map.Entry m : usermap.get(chatname).entrySet()) { // 메시지가 입력된 채팅방에 있는 클라이언트에게만 메시지 전송
			WebSocketSession sess = (WebSocketSession) m.getValue();
			sess.sendMessage(message);
		}
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		// 입장한 채팅방 이름 꺼내와 변수에 저장 
		String chatname = getCurrentChatRoom(session);
		usermap.get(chatname).remove(session.getId());  // 채팅방에서 클라이언트라 접속을 끊으면, 참여중인 목록에서 session을 삭제한 후
		session.close();
		
		
		// session종료시키기
	}
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {	
	}
	
	
	//채팅방의 정보를 받아오는 함수 
	public String getCurrentChatRoom(WebSocketSession session) {
		Map<String, Object> map = session.getAttributes();
		
		return (String) map.get("select");
	}
	


}
