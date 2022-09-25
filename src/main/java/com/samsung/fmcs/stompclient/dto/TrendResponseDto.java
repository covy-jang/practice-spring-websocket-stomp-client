package com.samsung.fmcs.stompclient.dto;

import com.samsung.fmcs.stompclient.vo.ResponseStatus;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrendResponseDto {
    private String requestId;
    private TrendDto trendDto;

    @Builder
    public TrendResponseDto(String requestId, TrendDto trendDto) {
        this.requestId = requestId;
        this.trendDto = trendDto;
    }
}
