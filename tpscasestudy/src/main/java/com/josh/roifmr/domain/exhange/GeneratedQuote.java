package com.josh.roifmr.domain.exhange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class GeneratedQuote {

    private MarketSymbol exchangeSymbol;
    private LocalDate exchangeDate;
    private BigDecimal exchangeHigh;
    private BigDecimal exchangeLow;
    private BigDecimal exchangeOpen;
    private BigDecimal exchangeClose;
    private int exchangeVolume;

    public GeneratedQuote() {
        this.exchangeHigh = BigDecimal.ZERO;
        this.exchangeLow = BigDecimal.ZERO;
        this.exchangeOpen = BigDecimal.ZERO;
        this.exchangeClose = BigDecimal.ZERO;
    }

    public GeneratedQuote(MarketSymbol exchangeSymbol, LocalDate exchangeDate){
        this();
        this.exchangeSymbol = exchangeSymbol;
        this.exchangeDate = exchangeDate;
    }

    public GeneratedQuote(MarketSymbol exchangeSymbol, LocalDate exchangeDate, BigDecimal exchangeHigh, BigDecimal exchangeLow, BigDecimal exchangeOpen, BigDecimal exchangeClose, int exchangeVolume) {
        this(exchangeSymbol, exchangeDate);
        this.exchangeHigh = exchangeHigh;
        this.exchangeLow = exchangeLow;
        this.exchangeOpen = exchangeOpen;
        this.exchangeClose = exchangeClose;
        this.exchangeVolume = exchangeVolume;
    }

    public MarketSymbol getExchageSymbol() {
        return exchangeSymbol;
    }

    public void setExchageSymbol(MarketSymbol exchageSymbol) {
        this.exchangeSymbol = exchageSymbol;
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDate exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public BigDecimal getExchangeHigh() {
        return exchangeHigh;
    }

    public void setExchangeHigh(BigDecimal exchangeHigh) {
        this.exchangeHigh = exchangeHigh;
    }

    public BigDecimal getExchangeLow() {
        return exchangeLow;
    }

    public void setExchangeLow(BigDecimal exchangeLow) {
        this.exchangeLow = exchangeLow;
    }

    public BigDecimal getExchangeOpen() {
        return exchangeOpen;
    }

    public void setExchangeOpen(BigDecimal exchangeOpen) {
        this.exchangeOpen = exchangeOpen;
    }

    public BigDecimal getExchangeClose() {
        return exchangeClose;
    }

    public void setExchangeClose(BigDecimal exchangeClose) {
        this.exchangeClose = exchangeClose;
    }

    public int getExchangeVolume() {
        return exchangeVolume;
    }

    public void setExchangeVolume(int exchangeVolume) {
        this.exchangeVolume = exchangeVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneratedQuote that = (GeneratedQuote) o;
        return exchangeVolume == that.exchangeVolume &&
                exchangeSymbol == that.exchangeSymbol &&
                exchangeDate.equals(that.exchangeDate) &&
                exchangeHigh.equals(that.exchangeHigh) &&
                exchangeLow.equals(that.exchangeLow) &&
                exchangeOpen.equals(that.exchangeOpen) &&
                exchangeClose.equals(that.exchangeClose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeSymbol, exchangeDate, exchangeHigh, exchangeLow, exchangeOpen, exchangeClose, exchangeVolume);
    }

    @Override
    public String toString() {
        return "GeneratedQuote{" +
                "exchangeSymbol=" + exchangeSymbol +
                ", exchangeDate=" + exchangeDate +
                ", exchangeHigh=" + exchangeHigh +
                ", exchangeLow=" + exchangeLow +
                ", exchangeOpen=" + exchangeOpen +
                ", exchangeClose=" + exchangeClose +
                ", exchangeVolume=" + exchangeVolume +
                '}';
    }
}
