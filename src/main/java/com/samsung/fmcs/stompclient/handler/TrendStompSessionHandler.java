package com.samsung.fmcs.stompclient.handler;

import com.samsung.fmcs.stompclient.dto.TrendRequestDto;
import com.samsung.fmcs.stompclient.dto.TrendResponseDto;
import com.samsung.fmcs.stompclient.service.TrendCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrendStompSessionHandler extends StompSessionHandlerAdapter {
    private final TrendCoreService trendCoreService;

    private Optional<StompSession> stompSession = Optional.empty();

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return TrendRequestDto.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        TrendRequestDto requestDto = (TrendRequestDto) payload;
        handleTrendStream(requestDto);
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        stompSession = Optional.of(session);
        stompSession.ifPresent(sess -> sess.subscribe("/user/queue/request", this));
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Exception : {}", exception.getMessage());
    }

    private void handleResponse(TrendResponseDto responseDto) {
        stompSession.ifPresent(sess -> sess.send("/app/trend", responseDto));
    }

    private void handleTrendStream(TrendRequestDto requestDto) {
        trendCoreService.processTrendStream(requestDto, responseDto -> handleResponse(responseDto));
    }

    public Optional<StompSession> getStompSession() {
        return this.stompSession;
    }
}
