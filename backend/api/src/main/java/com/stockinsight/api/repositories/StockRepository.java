package com.stockinsight.api.repositories;

import com.stockinsight.api.dtos.StockDto;
import com.stockinsight.api.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {

    @Query("""
        SELECT new com.stockinsight.api.dtos.StockDto(
            s.symbol as symbol,
            s.exchange.name as exchangeName,
            s.currency as currency,
            s.companyName as companyName,
            s.marketCap as marketCap,
            s.industry as industry,
            s.sector as sector
        )
        FROM Stock s
        """)
    List<StockDto> getStocks();
}