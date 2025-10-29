package com.stockinsight.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@IdClass(CandlestickPk.class)
@Table(name = "candlestick")
public class Candlestick {

  @Id
  @Column(name = "stock_id")
  private String stockId;
  
  @ManyToOne
  @JoinColumn(name = "stock_id")
  private Stock stock;

  @Id
  @Column(name = "date")
  private LocalDate date;

  @Column(name = "open")
  private BigDecimal open;

  @Column(name = "high")
  private BigDecimal high;

  @Column(name = "low")
  private BigDecimal low;

  @Column(name = "close")
  private BigDecimal close;

  private Candlestick() {
  }

  public Candlestick(Stock stock, LocalDate date, BigDecimal open, BigDecimal high, BigDecimal low,
      BigDecimal close) {
    this.stock = stock;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public BigDecimal getOpen() {
    return open;
  }

  public void setOpen(BigDecimal open) {
    this.open = open;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public void setHigh(BigDecimal high) {
    this.high = high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public void setLow(BigDecimal low) {
    this.low = low;
  }

  public BigDecimal getClose() {
    return close;
  }

  public void setClose(BigDecimal close) {
    this.close = close;
  }
}
