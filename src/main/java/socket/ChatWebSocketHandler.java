package socket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.apollo.inbox.service.InboxService;
import com.apollo.task.service.TaskService;


public class ChatWebSocketHandler extends TextWebSocketHandler{
	@Autowired
	private TaskService service;
	@Autowired
	private InboxService serviceinbox;
	
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished( //function onOpen(evt)
			WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		System.out.println(session.getAttributes().size());
		System.out.println(session.getAttributes().toString());
		String userid = (String)session.getAttributes().get("mid");
		
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
			WebSocketSession session, TextMessage message) {
		try {
			String userid = (String)session.getAttributes().get("mid");
			System.out.println(message.getPayload());
			System.out.println("userid " + userid);
			/*int tid = (Integer) map.get("tid");*/
			
			int idx = (message.getPayload()).indexOf("|");
			
			String strtid = message.getPayload().substring(0, idx);
			int tid = Integer.parseInt(strtid);
			System.out.println("tid : " +tid);
			ArrayList<String> midlist = service.getMidinAssingnee(tid);

			for(int i = 0 ;i<midlist.size();i++) {	
				System.out.println("midlist " + midlist.get(i));
			}
			int newcount = 0;
			String newcountstr = null;

			Set key = users.keySet();
			System.out.println("key" + key);

			for (Iterator iterator = key.iterator(); iterator.hasNext();) {
				String keyName = (String) iterator.next();
				WebSocketSession  wssession = users.get(keyName);				
				for(int i = 0 ;i<midlist.size();i++) {			
					if(midlist.get(i).equals(userid)) {
						
						System.out.println("나한테는 알람 안보냄~");
					}else if(midlist.get(i).equals(keyName)){
						newcount = serviceinbox.newCount(midlist.get(i));
						newcountstr = Integer.toString(newcount);
						wssession.sendMessage(new TextMessage(newcountstr));
						System.out.println(wssession.getId() + "에 메시지 발송: " + newcount);
					}
				}
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
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
