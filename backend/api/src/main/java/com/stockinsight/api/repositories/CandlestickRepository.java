package com.stockinsight.api.repositories;

import com.stockinsight.api.dtos.CandlestickDto;
import com.stockinsight.api.entities.Candlestick;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandlestickRepository extends JpaRepository<Candlestick, String> {

  @Query("""
      SELECT new com.stockinsight.api.dtos.CandlestickDto(
          c.stock.symbol as symbol,
          c.date as date,
          c.open as open,
          c.high as high,
          c.low as low,
          c.close as close
      )
      FROM Candlestick c
      WHERE c.stock.symbol = :symbol
      """)
  List<CandlestickDto> getCandlesticksForStock(@Param("symbol") String symbol);
}