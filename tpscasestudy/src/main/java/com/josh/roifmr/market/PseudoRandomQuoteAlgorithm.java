package com.josh.roifmr.market;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.domain.exhange.MarketSymbol;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;

import java.time.LocalDate;
import java.util.Optional;

public class PseudoRandomQuoteAlgorithm extends AbstractQuoteGenerator{
    @Override
    public Optional<HistoricalQuotes> generateQuote(LocalDate startingDate, int durationInDays, MarketSymbol stockSymbol) throws QuoteRequestDateValidationException {
        return Optional.empty();
    }
}
