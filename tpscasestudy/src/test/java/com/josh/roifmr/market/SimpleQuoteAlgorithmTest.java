package com.josh.roifmr.market;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.domain.exhange.MarketSymbol;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SimpleQuoteAlgorithmTest {

    private SimpleQuoteAlgorithm testFixture;

    @BeforeEach
    void setUp(){
        this.testFixture = new SimpleQuoteAlgorithm();
    }

    @AfterEach
    void tearDown(){
        this.testFixture = null;
    }

    @Test
    void generationOfHistoricalQuotesWithGoodSeedValues() throws QuoteRequestDateValidationException {
        LocalDate expectedOrigination = LocalDate.of(2019,1,1);
        int expectedDuration = 30;
        MarketSymbol expectedExchangeSymbol = MarketSymbol.ORCL;
        Optional<HistoricalQuotes> returnValue = testFixture.generateQuote(expectedOrigination, expectedDuration, expectedExchangeSymbol);

        assertAll(()->assertEquals(expectedDuration, returnValue.get().getDuration())
        ,()->assertEquals(expectedOrigination, returnValue.get().getOriginationDate())
        ,()->assertEquals(expectedExchangeSymbol, returnValue.get().getExchangeSymbol())
        ,()-> assertTrue(returnValue.get().getDailyQuotes().size()==expectedDuration));

        //returnValue.getDailyQuotes().stream().forEach(c->System.out.println(c));
    }

    @Test
    void badGenerationOfHistoricalQuotesWithNullOriginDate() throws QuoteRequestDateValidationException {
        assertFalse(testFixture.generateQuote(null, 1, MarketSymbol.ORCL).isPresent());
    }

    @Test
    void badGenerationOfHistoricalQuotesWithNullMarketSymbol() throws QuoteRequestDateValidationException {
        assertFalse(testFixture.generateQuote(LocalDate.of(2019,1,1), 1, null).isPresent());
    }

    @Test
    void badGenerationOfHistoricalQuotesThrowsExceptionWithBadDateRange() {
        LocalDate expectedOriginDate = LocalDate.now().minusDays(2);
        int expectedDurationDays = 2;

        assertThrows(QuoteRequestDateValidationException.class, ()->testFixture.generateQuote(expectedOriginDate, expectedDurationDays, MarketSymbol.ORCL));
    }

    @Test
    void generationOfHistoricalQuotesUpToPreviousDay() throws QuoteRequestDateValidationException {
        LocalDate expectedOriginDate = LocalDate.now().minusDays(3);
        int quoteDuration = 2;

        assertEquals(true, testFixture.generateQuote(expectedOriginDate,quoteDuration,MarketSymbol.ORCL).isPresent());
    }
}