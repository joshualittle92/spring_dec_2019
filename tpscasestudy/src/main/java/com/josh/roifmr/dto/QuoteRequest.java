package com.josh.roifmr.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.josh.roifmr.domain.exhange.Generator;
import com.josh.roifmr.domain.exhange.MarketSymbol;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

public class QuoteRequest {

    @JsonIgnore
    @NotNull(message = "Requested date/time cannot be null")
    private LocalDate requestedAt;

    @NotNull(message = "quote start date cannot be null")
    @Past(message = "quote start date must be in the past")
    private LocalDate startDate;

    @Min(value = 1, message = "min quoting duration must be at least one day")
    @Max(value = 180, message = "max quoting duration is 180 days")
    private int duration;

    @NotNull(message = "exchange symbol must exist")
    private MarketSymbol exchangeSymbol;

    private Generator quotingAlgorithm;

    public QuoteRequest() {

        this.requestedAt = LocalDate.now();
        this.quotingAlgorithm = Generator.DEFAULT;
    }

    public QuoteRequest(LocalDate startDate, int duration, MarketSymbol exchangeSymbol, Generator quotingAlgorithm) {
        this();
        this.startDate = startDate;
        this.duration = duration;
        this.exchangeSymbol = exchangeSymbol;
        this.quotingAlgorithm = quotingAlgorithm;
    }

    public LocalDate getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDate requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public MarketSymbol getExchangeSymbol() {
        return exchangeSymbol;
    }

    public void setExchangeSymbol(MarketSymbol exchangeSymbol) {
        this.exchangeSymbol = exchangeSymbol;
    }

    public Generator getQuotingAlgorithm() {
        return quotingAlgorithm;
    }

    public void setQuotingAlgorithm(Generator quotingAlgorithm) {
        if(quotingAlgorithm != null) {
            this.quotingAlgorithm = quotingAlgorithm;
        }
    }

    @Override
    public String toString() {
        return "QuoteRequest{" +
                "requestedAt=" + requestedAt +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", exchangeSymbol=" + exchangeSymbol +
                ", quotingAlgorithm=" + quotingAlgorithm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteRequest that = (QuoteRequest) o;
        return duration == that.duration &&
                requestedAt.equals(that.requestedAt) &&
                startDate.equals(that.startDate) &&
                exchangeSymbol == that.exchangeSymbol &&
                quotingAlgorithm == that.quotingAlgorithm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedAt, startDate, duration, exchangeSymbol, quotingAlgorithm);
    }
}
