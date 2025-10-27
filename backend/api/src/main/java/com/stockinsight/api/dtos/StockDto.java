package com.stockinsight.api.dtos;

public class StockDto {

  private String symbol;
  private String exchangeName;
  private String currency;
  private String companyName;
  private long marketCap;
  private String industry;
  private String sector;

  public StockDto(String symbol, String exchangeName, String currency, String companyName,
      long marketCap, String industry, String sector) {
    this.symbol = symbol;
    this.exchangeName = exchangeName;
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

  public String getExchangeName() {
    return exchangeName;
  }

  public void setExchangeName(String exchangeName) {
    this.exchangeName = exchangeName;
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
