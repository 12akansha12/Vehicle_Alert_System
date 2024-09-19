package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlertWebSocketHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> sessions = new ArrayList<>();
    private static List<VehicleAlert> criticalAlerts = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    public void broadcastCriticalAlerts() throws IOException {
        String alertsMessage = new ObjectMapper().writeValueAsString(criticalAlerts);
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(alertsMessage));
            }
        }
    }

    public static void updateCriticalAlerts(List<VehicleAlert> alerts) {
        criticalAlerts = alerts;
    }
}
