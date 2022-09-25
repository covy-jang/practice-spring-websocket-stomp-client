package com.samsung.fmcs.stompclient.client;

import com.samsung.fmcs.stompclient.handler.TrendStompSessionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrendStompSessionObserver {
    private final TrendWebSocketStompClient trendWebSocketStompClient;

    @Scheduled(fixedDelay = 1000)
    public void checkSessionStatus() throws ExecutionException, InterruptedException {
        TrendStompSessionHandler sessionHandler = (TrendStompSessionHandler) trendWebSocketStompClient.getStompSessionHandler();
        Optional<StompSession> sessionOptional = sessionHandler.getStompSession();
        // Session Status Check
        if(sessionOptional.isPresent()) {
            StompSession session = sessionOptional.get();
            if(!session.isConnected()) {
                log.error("Stomp session disconnect, try to reconnect");
                trendWebSocketStompClient.connect("ws://127.0.0.1/trend");
            }
        }
    }

}
