package com.samsung.fmcs.stompclient.service;

import com.samsung.fmcs.stompclient.dto.TrendRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrendCoreServiceTest {
    private final TrendCoreService trendCoreService;

    @Autowired
    public TrendCoreServiceTest(TrendCoreService trendCoreService) {
        this.trendCoreService = trendCoreService;
    }

    @Test
    void 트렌드_요청_응답() {
        TrendRequestDto requestDto = TrendRequestDto.builder()
                .requestId(UUID.randomUUID().toString())
                .trndDate(LocalDateTime.now().minusDays(1))
                .build();
    }
}