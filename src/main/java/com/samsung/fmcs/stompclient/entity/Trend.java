package com.samsung.fmcs.stompclient.entity;

import com.samsung.fmcs.stompclient.vo.TrendId;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "TH_CM_FMCS_TRND")
public class Trend {
    @EmbeddedId
    private TrendId trendId;
    private String trndComprsVal;

    @Builder
    public Trend(TrendId trendId, String trndComprsVal) {
        this.trendId = trendId;
        this.trndComprsVal = trndComprsVal;
    }
}
