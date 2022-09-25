package com.samsung.fmcs.stompclient.repository;

import com.samsung.fmcs.stompclient.entity.Trend;
import com.samsung.fmcs.stompclient.vo.TrendId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface TrendRepository extends JpaRepository<Trend, TrendId> {
    Stream<Trend> findTrendStream();
}
