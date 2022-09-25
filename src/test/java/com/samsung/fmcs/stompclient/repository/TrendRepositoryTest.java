package com.samsung.fmcs.stompclient.repository;

import com.samsung.fmcs.stompclient.entity.Trend;
import com.samsung.fmcs.stompclient.vo.TrendId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class TrendRepositoryTest {
    private final TrendRepository trendRepository;

    @Autowired
    public TrendRepositoryTest(TrendRepository trendRepository) {
        this.trendRepository = trendRepository;
    }

    @Test
    void 트렌드_조회() {
        trendRepository.findAll()
                .stream()
                .forEach(System.out::println);
    }

    @Test
    void 트랜드_아이디_리스트_조회() {
        List<TrendId> trendIds = new ArrayList<>();

        trendIds.add(TrendId.builder()
                .trndDate(LocalDateTime.parse("2022/04/24 11:48:56",
                        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .eqpNo(7995577)
                .paramCode("PVLAST")
                .build());

        trendIds.add(TrendId.builder()
                .trndDate(LocalDateTime.parse("2022/04/24 11:48:56",
                        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .eqpNo(2842986)
                .paramCode("PVLAST")
                .build());

        trendRepository.findByTrendIds(trendIds);
    }
}