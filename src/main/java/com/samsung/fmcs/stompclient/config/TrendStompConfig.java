package com.samsung.fmcs.stompclient.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.samsung.fmcs.stompclient.client.TrendWebSocketStompClient;
import com.samsung.fmcs.stompclient.handler.TrendStompSessionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Configuration
public class TrendStompConfig {
    private final TrendStompSessionHandler trendStompSessionHandler;

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        converter.setObjectMapper(mapper);
        return converter;
    }

    @Bean
    public List<Transport> transports() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    @Bean
    public StompHeaders stompHeaders() {
        StompHeaders stompHeaders = new StompHeaders();
        stompHeaders.add("substitute", "trend/shrink");
        return stompHeaders;
    }

    @Bean
    public TrendWebSocketStompClient webSocketStompClient() throws ExecutionException, InterruptedException {
        TrendWebSocketStompClient webSocketStompClient = new TrendWebSocketStompClient(new SockJsClient(transports()), trendStompSessionHandler);
        webSocketStompClient.setMessageConverter(messageConverter());
        webSocketStompClient.setAutoStartup(true);
        webSocketStompClient.setStompHeaders(stompHeaders());
        webSocketStompClient.connect("ws://127.0.0.1/trend");
        return webSocketStompClient;
    }

}
