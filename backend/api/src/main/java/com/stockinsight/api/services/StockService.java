package com.stockinsight.api.services;

import com.stockinsight.api.dtos.StockDto;
import com.stockinsight.api.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<StockDto> getStocks() {
        return this.stockRepository.getStocks();
    }
}
