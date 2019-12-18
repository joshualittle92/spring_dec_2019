package com.josh.roifmr.domain.exhange;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class HistoricalQuotes {

    private UUID id;
    private LocalDate generatedOn;
    private LocalDate originationDate;
    private List<GeneratedQuote> dailyQuotes;
    private MarketSymbol exchangeSymbol;
    private int duration;

    public HistoricalQuotes() {
        this.id = UUID.randomUUID();
        this.generatedOn = LocalDate.now();
    }

    public HistoricalQuotes(LocalDate originationDate, MarketSymbol exchangeSymbol, int duration) {
        this();
        this.originationDate = originationDate;
        this.exchangeSymbol = exchangeSymbol;
        this.duration = duration;
    }

    public void addDailyQuote(GeneratedQuote dayQuote){
        this.dailyQuotes.add(dayQuote);
    }

    public String getUniqueKey(){
        StringJoiner stringJoiner = new StringJoiner("_");
        stringJoiner.add("generated");
        stringJoiner.add(getId().toString());
        stringJoiner.add(getExchangeSymbol().name());
        stringJoiner.add(Integer.toString(getDuration()));
        return stringJoiner.toString();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    @JsonSetter("setGeneratedOnString")
    public LocalDate getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOnString(String dateFormatString){
        this.generatedOn = LocalDate.parse(dateFormatString);
    }

    public void setGeneratedOn(LocalDate generatedOn) {
        this.generatedOn = generatedOn;
    }

    @JsonIgnore
    @JsonSetter("setOriginationDate")
    public LocalDate getOriginationDate() {
        return originationDate;
    }

    @JsonGetter("getOriginationDate")
    public String getOriginationDateString() {
        return originationDate.toString();
    }

    public void setOriginationDate(LocalDate originationDate) {
        this.originationDate = originationDate;
    }

    public void setOriginationDate(String dateFormatString) {
        this.originationDate = LocalDate.parse(dateFormatString);
    }

    public List<GeneratedQuote> getDailyQuotes() {
        return dailyQuotes;
    }

    public void setDailyQuotes(List<GeneratedQuote> dailyQuotes) {
        this.dailyQuotes = dailyQuotes;
    }

    public MarketSymbol getExchangeSymbol() {
        return exchangeSymbol;
    }

    public void setExchangeSymbol(MarketSymbol exchangeSymbol) {
        this.exchangeSymbol = exchangeSymbol;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalQuotes that = (HistoricalQuotes) o;
        return duration == that.duration &&
                id.equals(that.id) &&
                generatedOn.equals(that.generatedOn) &&
                originationDate.equals(that.originationDate) &&
                exchangeSymbol == that.exchangeSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, generatedOn, originationDate, exchangeSymbol, duration);
    }

    @Override
    public String toString() {
        return "HistoricalQuotes{" +
                "id=" + id +
                ", generatedOn=" + generatedOn +
                ", exchangeSymbol=" + exchangeSymbol +
                ", duration=" + duration +
                '}';
    }
}
