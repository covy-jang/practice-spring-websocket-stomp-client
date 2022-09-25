package com.samsung.fmcs.stompclient.service;

import com.google.common.collect.Lists;
import com.samsung.fmcs.stompclient.dto.TrendDto;
import com.samsung.fmcs.stompclient.dto.TrendRequestDto;
import com.samsung.fmcs.stompclient.dto.TrendResponseDto;
import com.samsung.fmcs.stompclient.entity.Trend;
import com.samsung.fmcs.stompclient.repository.TrendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrendCoreService {
    private final TrendRepository trendRepository;

    @Async
    public void processTrendStream(TrendRequestDto requestDto,
                                   Consumer<TrendResponseDto> consumer) {
        Stream<Trend> trendStream = trendRepository.findTrendStream();
        trendStream.forEach(
                trend -> {
                    TrendResponseDto responseDto = TrendResponseDto.builder()
                            .requestId(requestDto.getRequestId())
                            .trendDto(TrendDto.of(trend))
                            .build();
                    consumer.accept(responseDto);
                }
        );
    }
}
