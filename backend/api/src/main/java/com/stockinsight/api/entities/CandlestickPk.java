package com.stockinsight.api.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class CandlestickPk implements Serializable {

  private String stockId;
  private LocalDate date;
}
