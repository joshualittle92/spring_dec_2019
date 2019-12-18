package com.josh.roifmr.services.dto;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.domain.exhange.QuoteGenerationException;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;
import com.josh.roifmr.dto.QuoteRequest;
import com.josh.roifmr.market.QuotingProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuoteFulfillmentService implements QuoteRequestService {
    @Override
    public HistoricalQuotes getRequestedQuote(QuoteRequest quoteRequest) throws QuoteRequestDateValidationException, QuoteGenerationException {

        Optional<HistoricalQuotes> rtnValue =
        QuotingProvider.getQuoteGenerator(quoteRequest.getQuotingAlgorithm())
                .generateQuote(quoteRequest.getStartDate(), quoteRequest.getDuration(), quoteRequest.getExchangeSymbol());
        if (rtnValue.isPresent()) return rtnValue.get();
        else throw new QuoteGenerationException("Failed to failed to receive historical quote");
    }
}
