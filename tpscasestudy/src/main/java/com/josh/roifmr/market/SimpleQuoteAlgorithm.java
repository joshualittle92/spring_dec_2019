package com.josh.roifmr.market;


import com.josh.roifmr.domain.exhange.GeneratedQuote;
import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.domain.exhange.MarketSymbol;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class SimpleQuoteAlgorithm extends AbstractQuoteGenerator {

    private HistoricalQuotes historicalQuotes;

    @Override
    public Optional<HistoricalQuotes> generateQuote(LocalDate startingDate, int durationInDays, MarketSymbol stockSymbol) throws QuoteRequestDateValidationException {
        if(Objects.nonNull(startingDate)&&Objects.nonNull(stockSymbol)){
            if(validateQuoteDates(startingDate, durationInDays)){
                setHistoricalQuotes(new HistoricalQuotes(startingDate, stockSymbol, durationInDays));
                double seed = 0;
                for (int i = 0; i < durationInDays; i++) {
                    seed = Math.random()*1000;
                    getHistoricalQuotes().addDailyQuote(
                            new GeneratedQuote(stockSymbol,startingDate.plusDays(i), generateValue(seed), generateValue(seed), generateValue(seed), generateValue(seed), generateValue(seed*1000).intValue())
                    );
                }
                return Optional.of(getHistoricalQuotes());
            } else {
                throw new QuoteRequestDateValidationException("Quote request failed date validation. Date range must be in past.");
            }
        }
        return Optional.empty();
    }

    private BigDecimal generateValue(double value){
        double delta = ((Math.sin(value)+ (2*Math.sin((value/2))+Math.cos(value))));
        double price = (value + (delta/100)*value);

        return new BigDecimal(price, new MathContext(7, RoundingMode.HALF_DOWN));
    }

    public HistoricalQuotes getHistoricalQuotes() {
        return historicalQuotes;
    }

    public void setHistoricalQuotes(HistoricalQuotes historicalQuotes) {
        this.historicalQuotes = historicalQuotes;
    }
}
