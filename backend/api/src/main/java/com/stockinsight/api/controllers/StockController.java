package com.stockinsight.api.controllers;

import com.stockinsight.api.dtos.CandlestickDto;
import com.stockinsight.api.dtos.StockDto;
import com.stockinsight.api.services.StockService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

  @Autowired
  private StockService stockService;

  @GetMapping("/stocks")
  public List<StockDto> getStocks() {
    return this.stockService.getStocks();
  }

  @GetMapping("/candlesticks/{symbol}")
  public List<CandlestickDto> getCandlesticks(@PathVariable("symbol") String symbol) {
    return this.stockService.getCandlesticksForStock(symbol);
  }
}
