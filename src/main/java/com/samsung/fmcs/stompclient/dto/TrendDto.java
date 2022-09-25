package com.samsung.fmcs.stompclient.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.samsung.fmcs.stompclient.entity.Trend;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrendDto {
    private LocalDateTime trndDate;
    private Integer eqpNo;
    private String paramCode;
    private String trndComprsVal;

    @Builder
    public TrendDto(LocalDateTime trndDate, Integer eqpNo, String paramCode,
                    String trndComprsVal) {
        this.trndDate = trndDate;
        this.eqpNo = eqpNo;
        this.paramCode = paramCode;
        this.trndComprsVal = trndComprsVal;
    }

    public static TrendDto of(Trend trend) {
        return TrendDto.builder()
                .trndDate(trend.getTrendId().getTrndDate())
                .eqpNo(trend.getTrendId().getEqpNo())
                .paramCode(trend.getTrendId().getParamCode())
                .trndComprsVal(trend.getTrndComprsVal())
                .build();
    }
}
