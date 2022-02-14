package com.websocket.wstest.handler;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

// 메세지 형태면 TextWebSocketHandler, 이미자와 같은 다른 리소스를 받으면 BinaryWebSocketHandler 쓰자.
@Profile("!stomp")
@Component
public class MyHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new ArrayList<>();

    // 클라이언트가 연결되었을 때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("afterConnectionEstablished = " + session);
        // 연결된 세션을 모두 담는 것.
        sessions.add(session);
    }

    // Handler 가 받은 메세지
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        System.out.println("handleTextMessage = " + session + " : " + message);

        String senderId = session.getId();

        for (WebSocketSession wss : sessions) {
            wss.sendMessage(new TextMessage(senderId + " : " + message.getPayload()));
        }
    }

    // 연결이 끊겼을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        System.out.println("afterConnectionClosed = " + session + " : " + status);
    }
}
