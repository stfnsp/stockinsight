package com.stockinsight.api.controllers;

import com.stockinsight.api.dtos.StockDto;
import com.stockinsight.api.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public List<StockDto> getStocks() {
        return this.stockService.getStocks();
    }
}
