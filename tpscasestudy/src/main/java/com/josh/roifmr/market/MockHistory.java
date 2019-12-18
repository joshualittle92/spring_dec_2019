package com.josh.roifmr.market;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.domain.exhange.MarketSymbol;

import java.time.LocalDate;
import java.util.Optional;

public class MockHistory implements QuoteGenerator {

    @Override
    public Optional<HistoricalQuotes> generateQuote(LocalDate startingDate, int durationInDays, MarketSymbol stockSymbol) {
        return null;
    }
}
