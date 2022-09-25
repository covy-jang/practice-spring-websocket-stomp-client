package com.samsung.fmcs.stompclient.vo;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class TrendId implements Serializable {
    private static final long serialVersionUID = 3145954945738861958L;
    private LocalDateTime trndDate;
    private Integer eqpNo;
    private String paramCode;

    @Builder
    public TrendId(LocalDateTime trndDate, Integer eqpNo, String paramCode) {
        this.trndDate = trndDate;
        this.eqpNo = eqpNo;
        this.paramCode = paramCode;
    }
}
