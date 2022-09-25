package com.samsung.fmcs.stompclient.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrendRequestDto {
    private String requestId;
    private LocalDateTime trndDate;

    @Builder
    public TrendRequestDto(String requestId, LocalDateTime trndDate) {
        this.requestId = requestId;
        this.trndDate = trndDate;
    }
}
