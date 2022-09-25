package com.samsung.fmcs.stompclient.client;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class TrendWebSocketStompClient extends WebSocketStompClient {
    private StompHeaders stompHeaders;
    private StompSessionHandler stompSessionHandler;

    public TrendWebSocketStompClient(WebSocketClient webSocketClient, StompSessionHandler stompSessionHandler) {
        super(webSocketClient);

        Objects.requireNonNull(stompSessionHandler);
        this.stompSessionHandler = stompSessionHandler;
    }

    public void setStompHeaders(StompHeaders stompHeaders) {
        this.stompHeaders = stompHeaders;
    }

    public StompSession connect(String url) throws ExecutionException, InterruptedException {
        return super.connect(url, new WebSocketHttpHeaders(), stompHeaders, stompSessionHandler).get();
    }

    public StompSessionHandler getStompSessionHandler() {
        return this.stompSessionHandler;
    }

}
