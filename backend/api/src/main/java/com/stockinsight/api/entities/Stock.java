package com.stockinsight.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "symbol")
    private String symbol;

    @ManyToOne
    @JoinColumn(name = "exchange_id")
    private Exchange exchange;

    @Column(name = "currency")
    private String currency;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "market_cap")
    private long marketCap;

    @Column(name = "industry")
    private String industry;

    @Column(name = "sector")
    private String sector;

    private Stock() {}

    public Stock(String symbol, Exchange exchange, String currency, String companyName, long marketCap, String industry, String sector) {
        this.symbol = symbol;
        this.exchange = exchange;
        this.currency = currency;
        this.companyName = companyName;
        this.marketCap = marketCap;
        this.industry = industry;
        this.sector = sector;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
